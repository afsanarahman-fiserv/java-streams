package com.xpanxion.solution;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.*;

public class Worker {

    public void ex1() {
        List<Product> productList = DataAccess.getProducts();
        Map<Integer, String> departments = DataAccess.getDepartments().stream()
                .collect(Collectors.toMap(Department::getId, Department::getName));
        productList.stream()
                .map(p -> new Product(p.getId(), p.getDepartmentId(), departments.get(p.getDepartmentId()), p.getName(), p.getPrice(), p.getSku()))
                .forEach(System.out::print);
    }

    public void ex2() {
        DataAccess.getProducts()
                .stream()
                .map(p -> new Product(p.getId(), p.getDepartmentId(), "N/A", p.getName(), p.getPrice(), p.getSku()))
                .forEach(System.out::print);
    }
    public void ex3() {
        DataAccess.getProducts()
                .stream()
                .filter(p -> p.getDepartmentId() == 1 && p.getPrice() >= 10)
                .forEach(System.out::print);
    }

    public void ex4() {
        double sum = DataAccess.getProducts()
                .stream()
                .filter(p -> p.getDepartmentId() == 2)
                .mapToDouble(Product::getPrice)
                .sum();
        DecimalFormat dFormat = new DecimalFormat("###,###,###.00");
        System.out.println("$" + dFormat.format(sum));
    }

    public void ex5() {
        DataAccess.getPeople()
                .stream()
                .filter(p -> p.getId() <= 3)
                .map(p -> new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(7)))
                .forEach(System.out::print);
    }

    public void ex6() {
        DataAccess.getCats()
                .stream()
                .sorted(Cat::compareTo)
                .forEach(System.out::print);
    }

    public void ex7() {
        var map = Arrays.stream(DataAccess.getWords().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        for(String key : keys) {
            System.out.println(key + "=" + map.get(key));
        }
    }

    public void ex8() {
        DataAccess.getPeople()
                .stream()
                .map(p -> new Person(p.getId(), p.getFirstName(), "null", 0, "null"))
                .forEach(System.out::print);
    }

    public void ex9() {
        double sum = DataAccess.getProducts()
                .stream()
                .filter(p -> p.getDepartmentId() == 1)
                .mapToDouble(p -> p.getPrice() + 2)
                .sum();
        DecimalFormat dFormat = new DecimalFormat("###,###,###.00");
        System.out.println("$" + dFormat.format(sum));
    }

    public void ex10() {
        var cats = DataAccess.getCats();
        DataAccess.getPeople()
                .stream()
                .map(p -> new PersonCat(p.getId(), p.getFirstName(),
                        cats.stream()
                                .filter(c -> p.getId() == c.getId())
                                .collect(Collectors.toList())))
                .forEach(System.out::print);
    }
}
