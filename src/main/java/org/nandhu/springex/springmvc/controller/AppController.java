package org.nandhu.springex.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.nandhu.springex.springmvc.configuration.AppMvcConfig;
import org.nandhu.springex.springmvc.model.User;
import org.nandhu.springex.springmvc.model.UserProfile;
import org.nandhu.springex.springmvc.service.UserProfileService;
import org.nandhu.springex.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication(scanBasePackages = { "org.nandhu.springex.springmvc" })
@Controller
@RequestMapping("/")
@Import(AppMvcConfig.class)
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserProfileService userProfileService;

	// For User list screen
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap map) {
		List<User> users = userService.findAllUsers();
		map.addAttribute("users", users);
		return "userslist";
	}

	// For loading new user registration screen. Same screen is used for new and
	// edit.
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(ModelMap map) {
		User user = new User();
		map.addAttribute("user", user);
		map.addAttribute("edit", false);
		return "registration";
	}

	// will be called when user hits the add new user hyperlink
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap map) {

		// return to the registration page if there are any validation errors
		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be
		 * implementing custom @Unique annotation and applying it on field [sso]
		 * of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSso_id())) {
			FieldError ssoError = new FieldError("user", "sso_id", messageSource.getMessage("non.unique.sso_id",
					new String[] { user.getSso_id() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		// Save the user after the validation is successful
		userService.saveUser(user);

		map.addAttribute("success",
				"User " + user.getFirst_name() + " " + user.getLast_name() + " registered Successfully");
		return "registrationsuccess";
	}

	// will be called to load the registration page for editing.
	@RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.GET)
	public String editUser(@PathVariable("ssoId") String ssoId, ModelMap map) {
		User user = userService.findBySSO(ssoId);
		map.addAttribute("user", user);
		map.addAttribute("edit", true);
		return "registration";
	}

	// will be called during form submission after editing
	@RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			System.out.println("Inside update erros " + user.toString());
			result.getAllErrors().forEach((ObjectError e) -> System.out
					.println("Printing each error " + e.getCode() + " ---- " + e.getDefaultMessage()));
			map.addAttribute("user", user);
			map.addAttribute("edit", true);
			return "registration";
		}
		System.out.println("Before update user in controller " + user.toString());
		userService.updateUser(user);
		map.addAttribute("success",
				"User " + user.getFirst_name() + " " + user.getLast_name() + " Updated Successfully");
		return "registrationsuccess";
	}

	// Will be called when the user hits the delete button
	@RequestMapping(value = "/delete-user-{ssoId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("ssoId") String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	//
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	public static void main(String args[]) {
		SpringApplication.run(AppController.class, args);
	}
}
