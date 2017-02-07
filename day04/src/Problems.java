import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.max;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map<Integer, Integer> map = new MyLinearMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) != null) {
                int prev = map.get(arr[i]);
                prev++;
                map.put(arr[i], prev);
            } else {
                map.put(arr[i], 1);
            }
        }
        return map;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {

        LinkedList<Integer> arr = new LinkedList<>();
        int copyk = k;

        for (int j = 0; j < num.length; j++) {
            while ((arr.size() != 0) && (num[j] < arr.getLast()) && (k>0)) {
                arr.removeLast();
                k--;
            }
            if (arr.size() < num.length - copyk) {
                arr.addLast(num[j]);
            }
        }

        return arr;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        //reverse the LinkedLists.
        Node prev1 = null;
        Node prev2 = null;
        Node curr1 = h1;
        Node curr2 = h2;
        Node next1 = curr1.next;
        Node next2 = curr2.next;
        while (next1 != null) {
            curr1.next = prev1;
            prev1 = curr1;
            curr1 = next1;
            next1 = next1.next;
        }
        curr1.next = prev1;
        while (next2 != null) {
            curr2.next = prev2;
            prev2 = curr2;
            curr2 = next2;
            next2 = next2.next;
        }
        curr2.next = prev2;
        //start adding the values to sum with the correct place value
        Node node1 = curr1;
        Node node2 = curr2;
        int pv = 1; //place value
        int sum = 0;
        while ((node1 != null) || (node2 != null)) {
            if (node1 == null) {
                sum += pv * (int) node2.data;
                node2 = node2.next;
            } else if (node2 == null) {
                sum += pv * (int) node1.data;
                node1 = node1.next;
            } else {
                sum += pv * ((int) node1.data + (int) node2.data);
                node1 = node1.next;
                node2 = node2.next;
            }
            pv *= 10;
        }
        return sum;
    }

}
