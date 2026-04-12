package com.leetCode.collection;

import javax.swing.*;
import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectInJava {
    public static void main(String[] args) {

        List<String> list = List.of("HI","I am ", "a", "Looser");

        System.out.println(list.stream().collect(
                StringBuilder::new,
                (s, a) -> {
                    s.append(" ").append(a);
                    }
                ,
                StringBuilder::append
                ).toString());

        List<String> words = List.of("apple", "banana", "orange", "grape", "avocado", "cherry");

        // Expected output: [apple, orange, avocado]

        var constV = List.of('a','e','i','o','u');

        System.out.println(Arrays.toString(words.stream().collect(
                ArrayList::new,
                (arr, s) -> {
                    if (constV.contains(s.charAt(0))) {
                        arr.add(s);
                    }
                },
                ArrayList::addAll
        ).toArray()));

        // Unlike reduce, your accumulator mutates arr in place and returns nothing (BiConsumer):

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

//         // Expected output: {ODD=[1,3,5], EVEN=[2,4,6]}
//
//        var map = nums.stream().collect(Collectors.partitioningBy(s-> s% 2 ==0));
//
//        Map<String, List<Integer>> finMap = new HashMap<>();
//
//        map.forEach((k,v)->{
//            if(k)
//                finMap.put("EVEN", v);
//            else
//                finMap.put("ODD", v);
//        });
//
//        System.out.println(finMap);

        List<String> words1 = List.of("java", "is", "very", "cool");

        // Expected output: [java | is | very | cool]

        System.out.println( words1.stream().collect(StringBuilder::new,
                (sb, s)->{
                    if(sb.isEmpty())
                        sb.append(s);
                    else
                        sb.append(" | ").append(s);
                },
                StringBuilder::append
        ).toString());


        List<String> words2 = List.of("apple", "banana", "apple", "orange", "banana", "apple");

// Expected output: {apple=3, banana=2, orange=1}


        System.out.println( words2.parallelStream().collect(HashMap::new,
                (HashMap<String, Integer> map,String c)-> {
                    Integer temp = map.getOrDefault(c, 0);
                    map.put(c, temp+1);
                },
                (t1, t2)->{
                    t2.forEach((k, v)->{
                        Integer temp = t1.getOrDefault(k, 0);
                        t1.put(k, v+temp);
                    });
                }));


        record Employee(String name, String department, double salary) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 90000),
                new Employee("Bob", "Engineering", 80000),
                new Employee("Charlie", "HR", 70000),
                new Employee("Diana", "HR", 60000),
                new Employee("Eve", "Marketing", 75000),
                new Employee("Frank", "Marketing", 85000)
        );

// Expected output:
// {Engineering=85000.0, HR=65000.0, Marketing=80000.0}

        Map<String, Object[]> nonProcessedMap = employees.parallelStream().collect(HashMap::new,
                (HashMap<String, Object[]> map,Employee e)->{
                    map.merge(e.department(), new Object[]{e.salary, 1.0}, (a,b) -> {
                        System.out.println(Arrays.toString(a) + " " + Arrays.toString(b));
                         double c = (Double) a[0] + (Double) b[0];
                         double d = (Double) a[1]+ (Double) b[1];
                        return new Object[]{c,d};
                    });
                },
                (stringDoubleHashMap, stringDoubleHashMap2) ->
                        stringDoubleHashMap2.forEach((k,v)-> stringDoubleHashMap.merge(k,v,(a,b)->
                        {
                            double c = (Double) a[0] + (Double) b[0];
                            double d = (Double) a[1]+ (Double) b[1];
                           return new Object[]{c,d};
                        }   )));

        Map<String,Double> finalMap = new HashMap<>();
        nonProcessedMap.forEach((k,v)->{
            double value = (double)v[0]/ (double)v[1] ;
            finalMap.put(k, value);
        });

        System.out.println(finalMap);


        List<Employee> staff = List.of(
                new Employee("Alice", "Engineering", 90000),
                new Employee("Bob", "Engineering", 80000),
                new Employee("Charlie", "HR", 70000),
                new Employee("Diana", "HR", 60000),
                new Employee("Eve", "Marketing", 75000),
                new Employee("Frank", "Marketing", 85000)
        );

// Expected output:
// {Engineering=[Alice, Bob], HR=[Charlie, Diana], Marketing=[Eve, Frank]}

       var s = staff.stream().collect(groupingBy(Employee::department, mapping(Employee::name, toList())));

        System.out.println(s);


        record Order(String customer, String product, double amount) {}

        List<Order> orders = List.of(
                new Order("Alice", "Laptop", 1200),
                new Order("Bob", "Phone", 800),
                new Order("Alice", "Tablet", 450),
                new Order("Charlie", "Laptop", 1200),
                new Order("Bob", "Headphones", 150),
                new Order("Alice", "Charger", 50)
        );

// Expected output:
// {Alice=3, Bob=2, Charlie=1}

        System.out.println(orders.stream().collect(Collectors.groupingBy(Order::customer, counting() )));


// groupingBy(HOW TO GROUP, WHAT TO DO WITH EACH GROUP)

        record Purchase(String customer, String product, double amount) {}

        List<Purchase> purchases = List.of(
                new Purchase("Alice", "Laptop", 1200),
                new Purchase("Bob", "Phone", 800),
                new Purchase("Alice", "Tablet", 450),
                new Purchase("Charlie", "Laptop", 1200),
                new Purchase("Bob", "Headphones", 150),
                new Purchase("Alice", "Charger", 50)
        );

// Expected output:
// {Alice=1700.0, Bob=950.0, Charlie=1200.0}


        System.out.println(purchases.stream().collect(Collectors.groupingBy(Purchase::customer, summingDouble(Purchase::amount))));


        record Staff(String name, String department, double salary) {}

        List<Staff> company = List.of(
                new Staff("Alice", "Engineering", 90000),
                new Staff("Bob", "Engineering", 60000),
                new Staff("Charlie", "HR", 70000),
                new Staff("Diana", "HR", 40000),
                new Staff("Eve", "Marketing", 75000),
                new Staff("Frank", "Marketing", 55000)
        );

// Expected output:
// {Engineering={High=[Alice], Low=[Bob]}, HR={High=[Charlie], Low=[Diana]}, Marketing={High=[Eve], Low=[Frank]}}

//       staff.stream().collect(Collectors.groupingBy(Staff::department,Collectors.groupingBy( m -> ( m.salary() >= 7000 ? "High":"Low"), mapping(Staff::name,toList()))));

        record Transaction(String customer, String month, String product, double amount) {}

        List<Transaction> transactions = List.of(
                new Transaction("Alice", "Jan", "Laptop", 1200),
                new Transaction("Alice", "Jan", "Charger", 50),
                new Transaction("Alice", "Feb", "Tablet", 450),
                new Transaction("Bob", "Jan", "Phone", 800),
                new Transaction("Bob", "Feb", "Headphones", 150),
                new Transaction("Bob", "Feb", "Cable", 30)
        );

// Expected output:
// {
//   Alice = {
//     Jan = {totalSpend=1250.0, mostExpensive=Laptop},
//     Feb = {totalSpend=450.0,  mostExpensive=Tablet}
//   },
//   Bob = {
//     Jan = {totalSpend=800.0,  mostExpensive=Phone},
//     Feb = {totalSpend=180.0,  mostExpensive=Headphones}
//   }
// }


        System.out.println( transactions.stream().collect(Collectors.groupingBy(Transaction::customer
                , Collectors.groupingBy(Transaction::month,
                        Collectors.teeing( Collectors.summarizingDouble(Transaction::amount),
                                Collectors.maxBy((l1,l2)-> l1.amount > l2.amount ? 1 : 0),
                                (a,b) ->{
                                    return Map.of("totalSpend", a.getSum() , "mostExpensive", b.get().product);
                                })
                ))));


        record Employee1(String name, String department, double salary) {}

        List<Employee1> team = List.of(
                new Employee1("Alice", "Engineering", 90000),
                new Employee1("Bob", "HR", 70000),
                new Employee1("Charlie", "Marketing", 75000)
        );

// Expected output:
// [Alice (Engineering) | Bob (HR) | Charlie (Marketing)]

        System.out.println(team.stream().map(m -> m.name+ " ("+ m.department+")").collect(Collectors.joining(" | ","[","]")));


//        record Staff(String name, String department, double salary) {}
//
//        List<Staff> company = List.of(
//                new Staff("Alice", "Engineering", 90000),
//                new Staff("Bob", "HR", 55000),
//                new Staff("Charlie", "Marketing", 75000),
//                new Staff("Diana", "Engineering", 48000),
//                new Staff("Eve", "HR", 95000),
//                new Staff("Frank", "Marketing", 62000)
//        );

// Expected output:
// {
//   false=[Bob, Diana, Frank],
//   true=[Alice, Charlie, Eve]
// }
        System.out.println(company.stream().collect(Collectors.partitioningBy(e -> e.salary() >= 70000, Collectors.mapping(Staff::name, toList()) )));


        record Product(String name, int quantity, double price) {}

        List<Product> inventory = List.of(
                new Product("Laptop", 5, 1200),
                new Product("Phone", 0, 800),
                new Product("Tablet", 3, 450),
                new Product("Charger", 0, 50),
                new Product("Headphones", 2, 150),
                new Product("Cable", 0, 30)
        );

// Expected output:
// {false=3, true=3}

        inventory.stream().collect(Collectors.partitioningBy(e -> e.quantity > 0, Collectors.counting()));



        record Student(String name, String subject, int score) {}

        List<Student> students = List.of(
                new Student("Alice", "Math", 85),
                new Student("Bob", "Math", 42),
                new Student("Charlie", "Math", 78),
                new Student("Diana", "Math", 35),
                new Student("Eve", "Math", 91),
                new Student("Frank", "Math", 55)
        );

// Expected output:
// {
//   false = Optional[Diana(35)],
//   true  = Optional[Eve(91)]
// }


        System.out.println(students.stream().collect(
                Collectors.partitioningBy(str-> str.score() >= 60,
                        Collectors.maxBy(((st1,st2)-> st1.score() > st2.score() ? 1: 0)))));
    }
}
