package cm.mapper;

import cm.entity.SeminarScore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/26
 */
@Mapper
@Repository
public interface SeminarScoreMapper {
    /**
     * 通过teamId和klassSeminarId找出讨论课成绩
     * @param klassSeminarId
     * @param teamId
     * @return cm.entity.SeminarScore
     */
    @Select("select * from seminar_score where klass_seminar_id=#{klassSeminarId} and team_id=#{teamId}")
    @Results({
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    SeminarScore getByKlassSeminarIdAndTeamId(@Param("klassSeminarId") Long klassSeminarId,
                                              @Param("teamId") Long teamId);

    /**
     * 通过teamId和seminarId和klassId找出讨论课成绩
     * @param seminarId
     * @param klassId
     * @param teamId
     * @return cm.entity.SeminarScore
     */
    @Select("select * from seminar_score where team_id=#{teamId} and klass_seminar_id" +
            " in(select id from klass_seminar where seminar_id=#{seminarId} and klass_id=#{klassId})")
    @Results({
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    SeminarScore getBySeminarIdAndKlassIdAndTeamId(@Param("seminarId") Long seminarId,
                                                   @Param("klassId") Long klassId,
                                                   @Param("teamId") Long teamId);

    /**
     * 根据klassSeminarId删除所有讨论课成绩
     * @param klassSeminarId
     * @return int
     */
    @Delete("delete from seminar_score where klass_seminar_id=#{klassSeminarId}")
    int deleteByKlassSeminarId(@Param("klassSeminarId") Long klassSeminarId);

    /**
     * 根据teamId删除所有讨论课成绩
     * @param teamId
     * @return int
     */
    @Delete("delete from seminar_score where team_id=#{teamId}")
    int deleteByTeamId(@Param("teamId") Long teamId);

    /**
     * 更新成绩
     * @param seminarScore
     * @param klassSeminarId
     * @param teamId
     * @return int
     */
    @Update("update seminar_score set presentation_score=#{seminarScore.presentationScore}," +
            "question_score=#{seminarScore.questionScore},report_score=#{seminarScore.reportScore}," +
            "total_score=#{seminarScore.totalScore} where klass_seminar_id=#{klassSeminarId} and " +
            "team_id=#{teamId}")
    int updateSeminarScore(@Param("seminarScore") SeminarScore seminarScore,
                           @Param("klassSeminarId") Long klassSeminarId,
                           @Param("teamId") Long teamId);
}
