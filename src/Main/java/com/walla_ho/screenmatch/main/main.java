package com.walla_ho.screenmatch.main;

import com.walla_ho.screenmatch.model.DataEpisode;
import com.walla_ho.screenmatch.model.DataSeason;
import com.walla_ho.screenmatch.model.DataSerie;
import com.walla_ho.screenmatch.model.Episode;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class main {
    private Scanner read = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConvert convert = new DataConvert();
    private final String  ADDRESS = "http://www.omdbapi.com/?t=";
    private final String  APIKEY = "&apikey=6585022c";

    public void showMenu(){
        System.out.println("Write down the serie name:");
        var serie_name = read.nextLine();

        //search
        var json = consumeAPI.getData( ADDRESS + serie_name.replaceAll(" ", "+") + APIKEY);

        List<DataSeason> seasons = new ArrayList<>();
        DataSerie serie = convert.consumeData(json, DataSerie.class);

//        System.out.println(serie);

        for( int i = 1; i <= serie.totalSeasons(); i++ ){
            json = consumeAPI.getData( ADDRESS + serie_name.replaceAll(" ", "+") + "&season=" + i + APIKEY);
            DataSeason season = convert.consumeData(json, DataSeason.class);
            seasons.add(season);
        }
//        seasons.forEach(System.out::println);


//        for( int s = 0; s < serie.totalSeasons(); s++ ){
//            List<DataEpisode> episodes = seasons.get(s).episodes();
//            for( int e = 0; e < seasons.size(); e++ ){
//                System.out.println(episodes.get(e).title());
//            }
//        }
        //Objeto(parametro -> expressaop)
//        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

//        //toList = Lista Imutavel
//        //Collectors.toList() = Lista Mutavel
//        List<DataEpisode> dataEpisodes = seasons.stream()
//                .flatMap(s -> s.episodes().stream())
//                .toList(); //.collect(Collectors.toList());

//        System.out.println("\nTop 5 episodes:");
//        episodes.stream()
//                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        System.out.println("\nEpisodes list:");
        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.season(), d))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);
    }
}
