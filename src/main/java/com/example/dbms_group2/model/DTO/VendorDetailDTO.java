package com.example.dbms_group2.model.DTO;

import java.util.List;

public class VendorDetailDTO {
    private String title;                 // 攤商標題
    private String description;           // 攤商介紹
    private List<LinkDTO> links;          // 社群連結清單
    private List<ProductDTO> products;    // 商品清單

    public VendorDetailDTO(String title, String description, List<LinkDTO> links, List<ProductDTO> products, applyId) {
        this.title = title;
        this.description = description;
        this.links = links;
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
