package com.musicsite.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "performers")
public class Performer extends Ens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(unique = true)
    private String pseudonym;

    @Size(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "performer", cascade = CascadeType.PERSIST)
    @Column()
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(mappedBy = "performer")
    private List<Track> tracks = new ArrayList<>();

    @Column(precision = 3, scale = 2)
    private Double average;

    @OneToMany(mappedBy = "performer")
    private List<Rating> ratings = new ArrayList<>();

    @Column(columnDefinition = "BIT")
    private boolean proposition;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private List<Category> categories;


//    private String wiki;
//
//    private String lastFm;
//
//    private String spotify;


    public Performer() {
        proposition = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void updateAverage() {
        double sum = 0.0;
        for (Rating rating : ratings)
            sum += rating.getRating();


        average = sum / ratings.size();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @PrePersist
    public void prePers() {
        average = 0.0;
        pseudonym = pseudonym.trim();
    }

    @PreUpdate
    public void preUp() {
        pseudonym = pseudonym.trim();
    }

    public boolean isProposition() {
        return proposition;
    }

    public void setProposition(boolean proposition) {
        this.proposition = proposition;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return pseudonym + " (performer)";
    }

//    public String getWiki() {
//        return wiki;
//    }
//
//    public void setWiki(String wiki) {
//        this.wiki = wiki;
//    }
//
//    public String getLastFm() {
//        return lastFm;
//    }
//
//    public void setLastFm(String lastFm) {
//        this.lastFm = lastFm;
//    }
//
//    public String getSpotify() {
//        return spotify;
//    }
//
//    public void setSpotify(String spotify) {
//        this.spotify = spotify;
//    }
//
//    @PrePersist
//    public void fillLinks() {
//        spotify = "https://open.spotify.com/search/results".concat(name);
//        wiki = "https://en.wikipedia.org/wiki/".concat(name);
//
//        String sufix = name;
//        String[] partedName = name.split(" ");
//        if (partedName.length != 1)
//              sufix= String.join("+", partedName);
//
//        lastFm = "https://www.last.fm/music/" + sufix;
//    }


}
