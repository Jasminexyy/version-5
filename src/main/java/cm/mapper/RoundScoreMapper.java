package cm.mapper;

import cm.entity.RoundScore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Mapper
@Repository
public interface RoundScoreMapper {
    /**
     * 根据roundId和teamId获得RoundScore
     * @param roundId
     * @param teamId
     * @return cm.entity.RoundScore
     */
    @Select("select * from round_score where round_id=#{roundId} and team_id=#{teamId}")
    @Results({
            @Result(property = "totalScore",column = "total_score"),
            @Result(property = "presentationScore",column = "presentation_score"),
            @Result(property = "questionScore",column = "question_score"),
            @Result(property = "reportScore",column = "report_score")
    })
    RoundScore getByRoundIdAndTeamId(@Param("roundId") Long roundId,
                                      @Param("teamId") Long teamId);

    /**
     * 根据teamId删除所有轮成绩
     * @param teamId
     * @return int
     */
    @Delete("delete from round_score where team_id=#{teamId}")
    int deleteByTeamId(@Param("teamId") Long teamId);
}
