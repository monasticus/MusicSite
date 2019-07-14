package com.musicsite.entity;

import com.musicsite.repository.CategoryValidationGroup;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends Ens{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {CategoryValidationGroup.class, Default.class})
    @Size(min = 2, max = 25, groups = {CategoryValidationGroup.class, Default.class})
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "category: " + name;
    }
}
