package mutithread.safecontainer;

import java.util.*;

public class SafeList {

    public static void main(String[] args) {

        List safeArrayList = Collections.synchronizedList(new ArrayList<>());
        List safeLinkedList = Collections.synchronizedList(new LinkedList<>());

        Set safeLinkedHashSet = Collections.synchronizedSet(new LinkedHashSet<>());
        Set safeTreeSet = Collections.synchronizedSet(new TreeSet<>());
        Set safeHashSet = Collections.synchronizedSet(new HashSet<>());

        Map safeHashMap = Collections.synchronizedMap(new HashMap<>());
        Map safeTreeMap = Collections.synchronizedMap(new TreeMap<>());
        Map safeLinkedHashMap = Collections.synchronizedMap(new LinkedHashMap<>());

    }

}
