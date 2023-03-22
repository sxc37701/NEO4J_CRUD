package com.advDB.neoCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */
@SpringBootApplication
@EnableNeo4jRepositories
public class NeoCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(NeoCrudApplication.class, args);
	}
}
