package com.datamaster.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.datamaster.model.User;
import com.datamaster.service.UserServiceImpl;

@Controller
public class WebController {

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, Principal principal){
		if (principal != null) {
			return "redirect:/console/dashboard";
		} else {
			return "login";
		}
    }

	@RequestMapping(value="/admin/personAdd")
	public String userAdd(){
			return "/admin/personAdd";
	}

	@RequestMapping(value="/admin/personList")
	public String userList(){
		return "/admin/personList";
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
    }
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (! bindingResult.hasErrors()) {
			userService.save(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
		}
		return "registration";
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public String adminHome(Model model, Principal principal) {
		if (principal != null) {
			User user = userService.findUserByEmail(((Authentication) principal).getName());//判断角色是否有查看权利
			//默认有查看权利
			model.addAttribute("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
			//model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
			return "admin/home";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/console/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model, Principal principal) {
		if (principal != null) {
			model.addAttribute("name",((Authentication) principal).getName());
			model.addAttribute("authorities",((Authentication) principal).getAuthorities());
			model.addAttribute("credentials",((Authentication) principal).getCredentials());
			model.addAttribute("details",((Authentication) principal).getDetails());
			model.addAttribute("principal",((Authentication) principal).getPrincipal());
			return "console/dashboard";
		} else {
			return "redirect:/login";
		}
		
		
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			model.addAttribute("username", loginedUser.getEmail());
			String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403";
    }
}
