package com.ars.core.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("pageJump")
@Controller
public class PageJumpController {
	@RequestMapping("info.do")
	public String info(){
		System.out.println("info");
		return "info";
	}
	
}
