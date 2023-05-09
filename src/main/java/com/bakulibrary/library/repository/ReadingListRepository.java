package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {

    List<ReadingList> findReadingListByUser(User user);

    ReadingList findReadingListByBook_Id(int id);

    void deleteReadingListByBook_Id(int id);

}
