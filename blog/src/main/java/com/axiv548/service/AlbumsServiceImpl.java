package com.axiv548.service;

import com.axiv548.dao.AlbumsDao;
import com.axiv548.entity.Albums;
import com.axiv548.entity.Photos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * AlbumsServiceImpl
 *
 * @date 2021/5/5 15:19
 */
@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    AlbumsDao albumsDao;


    @Override
    public List<Map<String, Object>> getAlbums() {

        List<Map<String, Object>> albumsList = albumsDao.getAlbums();

        return albumsList;
    }

    @Override
    public Albums getAlbumById(Integer id) {
        return albumsDao.getAlbumById(id);
    }

    @Override
    public void saveAlbums(Albums albums) {
        albums.setAlbum_create_time(new Date());
        albumsDao.saveAlbums(albums);
    }

    @Override
    public void putAlbums(Albums albums) {
        albums.setAlbum_create_time(new Date());

        albumsDao.putAlbums(albums);
    }

    @Override
    public void deleteAlbums(Integer id) {
        albumsDao.deleteAlbums(id);
    }

    @Override
    public List<String> getPhotoNameByAlbumId(Integer id) {

        return albumsDao.getPhotoNameByAlbumId(id);
    }

    @Override
    public List<Integer> getAlbumSonIdByAlbumId(Integer id) {
        return albumsDao.getAlbumSonIdByAlbumId(id);
    }

    @Override
    public List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize) {

        List<Map<String, Object>> photoList = albumsDao.getPhotos(album_id, (page-1)*pageSize, pageSize);
        return photoList;
    }

    @Override
    public Integer getPhotoCountById(Integer id) {
        return albumsDao.getPhotoCountById(id);
    }

    @Override
    public Photos getPhotoById(Integer id) {
        return albumsDao.getPhotoById(id);
    }

    @Override
    public void savePhotos(Photos photos) {

        albumsDao.savePhotos(photos);
    }

    @Override
    public void putPhotos(Photos photos) {
        albumsDao.putPhotos(photos);

    }


    @Override
    @Transactional
    public String deletePhotos(Integer id) {

        String photoSaveName = albumsDao.getPhotoNameById(id);
        albumsDao.deletePhotos(id);
        return photoSaveName;
    }

}
