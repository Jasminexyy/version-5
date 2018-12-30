package cm.dao;

import cm.entity.Question;
import cm.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/27
 */
@Component
public class QuestionDAO {

    @Autowired
    private QuestionMapper questionMapper;

    public int scoreQuestion(Long questionId, BigDecimal score){
        return questionMapper.scoreQuestion(questionId,score);
    }

    public int  createQuestion(Long klassSeminarId, Long attendanceId, Question question){
        return questionMapper.createQuestion(klassSeminarId,attendanceId,question);
    }

    public Question getByQuestionId(Long questionId){
        return  questionMapper.getByQuestionId(questionId);
    }

    public List<Question> listUnSelectedQuestion(Long attendanceId){
        return questionMapper.listUnSelectedQuestionsByAttendanceId(attendanceId);
    }
}
