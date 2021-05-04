package com.axiv548.dao;

import com.axiv548.entity.Types;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TypeDao
 *
 * @date 2021/3/2 23:51
 */
@Mapper
public interface TypeDao {

    List<Map<String, String>> getType();

    void postType(Types types);

    void deleteType(Integer id);

    void putType(Types types);


    List<Map<String, Object>> getTagByBlogId(Integer id);

    List<Map<String, Object>> getBlogByTag(Integer id);

    List<Map<String, Object>> getAllTypeTagByPage(Integer page, Integer size);

    Integer getTypeCount();

    List<Map<String, Object>> getAllTypeTag();


}


