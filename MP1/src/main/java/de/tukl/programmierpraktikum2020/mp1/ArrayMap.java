package de.tukl.programmierpraktikum2020.mp1;

import java.security.Key;

public class ArrayMap implements Map<String,Integer>{
    Pair [] arr ;
    int size;
    public ArrayMap(){
        arr = new Pair[50];
        size = 0;
    }


    @Override
    public Integer get(String key) {
        for(int i = 0 ; i < size ; i++){
            if(arr[i].getFirst().equals(key)){
                return arr[i].getSecond();
            }
        }
        return null;
    }

    private int get_Indix(String key){         // Hilfsfunktion , gibt uns die Indize der Elemnte in dem Array
        for(int i = 0 ; i < size ; i++){
            if(arr[i].getFirst()== key){
                return i;
            }
        }
        return -1;
    }

    private void inc_reaseSize() {    /// Hilfsgunktion -> inkriminiert den Size
        Pair[] copy_Keys = new Pair[arr.length*2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }

    private void dic_reaseSize() {    /// Hilfsgunktion -> dikriminiert den Size des Array  bis die Hilfte
        Pair[] copy_Keys = new Pair[arr.length/2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }

    @Override
    public void put(String key, Integer value) {
        Pair pair = new Pair(key,value);
        int indx = get_Indix(key);
        if(size == arr.length &&indx == -1){     // Array Length inkriminieren wenn es kein genug Platz in Array gibt(die Doppelte)
            inc_reaseSize();
        }
        if(indx == -1){          // falls der Key nicht vorgekomen ist
           arr[size] =pair;
            size++;
        }
        else{
            arr[indx].setSecond(value);  // falls der Key  schon im Array ist, hier änderen wir nur den Value
        }
    }

    @Override
    public void remove(String key) {
        int indx = get_Indix(key);
        if(indx!=-1 && arr[indx].getFirst() ==key ) {       // wenn der Key ist im Array
            for (int j = indx; j < arr.length - 1; j++) {  //  elemente nach links verschieben
                arr[j]=arr[j + 1];
                arr[j]=arr[j + 1];
            }
            size--;
            if(size < arr.length/2){
                dic_reaseSize();
            }
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void keys(String[] array) {
        if (array == null|| array.length< size()) {
            throw new IllegalArgumentException();
        } else {
            for(int i = 0 ; i < size ; i++) {
                array[i] = arr[i].getFirst();
            }
        }
    }
}
