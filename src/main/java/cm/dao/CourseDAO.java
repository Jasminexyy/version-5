package cm.dao;

import cm.entity.Course;
import cm.mapper.CourseMapper;
import cm.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Component
public class CourseDAO {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    public List<Course> listByStudentId(Long studentId) {
        return courseMapper.listByStudentId(studentId);
    }

    public List<Course> listByTeacherId(Long teacherId){
        return courseMapper.listByTeacherId(teacherId);
    }

    public Course getByCourseId(Long courseId){
        return courseMapper.getByCourseId(courseId);
    }

    public List<Course> listBySeminarMainCourseId(Long seminarMainCourseId){
        return courseMapper.listBySeminarMainCourseId(seminarMainCourseId);
    }

    public List<Course> listByTeamMainCourseId(Long teamMainCourseId){
        return courseMapper.listByTeamMainCourseId(teamMainCourseId);
    }

    public Course getByCourseIdAndTeacherId(Long courseId, Long teacherId){
        return courseMapper.getByCourseIdAndTeacherId(courseId, teacherId);
    }

    public Course getByKlassIdAndTeacherId(Long klassId, Long teacherId){
        return courseMapper.getByKlassIdAndTeacherId(klassId, teacherId);
    }

    public Long getTeacherIdByCourseId(Long courseId){
        return courseMapper.getTeacherIdByCourseId(courseId);
    }

    public int deleteByCourseId(Long courseId){
        return courseMapper.deleteByCourseId(courseId);
    }

    public int createCourse(Long teacherId, Course course){
        return courseMapper.createCourse(teacherId,course);
    }

    public List<Course> listAllCourse() {
        return courseMapper.listAllCourse();
    }
}

