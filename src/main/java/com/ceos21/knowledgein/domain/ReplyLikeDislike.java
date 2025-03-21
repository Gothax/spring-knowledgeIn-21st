package com.ceos21.knowledgein.domain;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reply_like_dislike")
public class ReplyLikeDislike extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated
    private LikeDislike likeDislike;

    @JoinColumn(name = "reply_id", referencedColumnName = "id")
    @ManyToOne(fetch = LAZY)
    private Reply reply;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = LAZY)
    private UserEntity user;
}
