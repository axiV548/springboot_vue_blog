package com.axiv548.controller;

import com.axiv548.entity.BlogComment;
import com.axiv548.service.CommentService;
import com.axiv548.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * CommentController
 *
 * @date 2021/3/17 21:57
 */

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse getComment(@PathVariable Integer id) {
        AjaxResponse ajaxResponse = new AjaxResponse().success(commentService.getCommentByBlogId(id), "查询成功");
        return ajaxResponse;

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public AjaxResponse postComment(@RequestBody BlogComment blogComment) {
        if (request.getHeader("token") == null) {
            blogComment.setComment_admin(false);
        } else {
            blogComment.setComment_admin(true);
            blogComment.setComment_nickname("axiV548");
        }
        commentService.postComment(blogComment);
        AjaxResponse ajaxResponse = new AjaxResponse().success("添加成功");
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
    public AjaxResponse putComment(@RequestBody BlogComment blogComment) {
        commentService.putComment(blogComment);
        AjaxResponse ajaxResponse = new AjaxResponse().success("修改成功");
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        AjaxResponse ajaxResponse = new AjaxResponse().success("删除成功");
        return ajaxResponse;
    }

}
