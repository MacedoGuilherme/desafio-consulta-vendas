package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesByPeriodAndSellerDTO;
import com.devsuperior.dsmeta.dto.SalesBySellerDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SalesBySellerDTO> getSalesBySeller(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDateConverted = null;
		LocalDate maxDateConverted = null;

		if (minDate == null) {
			minDateConverted = today.minusYears(1);
		} else {
			minDateConverted = LocalDate.parse(minDate);
		}

		if (maxDate == null) {
			maxDateConverted = today;
		} else {
			maxDateConverted = LocalDate.parse(maxDate);
		}

		return repository.getSalesBySeller(minDateConverted, maxDateConverted);
	}

	public SalesReportDTO getReport(String minDate, String maxDate, String name) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDateConverted = null;
		LocalDate maxDateConverted = null;
		String nameConverted = null;

		if (minDate == null) {
			minDateConverted = today.minusYears(1);
		} else {
			minDateConverted = LocalDate.parse(minDate);
		}

		if (maxDate == null) {
			maxDateConverted = today;
		} else {
			maxDateConverted = LocalDate.parse(maxDate);
		}

		if (name == null) {
			nameConverted = null;
		} else {
			nameConverted = name;
		}

		List<SalesByPeriodAndSellerDTO> result = repository.getReport(minDateConverted, maxDateConverted, nameConverted);

		return new SalesReportDTO(result);
	}

}
