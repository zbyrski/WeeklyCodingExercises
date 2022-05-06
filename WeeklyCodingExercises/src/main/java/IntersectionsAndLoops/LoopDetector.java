package IntersectionsAndLoops;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LoopDetector {

    //find the starting point of a loop by finding the first element that appears more than once
    //time complexity: O(n)
    public Object findLoopStart(LinkedList list){
        //use a hashset to keep track of elements already encountered
        Set listElements = new HashSet();

        //for each element in the list, check if that element has already been encountered
        for (Object o : list){
            if (listElements.contains(o)) return o;
            listElements.add(o);
        }

        //if an element in not encountered more than once, there is no loop
        return null;
    }
}
