package org.nandhu.springex.springmvc.service;

import java.util.List;

import org.nandhu.springex.springmvc.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(Integer id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
}
