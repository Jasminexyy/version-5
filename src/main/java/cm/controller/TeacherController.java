package cm.controller;

import cm.service.TeacherService;
import cm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cm/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    UserVO teacher;

    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    public String teacherActivation() {
        return "teacher_activation";
    }

    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    public String teacherActivationSubmit(String password, String password1) {
        if (teacherService.activate(password, password1,teacher)) {
            return "redirect:/login";
        }
        else
            return "redirect:/cm/teacher/activation";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String teacherIndex(Model model,String account) {
        System.out.println("teacherIndex");
        System.out.println(account);
        teacher=teacherService.getUserVOByAccount(account);
        if (teacherService.getIs_active(teacher) == 0)
            return "redirect:/cm/teacher/activation";
        else {
            model.addAttribute("curTeacher", teacher);
            return "teacher_index";
        }
    }

    //////////////////////////////////////账户设置
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String teacherAccountSet(Model model) {
        model.addAttribute("curUser", teacher);
        return "teacher_setting";
    }

    //修改邮箱按钮
    @RequestMapping(value = "/setting/modifyEmail", method = RequestMethod.GET)
    public String teacherModifyEmail(Model model) {
        model.addAttribute("teacher",teacher);
        return "modify_email";
    }

    ////////////////////////////修改邮箱提交待修改
    @RequestMapping(value = "/setting/modifyEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherModifyEmailSubmit(String email) {
        if (teacherService.modifyEmail(email,teacher))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /////////////////////////////////修改账户密码按钮
    @RequestMapping(value = "/setting/modifyPwd", method = RequestMethod.GET)
    public String teacherModifyPwd(Model model)
    {
        model.addAttribute("teacher",teacher);
        return "modify_pwd";
    }

    ////////////////////////////////修改账户密码提交
    @RequestMapping(value = "/setting/modifyPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity teacherModifyPwdSubmit(String password) {
        if (teacherService.modifyPwd(password,teacher))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /////////////////////////////////////通知页面
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public String teacherNotification() {
        return "teacher_notification";
    }
}
