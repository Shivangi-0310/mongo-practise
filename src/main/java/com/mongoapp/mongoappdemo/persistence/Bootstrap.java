package com.mongoapp.mongoappdemo.persistence;

import com.mongoapp.mongoappdemo.domain.DeliveryInfo;
import com.mongoapp.mongoappdemo.domain.LegoSet;
import com.mongoapp.mongoappdemo.domain.LegoSetDifficulty;
import com.mongoapp.mongoappdemo.domain.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Service
public class Bootstrap implements CommandLineRunner {

    /*using MongoRepository*/
    @Autowired
    private MongoRepository mongoRepository;

    Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Override
    @EventListener(ContextStartedEvent.class)
    public void run(String... args) throws Exception {

        logger.info("------Application UP------");

        /*Drop collection if already exists*/
        mongoRepository.deleteAll();
        logger.info("------record successfully deleted if existed  while bootstraping------");


/*
         Lego set dummy data
*/
        LegoSet classicSpace =
                new LegoSet("classicSpace", LegoSetDifficulty.HARD, "space",
                        new DeliveryInfo(LocalDate.now().plusDays(2), 30, true),
                        Arrays.asList(new ProductReview("Aakash", 9),
                                new ProductReview("Shivangi", 6)));

        LegoSet classicTown =
                new LegoSet("classicTown", LegoSetDifficulty.MEDIUM, "town",
                        new DeliveryInfo(LocalDate.now().plusDays(6), 80, true),
                        Arrays.asList(new ProductReview("Samayra", 8),
                                new ProductReview("Mary", 10),
                                new ProductReview("Gabby", 8)));

        LegoSet blackFalcon =
                new LegoSet("blackFalcon", LegoSetDifficulty.EASY, "castle",
                        new DeliveryInfo(LocalDate.now().plusDays(1), 20, true),
                        Arrays.asList(new ProductReview("Peter", 9),
                                new ProductReview("Aakash", 4),
                                new ProductReview("James", 7)));


        LegoSet classicCastle =
                new LegoSet("classicCastle", LegoSetDifficulty.MEDIUM, "town",
                        new DeliveryInfo(LocalDate.now().plusDays(5), 85, true),
                        Arrays.asList(new ProductReview("Adam", 9),
                                new ProductReview("Aakash", 4),
                                new ProductReview("Aron", 9)));


        Collection<LegoSet> dummyData = Arrays.asList(classicSpace, classicTown, blackFalcon, classicCastle);
        mongoRepository.insert(dummyData);
        logger.info("------records successfully inserted while bootstraping------");

    }
}
