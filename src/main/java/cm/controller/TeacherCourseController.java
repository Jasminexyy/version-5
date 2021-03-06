package cm.controller;

import cm.service.*;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cm/teacher/course")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private KlassService klassService;
    @Autowired
            private TeacherService teacherService;
    @Autowired
            private ShareService shareService;
@Autowired
        private StudentService studentService;
    CourseDetailVO courseDetailVO;
    UserVO userVO=new UserVO();
    TeamNeedVO teamNeedVO;


    //课程管理
    @RequestMapping(value = "/courselist",method= RequestMethod.GET)
    public String teacherCourseManage(Model model, String account) {
        System.out.println("my courselist");
        userVO= teacherService.getUserVOByAccount(account);
        model.addAttribute("courseList", courseService.listCourseByTeacherId(userVO));
//        model.addAttribute("teacherId",userVO);
        return "teacher_courseList";
    }

    /////////////////////////////////////课程详情页
    @RequestMapping(value="/info",method = RequestMethod.GET)
    public String teacherCourseInfo(Long courseId,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        teamNeedVO=courseService.getTeamStrategyByCourseId(courseId);
        model.addAttribute("teamNeedVO",teamNeedVO);
        model.addAttribute("curCourse",courseDetailVO);
        return "teacher_courseInfo";
    }

    ////////////////////////////////创建课程
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String teacherCourseCreate(Model model){
        System.out.println("why create");
        model.addAttribute("courseList",courseService.listAllCourse());
        return "teacher_course_create";
    }
//////////////////////////////创建课程
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherCourseCreateSubmit(@RequestBody CourseDetailVO course){
        if(courseService.addCourse(course,userVO))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    //////////////////////////////删除课程
    @RequestMapping(value = "/{courseId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity teacherCourseDelete(@PathVariable long courseId){
        courseService.deleteCourseById(courseId);
            return new ResponseEntity(HttpStatus.OK);
    }

//    //////////////////修改课程
//    @RequestMapping(value = "",method = RequestMethod.PATCH)
//    @ResponseBody
//    public ResponseEntity teacherCourseUpdate(@RequestBody CourseVO course){
//        courseService.updateCourse(course);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    //////////////学生组队
    @RequestMapping(value="/teamList",method = RequestMethod.GET)
    public String teacherTeamList(Long courseId,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        model.addAttribute("teamList",teamService.listTeamByCourseId(courseId));
        return "teacher_check_group";
    }

    ///////////共享情况
    @RequestMapping(value = "/share",method = RequestMethod.GET)
    public String teacherShare(Model model,Long courseId){
        System.out.println("我进来了嘛？");
        System.out.println(userVO.getId());
        System.out.println(courseId);
        courseDetailVO=courseService.getCourseById(courseId);

        model.addAttribute("courseId",courseId);
        model.addAttribute("shareCourseList",shareService.listByTeacherIdAndCourseId(userVO.getId(),courseId));
        return "teacher_share";
    }

    ///////////创建共享
    @RequestMapping(value = "/shareCreate",method = RequestMethod.GET)
    public String teacherShareCreate(Model model,Long courseId){
        model.addAttribute("courseId",courseId);
        model.addAttribute("courseList",courseService.listAllCourse());
        return "teacher_create_share";
    }

    @RequestMapping(value = "/shareCreate",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherShareCreateSubmit(Long shareCourseId){
        System.out.println("我进来了");
        Long mainCourseId=courseDetailVO.getId();
        System.out.println(mainCourseId);
        shareService.createShareTeam(mainCourseId,shareCourseId);
        return new ResponseEntity(HttpStatus.OK);
    }



    //////学生成绩
    @RequestMapping(value = "/grade",method = RequestMethod.GET)
    public String teacherGrade(Long courseId,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        Map<String, List<TeacherSeminarScoreVO>> maps=courseService.listScoreForTeacher(courseId);
        model.addAttribute("roundNameAndSeminarScore",maps);
        return "teacher_grade";
    }

    ////////班级管理
    @RequestMapping(value="/klassList",method = RequestMethod.GET)
    public String teacherKlassManage(Long course_id,Model model){
        System.out.println("进来了");
        courseDetailVO=courseService.getCourseById(course_id);
        model.addAttribute("klassList",klassService.listKlassByCourseId(course_id));
        return "teacher_klassList";
    }

    @RequestMapping(value = "/klass/studentList",method = RequestMethod.GET)
    public String teacherKlassStudentList(Long klassId,Model model){
        model.addAttribute("studentList",studentService.listStudentByKlassId(klassId));
        return "teacher_klass_studentList";
    }
    //////////创建班级
    @RequestMapping(value = "/klass/create",method = RequestMethod.GET)
    public String teacherKlassCreate(){
        System.out.println("为什么会400");
        return "teacher_klass_create";
    }

    @RequestMapping(value = "/klass/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherKlassCreateSubmit(@RequestBody NewKlassVO klassVO,@RequestBody MultipartFile multipartFile){
        if(klassService.addKlass(klassVO,courseDetailVO,multipartFile))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    ////////删除班级
    @RequestMapping(value = "/klass/{klassId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity teacherKlassDelete(@PathVariable long klassId) {
        klassService.deleteKlassById(klassId);
        return new ResponseEntity(HttpStatus.OK);
    }

    ///////提交学生名单
    @RequestMapping(value = "/klass",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherKlassStudentListUpload(Long klassId, MultipartFile fileUpload){
        if(klassService.uploadStudentList(klassId,fileUpload)){
            return new ResponseEntity(HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }
}
