package com.axiv548.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Albums
 *
 * @date 2021/5/5 15:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Albums {

    /**
     * 相册ID
     */
    private Integer album_id;
    /**
     * 相册名
     */
    private String album_name;
    /**
     * 相册封面
     */
    private String album_cover;
    /**
     * 相册简介
     */
    private String album_introduction;
    /**
     * 修改时间
     */
    private Date album_create_time;
    /**
     * 相册级别
     */
    private Integer album_level;
    /**
     * 所属一级相册
     */
    private Integer album_type;



}
