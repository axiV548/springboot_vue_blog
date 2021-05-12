package com.axiv548.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Photos
 *
 * @date 2021/5/5 15:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Photos {

    /**
     * 照片ID
     */
    private Integer photo_id;
    /**
     * 照片名
     */
    private String photo_name;
    /**
     * 保存文件名
     */
    private String photo_save_name;
    /**
     * 照片简介
     */
    private String  photo_introduction;
    /**
     * 拍摄时间
     */
    private Date photo_filming_time;
    /**
     * 所属二级相册
     */
    private Integer albums_tag;

}
