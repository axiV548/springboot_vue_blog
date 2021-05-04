package com.axiv548.service;

import com.axiv548.entity.Types;

import java.util.List;
import java.util.Map;

/**
 * TypyService
 *
 * @date 2021/3/2 23:52
 */
public interface TypeService {

    //    查询分类
    List<Map<String, String>> getType();

    //    新增Type
    void postType(Types types);

    //    删除Type
    void deleteType(Integer id);

    //    修改Type
    void putType(Types types);


    //根据文章查询分类标签
    List<Map<String, Object>> getTagByBlogId(Integer id);

//    根据分类标签查文章
    List<Map<String, Object>> getBlogByTag(Integer id);
    
//  分页查询所有分类标签
    List<Map<String, Object>> getAllTypeTagByPage(Integer page, Integer size, Integer pageSize);

//    查询分类总数
    Integer getTypeCount();

//    查询所有分类标签
    List<Map<String, Object>> getAllTypeTag();
}
