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
    @Column(nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Comments parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comments> children = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 삭제 flag
    public void setIsDeleted() {
        this.isDeleted = Boolean.TRUE;
    }
//
//    public void setParent(Comments parent) {
//        this.parent = parent;
////        if (!parent.getChildren().contains(this)) {
////            parent.getChildren().add(this);
////        }
//    }

}
