package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDTO;

public class BrewBeerEvent extends BeerEvent {

	private static final long serialVersionUID = 972780275837851562L;

	public BrewBeerEvent(BeerDTO beerDTO) {
		super(beerDTO);
	}

}
