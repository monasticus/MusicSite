package com.musicsite.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performers")
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String pseudonym;

    @Size(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "performers", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(mappedBy = "performers", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks = new ArrayList<>();

    @Column(name = "image_link")
    private String imageLink;


//    private String wiki;
//
//    private String lastFm;
//
//    private String spotify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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
