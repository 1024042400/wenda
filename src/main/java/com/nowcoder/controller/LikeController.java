package com.nowcoder.controller;

import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.User;
import com.nowcoder.server.CommentService;
import com.nowcoder.server.JedisAdapter;
import com.nowcoder.server.LikeService;
import com.nowcoder.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LikeController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(path = "/like",method = RequestMethod.POST)
    @ResponseBody
    public String like(@RequestParam("commentId") int commentId) {
        User user = hostHolder.getUser();
        if(user == null) {
            return WendaUtil.getJSONString(999);
        }

        Comment comment = commentService.getCommentById(commentId);

        eventProducer.fireEvent(new EventModel(EventType.COMMENT).setActorId(user.getId())
            .setEntityOwnerId(comment.getUserId()).setEntityType(EntityType.ENTITY_COMMENT)
            .setEntityId(commentId).setExt("questionId",String.valueOf(comment.getEntityId())));

        long count = likeService.like(user.getId(),EntityType.ENTITY_COMMENT,commentId);
        return WendaUtil.getJSONString(0,String.valueOf(count));

    }

    @RequestMapping(path = "/dislike", method = RequestMethod.POST)
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        User user = hostHolder.getUser();
        if(user == null){
            return WendaUtil.getJSONString(999);
        }

        long count = likeService.dislike(user.getId(),EntityType.ENTITY_COMMENT,commentId);
        return WendaUtil.getJSONString(0,String.valueOf(count));
    }
}
