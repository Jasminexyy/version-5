package cm.controller;

import cm.entity.Attendance;
import cm.entity.Course;
import cm.entity.Klass;
import cm.entity.Seminar;
import cm.service.RoundService;
import cm.service.SeminarService;
import cm.vo.CourseVO;
import cm.vo.KlassVO;
import cm.vo.SeminarVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cm/student/seminar")
public class StudentSeminarController {
    SeminarService seminarService=new SeminarService();
    RoundService roundService=new RoundService();

    //////student course List
    @RequestMapping(value = "/seminarEntrance",method = RequestMethod.GET)
    public String studentSeminarEntrance(Model model){
        List<Map<CourseVO, KlassVO>> maps=seminarService.findCourseAndKlass();
        model.addAttribute("courseAndKlassList",maps);
        return "student_seminar_entrance";
    }

    /////////student seminar List
    @RequestMapping(value = "/List",method=RequestMethod.GET)
    public String studentSeminarList(long courseID,long klassID,Model model){
        seminarService.setCourse();
        seminarService.setKlass();
        //String--RoundName
        List<Map<String, SeminarVO>> maps=ro   undService.findRoundAndSeminars(courseID,klassID);
        model.addAttribute("roundAndSeminarList",maps);
        return "student_seminarList";
    }

    ///////student seminar info
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String studentSeminarInfo(long klassID,long seminarID,Model model){
        model.addAttribute("courseName",seminarService.getCourse().getName());
        model.addAttribute("Seminar",seminarService.findSeminarById(seminarID))
        return "studnet_seminar_info";
    }

    @RequestMapping(value = "/enrollList",method = RequestMethod.GET)
    public String studentSeminarEnrollList(Model model,long klassSeminarID){
        model.addAttribute("klassSeminarID",);
        model.addAttribute("seminarStatus",);
        model.addAttribute("seminarName",);
        model.addAttribute("attendanceList",);
        model.addAttribute("attendance",);

        return "student_seminar_enrollList";
    }

    @RequestMapping(value = "/enrollList/enroll",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentSeminarEnroll(long klassSeminarId, Attendance attendance){
        if(seminarService.enroll(klassSeminarId,attendance))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/enroll",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity studentSeminarUnEnroll(long klassSeminarId){
        if(seminarService.unenroll(klassSeminarId))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/PPT",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity studentSeminarPPT(long klassSeminarId,String PPTurl){
        if(seminarService.getPPT(klassSeminarId,PPTurl))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public String studentSeminarScore(long klassSeminarID,long klassID,Model model){
        model.addAttribute("courseName",);
        model.addAttribute("score",);
        return "student_seminar_grade";
    }

    @RequestMapping(value = "/progressing",method = RequestMethod.GET)
    public String studentSeminarProgress(long klassSeminarId,Model model){
        model.addAttribute("teamList",);
        model.addAttribute("courseName",);
        model.addAttribute("seminarName",);
        return "student_seminar_progress";
    }
}
