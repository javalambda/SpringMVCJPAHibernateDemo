package org.nandhu.springex.springmvc.model;

public enum UserProfileType {
	USER("USER"), DBA("DBA"), ADMIN("ADMIN");

	private String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}
}