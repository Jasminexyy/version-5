package cm.controller;

import cm.service.StudentService;
import cm.service.TeacherService;
import cm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

//import cm.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;


	//登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login() {
		return "userlogin";
	}

	//登录提交表单
	@RequestMapping(value = "/cm/login", method = RequestMethod.POST)
	public String LoginSubmit(String account, String password) throws IOException {
		System.out.println(account);
		if (account.length()>0) {
			System.out.println("verify student successfully");
			if (studentService.vertify(account, password)) {
				System.out.println("verify student successfully");
				return "redirect:/cm/student/index?account="+account;
			}
			else if (teacherService.vertify(account, password)) {
					System.out.println("verify teacher successfully");
					return "redirect:/cm/teacher/index?account="+account;
			}
			System.out.println("verify teacher fail");

		}
		System.out.println("我败了");
		return "userlogin";
	}
}
