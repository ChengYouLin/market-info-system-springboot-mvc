package com.example.dbms_group2.model.DTO;

public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private String target;

    public NoticeDTO(Long id, String title, String content, String target) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.target = target;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
