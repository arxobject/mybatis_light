package com.ninjutsu.mybatis.cfg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author:arxobject
 * @desc : sql entity
 * @create time:8/19
 */
@Getter
@Setter
@NoArgsConstructor
public class SqlMapper {
    private String queryString;
    private String resultType;
}
