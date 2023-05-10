package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {

    List<ReadingList> findReadingListByUser(User user);

    ReadingList findReadingListByUser_IdAndBook_Id(int userId, int bookId);

    void deleteReadingListByUser_IdAndBook_Id(int userId, int bookId);

}
