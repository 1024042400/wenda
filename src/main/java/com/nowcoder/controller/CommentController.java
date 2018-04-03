package com.nowcoder.controller;

import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.server.CommentService;
import com.nowcoder.server.QuestionService;
import com.nowcoder.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    @RequestMapping(path = "/addComment",method =  RequestMethod.POST)
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("comment") String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedDate(new Date());
        if(hostHolder.getUser() == null) {
            comment.setUserId(WendaUtil.ANONYMOUS_USERID);
        } else {
            comment.setUserId(hostHolder.getUser().getId());
        }
        comment.setEntityId(questionId);
        comment.setEntityType(EntityType.ENTITY_COMMENT);
        commentService.addComment(comment);

        int count = commentService.getCommentCount(questionId,EntityType.ENTITY_COMMENT);
        questionService.updateQuestionCount(questionId,count);

        return "redirect:/question/"+questionId;
    }
}
