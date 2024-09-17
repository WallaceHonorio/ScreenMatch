/*  TEST SPRING WITHOUT WEB */
package com.walla_ho.screenmatch;

import com.walla_ho.screenmatch.model.DataSerie;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;
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
		System.out.println("Teste spring");

		var consumeAPI = new ConsumeAPI();
		var json = consumeAPI.getData("https://www.omdbapi.com/?t=gilmore+girls&apikey=f3723d13");

		System.out.println(json);

		DataConvert convert = new DataConvert();
		DataSerie data = convert.consumeData(json, DataSerie.class);

		System.out.println(data);
	}
}