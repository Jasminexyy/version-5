package cm.mapper;

import cm.entity.Attendance;
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
public interface AttendanceMapper {
    /**
     * 根据AttendanceId获得attendance
     * @param attendanceId
     * @return cm.entity.Attendance
     */
    @Select("select * from attendance where id=#{attendanceId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    Attendance getByAttendanceId(@Param("attendanceId") Long attendanceId);

    /**
     * 根据KlassSeminarId获得attendance 得到课程讨论课全部已报名展示
     * @param klassSeminarId
     * @return java.util.List<cm.entity.Attendance>
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    List<Attendance> listByKlassSeminarId(@Param("klassSeminarId") Long klassSeminarId);

    /**
     * 根据TeamId获得attendance 得到该小组全部已报名展示
     * @param teamId
     * @return java.util.List<cm.entity.Attendance>
     */
    @Select("select * from attendance where team_id=#{teamId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    List<Attendance> listByTeamId(@Param("teamId") Long teamId);

    /**
     * 根据KlassSeminarId，KlassId与StudentId获得attendance 得到某学生在某课程某讨论课报名展示
     * @param klassSeminarId
     * @param klassId
     * @param studentId
     * @return cm.entity.Attendance
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId} and team_id " +
            "in(select team_id from klass_student where klass_id=#{klassId} and student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    Attendance getByKlassSeminarIdAndKlassIdAndStudentId(@Param("klassSeminarId") Long klassSeminarId,
                                                          @Param("klassId") Long klassId,
                                                          @Param("studentId") Long studentId);
    /**
     * 根据SeminarId，KlassId和StudentId获得attendance 得到某学生在某课程某讨论课报名展示
     * @param seminarId
     * @param klassId
     * @param studentId
     * @return cm.entity.Attendance
     */
    @Select("select * from attendance where klass_seminar_id " +
            "in(select id from klass_seminar where seminar_id=#{seminarId} and klass_id=#{klassId}) and team_id " +
            "in(select team_id from klass_student where klass_id=#{klassId} and student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    Attendance getBySeminarIdAndKlassIdAndStudentId(@Param("seminarId") Long seminarId,
                                                     @Param("klassId") Long klassId,
                                                     @Param("studentId") Long studentId);
    /**
     * 根据TeamId和KlassSeminarId获得attendance 得到某小组在某课程讨论课报名展示
     * @param klassSeminarId
     * @param teamId
     * @return cm.entity.Attendance
     */
    @Select("select * from attendance where klass_seminar_id=#{klassSeminarId} and team_id=#{teamId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    Attendance getByKlassSeminarIdAndTeamId(@Param("klassSeminarId") Long klassSeminarId,
                                             @Param("teamId") Long teamId);

    /**
     * 根据AttendanceId与StudentId获得attendance 得到该小组成员已报名的展示
     * @param attendanceId
     * @param studentId
     * @return cm.entity.Attendance
     */
    @Select("select * from attendance where id=#{attendanceId} and team_id " +
            "in(select team_id from klass_student where student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    Attendance getByAttendanceIdAndStudentId(@Param("attendanceId") Long attendanceId,
                                             @Param("studentId") Long studentId);
    /**
     * 根据AttendanceId上传ppt
     * @param attendanceId
     * @param pptName
     * @param pptUrl
     * @return int
     */
    @Update("update attendance set ppt_name=#{pptName},ppt_url=#{pptUrl} where id=#{attendanceId}")
    int updatePptByAttendanceId(@Param("attendanceId") Long attendanceId,
                                @Param("pptName") String pptName,
                                @Param("pptUrl") String pptUrl);

    /**
     * 根据AttendanceId上传报告
     * @param attendanceId
     * @param reportName
     * @param reportUrl
     * @return int
     */
    @Update("update attendance set report_name=#{reportName},report_url=#{reportUrl} where id=#{attendanceId}")
    int updateReportByAttendanceId(@Param("attendanceId") Long attendanceId,
                                   @Param("reportName") String reportName,
                                   @Param("reportUrl") String reportUrl);

    /**
     * 根据AttendanceId删除展示
     * @param attendanceId
     * @return int
     */
    @Delete("delete from attendance where id = #{attendanceId}")
    int deleteByAttendanceId(@Param("attendanceId") Long attendanceId);

    /**
     * 根据klassSeminarId和teamId添加报名
     * @param attendance
     * @param klassSeminarId
     * @param teamId
     * @return int
     */
    @Insert("insert into attendance(klass_seminar_id,team_id,team_order,is_present) " +
            "values(#{klassSeminarId},#{teamId},#{attendance.teamOrder},#{attendance.isPresent})")
    @Options(useGeneratedKeys = true, keyProperty = "attendance.id")
    int addAttendance(@Param("attendance") Attendance attendance,
                      @Param("klassSeminarId")Long klassSeminarId,
                      @Param("teamId")Long teamId);

    /**
     * 根据AttendanceId获得TeamId
     * @param attendanceId
     * @return java.lang.Long
     */
    @Select("select team_id from attendance where id=#{attendanceId}")
    Long getTeamIdByAttendanceId(@Param("attendanceId")Long attendanceId);


    /**
     * 根据AttendanceId获得KlassSeminarId
     * @param attendanceId
     * @return java.lang.Long
     */
    @Select("select klass_seminar_id from attendance where id=#{attendanceId}")
    Long getKlassSeminarIdByAttendanceId(@Param("attendanceId")Long attendanceId);


    /**
     * 根据AttendanceId获得ReportUrl
     * @param attendanceId
     * @return java.lang.String
     */
    @Select("select report_url from attendance where id=#{attendanceId}")
    String getReportUrlByAttendanceId(@Param("attendanceId")Long attendanceId);

    /**
     * 根据AttendanceId获得PptUrl
     * @param attendanceId
     * @return java.lang.String
     */
    @Select("select ppt_url from attendance where id=#{attendanceId}")
    String getPptUrlByAttendanceId(@Param("attendanceId")Long attendanceId);


    /**
     * 开始展示
     * @param attendanceId
     * @return int
     */
    @Update("update attendance set is_present=1 where id=#{attendanceId}")
    int startAttendanceById(@Param("attendanceId")Long attendanceId);

    /**
     * 结束展示
     * @param attendanceId
     * @return int
     */
    @Update("update attendance set is_present=0 where id=#{attendanceId}")
    int endAttendanceById(@Param("attendanceId")Long attendanceId);

    /**
     * 根据RoundId与TeamId获得attendance
     * @param roundId
     * @param teamId
     * @return java.util.List<cm.entity.Attendance>
     */
    @Select("select * from attendance where team_id=#{teamId} and klass_seminar_id " +
            "in(select id from klass_seminar where seminar_id " +
            "in (select id from seminar where round_id =#{roundId}))")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "teamOrder",column = "team_order"),
            @Result(property = "isPresent",column = "is_present"),
            @Result(property = "reportName",column = "report_name"),
            @Result(property = "reportUrl",column = "report_url"),
            @Result(property = "pptName",column = "ppt_name"),
            @Result(property = "pptUrl",column = "ppt_url")
    })
    List<Attendance> listByRoundIdAndTeamId(@Param("roundId") Long roundId,
                                            @Param("teamId") Long teamId);
}
