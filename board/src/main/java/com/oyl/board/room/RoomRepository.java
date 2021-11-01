package com.oyl.board.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByIsPinned(Boolean isPinned);
    Room findRoomByIsPinnedAndIsDeletedNot(Boolean isPinned, Boolean isDeleted);

    Page<Room> findAllByIsDeletedOrderByStartDate(Boolean isDeleted, @PageableDefault(size = 5)Pageable pageable);

//    Page<Room> findAllByStartDateAndEndDate(@PageableDefault(size = 5)Pageable pageable);

//    Optional<Room> findById(Long id);
}
