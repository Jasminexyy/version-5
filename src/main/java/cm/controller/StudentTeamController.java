package cm.controller;

import cm.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cm/student/course/team")
public class StudentTeamController {
    TeamService teamService=new TeamService();

    //////student team list
    @RequestMapping(value="",method= RequestMethod.POST)
    public String studentTeam(long courseId, Model model){
        teamService.setCourse(courseId);//存储当前进入的课程
        model.addAttribute("teamList",teamService.findTeamsByCourseId(courseId));
        model.addAttribute("studentsNotInTeam",teamService.findStudentsNotInTeam(courseId));
        return "student_teams";
    }

    //////student my team
    @RequestMapping(value = "/myteam",method = RequestMethod.GET)
    public String studentMyTeam(Model model){
        model.addAttribute("myTeam",teamService.findMyTeam());
        return "student_myteam";
    }

    ///////student my team quit
    @RequestMapping(value="/myteam/quit",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamQuit(long teamId,long studentId){
        teamService.quitTeam(teamId,studentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    ///////student create team
    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamCreate(String teamName, long classId, List<String> studentNum){
        teamService.createTeam(teamName,classId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //////student delete member leader
    @RequestMapping(value = "/delete",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity studentTeamDeleteMember(long teamId,String studentNum){
        teamService.deleteMember(teamId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    /////student add member leader
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamAdd(long teamId,List<String> studentNum){
        teamService.addMember(teamId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //////student disband team leader
    @RequestMapping(value = "/disband",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamDisband(long teamId,List<String> studentNum){
        teamService.teamDisband(team,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //搜索成员
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public Student studentSearch(String studentId){
        return teamService.searchStudent(studentId);
    }
}
