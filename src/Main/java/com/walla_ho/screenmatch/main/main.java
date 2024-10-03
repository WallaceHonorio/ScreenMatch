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
        var menu = """
                1 - Search by series
                2 - Search by episodes
                
                0 - Logout                                 
                """;

        System.out.println(menu);
        var opcao = read.nextInt();
        read.nextLine();

        switch (opcao) {
            case 1:
                searchSerieWeb();
                break;
            case 2:
                searchEpisodeBySerie();
                break;
            case 0:
                System.out.println("Leaving...");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void searchSerieWeb() {
        DataSerie data = getDataSerie();
        System.out.println(data);
    }

    private DataSerie getDataSerie() {
        System.out.println("Write the serie name to search:");
        var nameSerie = read.nextLine();
        var json = consumeAPI.getData(ADDRESS + nameSerie.replace(" ", "+") + APIKEY);
        return convert.consumeData(json, DataSerie.class);
    }

    private void searchEpisodeBySerie(){
        DataSerie dataSerie = getDataSerie();
        List<DataSeason> temporadas = new ArrayList<>();

        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            var json = consumeAPI.getData(ADDRESS + dataSerie.title().replace(" ", "+") + "&season=" + i + APIKEY);
            DataSeason dataSeason = convert.consumeData(json, DataSeason.class);
            temporadas.add(dataSeason);
        }
        temporadas.forEach(System.out::println);
    }
}
