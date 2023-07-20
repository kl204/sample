package bitedu.bipa.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {
	
	@RequestMapping(value="/view_loginForm.do", method=RequestMethod.GET)
	public ModelAndView viewLoginForm() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./member/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id,@RequestParam("pwd") String pwd,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String url = "./member/loginForm";
		if(id.equals("admin")&&pwd.equals("1234")) {
			url = "./member/gatehome";
		} 
		//mav.addObject("id",id);
		session.setAttribute("id", id);
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping(value="/work.do", method=RequestMethod.GET)
	public ModelAndView work() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./member/gatehome");
		return mav;
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate();
		mav.setViewName("./member/gatehome");
		return mav;
	}
	
}
