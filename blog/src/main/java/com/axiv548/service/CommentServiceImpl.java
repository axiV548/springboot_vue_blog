package com.axiv548.service;

import com.axiv548.dao.CommentDao;
import com.axiv548.entity.BlogComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * CommentServiceImpl
 *
 * @date 2021/3/17 21:58
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;


    @Override
    public List<Map<String, Object>> getCommentByBlogId(Integer id) {

        return commentDao.getCommentByBlogId(id);
    }

    @Override
    public void postComment(BlogComment blogComment) {
        blogComment.setComment_create_time(LocalDateTime.now());
        commentDao.postComment(blogComment);
    }

    @Override
    public void putComment(BlogComment blogComment) {

    }

    @Override
    public void deleteComment(Integer id) {

        commentDao.deleteComment(id);

    }
}
