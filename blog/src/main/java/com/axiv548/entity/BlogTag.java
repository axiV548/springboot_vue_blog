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
public class BlogTag {


    /**
     * 博客ID
     */
    private Integer blog_id;

    /**
     * 标签ID
     */
    private Integer tag_id;


}
