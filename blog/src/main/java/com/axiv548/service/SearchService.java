package com.axiv548.service;


import java.util.List;
import java.util.Map;

/**
 * SearchService
 *
 * @date 2021/3/19 15:13
 */
public interface SearchService {

//    首页搜索
    List<Map<String, Object>> getSearch(String kw, Integer page, Integer size, Integer pageSize);

//    搜索总数
    Integer getSearchCount(String kw);

}
