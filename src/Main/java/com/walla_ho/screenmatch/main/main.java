package com.walla_ho.screenmatch.main;

import com.walla_ho.screenmatch.model.DataEpisode;
import com.walla_ho.screenmatch.model.DataSeason;
import com.walla_ho.screenmatch.model.DataSerie;
import com.walla_ho.screenmatch.model.Episode;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
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


        //=================================================================================
//        for( int s = 0; s < serie.totalSeasons(); s++ ){
//            List<DataEpisode> episodes = seasons.get(s).episodes();
//            for( int e = 0; e < seasons.size(); e++ ){
//                System.out.println(episodes.get(e).title());
//            }
//        }
        //Objeto(parametro -> expressaop)
//        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        //=================================================================================
        //toList = Lista Imutavel
        //Collectors.toList() = Lista Mutavel
//        List<DataEpisode> dataEpisodes = seasons.stream()
//                .flatMap(s -> s.episodes().stream())
//                .toList(); //.collect(Collectors.toList());
//
//        System.out.println("\nTop 5 episodes:");
//        dataEpisodes.stream()
//                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("First filter (N/A) " + e))
//                .sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
//                .peek(e -> System.out.println("Ordenation " + e))
//                .limit(5)
//                .peek(e -> System.out.println("Limit " + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("Map " + e))
//                .forEach(System.out::println);

        System.out.println("\nEpisodes list:");
        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.season(), d))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

        //=================================================================================
//        System.out.println("\nFrom what year do you want to see the episodes?");
//        var year = read.nextInt();
//        read.nextLine();
//
//        LocalDate searchDate = LocalDate.of(year, 1, 1);
//
////         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e -> e.getReleased() != null && e.getReleased().isAfter(searchDate))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getSeason() +
//                                ", Episode: " + e.getTitle() +
//                                ", Released: " + e.getReleased()
//                ));

        //=================================================================================
//        System.out.println("\nWhat episode name you want:");
//        var partTitle = read.nextLine();
//
//        Optional<Episode> first = episodes.stream()
//                .filter(e -> e.getTitle().toUpperCase().contains(partTitle.toUpperCase()))
//                .findFirst();
//
//        if(first.isPresent()){
//            System.out.println("Founded episode!");
//            System.out.println("Season: " + first.get().getSeason());
//        } else {
//            System.out.println("Founded not episode!");
//        }

        Map<Integer, Double> ratingBySeason = episodes.stream()
                .filter(e -> e.getImdbRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getImdbRating)));

        System.out.println(ratingBySeason);

        DoubleSummaryStatistics est = (DoubleSummaryStatistics) episodes.stream()
                .filter(e -> e.getImdbRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getImdbRating));

        System.out.println("Average: " + est.getAverage());
        System.out.println("Best: " + est.getMax());
        System.out.println("Worst: " + est.getMin());
        System.out.println("Amount: " + est.getCount());
    }
}
