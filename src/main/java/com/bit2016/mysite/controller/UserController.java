package com.bit2016.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/joinform")
	public String joinForm() {
		return "user/joinform";
	}

	@RequestMapping("/loginform")
	public String loginForm() {
		return "user/loginform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {

		if (result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError e : list) {
//				System.out.println("object Error:" + e);
//			}
			return "user/joinform";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	// @RequestMapping( "/login" )
	// public String login(
	// @ModelAttribute UserVo vo,HttpSession session ){
	// System.out.println( "로그인 시도");
	// UserVo userVo = userService.login(vo);
	// System.out.println("전달");
	// if( userVo == null ) {
	// return "redirect:/user/loginform?result=fail";
	// }
	// System.out.println("인증 시도");
	// // 인증 처리
	// session.setAttribute( "authUser", userVo );
	//
	// return "redirect:/";
	// }

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(@AuthUser UserVo authUser, Model model) {

		System.out.println(authUser.getNo());
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		System.out.println("수정폼 끝");
		return "user/ModifyForm";
	}

	@Auth
	@RequestMapping("/modify")
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo vo) {
		System.out.println("수정중 ");
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());

		return "redirect:/user/modifyform?update=success";
	}

	// @ExceptionHandler( UserDaoException.class )
	// public String handlerUserDaoException(){
	// // 1. logging ( 파일에 내용 저장 )
	// // 2. 사용자에게 안내(error) 페이지
	// return "error/500";
	// }
}