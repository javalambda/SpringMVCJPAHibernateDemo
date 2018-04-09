package org.nandhu.springex.springmvc.dao;

import org.nandhu.springex.springmvc.configuration.JpaConfig;
import org.nandhu.springex.springmvc.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/** MY WAY OF TESTING. HAVE TO TRY SPRING JUNIT RUNNER */
public class TestingDao {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(JpaConfig.class);
		context.refresh();
		context.registerShutdownHook();

		String beans[] = context.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++)
			System.out.println(beans[i]);

		// Testing user profile thru jpa repository

		// UserProfileDao profileDao = (UserProfileDao)
		// context.getBean(UserProfileDao.class);
		// List<UserProfile> profs = profileDao.findAll();
		// System.out.println("User profiles - Findall");
		// profs.forEach((UserProfile p) -> System.out.println(p));
		//
		// UserProfile p1 = profileDao.findById(1);
		// System.out.println("Find By ID - " + p1);
		//
		// UserProfile p2 =
		// profileDao.findByType(UserProfileType.ADMIN.getUserProfileType());
		// System.out.println("Find By Type - " + p2);

		// Testing user thru jpa with entity manager coding

		// User user = new User();
		// user.setFirst_name("John");
		// user.setLast_name("Anderson");
		// user.setPassword("abc123");
		// user.setSso_id("SSO123");
		// user.setEmail("john@gmail.com");
		//
		// Set<UserProfile> profileSet = new HashSet<UserProfile>();
		// profileSet.add(p1);
		// profileSet.add(p2);
		//
		// user.setUserProfiles(profileSet);

		UserDao userDao = (UserDao) context.getBean(UserDao.class);
		// userDao.save(user);
		// System.out.println("User Saved Successfully");

		// Update user
		User u1 = userDao.findBySSO("SSO123");
		System.out.println("Printing find By SSO " + u1);
		//
		// u1.setUserProfiles(profileSet);
		// userDao.updateUser(u1);
		// System.out.println("User updated successfully");

		// List<User> users = userDao.findAllUsers();
		//
		// System.out.println("Printing Results");
		// users.forEach((User u) -> System.out.println(u));

		// delete by sso

		// userDao.deleteBySSO("SSO123");
		// System.out.println("Deleted Successfully");

		context.close();
	}

}
