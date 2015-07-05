package com.sirma.itt.persistence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author skovatchev
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

	@Mock
	// EntityManager is a class that has methods to work with DB.
	private EntityManager entityManager;

	@InjectMocks
	private UserDao userDao;

	@SuppressWarnings("unchecked")
	@Test
	public void addUserInEmptyDb() {
		// A mocked typedQuery is assigned to query.
		TypedQuery<User> query = Mockito.mock(TypedQuery.class);
		// Tells Mockito to return query when method createNamedQuery is called on entityManager
		Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class)))
				.thenReturn(query);
		String actual = userDao.registerUser("Svetlio");
		// Check if the method persist is called at lest once on entityManager.
		Mockito.verify(entityManager, Mockito.atLeastOnce()).persist(Mockito.any(User.class));
		Assert.assertEquals("User Svetlio is registered.", actual);
	}

}
