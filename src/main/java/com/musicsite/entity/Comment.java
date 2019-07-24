package com.musicsite.entity;

import com.musicsite.validation.CleanLanguage;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @NotBlank
    @CleanLanguage
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Track track;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Performer performer;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Track getTrack() {
        return track;
    }

    public Comment setTrack(Track track) {
        this.track = track;
        return this;
    }

    public Album getAlbum() {
        return album;
    }

    public Comment setAlbum(Album album) {
        this.album = album;
        return this;
    }

    public Performer getPerformer() {
        return performer;
    }

    public Comment setPerformer(Performer performer) {
        this.performer = performer;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Comment setUser(User user) {
        this.user = user;
        return this;
    }
}
