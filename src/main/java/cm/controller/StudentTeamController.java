package cm.controller;

import cm.entity.Klass;
import cm.service.CourseService;
import cm.service.KlassService;
import cm.service.StudentService;
import cm.service.TeamService;
import cm.vo.CourseDetailVO;
import cm.vo.KlassVO;
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

import javax.jws.WebParam;
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

    UserVO student=StudentController.student;
    CourseDetailVO courseDetailVO;

    //////student team list
    @RequestMapping(value="/teamList",method= RequestMethod.GET)
    public String studentTeam(Long klassId,String account,Model model){
        courseDetailVO=courseService.getCourseByKlassId(klassId);
        KlassVO klass=klassService.getKlassById(klassId);
        student= studentService.getUserVOByAccount(account);
        if(teamService.getMyTeam(courseDetailVO.getId(),student.getId())==null)
            model.addAttribute("myTeam",null);
        else
        model.addAttribute("myTeam",teamService.getMyTeam(courseDetailVO.getId(),student.getId()));

        System.out.println(teamService.getMyTeam(courseDetailVO.getId(),student.getId()));

        model.addAttribute("course",courseDetailVO);
        model.addAttribute("klass",klass);
        model.addAttribute("teamList",teamService.listTeamByCourseId(courseDetailVO.getId()));
        model.addAttribute("studentsNotInTeam",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        return "student_teams";
    }

    //////student my team
    @RequestMapping(value = "/myteam",method = RequestMethod.GET)
    public String studentMyTeam(Model model,Long id){
        TeamVO tmp=teamService.getVOByTeamId(id);
        if(tmp==null)
            model.addAttribute("myTeam",null);
        else
        model.addAttribute("myTeam",tmp);

        model.addAttribute("account",student.getAccount());
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        if(student.getId().equals(tmp.getLeader().getId()))
            return "student_myteam_leader";
        else
            return "studnet_myteam_member";
    }


    ///////student my team quit
    @RequestMapping(value="/myteam/quit",method = RequestMethod.GET)
    public String studentTeamQuit(Long teamId,Model model){
        teamService.quitTeam(teamId,student.getId());
        model.addAttribute("curStudent",student);
        return "student_index";
    }

    ///////student create team

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String studentTeamCreate(Model model){
        System.out.println(courseDetailVO.getId()+"  "+student.getId());
        model.addAttribute("course",courseDetailVO);
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        KlassVO klass=klassService.getKlassById(klassService.getKlassByStudentIdCourseId(student.getId(),courseDetailVO.getId()));
        model.addAttribute("klass",klass);
        return "student_team_create";
    }

    ///////student create team
    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentTeamCreate(String teamName, List<String> studentNum){
        KlassVO klass=klassService.getKlassById(klassService.getKlassByStudentIdCourseId(student.getId(),courseDetailVO.getId()));
        teamService.createTeam(teamName,klass.getKlassId(),studentNum);
        return new ResponseEntity(HttpStatus.OK);
    }

    //////student delete member-leader
    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    public String studentTeamDeleteMember(Model model,String studentNum){
        Long studentId=studentService.getStudentByAccount(studentNum).getId();
        Long teamId=teamService.getMyTeam(courseDetailVO.getId(),studentId).getTeamId();
        teamService.deleteMember(student.getId(),teamId,studentNum);
        TeamVO tmp=teamService.getVOByTeamId(teamId);
        model.addAttribute("myTeam",tmp);
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        return "student_myteam_leader";
    }

    /////student add member leader
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String studentTeamAdd(String account,Model model){
        Long studentId=studentService.getStudentByAccount(account).getId();//要加的人
        Long teamId=teamService.getMyTeam(courseDetailVO.getId(),student.getId()).getTeamId();
        teamService.addMember(studentId,teamId);
        TeamVO tmp=teamService.getVOByTeamId(teamId);
        model.addAttribute("myTeam",tmp);
        model.addAttribute("studentList",studentService.getStudentNotInTeam(courseDetailVO.getId(),student.getId()));
        return "student_myteam_leader";
    }

    /**
     * 遣散小组
     * @param teamId
     * @return
     */
    //////student disband team leader
    @RequestMapping(value = "/disband",method = RequestMethod.GET)
    public String studentTeamDisband(Long teamId,Model model){
        teamService.teamDisband(teamId);
        student=studentService.getUserVOByAccount(studentService.getByStudentId(student.getId()).getAccount());
        model.addAttribute("curStudent",student);
        return "student_index";
    }

    //搜索成员
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public UserVO studentSearch(String studentAccount){
        return teamService.searchStudent(studentAccount);
    }
}
