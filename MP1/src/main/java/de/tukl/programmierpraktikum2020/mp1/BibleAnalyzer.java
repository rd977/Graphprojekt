package de.tukl.programmierpraktikum2020.mp1;

import java.lang.reflect.Array;
import java.util.Comparator;

public class BibleAnalyzer {
    public static void countWords(Map<String, Integer> counts) {
        String[] words = new String[counts.size()];
        counts.keys(words);
        for (String word : words) {
            int count = 0;
            for (String w : Util.getBibleWords()) {
                if( w.equals(word) ) {
                    count++;
                }
            }
            counts.put( word, count );
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> WordCountMap = new ArrayMap();
        WordCountMap.put("Gott", null);                                    //fülle die Map mit Beispielwörtern.
        WordCountMap.put("Vater", null);
        WordCountMap.put("Amen", null);
        WordCountMap.put("Gott", null);
        WordCountMap.put("Bundeskanzler", null);
        WordCountMap.put("Teufel", null);
        WordCountMap.put("Abraham", null);
        WordCountMap.put("Jakob", null);
        WordCountMap.put("Amen", null);
        WordCountMap.put("Engel", null);
        WordCountMap.put("Gottes", null);
        WordCountMap.put("Gebote", null);
        WordCountMap.put("Israel", null);
        WordCountMap.put("Herr", null);
        WordCountMap.put("aber", null);
        WordCountMap.put("da", null);
        WordCountMap.put("der", null);
        countWords(WordCountMap);                                       //Zähle wie oft jedes Beispielwort in der Bibel vorkommt und schreibe es in die Map.
        String[] words = new String[WordCountMap.size()];
        WordCountMap.keys(words);                                       //Schreibe die Wörter(keys) aus der Map in ein Array.
        sort(words, WordCountMap);                                      //Sortiere das Array absteigend nach der Häufigkeit des jeweiligen Wortes in der Bibel
        for ( String word : words ){
            System.out.println(WordCountMap.get(word) + " " + word);
        }
    }

    public static void sort(String[] words, Map<String, Integer> counts) {      //Bubblesort verwendet (Code-Inspiration: javabeginners.de)
        String helpString;
        for (int i = 0; i < words.length - 1; i++) {
            if ( counts.get(words[i]) < counts.get(words[i + 1]) ) {
                helpString = words[i];
                words[i] = words[i + 1];
                words[i + 1] = helpString;
                sort( words, counts );
            }
        }
    }
}
