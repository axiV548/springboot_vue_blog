package com.axiv548.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * SearchDao
 *
 * @date 2021/3/19 15:13
 */
@Mapper
public interface SearchDao {

    List<Map<String, Object>> getSearch(String kw, Integer page, Integer size);

    Integer getSearchCount(String kw);

}
