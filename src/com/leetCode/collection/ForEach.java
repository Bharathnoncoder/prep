package com.leetCode.collection;

import java.sql.SQLOutput;
import java.util.List;
import java.util.function.Consumer;

public class ForEach {

    public static void main(String[] args) {


        Consumer<Integer> com = n -> System.out.println(n);

        List<Integer> s = List.of(1,2,3,4,5);


        s.forEach(System.out::println);



    }
}
