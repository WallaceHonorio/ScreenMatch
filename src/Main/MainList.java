package Main;

import honorio.screenmatch.models.Film;
import honorio.screenmatch.models.Series;
import honorio.screenmatch.models.Title;

import java.util.ArrayList;

public class MainList {
    public static void main(String[] args) {
        Film film1 = new Film("The Matrix",1999);
        film1.evaluate(10);
        Film film2 = new Film("John Wick",2014);
        film1.evaluate(9);
        Series serie1 = new Series("La Casa de Papel",2017);

        ArrayList<Title> list = new ArrayList<>();
        list.add(film1);
        list.add(film2);
        list.add(serie1);


        for(Title item : list){
            System.out.println("Nome: " + item.getName());
            if(item instanceof Film film && film.getClassification() > 2){
                System.out.println("Classification: " + film.getClassification());
            }
        }
    }


}
