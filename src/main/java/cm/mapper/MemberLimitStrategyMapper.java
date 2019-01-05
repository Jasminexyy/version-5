package cm.mapper;

import cm.entity.MemberLimitStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Mapper
@Repository
public interface MemberLimitStrategyMapper {
    /**
     * 根据MemberLimitStrategyId获得成员限制策略
     * @param memberLimitStrategyId
     * @return cm.entity.MemberLimitStrategy
     */
    @Select("select * from member_limit_strategy where id =#{memberLimitStrategyId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "max_member")
    })
    MemberLimitStrategy getByMemberLimitStrategyId(@Param("memberLimitStrategyId") Long memberLimitStrategyId);

    /**
     * 创建成员限制策略
     * @param minMember
     * @param maxMember
     * @return int
     */
    @Insert("insert into member_limit_strategy(min_member,max_member) " +
            "values(#{minMember},#{maxMember})")
    @Options(useGeneratedKeys = true, keyProperty = "memberLimitStrategy.id")
    int createMemberLimitStrategy(@Param("minMember") Byte minMember,
                                  @Param("maxMember") Byte maxMember);
}
