package guru.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

@Component
public class BeerLoader implements CommandLineRunner {

	public static final Long BEER_1_UPC = 631234200036L;
	public static final Long BEER_2_UPC = 631234300019L;
	public static final Long BEER_3_UPC = 83783375213L;

	private final BeerRepository beerRepository;

	@Autowired
	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();

	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			Beer b1 = Beer.builder().beerName("Mango Bobs").beerStyle(BeerStyleEnum.IPA.name()).minOnHand(12)
					.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_1_UPC).build();

			Beer b2 = Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyleEnum.PALE_ALE.name()).minOnHand(12)
					.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_2_UPC).build();

			Beer b3 = Beer.builder().beerName("Pinball Porter").beerStyle(BeerStyleEnum.PALE_ALE.name()).minOnHand(12)
					.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_3_UPC).build();

			beerRepository.save(b1);
			beerRepository.save(b2);
			beerRepository.save(b3);
		}

	}

}
