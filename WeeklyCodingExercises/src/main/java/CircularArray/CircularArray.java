package CircularArray;

import java.util.ArrayList;
import java.util.Iterator;

public class CircularArray<E> implements Iterable<E> {

    private ArrayList<E> arr;

    public CircularArray(){
        arr = new ArrayList<>();
    }

    public CircularArray(int index){
        arr = new ArrayList<>(index);
    }

    public CircularArray(E... values){
        arr = new ArrayList<>();
        for (E value : values){
            arr.add(value);
        }
    }

    public E get(int index){
        return arr.get(index);
    }

    public void set(int index, E value) {
        arr.set(index, value);
    }

    //O(n) for rotations
    public void rotate(int numOfTurns){
        //removes extraneous rotations (i.e. it would rotate all the way around at least once)
        numOfTurns %= arr.size();
        ArrayList<E> newArr = new ArrayList<>(arr.size());

        for (int i = arr.size() -1; i >= 0; i--){
            int newIndex = i + numOfTurns;
            if (newIndex >= arr.size()){
                newIndex -= arr.size();
            }
            newArr.set(newIndex, arr.get(i));
        }

        arr = newArr;
    }

    public Iterator<E> iterator(){
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < arr.size();
            }

            @Override
            public E next() {
                E value = arr.get(index);
                index++;
                return value;
            }
        };
    }

    /*alternate iterator
    public Iterator<E> iterator(){
        return arr.iterator();
    }
    */
}
