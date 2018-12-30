package cm.controller;

import cm.entity.Course;
import cm.entity.Student;
import cm.entity.Teacher;
import cm.service.*;
//import cm.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cm")
public class UserController {

	StudentService studentService=new StudentService();
	TeacherService teacherService=new TeacherService();

	//登录
	@RequestMapping(value="/login",method= RequestMethod.GET)
	public String Login() {
		return "userlogin";
	}
	
	//登录提交表单
	@RequestMapping(value="/login",method= RequestMethod.POST)
	@ResponseBody
	public String LoginSubmit(String account, String password,HttpServletResponse response)throws IOException {
		if (account.length() == 11) {
			if (studentService.vertify(account, password))
				return "redirect:/cm/student/index";
		} else {
			if(teacherService.vertify(account,password))
				return "redirect:/cm/teacher/index";
		}
		return "userlogin";
	}
}
