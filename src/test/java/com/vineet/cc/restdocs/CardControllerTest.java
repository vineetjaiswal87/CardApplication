/**
 * 
 */
package com.vineet.cc.restdocs;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.vineet.cc.model.CardDetails;
import com.vineet.cc.repository.CardDetailsRepository;
import com.vineet.cc.service.CreditCardNumberValidatorImpl;
import com.vineet.cc.service.CreditCardService;

/**
 * This class is created to generated spring rest document.
 *
 */
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
@SpringBootTest
public class CardControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CardDetailsRepository crudRepo;

	@Autowired
	CreditCardNumberValidatorImpl cardImpl;

	@Autowired
	CreditCardService service;

	@Test
	public void getAllCardSuccessScenario() throws Exception {
		List<CardDetails> cardDetailsList = new ArrayList<>();
		CardDetails cardDetail = new CardDetails("Vineet", "111122224444", new BigDecimal("100.00"), "vx");
		cardDetailsList.add(cardDetail);
		when(crudRepo.findAll()).thenReturn(cardDetailsList);
		this.mockMvc.perform(get("/card/v1/getAll").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.[0].name", is("Vineet")))
				.andDo(document("getAllCard"));
	}

	@Test
	public void getAllCardNoRecordScenario() throws Exception {
		when(crudRepo.findAll()).thenReturn(null);
		this.mockMvc.perform(get("/card/v1/getAll").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound()).andDo(document("getAllNoRecord"));
	}

	@Test
	public void addCardSuccessScenario() throws Exception {
		CardDetails cardDetail = new CardDetails("Vineet", "1111222233334444", new BigDecimal("100.00"), "vx");
		when(crudRepo.save(cardDetail)).thenReturn(cardDetail);
		this.mockMvc.perform(post("/card/v1/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"Vineet\",\"cardNumber\": \"1111222233334444\",\"limitAmt\": \"100.00\",\"userId\": \"vx\"}"))
				.andDo(print()).andExpect(status().isCreated()).andDo(document("addCardSuccess"));
	}

	@Test
	public void addCardFailureAlphanumeric() throws Exception {
		this.mockMvc.perform(post("/card/v1/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"Vineet\",\"cardNumber\": \"11112222QQQQ4444\",\"limitAmt\": \"100.00\",\"userId\": \"vx\"}"))
				.andDo(print()).andExpect(status().isBadRequest()).andDo(document("addCardFailureAlphanumeric"));
	}

	@Test
	public void addCardFailureLuhnAlgo() throws Exception {
		this.mockMvc.perform(post("/card/v1/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"Vineet\",\"cardNumber\": \"1111222255554444\",\"limitAmt\": \"100.00\",\"userId\": \"vx\"}"))
				.andDo(print()).andExpect(status().isBadRequest()).andDo(document("addCardFailureLuhnAlgo"));
	}
}
