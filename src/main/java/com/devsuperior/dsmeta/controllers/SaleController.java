package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesByPeriodAndSellerDTO;
import com.devsuperior.dsmeta.dto.SalesBySellerDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<SalesReportDTO> getReport(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate,
			@RequestParam(required = false) String name
	) {
		SalesReportDTO result = service.getReport(minDate, maxDate, name);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SalesBySellerDTO>> getSummary(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate
	) {
		List<SalesBySellerDTO> result = service.getSalesBySeller(minDate, maxDate);
		return ResponseEntity.ok(result);
	}
}
