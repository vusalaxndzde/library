package com.bakulibrary.library.service.inter;

import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;

import java.util.List;

public interface ReadingListService {

    List<ReadingList> findReadingListByUser(User user);

    ReadingList save(ReadingList readingList);

}
