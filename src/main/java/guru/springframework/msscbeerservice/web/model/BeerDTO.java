package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
	
	private UUID id;
	
	private Integer version;
	
	private OffsetDateTime createdDate;
	
	private OffsetDateTime lastModifiedDate;
	
	private String beerName;
	
	private BeerStyleEnum beerStyleEnum;
	
	private Long upc;
	
	private BigDecimal price;
	
	private Integer quantityOnHand;
}
