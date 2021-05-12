package com.axiv548.controller;

import com.axiv548.entity.Types;
import com.axiv548.service.TypeService;
import com.axiv548.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * TypeController
 *
 * @date 2021/3/2 23:51
 */
@RestController
@RequestMapping("types")
public class TypeController {

    @Autowired
    TypeService typeService;


//    查询Type
    @RequestMapping(value = "", method = RequestMethod.GET)
    public AjaxResponse getType() {
        AjaxResponse ajaxResponse = new AjaxResponse().success(typeService.getType(),"查询成功");
        return ajaxResponse;
    }

    //    根据标签查询文章
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
    public AjaxResponse getBlogByTag(@PathVariable Integer id) {
        AjaxResponse ajaxResponse = new AjaxResponse().success(typeService.getBlogByTag(id), "查询成功");
        return ajaxResponse;
    }


    //    管理界面
    //    增删查改Type
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public AjaxResponse getAllTypeTag() {
        AjaxResponse ajaxResponse = new AjaxResponse().success(typeService.getAllTypeTag(),"查询成功");
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin/page/{page}", method = RequestMethod.GET)
    public AjaxResponse getAllTypeTagByPage(@PathVariable Integer page) {
        Integer pageSize = 10;
        Integer blogSize = typeService.getTypeCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list= typeService.getAllTypeTagByPage(page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public AjaxResponse postType(@RequestBody Types types) {
        typeService.postType(types);
        AjaxResponse ajaxResponse = new AjaxResponse().success(200, "添加成功");
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteType(@PathVariable Integer id) {

        typeService.deleteType(id);
        AjaxResponse ajaxResponse = new AjaxResponse().success(200, "删除成功");
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
    public AjaxResponse putType(@RequestBody Types types) {
        typeService.putType(types);
        AjaxResponse ajaxResponse = new AjaxResponse().success(200, "修改成功");
        return ajaxResponse;
    }


}
