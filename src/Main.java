import java.net.CookieHandler;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Arun", "IT", 55000),
                new Employee(2, "Bharath", "IT", 65000),
                new Employee(3, "Karthik", "HR", 48000),
                new Employee(4, "Divya", "Finance", 70000),
                new Employee(5, "Swathi", "HR", 52000),
                new Employee(6, "Vijay", "Finance", 60000),
                new Employee(7, "Abitha", "IT", 75000),
                new Employee(8, "John", "Admin", 45000),
                new Employee(8, "John", "Admin", 45000)
        );
        System.out.println( employees.stream().map(Employee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).toList().getFirst());

//       var result = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
//                Collectors.collectingAndThen(
//                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))
//                , Optional::get)));


        var result2 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        Optional::get)));
        System.out.println(result2);

        List<Integer> numbers = Arrays.asList(10, 20, 10, 5, 40, 30, 20, 50, 5);


        var result3 = employees.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection( () -> new TreeSet<>(Comparator.comparing(Employee::getName))),ArrayList::new
        ));

        System.out.println(result3);


        var result4 = employees.stream().filter(e -> e.getSalary() % 2 == 0).toList();
        System.out.println(result4);

        List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");

        var result5 = strings.stream().collect(StringBuilder::new, (a, b) -> a.append(", "+b),(x, y) -> x.append(y));
        System.out.println(result5);

        var result6 = strings.stream().collect(Collectors.joining(", "));
        System.out.println(result6);

        var result7 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(result7);

       var result8 =  employees.stream().filter(employee -> employee.getName().charAt(0) == 'A').sorted(Comparator.comparing(Employee::getName)).toList();
        System.out.println(result8);

       var result9 =  employees.stream().collect(Collectors.averagingDouble(Employee::getSalary)).doubleValue();
       var result10 =  employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);

        System.out.println(result9);
        System.out.println(result10);

        var result11 = employees.stream().collect(Collectors.partitioningBy(e-> e.getSalary() > 50000));

        System.out.println(result11);

    }
}