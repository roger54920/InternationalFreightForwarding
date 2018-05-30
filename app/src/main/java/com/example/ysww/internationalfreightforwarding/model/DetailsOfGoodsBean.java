package com.example.ysww.internationalfreightforwarding.model;

/**
 * 货品详情
 */

public class DetailsOfGoodsBean {
    private String number;
    private String weight;
    private String length;
    private String width;
    private String height;

    public DetailsOfGoodsBean(String number, String weight, String length, String width, String height) {
        this.number = number;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
