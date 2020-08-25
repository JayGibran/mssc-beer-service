package guru.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	private final BeerService beerService;
	
	private static final String DEFAULT_PAGE_NUMBER = "0";
	
	private static final String DEFAULT_PAGE_SIZE = "25"; 
	
	@GetMapping
	public ResponseEntity<BeerPagedList> listBeers(
			@RequestParam(value = "pageNumber", required = false, defaultValue= DEFAULT_PAGE_NUMBER) Integer pageNumber, 
			@RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize, 
			@RequestParam(value = "beerName", required = false) String beerName, 
			@RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
			@RequestParam(value="showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand){
	
		
		BeerPagedList beerList = beerService.listBeer(beerName, beerStyle, showInventoryOnHand, PageRequest.of(pageNumber, pageSize));
		
		return new ResponseEntity<BeerPagedList>(beerList, HttpStatus.OK);
	}
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(
			@PathVariable("beerId") UUID beerId, 
			@RequestParam(value="showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand){
		return new ResponseEntity<BeerDTO>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDTO> saveNewBeer(@Valid @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<BeerDTO>(beerService.saveNewBeer(beerDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDTO> updateBeedById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTO beerDTO){
		return new ResponseEntity<BeerDTO>(beerService.updateBeer(beerId, beerDTO), HttpStatus.OK);
	}
	

}
