package guru.springframework.msscbeerservice.web.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;


import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	
	@Test
	void getBeerById() throws Exception {
		
		given(beerService.getBeerById(any())).willReturn(getValidBeerDTO());
		
		 mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDTO beerDto = getValidBeerDTO();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        
        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDTO());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
	}
	
	@Test
	void updateBeerId() throws Exception {
		BeerDTO beerDto = getValidBeerDTO();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDTO());

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isOk());
	}
	
	BeerDTO getValidBeerDTO() {
		return BeerDTO.builder()
				.beerName("Guiness")
				.beerStyleEnum(BeerStyleEnum.STOUT)
				.price(new BigDecimal(2.0))
				.upc(123123123123L)
				.build();
	}

}
