package com.ceos21.knowledgein.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2048)
    private String content;
    private Long viewCount;
    private boolean nicknamePublic;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = LAZY)
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<HashTag> hashTags;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies;

    @Builder
    public Post(String title, String content, boolean nicknamePublic, List<HashTag> hashTags, List<PostImage> postImages, UserEntity user) {
        this.title = title;
        this.content = content;
        this.nicknamePublic = nicknamePublic;
        this.postImages = postImages;
        this.hashTags = hashTags;
        this.user = user;


        this.viewCount = 0L;
        if (postImages != null) {
            postImages.forEach(postImage -> postImage.setPost(this));
        }
        if (hashTags != null) {
            hashTags.forEach(hashTag -> hashTag.setPost(this));
        }
    }

    public void addPostImage(PostImage postImage) {
        postImages.add(postImage);
        if (postImage.getPost() != this) {
            postImage.setPost(this);
        }
    }

    public void addHashTag(HashTag hashTag) {
        hashTags.add(hashTag);
        if (hashTag.getPost() != this) {
            hashTag.setPost(this);
        }
    }
}
