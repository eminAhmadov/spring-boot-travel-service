package com.example.travelservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BINARY(36)")
    private UUID userId;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date",
            nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "added_on",
            nullable = false,
            updatable = false)
    private LocalDateTime addedOn = LocalDateTime.now();

    public Travel() {
    }

    public Travel(UUID userId, String origin, String destination, String comment, String facebook, String instagram, String mobile, Date date) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.facebook = facebook;
        this.instagram = instagram;
        this.mobile = mobile;
        this.date = date;
    }

    public Travel(String userId, String origin, String destination, String comment, String facebook, String instagram, String mobile, Date date) {
        this.userId = UUID.fromString(userId);
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.facebook = facebook;
        this.instagram = instagram;
        this.mobile = mobile;
        this.date = date;
    }

    public Travel(UUID userId, String origin, String destination, String comment, String facebook, String instagram, String mobile, String date) throws ParseException {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.facebook = facebook;
        this.instagram = instagram;
        this.mobile = mobile;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public Travel(String userId, String origin, String destination, String comment, String facebook, String instagram, String mobile, String date) throws ParseException {
        this.userId = UUID.fromString(userId);
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.facebook = facebook;
        this.instagram = instagram;
        this.mobile = mobile;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = UUID.fromString(userId);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String from) {
        this.origin = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String to) {
        this.destination = to;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
