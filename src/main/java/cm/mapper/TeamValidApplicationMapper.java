package cm.mapper;

import cm.entity.TeamValidApplication;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Mapper
@Repository
public interface TeamValidApplicationMapper {

    /**
     * 增加一条组队申请
     * @param teamValidApplication
     * @return int
     */
    @Insert("insert into team_valid_application(team_id,teacher_id,reason,status) " +
            "values(#{teamId},#{teacherId},#{reason},#{status})")
    int addApplication(TeamValidApplication teamValidApplication);

    /**
     * 教师同意申请
     * @param teacherId
     * @param teamId
     * @return int
     */
    @Update("update team_valid_application set status=1 where teacher_id=#{teacherId} and team_id=#{teamId}")
    int approveApplication(@Param("teacherId") Long teacherId,
                           @Param("teamId") Long teamId);

    /**
     * 通过team_id删除申请
     * @param teamId
     * @return int
     */
    @Delete("delete from team_valid_application where team_id=#{teamId}")
    int deleteByTeamId(@Param("teamId") Long teamId);

    /**
     * 通过teacherId找application
     * @param teacherId
     * @return java.util.List<cm.entity.TeamValidApplication>
     */
    @Select("select * from team_valid_application where teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamId",column = "team_id"),
            @Result(property = "teacherId",column = "teacher_id"),
            @Result(property = "reason",column = "reason"),
            @Result(property = "status",column = "status")
    })
    List<TeamValidApplication> listByTeacherId(@Param("teacherId") Long teacherId);
}
