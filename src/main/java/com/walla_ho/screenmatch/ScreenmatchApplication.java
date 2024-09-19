/*  TEST SPRING WITHOUT WEB */
package com.walla_ho.screenmatch;

import com.walla_ho.screenmatch.model.DataEpisode;
import com.walla_ho.screenmatch.model.DataSeason;
import com.walla_ho.screenmatch.model.DataSerie;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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
		DataSerie serie = convert.consumeData(json, DataSerie.class);

		json = consumeAPI.getData("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DataEpisode episode = convert.consumeData(json, DataEpisode.class);

		System.out.println(serie);
		System.out.println(episode);

		List<DataSeason> seasons = new ArrayList<>();

		for( int i = 1; i <= serie.totalSeasons(); i++ ){
			json = consumeAPI.getData("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
			DataSeason season = convert.consumeData(json, DataSeason.class);
			seasons.add(season);
		}
		seasons.forEach(System.out::println);
	}
}