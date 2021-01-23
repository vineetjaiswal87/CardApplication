/**
 * 
 */
package com.vineet.cc.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.vineet.cc.dto.CardDetailRequest;
import com.vineet.cc.exceptions.ErrorResponse;
import com.vineet.cc.repository.CardDetailsRepository;

/**
 * Class to test integration scenarios
 *
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = {CreditCardService.class, CreditCardNumberValidatorImpl.class, CardDetailsRepository.class})
@WebMvcTest
public class CreditCardServiceTest {
	@MockBean
	CardDetailsRepository crudRepo;
	
	@Autowired
	CreditCardNumberValidatorImpl cardImpl;
	
	@Autowired
	CreditCardService service;
	
	@Test
	public void testAddCardValidScenario( ) {
		CardDetailRequest req = new CardDetailRequest("Vineet", "1111222233334444", "100", "vx");
		ResponseEntity<ErrorResponse> resp = service.addCardDetails(req);
		Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
		
	}
	
	@Test
	public void testAddCardInValidScenario( ) {
		CardDetailRequest req = new CardDetailRequest("Vineet", "111122223333", "100", "vx");
		ResponseEntity<ErrorResponse> resp = service.addCardDetails(req);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	
	@Test
	public void testDatabaseException() {
		when(crudRepo.findAll()).thenThrow(DataAccessResourceFailureException.class);
		assertThrows(DataAccessResourceFailureException.class, () -> service.getAllCardDetails());
	}
	

}
