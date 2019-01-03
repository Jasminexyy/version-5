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

	public static UserVO userVO;

	//登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login() {
		return "userlogin";
	}

	//登录提交表单
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String LoginSubmit(String account, String password) throws IOException {
		if (account.length() == 11) {
			if (studentService.vertify(account, password)) {
				userVO=studentService.getUserVOByAccount(account);
				return "redirect:/cm/student/index";
			} else {
				if (teacherService.vertify(account, password)) {
					userVO=teacherService.getUserVOByAccount(account);
					return "redirect:/cm/teacher/index";
				}
			}
			return "userlogin";
		}
		else return "redirect:/cm/teacher/index";
	}
}
