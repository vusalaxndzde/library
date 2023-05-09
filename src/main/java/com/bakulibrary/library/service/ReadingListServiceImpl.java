package com.bakulibrary.library.service;

import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.repository.ReadingListRepository;
import com.bakulibrary.library.service.inter.ReadingListService;
import org.springframework.stereotype.Service;

@Service
public class ReadingListServiceImpl implements ReadingListService {

    private final ReadingListRepository readingListRepository;

    public ReadingListServiceImpl(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @Override
    public ReadingList save(ReadingList readingList) {
        return readingListRepository.save(readingList);
    }

}
