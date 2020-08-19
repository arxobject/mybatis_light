package com.ninjutsu.mybatis.io;

import java.io.InputStream;

/**
 * @author:arxobject
 * @desc : read file from resources
 * @create time:8/19
 */
public class Resources {
    /**
     * 根据路径创建字节流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
