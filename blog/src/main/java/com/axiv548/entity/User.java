package com.axiv548.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author axiv548
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User {


    /**
     * 用户ID
     */
    private Integer user_id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户密码
     */
    private String user_password;

    /**
     * 用户邮箱
     */
    private String user_email;

    /**
     * 用户头像地址
     */
    private String user_avatar;

    /**
     * 用户组
     */
    private Integer user_group;


}
