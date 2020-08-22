package guru.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	private final BeerService beerService;
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<BeerDTO>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDTO> saveNewBeer(@Valid @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<BeerDTO>(beerService.saveNewBeer(beerDTO), HttpStatus.OK);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDTO> updateBeedById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<BeerDTO>(beerService.updateBeer(beerId, beerDTO), HttpStatus.OK);
	}
	

}
