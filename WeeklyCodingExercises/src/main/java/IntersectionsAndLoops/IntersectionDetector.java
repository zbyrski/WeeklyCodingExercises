package IntersectionsAndLoops;

import java.util.Collections;
import java.util.LinkedList;

public class IntersectionDetector {

    public Object findIntersection(LinkedList list1, LinkedList list2){

        for (Object o : list1){
            for (Object o2 : list2){
                if (o == o2) return o;
            }
        }

        return null;
    }
}
