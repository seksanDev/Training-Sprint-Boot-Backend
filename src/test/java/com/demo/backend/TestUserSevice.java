package com.demo.backend;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.backend.entity.Address;
import com.demo.backend.entity.Social;
import com.demo.backend.entity.User;
import com.demo.backend.exception.BaseException;
import com.demo.backend.exception.UserException;
import com.demo.backend.service.AddressService;
import com.demo.backend.service.SocialService;
import com.demo.backend.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserSevice {
	@Autowired
	private UserService userService;
	@Autowired
	private SocialService socialService;
	@Autowired
	private AddressService addressService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);
		// Check Not Null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());
		// Check Equals
		Assertions.assertEquals(TestCreateData.email, user.getEmail());
		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);
		Assertions.assertEquals(TestCreateData.name, user.getName());

	}

	@Order(2)
	@Test
	void testUpdate() throws UserException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());

	}

	@Order(3)
	void testCreateSocial() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(user, SocialTestCreateData.facebook, SocialTestCreateData.line,
				SocialTestCreateData.instagram);

		Assertions.assertNotNull(social);

	}

	@Order(4)
	void testCreateAddress() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		List<Address> addresses = user.getAddresses();
		Assertions.assertTrue(addresses.isEmpty());

		Address addressed = addressService.create(user, AddressTestCreateData.line1, AddressTestCreateData.line2,
				AddressTestCreateData.zipcode);

		Assertions.assertNotNull(addressed);

	}

	@Order(9)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		userService.deleteById(user.getId());
		Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	/**
	 * TestCreateData
	 */
	interface TestCreateData {
		String email = "seksan@test.com";
		String password = "s#15sdf";
		String name = "seksan";
	}

	interface TestUpdateData {
		String name = "seksan.pa";
	}

	/**
	 * SocialTestCreateData
	 */
	interface SocialTestCreateData {

		String facebook = "seksan.pa";
		String line = "seksan11";
		String instagram = "seksan_pa";
	}

	/**
	 * AddressTestCreateData
	 */
	interface AddressTestCreateData {
		String line1 = "123/45";
		String line2 = "muang";
		String zipcode = "33180";

	}
}
