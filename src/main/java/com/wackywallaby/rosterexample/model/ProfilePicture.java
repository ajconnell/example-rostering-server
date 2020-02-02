package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "profile_pictures")
public class ProfilePicture extends RosterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "s3_key")
    private String s3Key;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

}
