package com.example.dbms_group2.model.DTO;

import java.util.List;

public class FilterVendorDTO {
    private int code; // 地圖編號 1 ~ 20（重要！）
    private int vendorId;
    private String name;
    private List<ProductDTO> filteredProducts;

    public FilterVendorDTO(int code, int vendorId, String name, List<ProductDTO> filteredProducts) {
        this.code = code;
        this.vendorId = vendorId;
        this.name = name;
        this.filteredProducts = filteredProducts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<ProductDTO> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }
}
