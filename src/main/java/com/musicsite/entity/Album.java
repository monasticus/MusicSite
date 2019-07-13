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
public class Album {

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
    private List<Performer> albumPerformers;


    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "tracks")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> albumTracks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }


    public List<Performer> getAlbumPerformers() {
        return albumPerformers;
    }

    public void setAlbumPerformers(List<Performer> albumPerformers) {
        this.albumPerformers = albumPerformers;
    }

    public List<Track> getAlbumTracks() {
        return albumTracks;
    }

    public void setAlbumTracks(List<Track> albumTracks) {
        this.albumTracks = albumTracks;
    }

    @Override
    public String toString() {
        return albumPerformers.toString() + " - " + yearOfPublication + " - " + name;
    }
}
