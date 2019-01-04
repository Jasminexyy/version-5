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
     * 创建或策略
     * @param teamOrStrategy
     * @return cm.entity.teamAndStrategy
     */
    @Insert("insert into team_or_strategy(min_member,max_member) " +
            "values(#{teamOrStrategy.minMember},#{teamOrStrategy.maxMember})")
    @Options(useGeneratedKeys = true, keyProperty = "teamOrStrategy.id")
    int createTeamOrStrategy(@Param("teamOrStrategy") TeamOrStrategy teamOrStrategy);
}
