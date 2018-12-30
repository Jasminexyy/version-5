package cm.controller;

import cm.entity.*;
import cm.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/cm/teacher")
public class TeacherController {
    TeacherService teacherService=new TeacherService();

    @RequestMapping(value = "/activation",method = RequestMethod.GET)
    public String teacherActivation() {
        return "teacher_activation";
    }

    @RequestMapping(value = "/activation",method = RequestMethod.POST)
    public String teacherActivationSubmit() {
        teacherService.activate()
        return "redirect:cm/teacher/index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String teacherIndex(Model model) {
        if(teacher.getIs_active()==0)
            return "redirect:/cm/teacher/activation";
        return "teacher_main";
    }
    //////////////////////////////////////账户设置
    @RequestMapping(value="/setting",method= RequestMethod.GET)
    public String teacherAccountSet(Model model) {
        model.addAttribute("curUser",teacher);
        return "teacher_setting";
    }

    //修改邮箱按钮
    @RequestMapping(value="/setting/modifyEmail",method=RequestMethod.GET)
    public String teacherModifyEmail(){
        return "modify_email";
    }

    ////////////////////////////修改邮箱提交待修改
    @RequestMapping(value="/setting/modifyEmail",method=RequestMethod.POST)
    @ResponseBody
    public String teacherModifyEmailSubmit(String email){
        if(teacherService.modifyEmail(teacher,email))
            return "修改成功 HttpStatus(200)";
        else
            return "修改失败 HttpStatus(400)";
    }

    /////////////////////////////////修改账户密码按钮
    @RequestMapping(value="/setting/modifyPwd",method = RequestMethod.GET)
    public String teacherModifyPwd(){
        return "modify_pwd";
    }

    ////////////////////////////////修改账户密码提交
    @RequestMapping(value="/setting/modifyPwd",method=RequestMethod.POST)
    @ResponseBody
    public void teacherModifyPwdSubmit(HttpServletResponse response, String password)throws IOException {
        if(teacherService.modifyPwd(teacher,password)){
            response.setStatus(200);
            response.getWriter().append("修改成功");
        }
        else{
            response.setStatus(409);
            response.getWriter().append("修改失败");
        }
    }

    /////////////////////////////////////通知页面
    @RequestMapping(value="/notification",method = RequestMethod.GET)
    public String teacherNotification(){
        return "teacher_notification";
    }


    }




}
