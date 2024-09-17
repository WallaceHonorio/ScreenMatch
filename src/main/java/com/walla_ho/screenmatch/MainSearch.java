/*  TEST OMDB FILM REQUEST *

package Main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import honorio.screenmatch.exception.YearConversationErrorException;
import honorio.screenmatch.models.Title;
import honorio.screenmatch.models.TitleOmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainSearch {

    public static void main(String[] args) throws IOException, InterruptedException {

        String  APIKEY = "f3723d13";

        Scanner read = new Scanner(System.in);
        System.out.println("Write down the film name:");
        var search = read.nextLine();

        //search.
        String searchNoSpaces = search.replaceAll(" ", "+");

        String address = "http://www.omdbapi.com/?t=" + searchNoSpaces + "&apikey=" + APIKEY;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //System.out.println(json);

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            TitleOmdb titleOmdb = gson.fromJson(json, TitleOmdb.class);
            Title title = new Title(titleOmdb);
            System.out.println(title);
        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Algum erro de argumento na busca, verifique o endereço");
        } catch (YearConversationErrorException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("O programa finalizou corretamente!");

    }

}
*/