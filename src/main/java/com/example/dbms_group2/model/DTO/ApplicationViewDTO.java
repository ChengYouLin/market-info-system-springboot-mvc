package com.example.dbms_group2.model.DTO;

import java.time.LocalDate;
import java.util.List;

public class ApplicationViewDTO {
    private Long id;
    private LocalDate date;
    private String email;
    private String name;
    private String status;

    private String boothCode;
    private String description;
    private String facebook;
    private String instagram;
    private String line;
    private String website;

    private List<ProductDTO> products;

    public ApplicationViewDTO(Long id, LocalDate date, String email, String name, String status, String boothCode, String description, String facebook, String instagram, String line, String website, List<ProductDTO> products) {
        this.id = id;
        this.date = date;
        this.email = email;
        this.name = name;
        this.status = status;
        this.boothCode = boothCode;
        this.description = description;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(String boothCode) {
        this.boothCode = boothCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
