package org.nandhu.springex.springmvc.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nandhu.springex.springmvc.configuration.AppMvcConfig;
import org.nandhu.springex.springmvc.model.User;
import org.nandhu.springex.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppMvcConfig.class })
@WebAppConfiguration
public class AppControllerTest {

	@Autowired
	WebApplicationContext ctx;

	// @InjectMocks
	// AppController controller;

	@Autowired
	UserService userServiceMock;

	private MockMvc mockMvc;

	// Reset mocks before each test as the mocks are managed by spring container
	// as it may result in leaks.
	@Before
	public void resetMocks() {
		reset(userServiceMock);
		mockMvc = webAppContextSetup(ctx).build();

	}

	public void checkLoadedClases() {
		System.out.println("Inside before class");
		String beans[] = ctx.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++)
			System.out.println(beans[i]);
	}

	// Positive Test case
	@Test
	public void listUsers_ShouldAddUsersToModelMapAndRenderUserListToView() throws Exception {

		List<User> userList = populateUserList();
		when(userServiceMock.findAllUsers()).thenReturn(userList);

		// mock mvc request object = MockMvcRequestBuilders - static import
		// mock mvc response object = MockMvcResultMatchers - static import
		// hamcrest matchers object = for pattern matching - static import
		mockMvc.perform(get("/list")).andExpect(status().isOk()).andExpect(view().name("userslist"))
				.andExpect(forwardedUrl("/WEB-INF/views/jsp/userslist.jsp"))
				.andExpect(model().attribute("users", hasSize(2)))
				.andExpect(model().attribute("users",
						hasItem(allOf(hasProperty("first_name", is("John")), hasProperty("last_name", is("Peter")),
								hasProperty("id", is(1)), hasProperty("sso_id", is("ABC123")),
								hasProperty("password", is("asdfgf")), hasProperty("email", is("a@a.com"))))))
				.andExpect(model().attribute("users",
						hasItem(allOf(hasProperty("first_name", is("Paul")), hasProperty("last_name", is("Smith")),
								hasProperty("id", is(2)), hasProperty("sso_id", is("XYZ245")),
								hasProperty("password", is("qweqwqw")), hasProperty("email", is("n@n.com"))))));

		verify(userServiceMock, times(1)).findAllUsers();
		verifyNoMoreInteractions(userServiceMock);
	}

	private List<User> populateUserList() {
		List<User> userList = new ArrayList<User>();

		User u1 = new User();
		u1.setId(1);
		u1.setFirst_name("John");
		u1.setLast_name("Peter");
		u1.setSso_id("ABC123");
		u1.setPassword("asdfgf");
		u1.setEmail("a@a.com");

		User u2 = new User();
		u2.setId(2);
		u2.setFirst_name("Paul");
		u2.setLast_name("Smith");
		u2.setSso_id("XYZ245");
		u2.setPassword("qweqwqw");
		u2.setEmail("n@n.com");

		userList.add(u1);
		userList.add(u2);

		return userList;
	}

}
