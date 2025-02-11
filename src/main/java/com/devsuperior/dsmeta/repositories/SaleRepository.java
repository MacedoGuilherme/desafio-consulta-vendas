package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesByPeriodAndSellerDTO;
import com.devsuperior.dsmeta.dto.SalesBySellerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesBySellerDTO(s.seller.name, SUM(s.amount)) " +
            "FROM Sale s " +
            "INNER JOIN s.seller " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.seller.name")
    List<SalesBySellerDTO> getSalesBySeller(LocalDate minDate, LocalDate maxDate);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesByPeriodAndSellerDTO(s.id, s.date, s.amount, s.seller.name) " +
            "FROM Sale s " +
            "INNER JOIN s.seller " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "AND (:sellerName IS NULL OR UPPER(s.seller.name) LIKE CONCAT('%', UPPER(:sellerName), '%'))")
    List<SalesByPeriodAndSellerDTO> getReport(LocalDate minDate, LocalDate maxDate, String sellerName);
}
