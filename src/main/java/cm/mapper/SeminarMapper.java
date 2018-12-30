package cm.mapper;

import cm.entity.Seminar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/25
 */
@Mapper
@Repository
public interface SeminarMapper {
    /**
     * 根据RoundId获得某轮下的所有seminar
     * @param roundId
     * @return java.util.List<cm.entity.Seminar>
     */
    @Select("select * from seminar where round_id=#{roundId} order by seminar_serial asc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "seminarName",column = "seminar_name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "maxTeam",column = "max_team"),
            @Result(property = "isVisible",column = "is_visible"),
            @Result(property = "seminarSerial",column = "seminar_serial"),
            @Result(property = "enrollStartTime",column = "enroll_start_time"),
            @Result(property = "enrollEndTime",column = "enroll_end_time")
    })
    List<Seminar>listByRoundId(@Param("roundId") Long roundId);

    /**
     * 根据SeminarId获得seminar
     * @param seminarId
     * @return cm.entity.Seminar
     */
    @Select("select * from seminar where id=#{seminarId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "seminarName",column = "seminar_name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "maxTeam",column = "max_team"),
            @Result(property = "isVisible",column = "is_visible"),
            @Result(property = "seminarSerial",column = "seminar_serial"),
            @Result(property = "enrollStartTime",column = "enroll_start_time"),
            @Result(property = "enrollEndTime",column = "enroll_end_time")
    })
     Seminar getBySeminarId(@Param("seminarId") Long seminarId);

    /**
     * 根据teacherId与SeminarId获得seminar 判断是否有权限操作
     * @param seminarId
     * @param teacherId
     * @return cm.entity.Seminar
     */
    @Select("select * from seminar where id=#{seminarId} and course_id " +
            "in(select id from course where teacher_id=#{teacherId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "seminarName",column = "seminar_name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "maxTeam",column = "max_team"),
            @Result(property = "isVisible",column = "is_visible"),
            @Result(property = "seminarSerial",column = "seminar_serial"),
            @Result(property = "enrollStartTime",column = "enroll_start_time"),
            @Result(property = "enrollEndTime",column = "enroll_end_time")
    })
    Seminar getBySeminarIdAndTeacherId(@Param("seminarId") Long seminarId,
                                       @Param("teacherId") Long teacherId);
    /**
     * 删除此seminar
     * @param seminarId
     * @return int
     */
    @Delete("delete from seminar where id = #{seminarId}")
    int deleteBySeminarId(@Param("seminarId") Long seminarId);


    /**
     * 教师新建班级
     * @param seminar
     * @return int
     */
    @Insert("insert into seminar(course_id,round_id,seminar_name,introduction,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time)" +
            " values(#{courseId},#{roundId},#{seminarName},#{introduction},#{maxTeam},#{isVisible},#{seminarSerial},#{enrollStartTime},#{enrollEndTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createSeminar(Seminar seminar);


    /**
     * 修改课程信息
     * @param seminar
     * @return int
     */
    @Update("update seminar set round_id=#{roundId},seminar_name=#{seminarName},introduction=#{introduction}," +
            "max_team=#{maxTeam},is_visible=#{isVisible},seminar_serial=#{seminarSerial}," +
            "enroll_start_time=#{enrollStartTime},enroll_end_time=#{enrollEndTime}" +
            " where id=#{id}")
    int modifySeminar(Seminar seminar);

    /**
     * 修改讨论课轮次
     * @param roundId
     * @param seminarId
     * @return int
     */
    @Update("update seminar set round_id=#{roundId} where id=#{seminarId}")
    int modifyRoundIdBySeminarId(@Param("roundId") Long roundId,
                                 @Param("seminarId") Long seminarId);

    /**
     * 根据SeminarId获得RoundId
     * @param seminarId
     * @return int
     */
    @Select("select round_id from seminar where id=#{seminarId}")
    Long getRoundIdBySeminarId(@Param("seminarId") Long seminarId);
}
