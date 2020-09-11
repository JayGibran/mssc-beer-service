package guru.springframework.msscbeerservice.events;

import java.io.Serializable;

import guru.springframework.msscbeerservice.web.model.BeerDTO;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {
	
	private static final long serialVersionUID = -526074651356797755L;
	
	private final BeerDTO beerDTO;

}
