package cm.mapper;

import cm.entity.TeamOrStrategy;
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
public interface TeamOrStrategyMapper {
    /**
     * 或策略
     * @param strategyId
     * @return java.util.List<cm.entity.TeamOrStrategy>
     */
    @Select("select * from team_or_strategy where id =#{strategyId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    List<TeamOrStrategy> listByStrategyId(@Param("strategyId") Long strategyId);

    /**
     * 或策略
     * @return java.util.List<cm.entity.TeamOrStrategy>
     */
    @Select("select * from team_or_strategy")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    List<TeamOrStrategy> listAllTeamOrStrategy();
    /**
     * 创建或策略
     * @param id
     * @param strategyName
     * @param strategyId
     * @return int
     */
    @Insert("insert into team_or_strategy(id,strategy_name,strategy_id) " +
            "values(#{id},#{strategyName},#{strategyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createTeamOrStrategy(@Param("id") Long id,
                             @Param("strategyName") String strategyName,
                             @Param("strategyId") Long strategyId);
}
