package com.ninjutsu.mybatis.utils;

import com.ninjutsu.mybatis.cfg.SqlMapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:arxobject
 * @desc : delegate class for execute sql and get result
 * @create time:8/19
 */
public class Executor {
    public <E> List<E> selectList(SqlMapper sqlMapper, Connection connection){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String queryString = sqlMapper.getQueryString();
        String resultType = sqlMapper.getResultType();
        List<E> list = new ArrayList<>();

        try {
            Class domainClass = Class.forName(resultType);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                E obj = (E)domainClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(obj,columnValue);
                }
                list.add(obj);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(resultSet!=null){
                    resultSet.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
