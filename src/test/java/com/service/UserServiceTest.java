package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.ExpectedCount;

import com.JournalRepository.UserRepo;
import com.entity.UserEnitiy;

@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserRepo userrepo;
	
	@Disabled
	@Test
	public void testadd() {
           // assertEquals(10, 6+4);
          UserEnitiy userEnitiy = userrepo.findByUsername("ABC");
        	assertTrue(!userEnitiy.getJournalentry().isEmpty());
	}
	
	@Disabled
	@Test
	@ParameterizedTest // Passing the parameters for TestMethod
	//pass the Teses 
	@CsvSource({
		"10,5,15",
		"11,4,15",
		"10,10,20"
	})
	public void test(int a , int b, int expected)
	{
		assertEquals(expected, a+b);
	}

}
