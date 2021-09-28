package com.oyl.board.comment;

import com.oyl.board.common.BaseTimeEntity;
import com.oyl.board.member.Member;
import com.oyl.board.post.Post;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "t_comment")
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long commentsId;

    @Column(length = 255)
    private String email;

    @Lob
    private String comments;

    @Setter
    private Boolean isDeleted = Boolean.FALSE;

    // TODO: @ManyToOne(fetch = FetchType.LAZY)

    @Setter
    @ManyToOne
    @JoinColumn(name = "parents_id")
    private Comments parent;

    @OneToMany
    private List<Comments> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
//
//    public void setParent(Comments parent) {
//        this.parent = parent;
////        if (!parent.getChildren().contains(this)) {
////            parent.getChildren().add(this);
////        }
//    }

}
