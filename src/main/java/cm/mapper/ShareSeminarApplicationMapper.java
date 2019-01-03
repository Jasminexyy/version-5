package cm.mapper;

import cm.entity.ShareSeminarApplication;
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
public interface ShareSeminarApplicationMapper {

    /**
     * 根据MainCourseId获得全部共享讨论课关系
     * @param mainCourseId
     * @return cm.entity.ShareSeminarApplication
     */
    @Select("select * from share_seminar_application where main_course_id=#{mainCourseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    List<ShareSeminarApplication> listByMainCourseId(Long mainCourseId);

    /**
     * 根据SubCourseTeacherId获得全部共享讨论课关系
     * @param teacherId
     * @return java.util.List<cm.entity.ShareSeminarApplication>
     */
    @Select("select * from share_seminar_application where sub_course_teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "mainCourseId",column = "main_course_id"),
            @Result(property = "subCourseId",column = "sub_course_id"),
            @Result(property = "subCourseTeacherId",column = "sub_course_teacher_id"),
            @Result(property = "status",column = "status")
    })
    List<ShareSeminarApplication> listBySubCourseTeacherId(@Param("teacherId") Long teacherId);
}
