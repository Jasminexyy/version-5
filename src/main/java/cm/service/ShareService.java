package cm.service;

import cm.dao.*;
import cm.entity.Course;
import cm.entity.Team;
import cm.vo.ShareCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2019/1/1
 */
@Service
public class ShareService {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private KlassDAO klassDAO;

    @Autowired
    private ShareTeamDAO shareTeamDAO;

    public List<ShareCourseVO> listByTeacherIdAndCourseId(Long teacherId,Long courseId){
        List<ShareCourseVO>result = new ArrayList<>();
        Course c=courseDAO.getByCourseIdAndTeacherId(courseId,teacherId);
        if (c == null) {
            return null;
        }

        if (c.getTeamMainCourseId() != null) {
            Course teamMainCourse = courseDAO.getByCourseId(c.getTeamMainCourseId());
            if (teamMainCourse != null) {
                ShareCourseVO shareCourseVO = new ShareCourseVO(1,
                        1,
                        teamMainCourse.getId(),
                        teamMainCourse.getCourseName(),
                        teacherDAO.getByCourseId(teamMainCourse.getId()).getTeacherName());
                result.add(shareCourseVO);
            }
        }
   /*     if (c.getSeminarMainCourseId() != null) {
            Course seminarMainCourse = courseDAO.getByCourseId(c.getSeminarMainCourseId());
            if (seminarMainCourse != null) {
                ShareCourseVO shareCourseVO = new ShareCourseVO(1,
                        2,
                        seminarMainCourse.getId(),
                        seminarMainCourse.getCourseName(),
                        teacherDAO.getByCourseId(seminarMainCourse.getId()).getTeacherName());
                result.add(shareCourseVO);
            }
        }*/
        List<Course> courseListTeam= courseDAO.listByTeamMainCourseId(courseId);
        for (Course course:courseListTeam) {
            ShareCourseVO shareCourseVO = new ShareCourseVO(2,
                    1,
                    course.getId(),
                    course.getCourseName(),
                    teacherDAO.getByCourseId(course.getId()).getTeacherName());
            result.add(shareCourseVO);
        }

        List<Course> courseListSeminar = courseDAO.listBySeminarMainCourseId(courseId);
        for (Course course:courseListSeminar) {
            ShareCourseVO shareCourseVO = new ShareCourseVO(2,
                    2,
                    course.getId(),
                    course.getCourseName(),
                    teacherDAO.getByCourseId(course.getId()).getTeacherName());
            result.add(shareCourseVO);
        }
        return result;
    }

    public void createShareTeam(Long mainCourseId,Long subCourseId){
        Long subCourseTeacherId=courseDAO.getTeacherIdByCourseId(subCourseId);
        List<Team> sourceTeamList = teamDAO.listByCourseId(mainCourseId);
        for(Team tempTeam: sourceTeamList)
        {
           // System.out.println(subCourseId);
            tempTeam.setCourseId(subCourseId);
            Long leaderId = tempTeam.getLeaderId();
            tempTeam.setKlassId(klassDAO.getKlassIdByStudentIdAndCourseId(leaderId,mainCourseId));

            teamDAO.createTeam(tempTeam);
        }
            Byte by = new Byte("1");
            shareTeamDAO.createShareTeamApplication(mainCourseId,subCourseId,subCourseTeacherId,by);

    }

}
