package cm.mapper;

import cm.entity.Round;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Mapper
@Repository
public interface RoundMapper {
    /**
     * 根据courseId获得所有round
     * @param courseId
     * @return java.util.List<cm.entity.Round>
     */
    @Select("select * from round where course_id=#{courseId} order by round_serial asc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roundSerial",column = "round_serial"),
            @Result(property = "presentationScoreMethod",column = "presentation_score_method"),
            @Result(property = "reportScoreMethod",column = "report_score_method"),
            @Result(property = "questionScoreMethod",column = "question_score_method"),
            @Result(property = "seminars", column = "id", many=@Many(select="cm.mapper.SeminarMapper.listByRoundId"))
    })
    List<Round>listByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据roundId获得round
     * @param roundId
     * @return cm.entity.Round
     */
    @Select("select * from round where id=#{roundId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roundSerial",column = "round_serial"),
            @Result(property = "presentationScoreMethod",column = "presentation_score_method"),
            @Result(property = "reportScoreMethod",column = "report_score_method"),
            @Result(property = "questionScoreMethod",column = "question_score_method"),
            @Result(property = "seminars", column = "id", many=@Many(select="cm.mapper.SeminarMapper.listByRoundId"))
    })
    Round getByRoundId(@Param("roundId") Long roundId);

    /**
     * 删除round
     * @param roundId
     * @return int
     */
    @Delete("delete from round where id = #{roundId}")
    int deleteByRoundId(@Param("roundId") Long roundId);
}
