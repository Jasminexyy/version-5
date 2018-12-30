package cm.mapper;

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

}
