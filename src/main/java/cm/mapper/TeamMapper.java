package cm.mapper;

import cm.entity.Team;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/20
 */
@Mapper
@Repository
public interface TeamMapper {
    /**
     * 根据KlassId获取所有Team
     * @param klassId
     * @return java.util.List<cm.entity.Team>
     */
    @Select("select * from team where id " +
            "in(select team_id from klass_team where klass_id=#{klassId}) order by team_serial asc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    List<Team>listByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据courseId获取所有team
     * @param courseId
     * @return java.util.List<cm.entity.Team>
     */
    @Select("select * from team where course_id=#{courseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    List<Team>listByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据klassId和studentId获取Team
     * @param klassId
     * @param studentId
     * @return cm.entity.Team
     */
    @Select("select * from team where klass_id=#{klassId} and id " +
            "in(select team_id from team_student where student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    Team getByKlassIdAndStudentId(@Param("klassId") Long klassId,
                                  @Param("studentId") Long studentId);

    /**
     * 创建Team
     * @param team
     * @return int
     */
    @Insert("insert into team(klass_id,course_id,leader_id,team_name,team_serial,klass_serial,status)" +
            " values(#{team.klassId},#{team.courseId},#{team.leaderId},#{team.teamName},#{team.teamSerial},#{team.klass_serial},#{team.status})")
    @Options(useGeneratedKeys = true, keyProperty = "team.id")
    int createTeam(@Param("team") Team team);

    /**
     * 根据TeamId获取Team
     * @param teamId
     * @return cm.entity.Team
     */
    @Select("select * from team where id=#{teamId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    Team getByTeamId(@Param("teamId") Long teamId);

    /**
     * 判断是否是组长
     * @param teamId
     * @param leaderId
     * @return cm.entity.Team
     */
    @Select("select * from team where id=#{teamId} and leader_id=#{leaderId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    Team getByIdAndLeaderId(@Param("teamId") Long teamId,
                            @Param("leaderId") Long leaderId);

    /**
     * 根据id删除小组
     * @param teamId
     * @return int
     */
    @Delete("delete from team where id=#{teamId}")
    int deleteById(Long teamId);

    /**
     * 根据courseId和学生id获取team
     * @param courseId
     * @param studentId
     * @return cm.entity.Team
     */
    @Select("select * from team where klass_id " +
            "in(select klass_id from klass_student where course_id =#{courseId} and student_id=#{studentId})and id " +
            "in(select team_id from team_student where student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    Team getByCourseIdAndStudentId(@Param("courseId") Long courseId,
                                   @Param("studentId") Long studentId);

    /**
     * 根据TeamId获取KlassId
     * @param teamId
     * @return java.lang.Long
     */
    @Select("select klass_id from team where id=#{teamId}")
    Long getKlassIdByTeamId(@Param("teamId") Long teamId);

    /**
     * 根据studentId和klassId获取teamId
     * @param studentId
     * @param klassId
     * @return java.lang.Long
     */
    @Select("select distinct klass_team.team_id from team_student,klass_team where team_student.student_id=#{studentId} " +
            "and klass_team.klass_id=#{klassId} " +
            "and team_student.team_id=klass_team.team_id")
    Long getTeamIdByStudentIdAndKlassId(@Param("studentId") Long studentId,
                                        @Param("klassId") Long klassId);

    /**
     * 根据attendanceId获取组
     * @param attendanceId
     * @return cm.entity.Team
     */
    @Select("select * from team where id " +
            "in(select team_id from attendance where id=#{attendanceId}) ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "leaderId",column = "leader_id"),
            @Result(property = "teamName",column = "team_name"),
            @Result(property = "teamSerial",column = "team_serial"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "status",column = "status"),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByTeamId",fetchType = FetchType.LAZY))
    })
    Team getByAttendanceId(Long attendanceId);
}
