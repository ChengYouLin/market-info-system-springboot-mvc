package com.example.dbms_group2.model.DTO;

public class StampDTO {
    private boolean correct;

    public StampDTO(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}
