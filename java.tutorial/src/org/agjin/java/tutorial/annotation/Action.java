package org.agjin.java.tutorial.annotation;

@Controller("actionTest")
public class Action {
	
	@RequestMapping("/Test.do")
	public String doAction() {
		return "/Test.jsp";
	}
}
