package org.nandhu.springex.springmvc.service;

import java.util.List;

import org.nandhu.springex.springmvc.dao.UserProfileDao;
import org.nandhu.springex.springmvc.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileDao profileDao;

	@Override
	public UserProfile findById(Integer id) {
		return profileDao.findById(id);
	}

	@Override
	public UserProfile findByType(String type) {
		return profileDao.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return profileDao.findAll();
	}
}
