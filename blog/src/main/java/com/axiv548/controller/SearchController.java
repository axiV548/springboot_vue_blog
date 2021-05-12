package com.axiv548.controller;

import com.axiv548.service.SearchService;
import com.axiv548.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * SearchController
 *
 * @date 2021/3/19 15:09
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/{kw}/{page}", method = RequestMethod.GET)
    public AjaxResponse getBlog(@PathVariable String kw,
                                @PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = searchService.getSearchCount(kw);
        String size = blogSize.toString();

        List<Map<String, Object>> list= searchService.getSearch(kw, page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin/{kw}/{page}", method = RequestMethod.GET)
    public AjaxResponse getAdminBlog(@PathVariable String kw,
                                @PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = searchService.getSearchCount(kw);
        String size = blogSize.toString();
        List<Map<String, Object>> list= searchService.getSearch(kw, page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }
}
