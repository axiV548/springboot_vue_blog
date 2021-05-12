package com.axiv548.service;

import com.axiv548.entity.Albums;
import com.axiv548.entity.Photos;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * AlbumsService
 *
 * @date 2021/5/5 15:19
 */
public interface AlbumsService {

//    查询所有相册（包含一二级）
    List<Map<String, Object>> getAlbums();

//    根据ID查相册信息
    Albums getAlbumById(Integer id);

    //    创建相册
    void saveAlbums(Albums albums);

//    修改相册
    void putAlbums(Albums albums);

//    删除相册
    void deleteAlbums(Integer id);
    List<String> getPhotoNameByAlbumId(Integer id);
    List<Integer> getAlbumSonIdByAlbumId(Integer id);

//    查询相册内的照片
    List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize);
//    查询相片数量
    Integer getPhotoCountById(Integer id);


    //    根据ID查照片信息
    Photos getPhotoById(Integer id);

//    保存照片
    void savePhotos(Photos photos);

//    修改照片
    void putPhotos(Photos photos);

//    删除照片
    String deletePhotos(Integer id);


}
