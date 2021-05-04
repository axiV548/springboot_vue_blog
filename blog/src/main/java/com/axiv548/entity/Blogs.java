package com.axiv548.entity;


import java.util.Date;
import java.util.List;

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
public class Blogs {


    /**
     * 博客ID
     */
    private Integer blog_id;

    /**
     * 标题
     */
    private String blog_title;

    /**
     * 内容
     */
    private String blog_content;

    /**
     * 描述
     */
    private String blog_description;

    /**
     * 更新时间
     */
    private Date blog_update_time;

    /**
     * 查看次数
     */
    private Long blog_views;

    /**
     * 是否开启评论
     */
    private Boolean blog_comment;

    /**
     * 是否公开
     */
    private Boolean blog_public;

    /**
     * 是否发布
     */
    private Boolean blog_release;

    /**
     * 是否推荐
     */
    private Boolean blog_recommend;

    /**
     * 首图地址
     */
    private String blog_first_img;

    /**
     * 关联用户ID
     */
    private Integer user_id;

    /**
     * 关联用户ID
     */
    private List<Integer> tags;


}
