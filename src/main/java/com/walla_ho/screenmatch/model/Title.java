package com.walla_ho.screenmatch.model;
/*
import com.google.gson.annotations.SerializedName;
import honorio.screenmatch.exception.YearConversationErrorException;

public class Title implements Comparable<Title>  {

    @SerializedName("Title")
    private String name;
    @SerializedName("Year")
    private int releaseYear;
    private int durationMinutes;
    private boolean includedPlan;
    private double sumEvaluations;
    private double totalReviews;

    public Title(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Title(TitleOmdb titleOmdb) {
        this.name = titleOmdb.title();

        if(titleOmdb.year().length() > 4) {
            throw new YearConversationErrorException("Não consegui converter o ano porque tem mais de 04 caracteres.");
        }
        this.releaseYear = Integer.valueOf(titleOmdb.year());
        this.durationMinutes = Integer.valueOf(titleOmdb.runtime().substring(0,2));
    }

    public void setSumEvaluations(double sumEvaluations) {
        this.sumEvaluations = sumEvaluations;
    }

    public void setTotalReviews(double totalReviews) {
        this.totalReviews = totalReviews;
    }

    public void showDescription () {
        System.out.println("Name: " + name);
        System.out.println("Release: " + releaseYear);
        System.out.println("Minutes duration: " + durationMinutes);
        System.out.println("Included in Special Plan: " + includedPlan);
    }
    public void evaluate (double evaluate) {
        sumEvaluations += evaluate;
        totalReviews++;
    }
    public double getMedia () {
        return sumEvaluations/totalReviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return "Film{" +
                "\n" + "Title=" + name +
                "\n" + "Year=" + releaseYear +
                "\n" + "Duration=" + durationMinutes +
//                "\n" + '\'' + "includedPlan=" + includedPlan +
//                "\n" + '\'' + "sumEvaluations=" + sumEvaluations +
//                "\n" + '\'' + "totalReviews=" + totalReviews +
                "\n}";
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isIncludedPlan() {
        return includedPlan;
    }

    public void setIncludedPlan(boolean includedPlan) {
        this.includedPlan = includedPlan;
    }

    public double getSumEvaluations() {
        return sumEvaluations;
    }

    public double getTotalReviews() {
        return totalReviews;
    }

    @Override
    public int compareTo(Title anotherTitle) {
        return this.getName().compareTo(anotherTitle.getName());
    }
}
*/