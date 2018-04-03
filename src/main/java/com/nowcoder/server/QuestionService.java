package com.nowcoder.server;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by jy on 2018/3/20.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    SensitiveService sensitiveService;

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }

    public Question getById(int id) {
        return questionDAO.getById(id);
    }

    public int addQuestion(Question question) {
        question.setTitle(sensitiveService.filter(HtmlUtils.htmlEscape(question.getTitle())));
        question.setContent(sensitiveService.filter(HtmlUtils.htmlEscape(question.getContent())));
        return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
    }

    public int updateQuestionCount(int id,int count) {
        return questionDAO.updateCommentCount(id,count);
    }
}
