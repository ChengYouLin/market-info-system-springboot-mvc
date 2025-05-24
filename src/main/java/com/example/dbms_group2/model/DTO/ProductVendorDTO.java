package com.example.dbms_group2.model.DTO;

public class ProductVendorDTO {
    private String name;
    private String category;
    private String price;
    private int id;

    public ProductVendorDTO(String name, String category, String price, int id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
