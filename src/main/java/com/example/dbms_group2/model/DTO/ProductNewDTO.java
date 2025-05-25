package com.example.dbms_group2.model.DTO;

public class ProductNewDTO {
    private String name;
    private String category;
    private int price;
    private int id;

    public ProductNewDTO() {}
    public ProductNewDTO(String name, String category, int price, int id) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
