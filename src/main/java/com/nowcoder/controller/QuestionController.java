package com.nowcoder.controller;

import com.nowcoder.model.*;
import com.nowcoder.server.CommentService;
import com.nowcoder.server.QuestionService;
import com.nowcoder.server.SensitiveService;
import com.nowcoder.server.UserService;
import com.nowcoder.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    HostHolder hostHolder;

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping(value ="/question/{qid}", method = RequestMethod.GET)
    public String getQuestionDetail(Model model, @PathVariable("qid") int qid) {
        Question question = questionService.getById(qid);
        model.addAttribute("question", question);

        List<Comment> commentList = commentService.getCommentsByEntityId(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> viewObjects = new ArrayList<>(commentList.size());
        for(Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            vo.set("user", userService.getUser(comment.getUserId()));
            viewObjects.add(vo);
        }

        model.addAttribute("comments",viewObjects);
        return "detail";
    }

    @RequestMapping(value = "/question/add",method = RequestMethod.POST)
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam("content") String content) {
        Question question = new Question();
        question.setContent(content);
        question.setCreatedDate(new Date());
        question.setTitle(title);
        User u = hostHolder.getUser();
        question.setUserId(u == null ? WendaUtil.ANONYMOUS_USERID : u.getId());
        return "";
    }
}
