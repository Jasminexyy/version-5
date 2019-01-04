package cm.controller;

import cm.service.CourseService;
import cm.service.KlassService;
import cm.service.StudentService;
import cm.service.TeamService;
import cm.vo.CourseDetailVO;
import cm.vo.TeamVO;
import cm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cm/student/course/team")
public class StudentTeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private KlassService klassService;
    @Autowired
    private StudentService studentService;

    UserVO student;
    CourseDetailVO courseDetailVO;

    //////student team list
    @RequestMapping(value="/{account}",method= RequestMethod.POST)
    public String studentTeam(Long courseId, @PathVariable  String account,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        student= studentService.getUserVOByAccount(account);
        model.addAttribute("teamList",teamService.listTeamByCourseId(courseId));
        model.addAttribute("studentsNotInTeam",studentService.getStudentNotInTeamByCourseId(courseId));
        return "student_teams";
    }

    //////student my team
    @RequestMapping(value = "/myteam",method = RequestMethod.GET)
    public String studentMyTeam(Model model){
        TeamVO tmp=teamService.getMyTeam(courseDetailVO.getId(),student.getId());
        model.addAttribute("myTeam",tmp);
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        if(student.getId()==tmp.getLeader().getId())
            return "student_myteam_leader";
        else
            return "studnet_myteam_member";
    }


    ///////student my team quit
    @RequestMapping(value="/myteam/quit",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamQuit(Long teamId,Long studentId){
        teamService.quitTeam(teamId,studentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    ///////student create team

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String studentTeamCreate(Model model){
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        return "student_team_create";
    }

    ///////student create team
    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamCreate(String teamName, Long classId, List<String> studentNum){
        teamService.createTeam(teamName,classId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //////student delete member-leader
    @RequestMapping(value = "/delete",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity studentTeamDeleteMember(Long teamId,String studentNum){
        teamService.deleteMember(student.getId(),teamId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    /////student add member leader
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamAdd(Long teamId,List<String> studentNum){
        teamService.addMember(student.getId(),teamId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 遣散小组
     * @param teamId
     * @param studentNum
     * @return
     */
    //////student disband team leader
    @RequestMapping(value = "/disband",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamDisband(Long teamId,List<String> studentNum){
        teamService.teamDisband(teamId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //搜索成员
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public UserVO studentSearch(String studentAccount){
        return teamService.searchStudent(studentAccount);
    }
}
