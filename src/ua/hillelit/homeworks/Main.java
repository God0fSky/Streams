package ua.hillelit.homeworks;

import ua.hillelit.homeworks.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", BigDecimal.valueOf(210), true,
                LocalDate.of(2022, 1, 11)));
        productList.add(new Product("NotBook", BigDecimal.valueOf(350), true,
                LocalDate.of(2022, 2, 21)));
        productList.add(new Product("Book", BigDecimal.valueOf(70), false,
                LocalDate.of(2022, 3, 1)));
        productList.add(new Product("Book", BigDecimal.valueOf(500), true,
                LocalDate.of(2021, 4, 17)));
        productList.add(new Product("Book", BigDecimal.valueOf(195), true,
                LocalDate.of(2021, 4, 15)));
        productList.add(new Product("Book", BigDecimal.valueOf(50), false,
                LocalDate.of(2022, 4, 16)));

        Product.getList(productList).stream()
                .peek(pr -> System.out.println(pr))
                .collect(Collectors.toList());

        System.out.println();

        Product.getSaleList(productList, BigDecimal.valueOf(0.9)).stream()
                .peek(pr -> System.out.println(pr))
                .collect(Collectors.toList());

        System.out.println();

        try {
            System.out.println(Product.getCheaperProduct(productList, BigDecimal.valueOf(0.9)));
        } catch (RuntimeException e) {
            e.getMessage();
        }

        Product.getLastProducts(productList, BigDecimal.valueOf(0.9)).stream()
                .peek(pr -> System.out.println(pr))
                .collect(Collectors.toList());

        System.out.println();

        Product.getGeneralPriceOfFiltredProducts(productList, BigDecimal.valueOf(0.9)).stream()
                .peek(pr -> System.out.println(pr))
                .collect(Collectors.toList());

        System.out.println();



        /*Map<String, List<Product>> map = Product.groupToMap(productList);
        map.entrySet().stream().forEach(System.out::println);*/
    }
}
