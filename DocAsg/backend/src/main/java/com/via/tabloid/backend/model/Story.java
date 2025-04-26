package com.via.tabloid.backend.model;

import jakarta.persistence.*;

@Entity

@Table(name = "stories")

public class Story {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;

    private String content;

    private String department;

    public Story() {

    }

    public Story(String title, String content, String department) {

        this.title = title;

        this.content = content;

        this.department = department;

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

    public String getDepartment() {

        return department;

    }

    public void setDepartment(String department) {

        this.department = department;

    }

}
