package guru.springframework.msscbeerservice.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<BeerDTO>(
				BeerDTO.builder()
				.beerName("Guiness")
				.beerStyleEnum(BeerStyleEnum.STOUT)
				.price(new BigDecimal(2.0))
				.quantityOnHand(2)
				.build(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> saveNewBeer(@Valid @RequestBody BeerDTO beerDTO){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/"+ beerDTO.getId().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<String> updateBeedById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	
	@DeleteMapping("{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		
	}

}
