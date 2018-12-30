package cm.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Mapper
@Repository
public interface KlassStudentMapper {

    /**
     * 根据KlassId删除该klassStudent
     * @param klassId
     * @return int
     */
    @Delete("delete from klass_student where klass_id = #{klassId}")
    int deleteByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据KlassId，StudentId与CourseId建立该klassStudent
     * @param klassId
     * @param studentId
     * @param courseId
     * @return int
     */
    @Insert("insert into klass_student(klass_id,student_id,course_id) " +
            "values(#{klassId},#{studentId},#{courseId})")
    int createByKlassIdAndStudentIdAndCourseId(@Param("klassId") Long klassId,
                                               @Param("studentId") Long studentId,
                                               @Param("courseId") Long courseId);

    /**
     * 学生加入小组
     * @param teamId
     * @param classId
     * @param studentId
     * @return int
     */
    @Update("update klass_student set team_id=#{teamId} where klass_id=#{classId} and student_id=#{studentId}")
    int updateTeam(@Param("teamId") Long teamId,
                   @Param("classId") Long classId,
                   @Param("studentId") Long studentId);

    /**
     * 解散小组
     * @param teamId
     * @return int
     */
    @Update("update klass_student set team_id=null where team_id=#{teamId}")
    int deleteTeam(@Param("teamId") Long teamId);

    /**
     * 学生退出小组
     * @param teamId
     * @param studentId
     * @return int
     */
    @Update("update klass_student set team_id=null where team_id=#{teamId} and student_id=#{studentId}")
    int deleteMember(@Param("teamId") Long teamId,
                     @Param("studentId") Long studentId);

    /**
     * 根据KlassId和StudentId获得TeamId
     * @param studentId
     * @param klassId
     * @return java.lang.Long
     */
    @Select("select team_id from klass_student where student_id=#{studentId} and klass_id=#{klassId}")
    Long getTeamIdByStudentIdAndKlassId(@Param("studentId") Long studentId,
                                        @Param("klassId") Long klassId);
}