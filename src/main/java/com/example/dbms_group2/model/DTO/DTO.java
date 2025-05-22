package com.example.dbms_group2.model.DTO;

public class DTO {
    private boolean activity;
    private boolean leftover;
    private boolean product;

    public DTO(boolean activity, boolean leftover, boolean product) {
        this.activity = activity;
        this.product = product;
        this.leftover = leftover;
    }

    public boolean isLeftover() {
        return leftover;
    }

    public void setLeftover(boolean leftover) {
        this.leftover = leftover;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }


    public boolean isProduct() {
        return product;
    }

    public void setProduct(boolean product) {
        this.product = product;
    }
}
