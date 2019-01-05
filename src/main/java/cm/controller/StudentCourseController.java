package cm.controller;

import cm.service.CourseService;
import cm.service.StudentService;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cm/student/course")
public class StudentCourseController {
    @Autowired
    private CourseService courseService;
@Autowired
        private StudentService studentService;
    UserVO student=new UserVO();


    ///////student course list get
    @RequestMapping(value = "/courselist",method= RequestMethod.GET)
    public String studentCourse(Model model,String account){
        student=studentService.getUserVOByAccount(account);
        Map<String, KlassVO> maps=courseService.listCourseAndKlassByStudentId(student.getId());
        model.addAttribute("courseAndKlassList",maps);
        return "studentCourse";
    }

    ///////student course info post
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String studentCourseInfo(long courseId,Model model){
        int min;
        int max;
        TeamNeedVO teamNeedVO=courseService.getTeamStrategyByCourseId(courseId);
        List<TeamAndStrategyVO> teamAndStrategyVOList=teamNeedVO.getTeamAndStrategyVOList();
        for(TeamAndStrategyVO teamAndStrategyVO:teamAndStrategyVOList){
          if(teamAndStrategyVO.getMemberLimitStrategyVO().getMinMember()!=null)
              min=teamAndStrategyVO.getMemberLimitStrategyVO().getMinMember();
            if(teamAndStrategyVO.getMemberLimitStrategyVO().getMaxMember()!=null)
                max=teamAndStrategyVO.getMemberLimitStrategyVO().getMaxMember();
        }
        model.addAttribute("curCourse",courseService.getCourseById(courseId));
        return "student_course_info";
    }

    ///////student course score Map<RoundName,SeminarScore>
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public String studentScore(long klassId, Model model){
        System.out.println(student.getId());
        List<RoundScoreVO> roundScoreVOList=courseService.listScoreForStudent(klassId,student.getId());
        if(roundScoreVOList==null) {
            System.out.println("are you empty");
            model.addAttribute("scoreDetails", null);
        }
        else
            model.addAttribute("scoreDetails", roundScoreVOList);
        return "studentScore";
    }
}
