package cm.controller;

import cm.entity.Round;
import cm.entity.Seminar;
import cm.service.KlassService;
import cm.service.RoundService;
import cm.service.SeminarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cm/teacher/course/seminar")
public class TeacherSeminarController {
    SeminarService seminarService=new SeminarService();
    RoundService roundService=new RoundService();
    KlassService klassService=new KlassService();
    ///////////讨论课列表
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String teacherSeminar(Model model){
        long courseId;//此处从security获取当前课程Id
        model.addAttribute("courseId",courseId);
        model.addAttribute("roundList",roundService.findRoundsByCourseId(courseId));
        model.addAttribute("klassList",klassService.findKlassesByCourseId(courseId));

        return "teacher_seminarList";
    }

    ////////////////////获取轮次设置
    @RequestMapping(value = "/setting/{round_id}",method = RequestMethod.POST)
    public String teacherSeminarSetting(@PathVariable long round_id){
        return "roundSetting";
    }

    //////////////////修改轮次设置
    @RequestMapping(value = "/setting",method = RequestMethod.PATCH,consumes = "application/json")
    @ResponseBody
    public void teacherSeminarSettingSubmit(HttpServletResponse response, @RequestBody Round round)throws IOException {
        long roundId;//此处应该从security获取
        if(roundService.updateRound(roundId,round)){
            response.setStatus(200);
        }
    }

    ////////////////////创建讨论课
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public void teacherSeminarCreate(@RequestBody Seminar seminar, HttpServletResponse response)throws IOException{
        if(seminarService.addSeminar(seminar)){
            response.setStatus(200);
            response.getWriter().append("新建成功");
        }
    }

    //////////讨论课详情
    @RequestMapping(value = "/info/{klassId}/{seminarId}",method = RequestMethod.GET)
    public String teacherSeminar(@PathVariable long klassId,@PathVariable long seminarId,Model model){
        model.addAttribute("klassSeminar",seminarService.findKlassSeminarById(klassId,seminarId));
        return "teacher_seminarInfo";
    }

    /////////讨论课修改
    @RequestMapping(value="",method = RequestMethod.PATCH,consumes = "application/json")
    @ResponseBody
    public void teacherSeminarUpdate(@RequestBody Seminar seminar,HttpServletResponse response){
        long seminarId;
        if(seminarService.updateSeminar(seminarId,seminar)){
            response.setStatus(200);
        }
    }

    ///////////////删除讨论课
    @RequestMapping(value = "/{seminarId}",method = RequestMethod.DELETE)
    @ResponseBody
    public void teacherSeminarDelete(@PathVariable long seminarId,HttpServletResponse response){
        if(seminarService.deleteSeminarById(seminarId)){
            response.setStatus(200);
        }
    }

    ///////////////讨论课报名列表
    @RequestMapping(value = "/enrollList",method = RequestMethod.GET)
    public String teacherSeminarEnrollList(Model model){
        long klassSeminarId;
        model.addAttribute("enrollList",seminarService.findAttendanceById(klassSeminarId));
        return "teacher_seminar_enrollList";
    }

    ///////////////////讨论课正在进行websocket
    @RequestMapping(value = "/progressing",method = RequestMethod.GET)
    public String teacherSeminarProgressing(Model model){
        long klassSeminarId;
        model.addAttribute("enrollList",seminarService.findAttendanceById(klassSeminarId));
        return "teacher_seminar_progressing";
    }

    //////讨论课结束报告页
    @RequestMapping(value = "/finished",method = RequestMethod.GET)
    public String teacherSeminarFinished(Model model){
        long klassSeminarId;
        model.addAttribute("enrollList",seminarService.findAttendanceById(klassSeminarId));
        return "teacher_seminar_finished";
    }

    /////////////////讨论课分数
    @RequestMapping(value ="/grade",method = RequestMethod.GET)
    public String teacherSeminarGrade(Model model){
        long klassSeminarId;
        model.addAttribute("score",seminarService.findSeminarScoreById(klassSeminarId));
        return "teacher_seminar_grade";
    }
}
