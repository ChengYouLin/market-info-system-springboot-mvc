package com.example.dbms_group2.model.DTO;

public class ProductDTO {
    private String name;
    private String category;
    private Object price;

    public ProductDTO() {}

    public ProductDTO(String name, String category, Object price) {
        this.name = name;
        this.category = category;
        this.price = price;
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
}

