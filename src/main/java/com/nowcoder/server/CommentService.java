package com.nowcoder.server;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    public List<Comment> getCommentsByEntityId(int entityId,int entityType) {
        return commentDAO.getCommentsByEntity(entityId,entityType);
    }

    public int addComment(Comment comment) {
        return commentDAO.addComment(comment) > 0 ? comment.getEntityId() : 0;
    }

    public int getCommentCount(int entityId,int entityType) {
        return commentDAO.getCommentCount(entityId,entityType);
    }

    public Comment getCommentById(int id) {
        return commentDAO.getCommentById(id);
    }
}
