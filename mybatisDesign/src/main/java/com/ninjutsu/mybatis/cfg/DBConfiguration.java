package com.ninjutsu.mybatis.cfg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:arxobject
 * @desc : db cfg
 * @create time:8/19
 */
@Getter
@Setter
@NoArgsConstructor
public class DBConfiguration {
    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String, SqlMapper> mappers = new HashMap<String, SqlMapper>();
}
