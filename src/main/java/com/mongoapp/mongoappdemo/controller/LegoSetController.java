package com.mongoapp.mongoappdemo.controller;

import com.mongoapp.mongoappdemo.domain.LegoSet;
import com.mongoapp.mongoappdemo.domain.LegoSetDifficulty;
import com.mongoapp.mongoappdemo.repository.LegoSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/legoset/api")
public class LegoSetController {

    /*using MongoRepository*/
    @Autowired
    private LegoSetRepository legoSetRepository;

    Logger logger = LoggerFactory.getLogger(LegoSetController.class);


    /*Save records*/
    @PostMapping("/storeresults")
    public void insert(@RequestBody LegoSet legoset) {
        legoSetRepository.insert(legoset);
        logger.info("------record successfully inserted------");
    }

    /*Read all records*/
    @GetMapping("/fetchresults")
    public Collection<LegoSet> fetchAll() {
        logger.info("------record successfully fetched------");
        return legoSetRepository.findAll();
    }

    /*Update records*/
    @PutMapping("/updateresults")
    public void update(@RequestBody LegoSet legoSet) {
        legoSetRepository.save(legoSet);
        logger.info("------record successfully updated------");

    }

    /*Delete records*/
    @DeleteMapping("/removeresults/{id}")
    public void remove(@PathVariable String id) {
        legoSetRepository.deleteById(id);
        logger.info("------record successfully deleted------");

    }

    /*Read records by id*/
    @GetMapping("/fetchrecordsbyid/{id}")
    public Optional<LegoSet> fetchById(@PathVariable String id) {

        Optional<LegoSet> legoSet = legoSetRepository.findById(id);
        logger.info("------record fetched by id------");
        return legoSet;

    }

    /*Read records by name*/
    @GetMapping("/fetchrecordsbyname/{name}")
    public LegoSet fetchByName(@PathVariable String name) {

        LegoSet legoSet = legoSetRepository.findByName(name);
        if (legoSet != null) {
            logger.info("------record fetched by name------");
            return legoSet;
        } else {
            logger.error("error---> Record of the given name not found");
            return null;
        }
    }

    /*Read records by letter starting with or word starting with*/
    @GetMapping("/fetchrecordsstartingwith/{prefix}")
    public Collection<LegoSet> fetchByPrefixName(@PathVariable String prefix) {
        return legoSetRepository.findByNameStartsWith(prefix);
    }

    /*Read records by name and theme*/
    @GetMapping("/fetchrecordsbynameandtheme/{name}/{theme}")
    public Collection<LegoSet> fetchByNameAndTheme(@PathVariable String name, @PathVariable String theme) {
        Collection<LegoSet> legoSets = legoSetRepository.findByNameAndThemeStartsWith(name, theme);
        if (legoSets != null) {
            logger.info("------record fetched by name and theme------");
            return legoSets;
        } else {
            logger.error("error-----> no record found");
            return null;
        }
    }

    /*Read records by name and difficulty level---> StartingWith()*/
    @GetMapping("/fetchrecordsbydifficultyandname/{difficulty}/{name}")
    public Collection<LegoSet> fetchByDifficultyAndName(@PathVariable LegoSetDifficulty difficulty, @PathVariable String name) {
        Collection<LegoSet> legoSets = legoSetRepository.findByDifficultyAndNameStartingWith(difficulty, name);
        if (legoSets != null) {
            logger.info("------record fetched by difficulty and name------");
            return legoSets;
        } else {
            logger.error("error------> no record found ");
            return null;
        }
    }

    /*Read records by name and difficulty level--> IsStartingWith() */
    @GetMapping("/fetchrecordsbydifficultyandnamestarts/{difficulty}/{name}")
    public Collection<LegoSet> fetchByDifficultyAndNameStarts(@PathVariable LegoSetDifficulty difficulty, @PathVariable String name) {
        Collection<LegoSet> legoSets = legoSetRepository.findByDifficultyAndNameIsStartingWith(
                difficulty, name);
        if (legoSets != null) {
            logger.info("------record fetched by difficulty and name ------");
            return legoSets;
        } else {
            logger.error("error------> no record found ");
            return null;
        }
    }

    /* read records by fetching data from subdocument*/
    @GetMapping("/fetchrecordsbypricegreaterthan/{price}")
    public Collection<LegoSet> fetchByPriceGreaterThan(@PathVariable Integer price) {
        Collection<LegoSet> legoSets = legoSetRepository.findByDeliveryPriceGreaterThan(price);
        if (legoSets != null) {
            logger.info("-----record fetched by price greater than----->" + price);
            return legoSets;
        } else {
            logger.error("error------> no record found ");
            return null;
        }
    }

    /*read records by fetching data from arrays or list within a document*/
    @GetMapping("fetchrecordsbyrating/{rating}")
    public Collection<LegoSet> fetchByRating(@PathVariable Integer rating) {
        Collection<LegoSet> legoSets = legoSetRepository.findByUserRating(rating);
        if (legoSets != null) {
            logger.info("-----record fetched by rating----->" + rating);
            return legoSets;
        } else {
            logger.error("error------> no record found ");
            return null;
        }

    }
    
    /*findAll and sort the records*/
    @GetMapping("/fetchallandsortbyname")
    public Collection<LegoSet> fetchAllAndSort(){
        Sort sortByName= Sort.by("name").ascending();
        return legoSetRepository.findAll(sortByName);
    }

    /*find records by theme and sort them*/
    @GetMapping("/fetchbythemeandsort/{theme}")
    public Collection<LegoSet> fetchByTheme(@PathVariable String theme){
        Sort sortByTheme= Sort.by("theme").ascending();
        return legoSetRepository.findByThemeContains(theme,sortByTheme);
    }

    /*@GetMapping("filterbybestbuys")
    public Collection<LegoSet> fetchByBestBuys(){
        
    }*/
}
