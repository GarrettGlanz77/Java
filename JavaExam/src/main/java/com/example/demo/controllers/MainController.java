package com.example.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Idea;
import com.example.demo.models.User;
import com.example.demo.services.IdeaService;
import com.example.demo.services.UserService;
import com.example.demo.validator.UserValidator;


@Controller
public class MainController {
	private final UserService userService;
	private final UserValidator userValidator;
	private final IdeaService ideaService;

	public MainController(UserService userService, UserValidator userValidator, IdeaService ideaService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.ideaService = ideaService;
	}

	@RequestMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "index.jsp";
		}
		 User u = userService.registerUser(user);
		 session.setAttribute("userId", u.getId());
		 return "redirect:/ideas";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/ideas";
		}
		 else {
			model.addAttribute("user", new User());
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "index.jsp";
		}
	}
	@RequestMapping("/ideas")
	public String homepage(HttpSession session, Model model) {

		if (session.getAttribute("userId") != null) {
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			model.addAttribute("user", u);
			List<Idea> ideaList = ideaService.findAllIdea();
			model.addAttribute("ideas", ideaList);
			return "homePage.jsp";

		}
		 else {
			System.out.println("Must log in before proceeding.");
			return "redirect:/";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("/ideas/new")
	public String newideaPage(@ModelAttribute("newidea")Idea myIdea) {
		return "newidea.jsp";
	}	
	@RequestMapping(value="/ideas/createnew", method=RequestMethod.POST)
	public String addIdea(@Valid @ModelAttribute("newidea")Idea myIdea, BindingResult result,HttpSession session) {
	
		if (result.hasErrors()) {
			System.out.println("Error- creating new idea");
			return "newidea.jsp";
		}
		 else {
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			
			Idea newIdea = ideaService.createIdea(myIdea);
			newIdea.setCreator(u);
			ideaService.updateIdea(newIdea);
			return "redirect:/ideas";
		}

	}
	@RequestMapping("/ideas/{id}")
	public String displayEditIdea(@PathVariable("id") Long ideaId, Model model) {
		Idea myIdea = ideaService.findIdea(ideaId);

		model.addAttribute("idea", myIdea);

		return "showidea.jsp";
	}

	
	@RequestMapping("/ideas/{id}/edit")
	public String editIdea(HttpSession session, @ModelAttribute("idea") Idea myIdea, @PathVariable("id") Long myId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);

		Idea findIdea = ideaService.findIdea(myId);
		if (u.getId() == findIdea.getCreator().getId()) {
			model.addAttribute("idea", findIdea);
			return "editidea.jsp";

		}
		 else {
		
			return "redirect:/ideas/"+myId;
		}
	}
	@PostMapping("/ideas/edit")
	public String updateIdea(HttpSession session,@Valid @ModelAttribute("idea")Idea myIdea, BindingResult result) {
		
		if (result.hasErrors()) {
			return "editidea.jsp";
		}
		 else {
			Long userId = (Long) session.getAttribute("userId");
			User u = userService.findUserById(userId);
			myIdea.setCreator(u);
			ideaService.updateIdea(myIdea);
			return "redirect:/ideas";
		}
	}
	@RequestMapping("/delete/{id}")
	public String deleteIdea(@PathVariable("id") Long myId, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		Idea myIdea = ideaService.findIdea(myId);
		if (myIdea == null) {
			return "redirect:/ideas";
		}
		 if (u.getId() != myIdea.getCreator().getId()) {
			return "redirect:/ideas";
		}
		 else {
			ideaService.deleteIdea(myId);
			return "redirect:/ideas";
		}
		
	}
	
}