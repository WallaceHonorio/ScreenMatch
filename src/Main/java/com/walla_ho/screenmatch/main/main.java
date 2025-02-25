package com.walla_ho.screenmatch.main;

import com.walla_ho.screenmatch.model.*;
import com.walla_ho.screenmatch.repository.SerieRepository;
import com.walla_ho.screenmatch.service.ConsumeAPI;
import com.walla_ho.screenmatch.service.DataConvert;
//import com.walla_ho.screenmatch.service.QueryChatGPT;

import java.util.*;
import java.util.stream.Collectors;

public class main {
    private Scanner read = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConvert convert = new DataConvert();
    private final String  ADDRESS = "http://www.omdbapi.com/?t=";
    private final String  APIKEY = "&apikey=6585022c";
    private List<Serie> listSerie = new ArrayList<>();
    private SerieRepository repository;
    private Optional<Serie> seachedSerie;

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
                    6 - Search top 5 series.
                    7 - Search by category.
                    8 - Search by season and rating.
                    9 - Search by excerpt.
                    10 - Search Episodes top 5 by serie.
                    11 - Search for episodes from a date
                    
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
                case 6:
                    searchTopFive();
                    break;
                case 7:
                    searchSerieByCategory();
                    break;
                case 8:
                    searchSerieBySeasonAndImdbRating();
                    break;
                case 9:
                    searchEpisodeByExcerpt();
                    break;
                case 10:
                    topEpisodesBySerie();
                    break;
                case 11:
                    searchEpisodesAfterADate();
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

        seachedSerie = repository.findByTitleContainingIgnoreCase(nameSerie);

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

    private void searchTopFive() {
        List<Serie> serieTop5 = repository.findTop5ByOrderByImdbRating();

        serieTop5.forEach(s ->
                System.out.println(s.getTitle() + " -> Imdb Rating: " + s.getImdbRating()));
    }


    private void searchSerieByCategory() {
        System.out.println("Choose the category:");
        var nameCategory = read.nextLine();

        Category category = Category.fromString(nameCategory);

        List<Serie> serieCategory = repository.findByGender(category);

        serieCategory.forEach(s ->
                System.out.println(s.getTitle() + " -> Category: " + s.getGender()));
    }

    private void searchSerieBySeasonAndImdbRating(){
        System.out.println("Filtrar séries até quantas temporadas? ");
        var totalSeasons = read.nextInt();
        read.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var imdbRating = read.nextDouble();
        read.nextLine();
        //List<Serie> filtroSeries = repository.findByTotalSeasonsLessThanEqualAndImdbRatingGreaterThanEqual(totalSeasons, imdbRating);
        //List<Serie> filtroSeries = repository.serieByTotalSeasonsAndImdbRating(); //@Query
        List<Serie> filtroSeries = repository.serieByTotalSeasonsAndImdbRating(totalSeasons, imdbRating); //@Query JPQL
        System.out.println("*** Séries filtradas ***");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitle() + "  - Imdb Rating: " + s.getImdbRating()));
    }

    private void searchEpisodeByExcerpt() {
        System.out.println("What is the name of the episode to search for?");
        var excerptEpisode = read.nextLine();
        List<Episode> episodeFounded = repository.episodeByExcerpt(excerptEpisode);
        episodeFounded.forEach(e ->
                System.out.printf("Serie: %s Season %s - Episode %s - %s\n",
                        e.getSerie().getTitle(), e.getSeason(),
                        e.getEpisode(), e.getTitle()));
    }

    private void topEpisodesBySerie() {
        searchSerieTitle();

        if(seachedSerie.isPresent()){
            Serie serie = seachedSerie.get();
            List<Episode> topEpisode = repository.topEpisodesBySerie(serie);

            topEpisode.forEach(e ->
                    System.out.printf("Serie: %s Season %s - Episode %s - %s\n",
                            e.getSerie().getTitle(), e.getSeason(),
                            e.getEpisode(), e.getTitle()));
        }
    }

    private void searchEpisodesAfterADate() {
        searchSerieTitle();

        if(seachedSerie.isPresent()){
            Serie serie = seachedSerie.get();
            System.out.println("Enter the release deadline year:");
            var yearRelease = read.nextInt();
            read.nextLine();

            List<Episode> episodesYear = repository.episodeBySerieANDYear(serie, yearRelease);

            episodesYear.forEach(e ->
                    System.out.printf("Serie: %s Season %s - Episode %s - %s\n",
                            e.getSerie().getTitle(), e.getSeason(),
                            e.getEpisode(), e.getTitle()));
        }
    }

//    private void whereWatch() {
//        System.out.println("Write the serie name to search:");
//        var nameSerie = read.nextLine();
//        System.out.println(QueryChatGPT.getWatch(nameSerie));
//    }
}
