package guru.springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDTO;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	BeerDTO beerToBeerDTO(Beer beer);
	
	Beer beerDTOToBeer(BeerDTO beer);
	
}
