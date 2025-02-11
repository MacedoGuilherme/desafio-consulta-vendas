package com.devsuperior.dsmeta.dto;

import java.util.List;

public class SalesReportDTO {

    private List<SalesByPeriodAndSellerDTO> content;

    public SalesReportDTO(List<SalesByPeriodAndSellerDTO> content) {
        this.content = content;
    }

    public List<SalesByPeriodAndSellerDTO> getContent() {
        return content;
    }

    public void setContent(SalesByPeriodAndSellerDTO content) {
        this.content.add(content);
    }
}
