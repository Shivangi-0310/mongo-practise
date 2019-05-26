package com.mongoapp.mongoappdemo.repository;

import com.mongoapp.mongoappdemo.domain.LegoSet;
import com.mongoapp.mongoappdemo.domain.LegoSetDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    LegoSet findByName(String name);

    Collection<LegoSet> findByNameStartsWith(String prefix);

    Collection<LegoSet> findByNameAndThemeStartsWith(String name, String theme);

    Collection<LegoSet> findByDifficultyAndNameStartingWith(LegoSetDifficulty difficulty, String name);

    Collection<LegoSet> findByDifficultyAndNameIsStartingWith(LegoSetDifficulty difficulty, String name);

    @Query("{'delivery.deliveryFee':{$gt:?0} }")
    Collection<LegoSet> findByDeliveryPriceGreaterThan(Integer price);

    @Query("{'productReviews.rating':{$eq:?0} }")
    Collection<LegoSet> findByUserRating(Integer rating);

    Collection<LegoSet> findByThemeContains(String theme, Sort sort);

}
