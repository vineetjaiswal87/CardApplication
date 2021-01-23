/**
 * 
 */
package com.vineet.cc.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.vineet.cc.model.CardDetails;

/**
 * Class to test DB data addition and fetch scenarios.
 *
 */
@DataJpaTest
public class CardDetailsRepositoryTest {
	
	@Autowired
	private CardDetailsRepository repository;

	@Test
	void testAddNewCard() {
		long initialCount = repository.count();
		repository.save(new CardDetails("Vineet", "1111222233334444", new BigDecimal("100"), "vx"));
		repository.save(new CardDetails("Jaiswal", "1111222233335555", new BigDecimal("100"), "vx"));
		long finalCount = repository.count();
		
		Assertions.assertEquals(finalCount - initialCount, 2);
		
	}
	
	@Test
	void testGetCard() {
		repository.save(new CardDetails("Vineet", "1111222233334444", new BigDecimal("100"), "vx"));
		boolean isExists = repository.existsBycardNum("1111222233334444");
		
		Assertions.assertTrue(isExists);
		
	}
}
