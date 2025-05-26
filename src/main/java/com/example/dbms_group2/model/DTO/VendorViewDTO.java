package com.example.dbms_group2.model.DTO;

import java.math.BigDecimal;
import java.util.List;

public class VendorViewDTO {
    private int boothId;              // 攤位位置（1~20）（記得按照順序放！！！！！！！）
    private int boothCode;            // 攤商位置（因為前端的關係有了兩個一樣的！！！）（如1~20）
    private Long id;                     // 攤商主鍵，供收藏與評分用
    private String name;                 // 攤商名稱
    private String description;          // 簡介
    private String facebook;
    private String instagram;
    private String line;
    private String website;
    private String imageUrl;    //直接空白回傳！！！
    private BigDecimal rating;  //回傳該商家被評分的所有結果之平均！！
    private Integer favorited;           // 該使用者是否已收藏
    private String zoneIndex;           // 分區代號（0~4）
    private String zoneCode;             // 分區代號（一樣因為前端，所以跟前面的一樣！）（最多幾個是在後面就會被設定了，所以不用管數量，他就是數字）
    private String zoneName;             // 分區名稱
    private List<ProductDTO> productList;// 商品清單  如果為空就回傳為空！

    public VendorViewDTO(int boothId, int boothCode, Long id, String name, String description, String facebook, String instagram, String line, String website, String imageUrl, BigDecimal rating, Integer favorited, String zoneIndex, String zoneCode, String zoneName, List<ProductDTO> productList) {
        this.boothId = boothId;
        this.boothCode = boothCode;
        this.id = id;
        this.name = name;
        this.description = description;
        this.facebook = facebook;
        this.instagram = instagram;
        this.line = line;
        this.website = website;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.favorited = favorited;
        this.zoneIndex = zoneIndex;
        this.zoneCode = zoneCode;
        this.zoneName = zoneName;
        this.productList = productList;
    }

    public int getBoothId() {
        return boothId;
    }

    public void setBoothId(int boothId) {
        this.boothId = boothId;
    }

    public int getBoothCode() {
        return boothCode;
    }

    public void setBoothCode(int boothCode) {
        this.boothCode = boothCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
    }

    public String getZoneIndex() {
        return zoneIndex;
    }

    public void setZoneIndex(String zoneIndex) {
        this.zoneIndex = zoneIndex;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }
}

