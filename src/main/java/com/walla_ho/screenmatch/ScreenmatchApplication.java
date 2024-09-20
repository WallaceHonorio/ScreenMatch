/*  TEST SPRING WITHOUT WEB */
package com.walla_ho.screenmatch;

import com.walla_ho.screenmatch.main.main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main main = new main();
		main.showMenu();
	}
}