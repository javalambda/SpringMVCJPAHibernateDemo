package org.nandhu.springex.springmvc.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.nandhu.springex.springmvc.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
// @Transactional - Required only while testing it using the main method
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(Integer id) {
		User user = this.getByKey(id);
		// Alternative to Hibernate.initalize(collection)
		if (user != null)
			initializeCollection(user.getUserProfiles());
		return user;
	}

	protected void initializeCollection(Collection<?> collection) {
		if (collection == null)
			return;
		collection.iterator().hasNext();
	}

	public User findBySSO(String sso_id) {
		try {
			User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE sso_id = :sso_id")
					.setParameter("sso_id", sso_id).getSingleResult();

			if (user != null)
				initializeCollection(user.getUserProfiles());
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<User> users = (List<User>) getEntityManager().createQuery("SELECT u FROM User u ORDER BY u.first_name ASC")
				.getResultList();
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE sso_id = :sso")
				.setParameter("sso", sso).getSingleResult();

		delete(user);
	}

	// public void updateUser(User user) {
	// update(user);
	// }
}
