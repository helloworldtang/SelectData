package com.domain.biz;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangcheng on 2017/2/18.
 */
@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String version;
    private Boolean rate;
    private String title;
    private String comment;
    private String country;
    private Long app;
    @Column(name = "retrieved_date")
    private Date retrievedDate;
    private Boolean emailed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getApp() {
        return app;
    }

    public void setApp(Long app) {
        this.app = app;
    }

    public Date getRetrievedDate() {
        return retrievedDate;
    }

    public void setRetrievedDate(Date retrievedDate) {
        this.retrievedDate = retrievedDate;
    }

    public Boolean getEmailed() {
        return emailed;
    }

    public void setEmailed(Boolean emailed) {
        this.emailed = emailed;
    }
}
