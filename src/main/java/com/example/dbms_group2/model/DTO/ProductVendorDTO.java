package com.example.dbms_group2.model.DTO;

public class ProductVendorDTO {
    private String name;
    private String category;
    private Object price;
    private Long id;

    public ProductVendorDTO(String name, String category, Object price, Long id) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
