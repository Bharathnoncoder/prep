package com.leetCode.collection;

import java.security.Identity;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapfilterReduce {

    public static void main(String[] args) {
//        // map
//        List<Integer> nums = List.of(1, 2, 3, 4, 5);
//
//        System.out.println(nums.stream().map(n -> n * n).toList());
//
//        // Convert names to uppercase
//
//        List<String> names = List.of("bharath", "raj", "java");
//
//        System.out.println(names.stream().map(d -> d.toUpperCase()).toList());
//
//        //Convert names to uppercase
//
//        List<String> words = List.of("apple", "banana", "kiwi");
//
//        System.out.println(words.stream().map(String::length).toList());
//
//
//        // reduce

//        ## The Hard Rule
//
//        If you're tempted to mutate inside reduce  →  use collect
//        If your result is a List, Set, or Map      →  use collect
//        Everything else                            →  reduce
//
//        //T reduce(T identity, BinaryOperator<T> occumulator, combiner);
//
//        // The combiner is only called in parallel streams — it merges results from different threads.
//
//        System.out.println(nums.stream().reduce(0, (Integer i1, Integer i2) -> i1 + i2));
//
//
//        BinaryOperator<Integer> bi = (Integer integer, Integer integer2) -> integer + integer2;
//
//
//        List<String> words1 = List.of("apple", "banana", "apple", "orange", "banana", "apple");
//
//        System.out.println(words1.parallelStream().reduce(new HashMap<String, Integer>(),
//                (HashMap<String, Integer> a, String s) -> {
//                    if (a.get(s) != null) {
//                        var count = a.get(s);
//                        count++;
//                        a.put(s, count);
//                    } else {
//                        a.put(s, 1);
//                    }
//                    return a;
//                },
//                (a, b) -> {
//                    b.putAll(a);
//                    return b;
//                }));
//
//
//        List<String> words2 = List.of("java", "stream", "api");
//
//        System.out.println(words2.stream().reduce(new StringBuilder(),
//                (res, word) -> {
//                    return res.isEmpty() ? new StringBuilder(word) : res.append("=").append(word);
//                },
//                (StringBuilder a, StringBuilder b) -> {
//                    a.append("-").append(b);
//                    return a;
//                }));
//
//        System.out.println(words2.parallelStream().reduce(new StringBuilder(),
//                (res, word) -> {
//                    return res.isEmpty() ? new StringBuilder(word) : res.append("=").append(word);
//                },
//                (StringBuilder a, StringBuilder b) -> {
//                    a.append("-").append(b);
//                    return a;
//                }));
//
//
//        // longest word
//
//        List<String> words3 = List.of("java", "microservices", "api", "springboot");
//
//        System.out.println(words3.stream().reduce(new String(), (s, s1) -> {
//                    return s.isEmpty() ? s1 : (s.length() > s1.length() ? s : s1);
//                }, (a, b) -> {
//                    return a.isEmpty() ? b : (a.length() > b.length() ? a : b);
//                }
//        ));
//
//        List<Integer> nums1 = List.of(1, 2, 3, 4, 5, 6);
//        System.out.println(  nums1.parallelStream().reduce(new HashMap<String, ArrayList<Integer>>(),
//                (HashMap<String, ArrayList<Integer>> a, Integer b) -> {
//                    if (b % 2 == 0) {
//                        var c = a.getOrDefault("EVEN", new ArrayList<Integer>());
//                        c.add(b);
//                        a.put("EVEN", c);
//                    } else {
//                        var c = a.getOrDefault("ODD", new ArrayList<Integer>());
//                        c.add(b);
//                        a.put("ODD", c);
//                    }
//                    return a;
//                }, (i, j) -> {
//                     Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>(j);
//                     Map<String, ArrayList<Integer>> map2 = new HashMap<String, ArrayList<Integer>>(i);
//                     map2.forEach((k, v)-> {
//                        map.merge(k, v, (integers, interger2) -> {
//                            integers.addAll(interger2);
//                            return integers;
//                        });
//                    });
//                    return j;
//                }
//        ));

        // reduce p1

        List<Integer> c1 = List.of(10, 20, 30, 40, 50);

        // Expected output: 150

        System.out.println( c1.stream().reduce(0, (a, b)-> a+b));


        List<String> words = List.of("Java", "is", "very", "fun");

         // Expected output: "Java-is-very-fun"  no not fun bruh ;(

        System.out.println( words.parallelStream().reduce("",
                (String s, String s1 )-> {
                    if(s.isEmpty()) {
                       return s + s1;
                    } else {
                       return s + "-"+ s1;
                    }
                },
                (String s, String s2) -> {

                  if(s.isEmpty()) return s2 ;
                  if(s2.isEmpty()) return s;

                 return s+"-"+s2;
                }));

        List<Integer> nums = List.of(-3, -7, -1, -9, -4);;

        // Expected output: 9

        System.out.println(nums.stream().reduce(
                (Integer max,Integer newValue)->{
                    return max > newValue ? max : newValue;
                }).get());

        List<String> words1 = List.of("hi", "java", "is", "cool", "fun", "stream");

        // Expected output: 3  (java, cool, stream)

        System.out.println( words1.parallelStream().reduce(new ArrayList<String>(),(List<String> a,String b)-> {
            List<String> accList = new ArrayList<>();
            if(b.length() > 3) {
                accList.add(b);
            }
            return accList;
        },(i, j)->{
            if(i.isEmpty()) return j;
            if(j.isEmpty()) return i;
            List<String> compbinerList = new ArrayList<>(i);
            compbinerList.addAll(j);
            return compbinerList;
        }));


        List<Integer> nums2 = List.of(1, 2, 3, 4, 5);

        // Expected output: 55
        // (1*1 + 2*2 + 3*3 + 4*4 + 5*5 = 1 + 4 + 9 + 16 + 25 = 55)

        System.out.println( nums2.stream().reduce(0,(a,b)->{
            return a+ (b*b);
        }));

        List<String> words2 = List.of("hello", "world", "java");

        // Expected output: "olleh dlrow avaj"

        System.out.println(words2.parallelStream().reduce("",(a,b)-> {
            return a.isEmpty() ? new StringBuilder(b).reverse().toString() : a +" "+ new StringBuilder(b).reverse().toString();
        },(i,j)-> {
            if(i.isEmpty()) return j;
            if(j.isEmpty()) return i;
            return i+" "+j;
        }));

        List<Integer> nums3 = List.of(1, 2, 3, 4, 5, 6);

         // Expected output: {ODD=[1,3,5], EVEN=[2,4,6]}


        System.out.println(nums3.parallelStream().reduce(new HashMap<String, ArrayList<Integer>>(),
                (a,b)-> {
                    HashMap<String, ArrayList<Integer>> newAccu = new HashMap<>(a);
                    var key =   b % 2 == 0 ? "odd" :  "even"; // var key = b % 2 == 0 ? "even" : "odd"; // ✅
                    var value = a.getOrDefault(key, new ArrayList<Integer>()); // new ArrayList<>(a.getOrDefault(key, new ArrayList<>())); // ✅
                    value.add(b);
                    newAccu.put(key, value);
                    return newAccu;
                },
                (i,j) -> {
                    if(i.isEmpty()) return j;
                    if(j.isEmpty()) return i;
                    HashMap<String, ArrayList<Integer>> newCombiner = new HashMap<>(i);
                    j.forEach((k,v) -> {
                        var value = i.getOrDefault(k, new ArrayList<Integer>());  // var value = new ArrayList<>(i.getOrDefault(k, new ArrayList<>())); // ✅
                        value.addAll(v);
                        newCombiner.put(k,value);
                    });
                    return newCombiner;
                }
        ));


        System.out.println(nums.stream().collect(
                Collectors.teeing(
                        Collectors.filtering(n -> n % 2 == 0, Collectors.toList()),
                        Collectors.filtering(n -> n % 2 != 0, Collectors.toList()),
                        (evens, odds) -> evens.size() + odds.size()
                )
        ).toString());
    }
}
