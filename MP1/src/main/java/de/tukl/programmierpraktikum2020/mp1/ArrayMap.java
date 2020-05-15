package de.tukl.programmierpraktikum2020.mp1;

import java.security.Key;

public class ArrayMap implements Map<String,Integer>{
    Pair [] arr ;
    int size;
    public ArrayMap(){
        // initialisierung ein Array mit length 50 (belibig)!
        arr = new Pair[50];
        size = 0;
    }



    //-----------get_Indix Funktion------------------------------------//
    //Diese Funktion gibt den Index der Position des Keys in dem Array
    private int get_Indix(String key){
        for(int i = 0 ; i < size ; i++){
            if(arr[i].getFirst().equals( key)){
                return i;
            }
        }
        //key nicht gefunden
        return -1;
    }
   //-------------------inc_reaseSize FUnktion--------------------------//
    // vergrößert die Länge des Arrays zum Doppel
    //und alle Elemente von dem alten Array in das neuen Array koppiert
    private void inc_reaseSize() {
        Pair[] copy_Keys = new Pair[arr.length*2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }
    //-------------------dic_reaseSize FUnktion--------------------------//
    //verkleinert den Länge des Arrays zur Hälfte
    //und alle Elemente von dem alten Array in das neuen Array koppiert
    private void dic_reaseSize() {
        Pair[] copy_Keys = new Pair[arr.length/2];
        for (int i = 0; i <size; i++){
            copy_Keys[i] = arr[i];
        }
        arr = copy_Keys;

    }

    @Override
    public Integer get(String key) {
        //Um den Value aus dem Array  zurückzuliefern 
        //, suchen wir nach dem key mit Hilfe der getFirst Funktion 
        //,die  jeden key aus dem 2-er Tuple holt und wird er mit dem gesuchten key vergliechen .
        // wenn der Schlüssel gefunden wurde dann holt die Funktion getSecond den value aus dem Tuple des keys
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
        //bevor dem Einfügen ,müssen wir sicher sein dass size kleiner als  arr.length sein muss 
        //sonst können wir dass Element nicht einfügen , weil das Array  nicht voll sein muss
        //wenn dass Array voll ist dann wird die Länge des Arrays verdoppelt.
        if(size == arr.length &&indx == -1){
            inc_reaseSize();
        }
        // falls er nicht in dem Array ist 
        // werden der key und value in neuer Paar geschpeichet und ins Array eingefügt
        if(indx == -1){
           arr[size] =pair;
            size++;
        }
        //falls der Schlüssel ist bereit in dem Array , dann ändern wir nur den Value
        else{
            arr[indx].setSecond(value);
        }
    }

    @Override
    public void remove(String key) {
        int indx = get_Indix(key);
        // Um die remove Operation zuführen , muss gesichert sein dass das gelöchende Element im Array sein muss
        if(indx!=-1 ) {
            for (int j = indx; j < arr.length - 1; j++) { 
                //Löchen geht so , dass das Element überschrieben wird , 
                //dass heißt , alle Elemente an der linker Seite des Elements werden nach rechts verschoben 
                arr[j]=arr[j + 1];
                arr[j]=arr[j + 1];
            }
            // Ein Element gelöcht dass heißt , der size muss reduziert werden 
            size--;
            //Um platz in RAM zu sparen , verkleinen wir die Länge des Arrays wenn
            //die Anzhal oder size der Elemente kleiner als die Hälfte der Länge des Arrays
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
            // i bis size , weil wir wissen ,wie viele Elemente in dem Array sind
            for(int i = 0 ; i < size ; i++) {
                array[i] = arr[i].getFirst();
            }
        }
    }


}
