package guru.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.web.model.BeerDTO;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<BeerDTO>(BeerDTO.builder().build(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> saveNewBeer(@RequestBody BeerDTO beerDTO){
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<String> updateBeedById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	

}
