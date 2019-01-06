package cm.controller;

import cm.service.*;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cm/student/seminar")
public class StudentSeminarController {
    @Autowired
    private SeminarService seminarService;
    @Autowired
    private RoundService roundService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private KlassSeminarService klassSeminarService;

    UserVO student;

    public static CourseDetailVO courseDetailVO;

        //////student course List
    @RequestMapping(value = "/seminarEntrance/{account}",method = RequestMethod.GET)
    public String studentSeminarEntrance(Model model, @PathVariable String account){
        student= studentService.getUserVOByAccount(account);
        Map<String, KlassVO>maps=seminarService.listCourseAndKlass(student);
        model.addAttribute("courseAndKlassList",maps);
        return "student_seminar_entrance";
    }

    /////////student seminar List
    @RequestMapping(value = "/List/{klassId}",method=RequestMethod.GET)
    public String studentSeminarList(@PathVariable Long klassId,Model model){
        System.out.println(klassId);
        Long courseId=courseService.getCourseByKlassId(klassId).getId();
        //String--RoundName
        Map<String, SeminarListVO> maps=roundService.listRoundNameAndSeminar(courseId,klassId);
        model.addAttribute("roundAndSeminarList",maps);
        model.addAttribute("klassId",klassId);
        model.addAttribute("student",student);
        return "student_seminarList";
    }

    ///////student seminar info
    @RequestMapping(value = "/info/{klassId}/{seminarId}",method = RequestMethod.GET)
    public String studentSeminarInfo(@PathVariable Long klassId,@PathVariable Long seminarId,Model model){
        Long courseId=courseService.getCourseByKlassId(klassId).getId();
        String courseName=courseService.getCourseById(courseId).getCourseName();
        model.addAttribute("courseName",courseName);
        model.addAttribute("klassId",klassId);
        model.addAttribute("seminarInfo",seminarService.getSeminarInfo(klassId,seminarId));
        model.addAttribute("attendance",seminarService.getAttendance(klassId,seminarId,student.getId()));
        return "student_seminar_info";
    }

    //////student enroll List
    @RequestMapping(value = "/enrollList/{klassId}/{seminarId}",method = RequestMethod.GET)
    public String studentSeminarEnrollList(Model model, @PathVariable Long klassId, @PathVariable Long seminarId){
        System.out.println(klassId);
        System.out.println(seminarId);
        Long klassSeminarId=klassSeminarService.getKlassSeminarIdByEach(klassId,seminarId);
        System.out.println("hhhh"+klassSeminarId);
        SeminarInfoVO seminarInfoVO=seminarService.getSeminarInfo(klassSeminarId);
        model.addAttribute("seminarInfo",seminarInfoVO);
        model.addAttribute("team",teamService.getMyTeam(courseDetailVO.getId(),student.getId()));
        return "student_enrolling";
    }


    //////student enroll
    @RequestMapping(value = "/enrollList/enroll",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentSeminarEnroll(Long klassSeminarId, AttendanceVO attendance){
        if(seminarService.enroll(klassSeminarId,attendance,student.getId()))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    //////student enroll cancel
    @RequestMapping(value = "/enroll",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity studentSeminarUnEnroll(Long klassSeminarId){
        if(seminarService.unenroll(klassSeminarId,student.getId()))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

//    //////student ppt getPPT
//    @RequestMapping(value = "/PPT",method=RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity studentSeminarPPT(Long klassSeminarId,String PPTurl){
//        if(seminarService.getPPT(klassSeminarId,PPTurl))
//            return new ResponseEntity(HttpStatus.OK);
//        else
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }

    /////student ppt upload
    @RequestMapping(value = "/PPT",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentSeminarPPTUpload(Long klassSeminarId, MultipartFile file,AttendanceVO attendance){
        if(seminarService.uploadPPT(klassSeminarId,file,attendance))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /**
     * 学生-讨论课-查看成绩
     * @param klassSeminarId
     * @param klassId
     * @param model
     * @return
     */
    //////student seminar score
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public String studentSeminarScore(Long klassSeminarId,Long klassId,Model model){
        model.addAttribute("seminarScore",seminarService.getSeminarScore(klassId,klassSeminarId,student.getId()));
        return "student_seminar_grade";
    }

    //////student enter seminar
    @RequestMapping(value = "/progressing",method = RequestMethod.GET)
    public String studentSeminarProgress(Long klassSeminarId,Model model){
        model.addAttribute("seminarInfo",seminarService.getSeminarInfo(klassSeminarId));
        model.addAttribute("teamId",seminarService.getPresentatingTeamId(klassSeminarId));
        model.addAttribute("myId",student.getId());
        model.addAttribute("klassSeminarId",klassSeminarId);
        return "student_seminar_progress";
    }
}
