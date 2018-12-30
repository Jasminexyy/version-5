package cm.controller;

import cm.entity.Student;
import cm.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


@Controller
@RequestMapping("/cm/student")
public class StudentController {
    StudentService studentService=new StudentService();

    ///////////////student activation get
    @RequestMapping(value = "/activation",method = RequestMethod.GET)
    public String studentActive(){
        return "student_activation";
    }

    /////////////student activation submit
    @RequestMapping(value="/activation",method = RequestMethod.POST)
    public String studentActivationSubmit(String password,String email){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        User user = (User) auth.getPrincipal();

        user.getUsername();
        studentService.activate(user);
        return "redirect:/cm/student/index";
    }

    //////////////student index get
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String studentIndex(Model model){
        if (student.getIs_active()==0)
                return "redirect:/cm/student/activation";
        else{
            model.addAttribute("curStudent",student);
            return "student_index";
        }
    }

    ////////////student setting get
    @RequestMapping(value = "/setting",method=RequestMethod.GET)
    public String studentSetting(Model model){
        model.addAttribute("curUser",student);
        return "student_setting";
    }

    //////////student setting modifypwd get
    @RequestMapping(value="/setting/modifyPwd",method = RequestMethod.GET)
    public String studentModifyPwd(){
        return "modify_pwd";
    }

    /////////student setting modifyPwd submit
    @RequestMapping(value = "/setting/modifyPwd",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentModifyPwdSubmit(String studentNum,String password){
        studentService.modifyStudentPwd(studentNum,password);
            return new ResponseEntity(HttpStatus.OK);
    }

    ///////student setting modifyEmail get
    @RequestMapping(value = "/setting/modifyEmail",method = RequestMethod.GET)
    public String studentModifyEmail(){
        return "modify_email";
    }

    //////student setting modifyEmail submit
    @RequestMapping(value = "/setting/modifyEmail",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity studentModifyEmail(String studentNum, String email){
        studentService.modifyStudentEmail(studentNum,email);
        return new ResponseEntity(HttpStatus.OK);
    }

   ///////student notification 需要修改
    @RequestMapping(value="/notification",method = RequestMethod.GET)
    public String teacherNotification(){
        return "student_notification";
    }
}
