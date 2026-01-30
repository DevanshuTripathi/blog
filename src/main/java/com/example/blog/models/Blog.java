package com.example.blog.models;

import jakarta.persistence.*;

@Entity
public class Blog {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Transient
    public String getPreview() {
        if (body == null || body.isBlank()) {
            return "";
        }

        int dotIndex = body.indexOf(".");
        if (dotIndex == -1 || dotIndex > 150) {
            return body.length() > 150
                    ? body.substring(0, 150) + "..."
                    : body;
        }

        return body.substring(0, dotIndex + 1);

    }

}
