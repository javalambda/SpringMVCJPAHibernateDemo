package org.nandhu.springex.springmvc.dao;

import java.util.List;

import org.nandhu.springex.springmvc.model.User;

public interface UserDao {

	User findById(Integer id);

	User findBySSO(String sso_id);

	List<User> findAllUsers();

	void save(User user);

	void deleteBySSO(String sso);

	// void updateUser(User user);
}
