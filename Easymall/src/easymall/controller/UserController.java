package easymall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.po.User;
import easymall.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		User login = userService.login(user);
		if (login != null) {
			session.setAttribute("user", login);
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("message", "用户名或密码错误！");
			return "login";
		}
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取前端传过来的username
		String username = request.getParameter("username");
		if (userService.checkUsername(username)) {
			response.getWriter().print("用户名" + username + "已被注册！");
		} else {
			response.getWriter().print("恭喜你，" + username + "可以使用！");
		}
	}
	
	@RequestMapping("/regist")
	public String regist(User user, String valistr, HttpSession session, Model model) {
		if (userService.checkUsername(user.getUsername())) {
			model.addAttribute("msg", "用户名已经存在！");
			return "regist";
		}
		if (user.getUsername() == null || user.getUsername() == "") {
			model.addAttribute("msg", "用户名不能为空！");
			return "regist";
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			model.addAttribute("msg", "密码不能为空！");
		}
		if (!valistr.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "验证码错误！");
			return "regist";
		}
		if (userService.regist(user) > 0) {
			model.addAttribute("msg", "注册成功！");
			return "regist";
		} else {
			model.addAttribute("msg", "注册失败！");
			return "regist";
		}
	}
	
}
