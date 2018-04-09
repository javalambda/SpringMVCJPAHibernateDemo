package org.nandhu.springex.springmvc.service;

import java.util.List;

import org.nandhu.springex.springmvc.model.User;

public interface UserService {

	User findById(Integer id);

	User findBySSO(String sso);

	List<User> findAllUsers();

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	boolean isUserSSOUnique(Integer id, String sso);
}
