package com.oyl.board.post;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.oyl.board.board.Board;
import com.oyl.board.common.BaseTimeEntity;
import com.oyl.board.member.Member;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="postId", callSuper = false)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long postId;

    @Column(name="title", nullable = false, length=100)
    @Setter
    private String title;

    @Column(name="content", length = 10000)
    @Setter
    private String content;


    @Column(name="email", length = 50)
    @Setter
    private String email;

    @Column(name="commentsLength", length = 50)
    @Setter
    private Long commentsLength;

    @Builder.Default
    @Column(name="isDeleted", length = 10)
    @Setter
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    @Setter
    private Board board;

    protected Board getBoard(){
        return board;
    }

    public Post setUpdate(String title, String content){
        this.title=title;
        this.content=content;
        return this;
    }

}
