package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NewsReadRepository extends JpaRepository<NewsReadEntity, Integer> {
    //list
    List<NewsReadEntity> findAllByUserIdAndStatus(Integer userId, Byte status);

    default List<NewsReadEntity> findAllByUserIdAndStatusNot(Integer userId, Byte status) {
        return null;
    }

    //native query
    @Query(nativeQuery = true, value = "select concat(news.id, ',', IF(`groupId` IS NULL, -1, groupId), ',',\n" +
            "              IF(`group` IS NULL, 'all', `group`)) as newsId_groupId\n" +
            "from news\n" +
            "       left join news_group on news.groupId = news_group.id\n" +
            "where news.status = 0\n" +
            "  and news.id is not null\n" +
            "  and (news_group.status = 0 or news_group.status is null)\n" +
            "  and news.id not in (?1)")
    List<String> getUnReadList(List<Integer> readList);

}
