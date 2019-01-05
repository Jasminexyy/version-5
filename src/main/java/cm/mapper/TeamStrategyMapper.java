package cm.mapper;

import cm.entity.TeamOrStrategy;
import cm.entity.TeamStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Mapper
@Repository
public interface TeamStrategyMapper {
    /**
     * 通过courseId找出所有策略
     * @param courseId
     * @return java.util.List<cm.entity.TeamStrategy>
     */
    @Select("select * from team_strategy where course_id =#{courseId}")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "strategySerial",column = "strategy_serial"),
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    List<TeamStrategy> listByCourseId(@Param("courseId") Long courseId);

    /**
     * 创建策略
     * @param courseId
     * @param strategySerial
     * @param strategyName
     * @param strategyId
     * @return int
     */
    @Insert("insert into team_or_strategy(course_id,strategy_serial,strategy_name,strategy_id) " +
            "values(#{courseId},#{strategySerial},#{strategyName},#{strategyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createTeamStrategy(@Param("courseId") Long courseId,
                           @Param("strategySerial") Byte strategySerial,
                           @Param("strategyName") String strategyName,
                           @Param("strategyId") Long strategyId);
    /**
     * 所有策略
     * @return java.util.List<cm.entity.TeamStrategy>
     */
    @Select("select * from team_strategy ")
    @Results({
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "strategySerial",column = "strategy_serial"),
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    List<TeamStrategy> listAllTeamStrategy();
}
