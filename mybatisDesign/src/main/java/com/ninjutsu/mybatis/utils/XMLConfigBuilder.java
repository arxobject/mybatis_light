package com.ninjutsu.mybatis.utils;

import com.ninjutsu.mybatis.cfg.DBConfiguration;
import com.ninjutsu.mybatis.cfg.SqlMapper;
import com.ninjutsu.mybatis.io.Resources;
import com.ninjutsu.mybatis.io.sqlsession.annotations.Select;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:arxobject
 * @desc : parse xml
 * @create time:8/19
 */
public class XMLConfigBuilder {
    public static DBConfiguration loadConfiguration(InputStream config){
        DBConfiguration cfg = new DBConfiguration();

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(config);
            Element root = document.getRootElement();
            List<Element> propertyElements = root.selectNodes("//property");

            for(Element element:propertyElements){
                String name = element.attributeValue("name");
                if("driver".equals(name)){
                    String driver = element.attributeValue("value");
                    cfg.setDriver(driver);
                }
                if("url".equals(name)){
                    String url = element.attributeValue("value");
                    cfg.setUrl(url);
                }
                if("username".equals(name)){
                    String username = element.attributeValue("value");
                    cfg.setUsername(username);
                }
                if("password".equals(name)){
                    String password = element.attributeValue("value");
                    cfg.setPassword(password);
                }
            }

            List<Element> mapperElements = root.selectNodes("//mappers/mapper");
            for(Element element:mapperElements){
                Attribute attribute = element.attribute("resource");
                if(attribute!=null){
                    System.out.println("使用xml配置文件");
                    String mapperPath = attribute.getValue();
                    Map<String, SqlMapper> mappers = loadMapperConfiguration(mapperPath);
                    cfg.setMappers(mappers);
                }else{
                    System.out.println("使用的是注解");
                    String daoClassPath = element.attributeValue("class");
                    Map<String, SqlMapper> mappers = loadMapperAnnotation(daoClassPath);
                    cfg.setMappers(mappers);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cfg;
    }

    private static Map<String, SqlMapper> loadMapperConfiguration(String mapperPath) throws DocumentException {
        InputStream in = null;
        Map<String, SqlMapper> mappers = new HashMap<String, SqlMapper>();
        in = Resources.getResourceAsStream(mapperPath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);

        Element root = document.getRootElement();
        String namespace = root.attributeValue("namespace");
        List<Element> selectElements = root.selectNodes("//select");
        for (Element element:selectElements){
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String queryString = element.getText();
            String key = namespace+"."+id;
            SqlMapper sqlMapper = new SqlMapper();
            sqlMapper.setQueryString(queryString);
            sqlMapper.setResultType(resultType);
            mappers.put(key, sqlMapper);
        }

        return mappers;
    }

    private static Map<String, SqlMapper> loadMapperAnnotation(String daoClassPath) throws ClassNotFoundException {
        Map<String, SqlMapper> mappers = new HashMap<>();
        Class daoClass = Class.forName(daoClassPath);
        Method[] methods = daoClass.getMethods();
        for(Method method:methods){
            boolean isAnnotated = method.isAnnotationPresent(Select.class);
            if(isAnnotated){
                SqlMapper sqlMapper = new SqlMapper();
                Select selectAnno = method.getAnnotation(Select.class);
                String queryString = selectAnno.value();
                sqlMapper.setQueryString(queryString);
                Type type = method.getGenericReturnType();
                if(type instanceof ParameterizedType){
                    ParameterizedType ptype = (ParameterizedType)type;
                    Type[] types = ptype.getActualTypeArguments();
                    Class domainClass = (Class)types[0];
                    String resultType = domainClass.getName();
                    sqlMapper.setResultType(resultType);
                }

                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String key = className+"."+methodName;
                mappers.put(key, sqlMapper);
            }
        }
        return mappers;
    }
}
