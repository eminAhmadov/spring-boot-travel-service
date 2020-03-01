package com.example.travelservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Type(type="uuid-char")
    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(36)")
    private UUID userId;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date",
            nullable = false,
            updatable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Travel() {
    }

    public Travel(UUID userId, String origin, String destination, String comment, Date date) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.date = date;
    }

    public Travel(String userId, String origin, String destination, String comment, Date date) throws ParseException {
        this.userId = UUID.fromString(userId);
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.date = date;
    }

    public Travel(UUID userId, String origin, String destination, String comment, String date) throws ParseException {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public Travel(String userId, String origin, String destination, String comment, String date) throws ParseException {
        this.userId = UUID.fromString(userId);
        this.origin = origin;
        this.destination = destination;
        this.comment = comment;
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
}
