package com.manli.manli_java.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manli.manli_java.model_auto.NewsEntity;
import com.manli.manli_java.model_auto.NewsReadEntity;
import com.manli.manli_java.repository.NewsReadRepository;
import com.manli.manli_java.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    NewsRepository     newsRepository;
    @Autowired
    NewsReadRepository newsReadRepository;

    public Page getList(int page, int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "position");
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort);
        Page<NewsEntity> list = newsRepository.findAllByStatus(Byte.valueOf("0"), pageable);
        return list;
    }

    public JSONObject getList2(int page, int size, int userId, Integer groupId) {
        Sort sort = new Sort(Sort.Direction.ASC, "position");
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort);
        Page<NewsEntity> pageList;
        if (groupId != null) {
            pageList = newsRepository.findAllByStatusAndGroupId(Byte.valueOf("0"), groupId, pageable);
        } else {
            pageList = newsRepository.findAllByStatus(Byte.valueOf("0"), pageable);
        }

        List<Integer> readList = newsReadRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"))
                .stream().map(NewsReadEntity::getNewsId).collect(Collectors.toList());

        JSONObject jsonObject = (JSONObject) JSON.toJSON(pageList);
        JSONArray jsonArray_content = jsonObject.getJSONArray("content");
        for (int i = 0; i < jsonArray_content.size(); i++) {
            JSONObject jsonObject_news = jsonArray_content.getJSONObject(i);
            if (readList.contains(jsonObject_news.getInteger("id"))) {
                jsonObject_news.put("isRead", true);
            } else {
                jsonObject_news.put("isRead", false);
            }
        }

        return jsonObject;

    }

    public boolean isValid(int newsId) {
        return newsRepository.existsByStatus(Byte.valueOf("0"));
    }

    public void read(int newsId) {
        NewsEntity newsEntity = newsRepository.findOneById(newsId);
        if (null != newsEntity) {
            newsEntity.setStatus(Byte.valueOf("0"));
            newsRepository.save(newsEntity);
        }
    }

    public void test() {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setStatus(Byte.valueOf("0"));
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("status", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<NewsEntity> ex = Example.of(newsEntity, matcher);

        newsRepository.findAll(ex).stream().map(NewsEntity::toString).forEach(System.out::println);

    }

}
