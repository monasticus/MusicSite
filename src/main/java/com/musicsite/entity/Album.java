package com.musicsite.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album extends Opus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Pattern(regexp = "\\d{4}")
    @Column(name = "year_of_publication")
    private String yearOfPublication;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToMany (fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Performer> performers;


    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks = new ArrayList<>();

    @Column(precision = 3, scale = 2)
    private Double average;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Rating> ratings = new ArrayList<>();

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @PrePersist
    public void startAverage() {
        average = 0.0;
    }

    @PreUpdate
    public void updateAverage() {
        double sum = 0.0;
        for (Rating rating : ratings)
            sum += rating.getValue();


        average = sum / ratings.size();
    }

    @Override
    public String toString() {
        return performers.toString() + " - " + yearOfPublication + " - " + name;
    }
}
