package JavaBasicsJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImpJava {
    public static void main(String[] args) {
        //convert from char to int
        char c = '3';
        int i = c - '0';
        System.out.println(i); //i = 1


        String s= "12";
        i = Integer.parseInt(s);
        System.out.println("hello {i} here");


        List<Integer> salaries = new ArrayList<>();
        int n = 4;
        salaries.add(10);
        salaries.add(20);
        salaries.add(30);
        salaries.add(40);
        salaries.add(50);

        Stream<Integer> limit = salaries.stream().sorted(Collections.reverseOrder());
        int i1 = 1/2;
        System.out.println(i1);

        int[][] logs = {
                {20190107,0,1},
                {20190104,3,4},{20190101,2,3}
        };
        List<int[]> sortedLogs =
                Arrays.stream(logs).sorted((a, b) -> new Integer(a[0]).compareTo(new Integer(b[0])))
                        .collect(Collectors.toList());
        System.out.println(sortedLogs);
    }

}
