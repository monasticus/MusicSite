package com.musicsite.entity;

import com.musicsite.validation.TrackValidationGroup;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tracks")
public class Track extends Opus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {TrackValidationGroup.class, Default.class})
    @Size(min = 2, max = 50, groups = {TrackValidationGroup.class, Default.class})
    private String name;

    @Pattern(regexp = "\\d{4}", groups = {TrackValidationGroup.class, Default.class})
    @Column(name = "year_of_publication")
    private String yearOfPublication;

    @ManyToOne
    private Album album;

    @ManyToOne
    @NotNull(groups = TrackValidationGroup.class)
    private Performer performer;

    @Column(precision = 3, scale = 2)
    private Double average;

    @OneToMany(mappedBy = "track")
    private List<Rating> ratings = new ArrayList<>();

    @ManyToOne
    @NotNull
    private Category category;

    @Column(columnDefinition = "BIT")
    private boolean proposition;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Track() {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public void updateAverage() {
        double sum = 0.0;
        for (Rating rating : ratings)
            sum += rating.getRating();


        average = sum / ratings.size();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @PrePersist
    public void prePer() {
        average = 0.0;
        name = name.trim();
    }

    @PreUpdate
    public void preUp() {
        name = name.trim();
    }

    public boolean isProposition() {
        return proposition;
    }

    public void setProposition(boolean proposition) {
        this.proposition = proposition;
    }

    @Override
    public String toString() {
        return performer.getPseudonym() + " - " + name + " (" + yearOfPublication + ")";
    }
}
