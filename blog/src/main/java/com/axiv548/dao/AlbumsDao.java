package com.axiv548.dao;

import com.axiv548.entity.Albums;
import com.axiv548.entity.Photos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * AlbumsDao
 *
 * @date 2021/5/5 15:18
 */
@Mapper
public interface AlbumsDao {


    List<Map<String, Object>> getAlbums();

    Albums getAlbumById(Integer id);

    void saveAlbums(Albums albums);

    void putAlbums(Albums albums);

    void deleteAlbums(Integer id);
    List<String> getPhotoNameByAlbumId(Integer id);
    List<Integer> getAlbumSonIdByAlbumId(Integer id);


    List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize);
    Integer getPhotoCountById(Integer id);

    Photos getPhotoById(Integer id);

    void savePhotos(Photos photos);

    void putPhotos(Photos photos);

    void deletePhotos(Integer photoId);

    String getPhotoNameById(Integer photoId);




}
