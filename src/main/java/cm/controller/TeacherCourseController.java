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

    CourseDetailVO courseDetailVO;
    UserVO userVO=new UserVO();

    //课程管理
    @RequestMapping(value = "/courselist",method= RequestMethod.GET)
    public String teacherCourseManage(Model model, String account) {
        System.out.println("我的课程");
        userVO= teacherService.getUserVOByAccount(account);
        model.addAttribute("courseList", courseService.listCourseByTeacherId(userVO));
        return "teacher_courseList";
    }

    /////////////////////////////////////课程详情页
    @RequestMapping(value="/info",method = RequestMethod.POST)
    public String teacherCourseInfo(Long courseId,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        model.addAttribute("curCourse",courseDetailVO);
        return "teacher_courseInfo";
    }

    ////////////////////////////////创建课程
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String teacherCourseCreate(Model model){
        model.addAttribute("courseList",courseService.listAllCourse());
        return "teacher_course_create";
    }
////////////////////////////////创建课程
    @RequestMapping(value = "/create",method = RequestMethod.PUT,consumes ="application/json" )
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
    @RequestMapping(value="/teamList",method = RequestMethod.POST)
    public String teacherTeamList(Long courseId,Model model){
        courseDetailVO=courseService.getCourseById(courseId);
        model.addAttribute("teamList",teamService.listTeamByCourseId(courseId));
        return "teacher_teamList";
    }

    ///////////共享情况
    @RequestMapping(value = "/share",method = RequestMethod.GET)
    public String teacherShare(Model model){
        model.addAttribute("shareCourseList",shareService.listByTeacherIdAndCourseId(userVO.getId(),courseDetailVO.getId()));
        return "teacher_share";
    }

    ///////////创建共享
    @RequestMapping(value = "/shareCreate",method = RequestMethod.GET)
    public String teacherShareCreate(Model model){
        model.addAttribute("courseList",courseService.listAllCourse());
        return "teacher_create_share";
    }

    @RequestMapping(value = "/shareCreate",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherShareCreateSubmit(Long shareCourseId){
        courseService.createShare(shareCourseId,courseDetailVO.getId());
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
    @RequestMapping(value="/klassList",method = RequestMethod.POST)
    public String teacherKlassManage(Long course_id,Model model){
        courseDetailVO=courseService.getCourseById(course_id);

        model.addAttribute("klassList",klassService.listKlassByCourseId(course_id));
        return "teacher_klassList";
    }

    //////////创建班级
    @RequestMapping(value = "/klass/create",method = RequestMethod.GET)
    public String teacherKlassCreate(){
        return "teacher_klass_create";
    }

    @RequestMapping(value = "/klass/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherKlassCreateSubmit(@RequestBody KlassVO klassVO){
        if(klassService.addKlass(klassVO,courseDetailVO))
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
