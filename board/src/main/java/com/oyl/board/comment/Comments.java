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
@EqualsAndHashCode(of = "commentsId", callSuper = false)
@Entity
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long commentsId;
    @Setter
    private String email;
    @Setter
    private String comments;
    @Setter
    private Boolean isDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Comments parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval = true)
    private List<Comments> children = new ArrayList<Comments>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;

    public void setParent(Comments parent) {
        this.parent = parent;
        if (!parent.getChildren().contains(this)) {
            parent.getChildren().add(this);
        }
    }

}
