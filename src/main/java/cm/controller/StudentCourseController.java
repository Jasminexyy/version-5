package cm.controller;

import cm.entity.*;
import cm.service.CourseService;
import cm.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cm/student/course")
public class StudentCourseController {
    CourseService courseService=new CourseService();

    ///////student course list get
    @RequestMapping(value = "",method= RequestMethod.GET)
    public String studentCourse(Model model){
        List<Map<Course, Klass>> maps=courseService.findCourseAndKlassByStudentId(studentId);
        model.addAttribute("courseAndKlassList",maps);
        return "studentCourse";
    }

    ///////student course info post
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public String studentCourseInfo(long courseId,Model model){
        model.addAttribute("curCourse",courseService.findCourseById(courseId));
        return "student_course_info";
    }

    ///////student course score Map<RoundName,SeminarScore>
    @RequestMapping(value = "/score",method = RequestMethod.POST)
    public String studentScore(long courseId,long klassId,Model model){
        List<Map<String,SeminarScore>> maps=courseService.findScoreForStudent(courseId,klassId,studentId);
        model.addAttribute("scoreDetails",maps);
        return "studentScore";
    }
}
