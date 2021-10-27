package com.oyl.board.participant;

import com.oyl.board.member.Member;
import com.oyl.board.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_participant")
public class Participant {

    @Id
    @Column(name = "participant_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long participantId;

    // TODO leaveReason 넣어주기

    @OneToOne
    @JoinColumn(name = "memberId") // TODO member_id로 바꿔
    private Member member;

    @JsonIgnore
    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
