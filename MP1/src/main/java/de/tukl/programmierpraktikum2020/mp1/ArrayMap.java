package de.tukl.programmierpraktikum2020.mp1;

import java.security.Key;

public class ArrayMap implements Map<String,Integer>{
    public String [] keys;
    public Integer [] values;
    int size;
    public ArrayMap(){
        keys = new String[4];
        values = new Integer[4];
        size = 0;
    }


    @Override
    public Integer get(String key) {
        for(int i = 0 ; i < size ; i++){
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }
    
    public int get_Indix(String key){         // Hilfsfunktion , gibt uns die Indize der Elemnte in dem Array
        for(int i = 0 ; i < size ; i++){
            if(keys[i] ==key){
                return i;
            }
        }
        return -1;
    }

    public void inc_reaseSize() {    /// Hilfsgunktion -> inkriminiert den Size
            String[] copy_Keys = new String[keys.length*2];
            Integer[] copy_Values = new Integer[values.length*2];
            for (int i = 0; i <size; i++){
                copy_Keys[i] = keys[i];
                copy_Values[i] = values[i];
            }
            keys = copy_Keys;
            values = copy_Values;
        }

    public void dic_reaseSize() {    /// Hilfsgunktion -> dikriminiert den Size des Array  bis die Hilfte
        String[] copy_Keys = new String[keys.length/2];
        Integer[] copy_Values = new Integer[values.length/2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = keys[i];
            copy_Values[i] = values[i];
        }
        keys = copy_Keys;
        values = copy_Values;
    }

    @Override
    public void put(String key, Integer value) {

        int indix = get_Indix(key);
       if(size == keys.length &&indix == -1){     // Array Length inkriminieren wenn es kein genug Platz in Array gibt(die Doppelte)
           inc_reaseSize();
        }
        if(indix == -1){          // falls der Key nicht vorgekomen ist
            keys[size] = key;
            values[size] = value;
            size++;
        }
        else{
            values[indix]=value;  // falls der Key  schon im Array ist, hier änderen wir nur den Value
        }
    }

    @Override
    public void remove(String key) {
        int indix = get_Indix(key);
        if(indix!=-1 && keys[indix] == key ) {       // wenn der Key ist im Array
            for (int j = indix; j < keys.length - 1; j++) {  //  elemente nach links verschieben
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            size--;
            if(size < keys.length/2){
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
                array[i] = keys[i];
            }
        }
    }

}
