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
public class Types {


    /**
     * 分类_标签ID
     */
    private Integer type_tag_id;

    /**
     * 分类_标签名
     */
    private String type_tag_name;

    /**
     * 关联的父分类ID
     */
    private Integer type_parent_id;

    /**
     * 分类_标签级别
     */
    private Integer type_tag_level;


}
