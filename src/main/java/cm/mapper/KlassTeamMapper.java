package cm.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Mapper
@Repository
public interface KlassTeamMapper {
    /**
     * 根据klassId与teamId创建
     * @param klassId
     * @param teamId
     * @return cm.entity.KlassTeam
     */
    @Insert("insert into klass_team(klass_id,team_id) " +
            "values(#{klassId},#{teamId})")
    int createByKlassIdAndTeamId(@Param("klassId") Long klassId,
                                 @Param("teamId") Long teamId);
    /**
     * 根据teamId删除klassTeam
     * @param teamId
     * @return cm.entity.KlassTeam
     */
    @Delete("delete from klass_team where team_id = #{teamId}")
    int deleteByTeamId(@Param("teamId") Long teamId);

    /**
     * 根据klassId删除klassTeam
     * @param klassId
     * @return cm.entity.KlassTeam
     */
    @Delete("delete from klass_team where klass_id = #{klassId}")
    int deleteByKlassId(@Param("klassId") Long klassId);
}
