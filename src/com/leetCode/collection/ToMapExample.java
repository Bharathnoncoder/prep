package com.leetCode.collection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMapExample {


    public static void main(String[] args) {

        // 1. Basic
       // toMap(keyMapper, valueMapper)
       // ⚠️ throws exception on duplicate keys

// 2. With merge function — handles duplicates
       // toMap(keyMapper, valueMapper, mergeFunction)

// 3. With merge function + map factory
    //        toMap(keyMapper, valueMapper, mergeFunction, mapFactory)
        record Employee(String name, String department, double salary) {}

        List<Employee> workers = List.of(
                new Employee("Alice", "Engineering", 90000),
                new Employee("Bob", "HR", 70000),
                new Employee("Charlie", "Marketing", 75000),
                new Employee("Diana", "Engineering", 60000)
        );

// Expected output:
// {Alice=90000.0, Bob=70000.0, Charlie=75000.0, Diana=60000.0}

        System.out.println( workers.stream().collect(Collectors.toMap(s-> s.name(),Employee::salary)));


        record Staff(String name, String department, double salary) {}

        List<Staff> team = List.of(
                new Staff("Alice", "Engineering", 90000),
                new Staff("Bob", "Engineering", 80000),
                new Staff("Charlie", "HR", 70000),
                new Staff("Diana", "HR", 60000),
                new Staff("Eve", "Marketing", 75000),
                new Staff("Frank", "Marketing", 85000)
        );

// Expected output:
// {Engineering=170000.0, HR=130000.0, Marketing=160000.0}


        System.out.println( team.stream().collect(Collectors.toMap(Staff::department, Staff::salary, Double::sum)));


        record Student(String name, String subject, int score) {}

        List<Student> pupils = List.of(
                new Student("Alice", "Math", 85),
                new Student("Bob", "Math", 92),
                new Student("Charlie", "Science", 78),
                new Student("Diana", "Science", 95),
                new Student("Eve", "English", 88),
                new Student("Frank", "English", 73)
        );

// Expected output:
// {Math=Bob, Science=Diana, English=Eve}

       var value =  pupils.stream().collect(Collectors.toMap(Student::subject,s->s, (Student o,Student n)->  o.score() > n.score() ? o : n));

        System.out.println(value.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,v -> v.getValue().name)));

//        pupils.stream().collect(
//                Collectors.toMap(
//                        Student::subject,
//                        Student::name,
//                        (n1, n2) -> {
//                            // find the student with higher score
//                            return pupils.stream()
//                                    .filter(s -> s.name().equals(n1) || s.name().equals(n2))
//                                    .max(Comparator.comparingInt(Student::score))
//                                    .get().name();
//                        }
//                )
//        );





    }
}
