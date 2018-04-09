package org.nandhu.springex.springmvc.service;

import java.util.List;

import org.nandhu.springex.springmvc.dao.UserDao;
import org.nandhu.springex.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public User findBySSO(String sso) {
		return userDao.findBySSO(sso);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	// Either we can call entitymanager.update in the dao or just update the
	// object..

	// Since the method is running with Transaction, No need to call hibernate
	// update explicitly.
	// Just fetch the entity from db and update it with proper values within
	// transaction.
	// It will be updated in db once transaction ends.
	@Override
	public void updateUser(User user) {
		User entity = userDao.findById(user.getId());
		if (entity != null) {
			entity.setEmail(user.getEmail());
			entity.setFirst_name(user.getFirst_name());
			entity.setLast_name(user.getLast_name());
			entity.setPassword(user.getPassword());
			entity.setSso_id(user.getSso_id());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	@Override
	public void deleteUserBySSO(String sso) {
		userDao.deleteBySSO(sso);
	}

	@Override
	public boolean isUserSSOUnique(Integer id, String sso) {
		System.out.println("Printing SSO " + sso);
		User user = userDao.findBySSO(sso);
		return ((user == null) || (id != null && user.getId() == id));
	}
}
