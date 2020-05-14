package de.tukl.programmierpraktikum2020.mp1;

import java.security.Key;

public class ArrayMap implements Map<String,Integer>{
    Pair [] arr ;
    int size;
    public ArrayMap(){
        // initialisierung Arra mit length 50 (belibig)!
        arr = new Pair[50];
        size = 0;
    }
    //-----------get_Indix Funktion------------------------------------//
    //gibt den Index der Position an der den Key in arr gechpeichert ist fall er existiert und -1 falls nicht
    //DIese Funktion Hilft uns
    private int get_Indix(String key){
        for(int i = 0 ; i < size ; i++){
            if(arr[i].getFirst().equals( key)){
                return i;
            }
        }
        return -1;
    }
   //-------------------inc_reaseSize FUnktion--------------------------//
    // vergrößert den Length des Arrays
    private void inc_reaseSize() {
        Pair[] copy_Keys = new Pair[arr.length*2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }
    //-------------------dic_reaseSize FUnktion--------------------------//
    //verkleinert den Length des Arrays
    private void dic_reaseSize() {
        Pair[] copy_Keys = new Pair[arr.length/2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }

    @Override
    public Integer get(String key) {
        //getFirst holt den Schlüssel von der Tuple die an der Position i geschpeichert
        // get vergleicht jeden einzelnen Schlüssel aus der arr mit dem eingegeben Key
        // und gibt ihren Value zurück
        //wenn nichts gefunden ist dann der Schlüssel existiert nicht und gibt null zurück
        for(int i = 0 ; i < size ; i++){
            if(arr[i].getFirst().equals(key)){
                return arr[i].getSecond();
            }
        }
        return null;
    }





    @Override
    public void put(String key, Integer value) {
        Pair pair = new Pair(key,value);
        int indx = get_Indix(key);
        //size == arr.length prügfen , weil das Array muss nicht voll sein
        // wenn ist es der Fall dann der Length des Arrayes wird vergrößert und alled Elemente ins neuen Array koppiert werden
        if(size == arr.length &&indx == -1){
            inc_reaseSize();
        }
        // idex == -1 bedeuted dass der schlüssel ist nicht in dem Array dann wird der Key und Value gefügt
        if(indx == -1){
           arr[size] =pair;
            size++;
        }
        //falls der Schlüssel ist bereit in dem Array , dann ändern wird nur seinen Value
        else{
            arr[indx].setSecond(value);
        }
    }

    @Override
    public void remove(String key) {
        int indx = get_Indix(key);
        // Falls der Key im Array ist , wird gelöcht
        if(indx!=-1 ) {
            for (int j = indx; j < arr.length - 1; j++) {  //  elemente nach links verschieben
                arr[j]=arr[j + 1];
                arr[j]=arr[j + 1];
            }
            size--;
            //Uhm platz im RAM zu sparen , verkleinerb wir den Length des Arrays wenn
            //die Anzhal der Elemente kleiner alls die Hälfte des Lengthes des Arrays
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
