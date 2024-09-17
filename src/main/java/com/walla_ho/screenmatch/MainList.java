/*  TEST ARRAY FILM *
package Main;

import honorio.screenmatch.models.Film;
import honorio.screenmatch.models.Series;
import honorio.screenmatch.models.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainList {
    public static void main(String[] args) {
        Film film1 = new Film("The Matrix",1999);
        film1.evaluate(10);
        Film film2 = new Film("John Wick",2014);
        film1.evaluate(9);
        Series serie1 = new Series("La Casa de Papel",2017);

        List<Title> list = new ArrayList<>();
        list.add(film1);
        list.add(film2);
        list.add(serie1);


        for(Title item : list){
            System.out.println("Name: " + item.getName());
            if(item instanceof Film film && film.getClassification() > 2){
                System.out.println("Classification: " + film.getClassification());
            }
        }

        ArrayList<String> seachActor = new ArrayList<>();
        seachActor.add("Adam Sandler");
        seachActor.add("Paulo");
        seachActor.add("Jacqueline");
        System.out.println(seachActor);
        Collections.sort(seachActor);
        System.out.println("After ordering");
        System.out.println(seachActor);


        System.out.println("Ordering title list:");
        Collections.sort(list);
        System.out.println(list);
        list.sort(Comparator.comparing(Title::getReleaseYear));
        System.out.println("Ordering per year:");
        System.out.println(list);

    }



}
*/