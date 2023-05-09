package com.bakulibrary.library.service;

import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.repository.ReadingListRepository;
import com.bakulibrary.library.service.inter.ReadingListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReadingListServiceImpl implements ReadingListService {

    private final ReadingListRepository readingListRepository;

    public ReadingListServiceImpl(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @Override
    public List<ReadingList> findReadingListByUser(User user) {
        return readingListRepository.findReadingListByUser(user);
    }

    @Override
    public ReadingList findReadingListByBookId(int id) {
        return readingListRepository.findReadingListByBook_Id(id);
    }

    @Override
    public ReadingList save(ReadingList readingList) {
        return readingListRepository.save(readingList);
    }

    @Transactional
    @Override
    public void deleteReadingListByUserIdAndBookId(int userId, int bookId) {
        readingListRepository.deleteReadingListByUser_IdAndBook_Id(userId, bookId);
    }

}
