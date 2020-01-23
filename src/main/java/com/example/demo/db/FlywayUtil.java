package com.example.demo.db;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
	
	
	public static void migrate() {

		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456")
				.load();
		flyway.migrate();
	}

}
