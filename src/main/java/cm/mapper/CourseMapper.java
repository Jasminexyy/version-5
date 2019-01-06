package cm.mapper;

import cm.entity.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/26
 */

@Mapper
@Repository
public interface CourseMapper {

    /**
     * 获得该学生的所有Course
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select * from course")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    List<Course> listAllCourse();

    /**
     * 根据StudentId获得该学生的所有Course
     * @param studentId
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select * from course where id " +
            "in(select course_id from klass_student where student_id=#{studentId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    List<Course> listByStudentId(@Param("studentId") Long studentId);

    /**
     * 根据StudentId获得该学生的所有Course
     * @param studentId
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select id from course where id " +
            "in(select course_id from klass_student where student_id=#{studentId})")
    List<Long> listCourseIdByStudentId(@Param("studentId") Long studentId);

    /**
     * 根据TeacherId获得老师的所有Course
     * @param teacherId
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select * from course where teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    List<Course> listByTeacherId(@Param("teacherId") Long teacherId);

    /**
     * 根据CourseId获得Course
     * @param courseId
     * @return cm.entity.Course
     */
    @Select("select * from course where id=#{courseId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    Course getByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据TeamMainCourseId获得course 得到所有的该Team分享的从课程
     * @param courseId
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select * from course where team_main_course_id=#{courseId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    List<Course> listByTeamMainCourseId(@Param("courseId") Long courseId);

    /**
     * 根据SeminarMainCourseId获得course 得到所有的该Seminar分享的从课程
     * @param courseId
     * @return java.util.List<cm.entity.Course>
     */
    @Select("select * from course where seminar_main_course_id=#{courseId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    List<Course> listBySeminarMainCourseId(@Param("courseId") Long courseId);

    /**
     * 根据TeahcerId与CourseId获得course 确认自己能否查看此课程
     * @param courseId
     * @param teacherId
     * @return cm.entity.Course
     */
    @Select("select * from course where id=#{courseId} and teacher_id=#{teacherId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    Course getByCourseIdAndTeacherId(@Param("courseId") Long courseId,
                                     @Param("teacherId") Long teacherId);

    /**
     * 根据TeahcerId与KlassId获得course 确认自己能否查看此课程
     * @param klassId
     * @param teacherId
     * @return cm.entity.Course
     */
    @Select("select * from course where teacher_id=#{teacherId} and id " + "in(select course_id from klass where id =#{klassId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    Course getByKlassIdAndTeacherId(@Param("klassId") Long klassId,
                                    @Param("teacherId") Long teacherId);

    /**
     *  根据KlassId获得Course
     * @param klassId
     * @return cm.entity.Course
     */
    @Select("select * from course where id " +
            "in(select course_id from klass where id =#{klassId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "teacherId", column = "teacher_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "presentationPercentage", column = "presentation_percentage"),
            @Result(property = "questionPercentage", column = "question_percentage"),
            @Result(property = "reportPercentage", column = "report_percentage"),
            @Result(property = "teamStartTime", column = "team_start_time"),
            @Result(property = "teamEndTime", column = "team_end_time"),
            @Result(property = "teamMainCourseId", column = "team_main_course_id"),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id"),
            @Result(property = "rounds", column = "id", many = @Many(select = "cm.mapper.RoundMapper.listByCourseId", fetchType = FetchType.LAZY)),
            @Result(property = "klasses", column = "id", many = @Many(select = "cm.mapper.KlassMapper.listByCourseId", fetchType = FetchType.LAZY))
    })
    Course getByKlassId(@Param("klassId") Long klassId);
    /**
     * 根据CourseId获得TeacherId
     * @param courseId
     * @return java.lang.Long
     */
    @Select("select teacher_id from course where id=#{courseId}")
    Long getTeacherIdByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据CourseId删除课程
     * @param courseId
     * @return int
     */
    @Delete("delete from course where id = #{courseId}")
    int deleteByCourseId(@Param("courseId") Long courseId);


    /**
     * 根据teacherId创建课程
     * @param teacherId
     * @param course
     * @return int
     */
    @Insert("insert into course(teacher_id,course_name,introduction," +
            "presentation_percentage,question_percentage,report_percentage," +
            "team_start_time,team_end_time) " +
            "values(#{teacherId},#{course.courseName},#{course.introduction}" +
            ",#{course.presentationPercentage},#{course.questionPercentage}," +
            "#{course.reportPercentage},#{course.teamStartTime},#{course.teamEndTime})")
    @Options(useGeneratedKeys = true, keyProperty = "course.id")
    int createCourse(@Param("teacherId") Long teacherId,
                     @Param("course") Course course);
}