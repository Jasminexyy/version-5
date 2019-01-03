package cm.mapper;

import cm.entity.ShareTeamApplication;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/16
 */
@Mapper
@Repository
public interface ShareTeamApplicationMapper {

    /**
     * 通过MainCourseId获得全部共享情况
     * @param mainCourseId
     * @return cm.entity.ShareSeminarApplication
     */
    @Select("select * from share_team_application where main_course_id=#{mainCourseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    List<ShareTeamApplication> listByMainCourseId(Long mainCourseId);

    /**
     * 根据SubCourseTeacherId获得该从课程教师所有共享关系
     * @param teacherId
     * @return java.util.List<cm.entity.ShareTeamApplication>
     */
    @Select("select * from share_team_application where sub_course_teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    List<ShareTeamApplication> listBySubCourseTeacherId(@Param("teacherId") Long teacherId);

    /**
     * 根据Id和SubCourseTeacherId获得该从课程教师该共享关系
     * @param id
     * @param teacherId
     * @return java.util.List<cm.entity.ShareTeamApplication>
     */
    @Select("select * from share_team_application where id=#{id} and sub_course_teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    ShareTeamApplication getByIdAndSubCourseTeacherId(@Param("id") Long id,
                                                      @Param("teacherId") Long teacherId);

    /**
     * 更新组队
     * @param status
     * @param id
     * @return int
     */
    @Update("update share_team_application set status=#{status} where id=#{id}")
    int updateStatusById(@Param("status") Byte status,
                         @Param("id") Long id);

    @Insert("insert into share_team_application(main_course_id,sub_course_id,sub_course_teacher_id,status)" +
            "value(#{mainCourseId},#{subCourseId},#{subCourseTeacherId},#{status})")
    int createShareTeamApplication(@Param("mainCourseId") Long mainCourseId,
                                   @Param("subCourseId") Long subCourseId,
                                   @Param("subCourseTeacherId") Long subCourseTeacherId,
                                   @Param("status") Byte status);

}
