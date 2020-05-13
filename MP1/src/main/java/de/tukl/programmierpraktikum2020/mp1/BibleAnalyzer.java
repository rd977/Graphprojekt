package de.tukl.programmierpraktikum2020.mp1;

import java.lang.reflect.Array;
import java.util.Comparator;

public class BibleAnalyzer {
    public static void countWords(Map<String, Integer> counts) { ;
    for (String word : Util. getBibleWords ()) {
        Integer v = counts.get(word);
        //um (null +1) zu vermeiden , überprüfen wir ersmal den value
        counts.put(word , (v== null)? 1 :v+1) ;
    }
    }


    public static void main(String[] args) {
        TreeMap<String, Integer> m = new TreeMap<>(Comparator.<String>naturalOrder());
        countWords(m);
        String[] words = new String[m.size()];
        //alle Wötrter in Array words speichern
        m.keys(words);
        // Array words aufsteigent sortieren
        sort(words, m);
        //print alle Wörter aud Console
        for (String s : words){
            System.out.println(m.get(s) + " " + s);
    }


    }
    //insertion sort
    public static void sort(String[] words, Map<String, Integer> counts) {
            String temp;
            for (int i = 1; i <words.length; i++) {
                temp = words[i];
                int j = i;
                Integer d = counts.get(temp);
                while (j > 0 && counts.get(words[j - 1] )>d) {
                    words[j] = words[j - 1];
                    j--;
                }
                words[j] = temp;
            }

    }
}
