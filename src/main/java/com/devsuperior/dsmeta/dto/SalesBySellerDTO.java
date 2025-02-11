package com.devsuperior.dsmeta.dto;

public class SalesBySellerDTO {

    String sellerName;
    Double total;

    public SalesBySellerDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
