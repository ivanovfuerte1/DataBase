package com.sirma.itt.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import com.sirma.itt.persistence.UserDao;

@Path("addressdirectory")
public class UserRequests {

	// private static final Logger LOGGER = LogManager
	// .getLogger(UserRequests.class);
	@Inject
	private UserDao userDao;

	@GET
	@Path("addresses/{user}")
	public String getUser(@PathParam("user") String user) {
		return "This is the GET method and the argiment is: " + user;
	}

	@POST
	@Path("users/{user}")
	public String registerUser(@PathParam("user") String user) {
		return userDao.registerUser(user);
	}

	@PUT
	@Path("users/{user}/{city}/{street}")
	public String addAddressToUser(@PathParam("user") String user,
			@PathParam("city") String city, @PathParam("street") String street) {
		return userDao.addAddress(user, city, street);
	}

	@POST
	@Path("completeusers")
	public String addUserWithAddresses(@QueryParam("name") String name,
			@QueryParam("addresses") String addresses) {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) jsonParser.parse(addresses);
		} catch (Exception e) {
			// LOGGER.error("The parsing has failed", e);
		}
		return jsonArray.get(1).toString();

	}

	@GET
	@Path("completeusers")
	public String setUserWithAddresses(@QueryParam("name") String name,
			@QueryParam("addresses") String addresses) {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) jsonParser.parse(addresses);
		} catch (Exception e) {
			// LOGGER.error("The parsing has failed", e);
		}
		return jsonArray.get(1).toString();

	}
}
