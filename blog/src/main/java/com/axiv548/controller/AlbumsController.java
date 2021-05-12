package com.axiv548.controller;

import com.axiv548.entity.Albums;
import com.axiv548.entity.Photos;
import com.axiv548.service.AlbumsService;
import com.axiv548.util.AjaxResponse;
import com.axiv548.util.PhotoUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * AlbumsController
 *
 * @date 2021/5/5 15:18
 */
@RestController
@RequestMapping("albums")
public class AlbumsController {

    @Autowired
    AlbumsService albumsService;

    @Value("${img.dir}")
    private String realPath;



    //    查询相册
    @RequestMapping(method = RequestMethod.GET)
    public AjaxResponse getAlbums() {

        AjaxResponse ajaxResponse = new AjaxResponse().success(albumsService.getAlbums(),"查询成功");

    return ajaxResponse;
    }
    //    查询相册
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResponse getAlbums(@PathVariable Integer id) {

        AjaxResponse ajaxResponse = new AjaxResponse().success(albumsService.getAlbumById(id),"查询成功");

        return ajaxResponse;
    }

//    创建相册
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse postAlbums(Albums albums, MultipartFile cover) throws IOException {
//        保存图片
        String imgPath = realPath + "/albumImg/";
        Map<String, Object> photoInformation = PhotoUtils.photoSave(imgPath, cover);
        albums.setAlbum_cover(photoInformation.get("newFileName").toString());

        albumsService.saveAlbums(albums);
        AjaxResponse ajaxResponse = new AjaxResponse().success("添加成功");

        return ajaxResponse;
    }

//    修改相册
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse putAlbums(@PathVariable("id") Integer id,
                                  Albums albums,
                                  MultipartFile cover) throws IOException {

        albums.setAlbum_id(id);
        String imgPath = realPath + "/albumImg";

//        删除原有图片保存新图图片
        String fileName = albums.getAlbum_cover();
        if (cover !=null) {
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }

            Map<String, Object> photoInformation = PhotoUtils.photoSave(imgPath, cover);
            albums.setAlbum_cover(photoInformation.get("newFileName").toString());

        }


        albumsService.putAlbums(albums);
        AjaxResponse ajaxResponse = new AjaxResponse().success("修改成功");

        return ajaxResponse;
    }
//    删除相册
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteAlbums(@PathVariable("id") Integer id) {

        String photoImgPath = realPath + "/photoImg";

        Albums albums = albumsService.getAlbumById(id);
        //        查询子相册
        if ( albums.getAlbum_level() == 0 ) {
            List<Integer> albumSonList = albumsService.getAlbumSonIdByAlbumId(id);
            for (Integer albumSonId : albumSonList) {
                List<String> photoSaveNameList= albumsService.getPhotoNameByAlbumId(albumSonId);
//          删除相册下的照片
                for (String photoSaveName : photoSaveNameList) {
                    if (photoSaveName != null) {
                        File delFile = new File(photoImgPath, photoSaveName);
                        delFile.delete();
                    }
                }
//                删除子相册
                albumsService.deleteAlbums(albumSonId);
            }
        } else {
            List<String> photoSaveNameList= albumsService.getPhotoNameByAlbumId(id);
            for (String photoSaveName : photoSaveNameList) {
                if (photoSaveName != null) {
                    File delFile = new File(photoImgPath, photoSaveName);
                    delFile.delete();
                }
            }
            albumsService.deleteAlbums(id);
        }

//        删除相册
        albumsService.deleteAlbums(id);
        AjaxResponse ajaxResponse = new AjaxResponse().success("删除成功");

        return ajaxResponse;
    }


//    查询照片
    @RequestMapping(value = "/{album_id}/page/{page}", method = RequestMethod.GET)
    public AjaxResponse getPhotos(@PathVariable("album_id") Integer albumId,
                                  @PathVariable("page") Integer page) {

        Integer pageSize = 25;
        Integer photoCount = albumsService.getPhotoCountById(albumId);
        String size = photoCount.toString();

        List<Map<String, Object>> list= albumsService.getPhotos(albumId, page, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(list, "查询成功", size);
        } else {
            ajaxResponse = new AjaxResponse().success(404, "超出范围");
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/photos/{id}", method = RequestMethod.GET)
    public AjaxResponse getPhotos(@PathVariable("id") Integer id) {

        AjaxResponse ajaxResponse = new AjaxResponse().success(albumsService.getPhotoById(id),"查询成功");

        return ajaxResponse;
    }

//    新增照片
    @RequestMapping(value = "/admin/photos/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse postPhotos(MultipartFile[] photoList, @PathVariable("id") Integer id) throws IOException {

        String imgPath = realPath + "/photoImg/";

        for (int i=0; i < photoList.length; i++){
            MultipartFile photoFile = photoList[i];
            Photos photo = new Photos();
            photo.setAlbums_tag(id);
            Map<String, Object> photoInformation = PhotoUtils.photoSave(imgPath, photoFile);
//            photo.transferTo(new File(imgPath, newFileName));

            //            构建照片信息
            photo.setPhoto_filming_time((Date) photoInformation.get("img_filming_time"));

            if (photo.getPhoto_name() == null) {
                photo.setPhoto_name(photoFile.getOriginalFilename());

            }
            photo.setPhoto_introduction(photo.getPhoto_name());
            photo.setPhoto_save_name(photoInformation.get("newFileName").toString());
            albumsService.savePhotos(photo);
        }

        AjaxResponse ajaxResponse = new AjaxResponse().success("添加成功");

        return ajaxResponse;
    }

//    修改照片
    @RequestMapping(value = "/admin/photos", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse putPhotos(Photos photos) {

        albumsService.putPhotos(photos);
        AjaxResponse ajaxResponse = new AjaxResponse().success("修改成功");

        return ajaxResponse;
    }


//    删除照片
    @RequestMapping(value = "/admin/photoIdList", method = RequestMethod.DELETE)
    public AjaxResponse deletePhotos(@RequestBody List<Integer> photoIdList) {

        String imgPath = realPath + "/photoImg";

        for (Integer photoId : photoIdList) {
            String fileName= albumsService.deletePhotos(photoId);
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }
        }

        AjaxResponse ajaxResponse = new AjaxResponse().success("删除成功");

        return ajaxResponse;
    }

}
