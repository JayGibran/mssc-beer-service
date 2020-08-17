package guru.springframework.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2655759673295762860L;

	public BeerPagedList(List<BeerDTO> content, Pageable pageable, long total) {
		super(content, pageable, total);
		// TODO Auto-generated constructor stub
	}

	public BeerPagedList(List<BeerDTO> content) {
		super(content);
		// TODO Auto-generated constructor stub
	}
}
