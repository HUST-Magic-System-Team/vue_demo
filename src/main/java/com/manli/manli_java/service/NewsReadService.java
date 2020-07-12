package com.manli.manli_java.service;


import com.manli.manli_java.repository.NewsGroupRepository;
import com.manli.manli_java.repository.NewsReadRepository;
import com.manli.manli_java.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsReadService {
    @Autowired
    NewsRepository      newsRepository;
    @Autowired
    NewsReadRepository  newsReadRepository;
    @Autowired
    NewsGroupRepository newsGroupRepository;

    public int getTotalUnreadCount() {
        return 0;
    }
}
