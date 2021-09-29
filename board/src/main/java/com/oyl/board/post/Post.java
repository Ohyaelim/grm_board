package com.oyl.board.post;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.oyl.board.board.Board;
import com.oyl.board.common.BaseTimeEntity;
import com.oyl.board.member.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_post")
public class Post {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name="nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name="reg_date")
    private LocalDate regDate;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;


    public void update(Post post){
        this.content =post.content;
        this.title = post.title;
        this.regDate = post.regDate;
    }

}