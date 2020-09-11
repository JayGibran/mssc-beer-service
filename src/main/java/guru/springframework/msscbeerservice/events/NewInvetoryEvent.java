package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDTO;

public class NewInvetoryEvent extends BeerEvent {

	private static final long serialVersionUID = -5670264433508576659L;

	public NewInvetoryEvent(BeerDTO beerDTO) {
		super(beerDTO);
	}

}
