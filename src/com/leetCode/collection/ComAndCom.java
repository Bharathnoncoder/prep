package com.leetCode.collection;

import java.util.*;

public class ComAndCom {

    public static void main(String[] args) {


        Comparator<String> c = new Comparator<String>() {
            public int compare(String s , String s1){
               if(s1.length() > s.length())
                   return 1;
               else  if(s1.length() == s.length())
                   return 0;
                else
                    return -1;
            }
        };


        List<String> col = new ArrayList<>();


        col.add("jwdfbhvoidkfncvoda");
        col.add("sfdgsdfs");
        col.add("sfdsdsfdsd");
        col.add("sdfgsdfgsrgsdrf");
        col.add("bsfdgvsdthwerthrs");
        col.add("dsfgertghert");

        Collections.sort(col, c);
        System.out.println(col);


    }
}
