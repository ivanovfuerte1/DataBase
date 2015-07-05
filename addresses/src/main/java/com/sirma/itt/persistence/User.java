package com.sirma.itt.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "Users", query = "SELECT u FROM User as u"),
		@NamedQuery(name = "Names", query = "SELECT u.name FROM User as u") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "user")
	private List<Address> addresses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address setNewAddress(String city, String street) {
		Address address = new Address();
		address.setCity(city);
		address.setStreet(street);
		address.setUser(this);
		this.addresses.add(address);
		return address;
	}

}
