package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
	
	@Null
	private UUID id;
	
	@Null
	private Integer version;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime lastModifiedDate;
	
	@NotBlank
	private String beerName;
	
	@NotNull
	private BeerStyleEnum beerStyleEnum;
	
	@Positive
	@NotNull
	private Long upc;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Positive
	@NotNull
	private BigDecimal price;
	
	private Integer quantityOnHand;
}
