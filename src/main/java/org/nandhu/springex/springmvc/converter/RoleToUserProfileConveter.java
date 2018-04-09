package org.nandhu.springex.springmvc.converter;

import org.nandhu.springex.springmvc.model.UserProfile;
import org.nandhu.springex.springmvc.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */

@Component
public class RoleToUserProfileConveter implements Converter<Object, UserProfile> {

	@Autowired
	UserProfileService profileService;

	// Gets user profile by id
	@Override
	public UserProfile convert(Object source) {
		Integer id = Integer.parseInt((String) source);
		UserProfile userProfile = profileService.findById(id);
		System.out.println("Profile : " + userProfile);
		return userProfile;
	}
}
