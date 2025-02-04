package com.walla_ho.screenmatch.main;

import com.walla_ho.screenmatch.model.*;
import com.walla_ho.screenmatch.repository.SerieRepository;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
//import com.walla_ho.screenmatch.service.QueryChatGPT;

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
    private List<Serie> listSerie = new ArrayList<>();
    private SerieRepository repository;

    public main(SerieRepository repository) {
        this.repository = repository;
    }

    public void showMenu(){
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Search by series web.
                    2 - Search by title episodes.
                    3 - List series.
                    4 - Search by title series.
                    5 - Search series by actor.
                    
                    0 - Logout                                 
                    """;

            System.out.println(menu);
            opcao = read.nextInt();
            read.nextLine();

            switch (opcao) {
                case 1:
                    searchSerieWeb();
                    break;
                case 2:
                    searchEpisodeBySerie();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 4:
                    searchSerieTitle();
                    break;
                case 5:
                    searchSerieActor();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void searchSerieWeb() {
        DataSerie data = getDataSerie();
        Serie serie = new Serie(data);
        repository.save(serie);
        System.out.println(data);
    }

    private DataSerie getDataSerie() {
        System.out.println("Write the serie name to search:");
        var nameSerie = read.nextLine();
        var json = consumeAPI.getData(ADDRESS + nameSerie.replace(" ", "+") + APIKEY);
        return convert.consumeData(json, DataSerie.class);
    }

    private void searchEpisodeBySerie(){
        listSearchedSeries();

        System.out.println("Choose a series by name:");
        var nameSerie = read.nextLine();

        Optional<Serie> serie = repository.findByTitleContainingIgnoreCase(nameSerie);

        if(serie.isPresent()){
            var serieFounded = serie.get();

            List<DataSeason> seasons = new ArrayList<>();

            for (int i = 1; i <= serieFounded.getTotalSeasons(); i++) {
                var json = consumeAPI.getData(ADDRESS + serieFounded.getTitle().replace(" ", "+") + "&season=" + i + APIKEY);
                DataSeason dataSeason = convert.consumeData(json, DataSeason.class);
                seasons.add(dataSeason);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.season(), e)))
                    .collect(Collectors.toList());

            serieFounded.setEpisodes(episodes);
            repository.save(serieFounded);
        } else {
            System.out.println("Serie not founded.");
        }
    }

    private void listSearchedSeries() {
        listSerie = repository.findAll();

        listSerie.stream()
                .sorted(Comparator.comparing(Serie::getGender))
                .forEach(System.out::println);
    }

    private void searchSerieTitle() {
        System.out.println("Choose a series by name:");
        var nameSerie = read.nextLine();

        Optional<Serie> seachedSerie = repository.findByTitleContainingIgnoreCase(nameSerie);

        if(seachedSerie.isPresent()){
            System.out.println("Serie data: " + seachedSerie.get());
        } else {
            System.out.println("Serie not founded.");
        }
    }

    private void searchSerieActor() {
        System.out.println("Choose a series by actor:");
        var nameActor = read.nextLine();

        System.out.println("Choose the Imdb rating minimum value:");
        var ratingValuie = read.nextDouble();

        List<Serie> seachedSeries = repository.findByActorsContainingIgnoreCaseAndImdbRatingGreaterThanEqual(nameActor,ratingValuie);

        System.out.println("Series " + nameActor + " worked on:");
        System.out.println(seachedSeries);

        seachedSeries.forEach(s ->
                System.out.println(s.getTitle() + "Imdb Rating: " + s.getImdbRating()));

    }

//    private void whereWatch() {
//        System.out.println("Write the serie name to search:");
//        var nameSerie = read.nextLine();
//        System.out.println(QueryChatGPT.getWatch(nameSerie));
//    }
}
