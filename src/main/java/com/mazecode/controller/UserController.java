package com.mazecode.controller;

import com.mazecode.model.MUser;
import com.mazecode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private IUserService   userService;
	@Autowired
	private MessageSource  messageSource;
	@Autowired
	private LocaleResolver localeResolver;
	
	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv   = new ModelAndView("user/list");
		List<MUser>  list = userService.all();
		mv.addObject("list", list);
		return mv;
	}

//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public ModelAndView add() {
//		ModelAndView mv = new ModelAndView("user/form");
//		mv.addObject("userForm", new MUser());
//		return mv;
//	}
//	
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public ModelAndView save(@ModelAttribute("userform") MUser u) {
//		userService.create(u);
//		return new ModelAndView("redirect:/user/list");
//	}
//	
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//	public ModelAndView update(@PathVariable int id) {
//		ModelAndView mv = new ModelAndView("user/form");
//		MUser         u  = userService.findById(id);
//		mv.addObject("userForm", u);
//		return mv;
//	}
//	
//	private String getMsg(String key, HttpServletRequest request) {
//		return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
//	}
}
