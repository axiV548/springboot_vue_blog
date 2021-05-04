package com.axiv548.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
public class BlogComment {


    /**
     * 评论ID
     */
    private Integer comment_id;

    /**
     * 是否是管理员
     */
    private Boolean comment_admin;

    /**
     * 评论内容
     */
    private String comment_content;

    /**
     * 邮箱
     */
    private String comment_email;

    /**
     * 昵称
     */
    private String comment_nickname;

    /**
     * 评论时间
     */
    private LocalDateTime comment_create_time;

    /**
     * 博客ID
     */
    private Integer blog_id;


}
