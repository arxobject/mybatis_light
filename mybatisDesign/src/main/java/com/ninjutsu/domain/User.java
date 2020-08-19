package com.ninjutsu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * @author:arxobject
 * @desc : table entity
 * @create time:8/19
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String user_name;
    private Date birth_day;
    private String sex;
    private String address;
}
