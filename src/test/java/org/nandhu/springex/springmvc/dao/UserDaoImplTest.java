package org.nandhu.springex.springmvc.dao;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nandhu.springex.springmvc.configuration.JpaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// SPRING INTEGRATION TEST FOR DAO LAYER

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class })
public class UserDaoImplTest {

	@Autowired
	UserDao userDao;

	@Autowired
	ApplicationContext context;

	@Before
	public void checkLoadedClases() {
		System.out.println("Inside before class");
		String beans[] = context.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++)
			System.out.println(beans[i]);
	}

	@Test
	@Transactional
	public void testFindAllUsers() {
		System.out.println("Inside Test method");

		assert (userDao.findAllUsers().size() > 0);
	}
}
