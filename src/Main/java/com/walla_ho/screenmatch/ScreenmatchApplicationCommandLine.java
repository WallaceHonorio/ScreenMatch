/*  TEST SPRING WITHOUT WEB */
package com.walla_ho.screenmatch;

import com.walla_ho.screenmatch.main.main;
import com.walla_ho.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class ScreenmatchApplicationCommandLine implements CommandLineRunner {
//
//	@Autowired
//	private SerieRepository repository;
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenmatchApplicationCommandLine.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		main main = new main(repository);
//		main.showMenu();
//	}
//}