package cm.mapper;

import cm.entity.TeamAndStrategy;
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
public interface TeamAndStrategyMapper {
    /**
     * 与策略
     * @param strategyId
     * @return java.util.List<cm.entity.TeamAndStrategy>
     */
    @Select("select * from team_and_strategy where id =#{strategyId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "strategyName",column = "strategy_name"),
            @Result(property = "strategyId",column = "strategy_id")
    })
    List<TeamAndStrategy> listByStrategyId(@Param("strategyId") Long strategyId);

}
