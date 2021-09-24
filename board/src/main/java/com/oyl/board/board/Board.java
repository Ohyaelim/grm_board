package com.oyl.board.board;

import lombok.*;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "t_board")
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="board_id")
    private Long boardId;

    @Column(name = "board_type" ,nullable = false, length=50)
    private String boardType;
}
