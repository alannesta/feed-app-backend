package com.alan.feedapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alan.feedapp.models.Feed;
import com.alan.feedapp.repositories.FeedRepository;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner demo(FeedRepository repository) {
        return (args) -> {
            // save a couple of feeds
            repository.save(new Feed("Jack", "www.kaka.com"));
            repository.save(new Feed("Chloe", "www.kaka.com"));
            repository.save(new Feed("Kim", "www.kaka.com"));
            repository.save(new Feed("David", "www.kaka.com"));
            repository.save(new Feed("Michelle", "www.kaka.com"));

            // fetch all customers
            log.info("Feeds found with findAll():");
            log.info("-------------------------------");
            for (Feed feed : repository.findAll()) {
                log.info(feed.toString());
            }
            log.info("-------------------------------");

            // fetch an individual customer by ID
//            Feed feed = repository.findOne(1L);
//            log.info("Customer found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(feed.toString());
//            log.info("");
        };
    }
//    @Override
//    public void run(String... strings) throws Exception {
//
//        log.info("Creating tables");
//
//        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//
//        // Split up the array of whole names into an array of first/last names
//        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
//
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
//
//        log.info("Querying for customer records where first_name = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
//    }
}
