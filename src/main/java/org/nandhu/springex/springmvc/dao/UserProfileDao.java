package org.nandhu.springex.springmvc.dao;

import java.util.List;

import org.nandhu.springex.springmvc.model.UserProfile;
import org.springframework.data.repository.Repository;

public interface UserProfileDao extends Repository<UserProfile, Integer> {
	
	// USING METHOD NAME QUERIES - Put @EnableJpaRepositories in jpa configuration file
	UserProfile findById(Integer i);
	
	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
}