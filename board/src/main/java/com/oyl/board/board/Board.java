package com.oyl.board.board;

import lombok.*;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="board_id")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long board_id;

    @Column(nullable = false, length=50)
    @Setter
    private String boardType;
}
