package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.NewsEntity;
import com.manli.manli_java.model_auto.NewsGroupEntity;
import com.manli.manli_java.model_auto.NewsReadEntity;
import com.manli.manli_java.model_impl.NewsGroupImpl1;
import com.manli.manli_java.repository.NewsGroupRepository;
import com.manli.manli_java.repository.NewsReadRepository;
import com.manli.manli_java.repository.NewsRepository;
import com.manli.manli_java.util.TupleThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsGroupService {
    @Autowired
    NewsRepository      newsRepository;
    @Autowired
    NewsReadRepository  newsReadRepository;
    @Autowired
    NewsGroupRepository newsGroupRepository;


    private Integer __getGroupIdByNewsId(List<NewsEntity> newsList, Integer newsId) {
        if (newsId == null) {
            return null;
        }

        Optional<NewsEntity> o = newsList.stream().filter((NewsEntity newsEntity) -> {
            return newsEntity.getId() == newsId;
        }).findFirst();
        if (o.isPresent()) {
            return o.get().getGroupId();
        }

        return null;
    }

    private String __getGroupByGroupId(List<NewsGroupEntity> newsGroupEntityList, Integer groupId) {
        if (groupId == null) {
            return null;
        }

        Optional<NewsGroupEntity> o = newsGroupEntityList.stream().filter((NewsGroupEntity newsGroupEntity) -> {
            return newsGroupEntity.getId() == groupId;
        }).findFirst();

        if (o.isPresent()) {
            return o.get().getGroup();
        }

        return null;
    }

    public List<NewsGroupImpl1> getList(Integer userId) {
        List<NewsEntity> newsList = newsRepository.findAllByStatus(Byte.valueOf("0"));
        List<NewsGroupEntity> newsGroupEntityList = newsGroupRepository.findAllByStatus(Byte.valueOf("0"));


        List<NewsReadEntity> unreadEntityList = newsReadRepository.findAllByUserIdAndStatusNot(userId, Byte.valueOf("0"));
        if (null == unreadEntityList || unreadEntityList.size() == 0) {
            unreadEntityList = new ArrayList<>();
        }

        Map<Integer, List<TupleThree<Integer, Integer, String>>> map = unreadEntityList.stream()
                .map((NewsReadEntity newsReadEntity) -> {
                    Integer newsId = newsReadEntity.getNewsId();
                    Integer groupId = __getGroupIdByNewsId(newsList, newsId);
                    String group = __getGroupByGroupId(newsGroupEntityList, groupId);

                    return new TupleThree<>(newsId, groupId, group);

                })
                .collect(Collectors.groupingBy((TupleThree tupleThree) -> {
                    if (null == tupleThree.second) {
                        return -1;
                    }
                    return (Integer) tupleThree.second;
                }));

        List<NewsGroupImpl1> list = new ArrayList<>();
        Integer totalUnReadCount = 0;
        NewsGroupImpl1 totalNewsGroupImpl1 = new NewsGroupImpl1();
        totalNewsGroupImpl1.setGroupId(-1);
        totalNewsGroupImpl1.setGroup("全局");

        for (Integer key : map.keySet()) {
            if (key == -1) {
                list.add(totalNewsGroupImpl1);
                ;
            } else {
                NewsGroupImpl1 newsGroupImpl1 = new NewsGroupImpl1();
                newsGroupImpl1.setGroupId(map.get(key).get(0).second);
                newsGroupImpl1.setGroup(map.get(key).get(0).third);
                newsGroupImpl1.setUnreadCount(map.get(key).size());

                totalUnReadCount += newsGroupImpl1.getUnreadCount();

                list.add(newsGroupImpl1);
            }
        }
        totalNewsGroupImpl1.setUnreadCount(totalUnReadCount);

        return list;
    }


}
