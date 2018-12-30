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
public interface TeamStudentMapper {

    @Insert("insert into team_student(team_id,student_id) " +
            "values(#{teamId},#{studentId})")
    int createByTeamIdAndStudentId(@Param("teamId") Long teamId,
                                   @Param("studentId") Long studentId);

    @Delete("delete from team_student where team_id = #{teamId}")
    int deleteTeamByTeamId(@Param("teamId") Long teamId);

    @Delete("delete from team_student where team_id = #{teamId} and student_id = #{studentId}")
    int deleteMemberByTeamIdAndStudentId(@Param("teamId") Long teamId,
                                         @Param("studentId") Long studentId);
}
