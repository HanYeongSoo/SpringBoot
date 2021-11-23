package com.care.root.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CommonController {
	@Autowired
	HttpSession session;
	

	@GetMapping("test")
	public String test() {
		
		return "test";
	}
	
	// 여기서 하는 건 html쪽임 jsp파일이 아님!!
	@GetMapping("/")
	public String index(Model model) {
		session.setAttribute("user", "session value");		// index.html에서 {session.user}로 사용
		model.addAttribute("user", "model value");		// index.html에서 {user}로 사용
		return "index";
	}
	
	// Model과 Session값 전달 하는거 보기
	@RequestMapping("object")
	public String object(Model model) {
		TestDTO sdto = new TestDTO();
		sdto.setName("s 홍길동"); sdto.setAddr("s 산골짜기");
		session.setAttribute("user", sdto);
		
		
		TestDTO mdto = new TestDTO();
		mdto.setName("m 홍길동"); mdto.setAddr("m 산골짜기");
		model.addAttribute("user",mdto);
		
		String msg = "<script>alert('안녕');</script>";
		msg += "<h1>안녕하세요</h1>";
		model.addAttribute("msg", msg);
		
		return "object";
	}
	
	@RequestMapping("operator/{num}")		// mapping주소에 있는 {num}은 밑에 변수 int num
	public ModelAndView operator(@PathVariable int num, ModelAndView mav) {
		mav.setViewName("operator");
		
		List<String> arr = new ArrayList<String>();
		arr.add("홍길동"); 
		arr.add("김개똥"); 
		arr.add("고길동");
		
		mav.addObject("arr",arr); 
		mav.addObject("num",num);
		mav.addObject("name","김말이");
		
		return mav;		
	}


	
	
}
