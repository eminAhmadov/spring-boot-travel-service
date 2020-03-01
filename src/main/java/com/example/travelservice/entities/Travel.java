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
@Table(name = "users")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type="uuid-char")
    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(36)")
    private UUID userId;

    @Column(name = "from", nullable = false)
    private String from;

    @Column(name = "to", nullable = false)
    private String to;

    @Column(name = "date",
            nullable = false,
            updatable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Travel() {
    }

    public Travel(UUID userId, String from, String to, Date date) {
        this.userId = userId;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Travel(String userId, String from, String to, Date date) throws ParseException {
        this.userId = UUID.fromString(userId);
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Travel(UUID userId, String from, String to, String date) throws ParseException {
        this.userId = userId;
        this.from = from;
        this.to = to;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public Travel(String userId, String from, String to, String date) throws ParseException {
        this.userId = UUID.fromString(userId);
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
