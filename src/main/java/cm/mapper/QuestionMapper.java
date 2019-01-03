package cm.mapper;

import cm.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/27
 */
@Mapper
@Repository
public interface QuestionMapper {
    /**
     * 根据questionId获得question
     * @param questionId
     * @return cm.entity.Question
     */
    @Select("select * from question where id=#{questionId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "isSelected",column = "is_selected"),
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "score",column = "score")
    })
    Question getByQuestionId(@Param("questionId") Long questionId);

    /**
     * 根据attendanceId删除提问
     * @param attendanceId
     * @return int
     */
    @Delete("delete from question where attendance_id = #{attendanceId}")
    int deleteByAttendanceId(@Param("attendanceId") Long attendanceId);

    /**
     * 根据teamId删除提问
     * @param teamId
     * @return int
     */
    @Delete("delete from question where team_id = #{teamId}")
    int deleteByTeamId(@Param("teamId") Long teamId);

    /**
     * 发起提问
     * @param klassSeminarId
     * @param attendanceId
     * @param question
     * @return int
     */
    @Insert("insert into question(klass_seminar_id,attendance_id,team_id,student_id,is_selected) " +
            "values(#{klassSeminarId},#{attendanceId}," +
            "#{question.teamId},#{question.studentId},#{question.isSelected}) ")
    @Options(useGeneratedKeys = true,keyProperty = "question.id")
    int createQuestion(@Param("klassSeminarId") Long klassSeminarId,
                       @Param("attendanceId") Long attendanceId,
                       @Param("question") Question question);


    /**
     * 提问评分
     * @param questionId
     * @param score
     * @return int
     */
    @Update("update question set score=#{score},is_selected=1 where id=#{questionId}")
    int scoreQuestion(@Param("questionId") Long questionId,
                      @Param("score") BigDecimal score);


    /**
     * 根据AttendanceId获得UnSelectedQuestions
     * @param attendanceId
     * @return java.util.List<cm.entity.Question>
     */
    @Select("select * from question where is_selected=0 and attendance_id=#{attendanceId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "isSelected",column = "is_selected"),
            @Result(property = "klassSeminarId",column = "klass_seminar_id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "score",column = "score")
    })
    List<Question> listUnSelectedQuestionsByAttendanceId(@Param("attendanceId") Long attendanceId);
}
