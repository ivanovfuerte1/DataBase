package com.sirma.itt.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public String registerUser(String name) {
		List<User> users = getUsers();
		for (User user : users) {
			if (user.getName().equals(name)) {
				return "User " + name + " already exists.";
			}
		}
		User user = new User();
		user.setName(name);
		user.setAddresses(new ArrayList<Address>());
		entityManager.persist(user);
		return "User " + name + " is registered.";
	}

	public String addAddress(String name, String city, String street) {
		List<User> users = getUsers();
		for (User user : users) {
			if (user.getName().equals(name)) {
				entityManager.persist(user.setNewAddress(city, street));
				return city + " and " + street + " are added to " + name
						+ "'s list of addresses";
			}
		}
		return "Does not exist user " + name + " in the database";
	}

	private List<User> getUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("Users",
				User.class);
		List<User> users = query.getResultList();
		return users;
	}
}
