package com.axiv548.controller;

import com.axiv548.entity.Blogs;
import com.axiv548.service.BlogService;
import com.axiv548.util.AjaxResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * BlogController
 *
 * @date 2021/2/28 16:29
 */
@RestController
@RequestMapping("blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Value("${img.dir}")
    private String realPath;


//    首页查询所有文章
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public AjaxResponse getBlog(@PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = blogService.getBlogCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list= blogService.getBlog(page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }

//    文章页根据ID查文章
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse getBlogById(@PathVariable("id") Integer id) {
        Map<String, Object> map = blogService.getBlogById(id);
        if ((Boolean) map.get("blog_release") && (Boolean) map.get("blog_public")) {
            AjaxResponse ajaxResponse = new AjaxResponse().success(map,"查询成功");
            return ajaxResponse;
        } else {
            AjaxResponse ajaxResponse = new AjaxResponse().success(401,"无权限");
            return ajaxResponse;
        }
    }

    @RequestMapping("/recommend")
    public AjaxResponse getBlogRecommend() {
        AjaxResponse ajaxResponse = new AjaxResponse().success(blogService.getBlogRecommend(), "查询成功");
        return ajaxResponse;
    }



//    管理页
//    文章管理
    @RequestMapping(value = "/admin/page/{page}", method = RequestMethod.GET)
    public AjaxResponse adminGetBlog(@PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = blogService.getAdminBlogCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list= blogService.adminGetBlog(page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }

//    根据ID查询文章
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    public AjaxResponse adminGetBlogById(@PathVariable("id") Integer id) {
        AjaxResponse ajaxResponse = new AjaxResponse().success(blogService.adminGetBlogById(id), "查询成功");
        return ajaxResponse;
    }

//    添加文章
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse saveBlog(Blogs blogs, MultipartFile first_img) throws IOException {
//        保存首页图片
        String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(first_img.getOriginalFilename());
        String imgPath = realPath + "/" + "firstImg";
        first_img.transferTo(new File(imgPath, newFileName));
        blogs.setBlog_first_img(newFileName);
        blogService.saveBlog(blogs);
        AjaxResponse ajaxResponse = new AjaxResponse().success("添加成功");
        return ajaxResponse;
    }

//    删除文章
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteBlog(@PathVariable("id") Integer id) {
        if (id == null) {
            AjaxResponse ajaxResponse = new AjaxResponse().success(400, "无效ID");
            return ajaxResponse;
        } else {
            blogService.deleteBlog(id);
            AjaxResponse ajaxResponse = new AjaxResponse().success("删除成功");
            return ajaxResponse;
        }
    }

//    修改文章
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse putBlog(Blogs blogs,
                                @PathVariable("id") Integer id,
                                MultipartFile first_img) throws IOException {

        blogs.setBlog_id(id);
        String imgPath = realPath + "/" + "firstImg";

//        删除原有图片保存新图图片
        String fileName = blogs.getBlog_first_img();

        if (first_img !=null) {
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }
            String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(first_img.getOriginalFilename());
            blogs.setBlog_first_img(newFileName);
            first_img.transferTo(new File(imgPath, newFileName));
        }

        blogService.putBlog(blogs);
        AjaxResponse ajaxResponse = new AjaxResponse().success("修改成功");
        return ajaxResponse;

    }

//文章内图片处理
    @RequestMapping(value = "/admin/img", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse postImg(MultipartFile content_img) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(content_img.getOriginalFilename());
        String imgPath = realPath + "/" + "contentImg";
        content_img.transferTo(new File(imgPath, newFileName));

        AjaxResponse ajaxResponse = new AjaxResponse().success("添加成功", newFileName);
        return ajaxResponse;
    }

    @RequestMapping(value = "/admin/img/{imgName}", method = RequestMethod.DELETE)
    public AjaxResponse deleteImg(@PathVariable("imgName") String imgName) {

        String imgPath = realPath + "/" + "contentImg";
        if (imgName != null) {
            File delFile = new File(imgPath, imgName);
            delFile.delete();
        }

        AjaxResponse ajaxResponse = new AjaxResponse().success("删除成功");
        return ajaxResponse;

    }

}
