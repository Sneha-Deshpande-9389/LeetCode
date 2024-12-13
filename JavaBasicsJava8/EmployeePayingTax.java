package JavaBasicsJava8;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeePayingTax {
    public static void main(String[] args) {
        Employee e1 = new Employee("John", 1, "IT", 600000);
        Employee e2 = new Employee("Mark", 2, "IT", 300000);
        Employee e3 = new Employee("Harsh", 3, "R&D", 200000);
        Employee e4 = new Employee("Vidya", 4, "HR", 100000);
        Employee e5 = new Employee("Megha", 5, "HR", 700000);
        Employee e6 = new Employee("Sneha", 6, "R&D", 1000000);
        Employee e7 = new Employee("Sneha1", 6, "R&D", 1000000);
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);
        employees.add(e7);

        System.out.println(employees.stream().collect(Collectors.groupingBy(employee -> employee.salary)).entrySet()
                .stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList()));
        //employees.stream().filter(e -> e.salary >= 600000)
                //.forEach(employee -> System.out.println(employee.name + " : " + employee.salary + " tax amount: " + employee.salary*0.30));

        //employees.stream().sorted((emp1, emp2) ->  emp2.salary - emp1.salary ).forEach(employee -> System.out.println(employee.name));

        //employees.stream().collect(Collectors.groupingBy(employee -> employee.dept)).entrySet().stream()
         //       .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().toString()));

        System.out.println(employees.stream().collect(Collectors.groupingBy(employee -> employee.salary)).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .toList().get(2));


        /* use map reduce */
        //System.out.println(employees.stream().mapToInt(e -> e.salary).sum());

        //System.out.println(employees.stream().mapToInt(e -> e.salary).reduce(Integer::max).getAsInt());

        System.out.println(employees.stream().map(e -> e.name).filter(e -> e.startsWith("S")).collect(Collectors.toList()));

    }
}

class Employee {
    String name;
    int id;
    String dept;
    int salary;

    public Employee(String name, int id, String dept, int salary) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "JavaBasicsJava8.Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}
