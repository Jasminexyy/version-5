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
    @RequestMapping(value="/teamList",method= RequestMethod.GET)
    public String studentTeam(Long klassId,String account,Model model){
        System.out.println("teamList");
        System.out.println(klassId);
        System.out.println(account);
        courseDetailVO=courseService.getCourseByKlassId(klassId);
        student= studentService.getUserVOByAccount(account);
        model.addAttribute("myTeam",teamService.getMyTeam(courseDetailVO.getId(),student.getId()));
        model.addAttribute("teamList",teamService.listTeamByCourseId(courseDetailVO.getId()));
        model.addAttribute("studentsNotInTeam",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        return "student_teams";
    }

    //////student my team
    @RequestMapping(value = "/myteam",method = RequestMethod.GET)
    public String studentMyTeam(Model model,Long id){
        TeamVO tmp=teamService.getVOByTeamId(id);
        model.addAttribute("myTeam",tmp);
        System.out.println("student id:"+student.getId());
        System.out.println("course id:"+courseDetailVO.getId());
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        if(student.getId().equals(tmp.getLeader().getId()))
            return "student_myteam_leader";
        else
            return "studnet_myteam_member";
    }


    ///////student my team quit
    @RequestMapping(value="/myteam/quit/{teamId}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamQuit(@PathVariable Long teamId){
        teamService.quitTeam(teamId,student.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    ///////student create team

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String studentTeamCreate(Model model){
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        model.addAttribute("klassId",klassService.getKlassByStudentIdCourseId(student.getId(),courseDetailVO.getId()));
        return "student_team_create";
    }

    ///////student create team
    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamCreate(String teamName, List<String> studentNum,Long klassId){
        teamService.createTeam(teamName,klassId,studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //////student delete member-leader
    @RequestMapping(value = "/delete/{studentNum}",method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity studentTeamDeleteMember(@PathVariable String studentNum){
        Long teamId=teamService.getMyTeam(courseDetailVO.getId(),student.getId()).getTeamId();
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
