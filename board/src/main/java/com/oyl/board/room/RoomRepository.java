package com.oyl.board.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

//    Page<Room> findAllByStartDateAndEndDate(@PageableDefault(size = 5)Pageable pageable);
}
