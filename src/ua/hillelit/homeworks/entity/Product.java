package ua.hillelit.homeworks.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Product implements Comparable<Product> {
    private String type;
    private BigDecimal price;
    private boolean sale;
    private LocalDate date;

    public String getType() {
        return type;
    }

    public Product(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public Product(String type, BigDecimal price, boolean sale) {
        this.type = type;
        this.price = price;
        this.sale = sale;
    }

    public Product(String type, BigDecimal price, boolean sale, LocalDate date) {
        this.type = type;
        this.price = price;
        this.sale = sale;
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public LocalDate getDate() {
        return date;
    }

    public static List<Product> getList(List<Product> list) {
        return list.stream()
                .filter(coll -> coll.getType().equals("Book"))
                .filter(pr -> pr.getPrice().compareTo(BigDecimal.valueOf(250)) > 0)
                .collect(Collectors.toList());
    }

    public static List<Product> getSaleList(List<Product> list, BigDecimal saleValue) {
        return list.stream()
                .filter(coll -> coll.getType().equals("Book"))
                .filter(Product::isSale)
                .peek(sl -> sl.setPrice(sl.getPrice().multiply(saleValue)))
                .collect(Collectors.toList());
    }

    public static Product getCheaperProduct(List<Product> list, BigDecimal saleValue) {
        boolean isBook = list.stream()
                .noneMatch(coll -> "Book".equals(coll.getType()));
        if (!isBook) {
            throw new RuntimeException("Продукт категории 'Book' не найден");
        }
        return list.stream()
                .filter(coll -> coll.getType().equals("Book"))
                .filter(Product::isSale)
                .peek(coll -> coll.setPrice(coll.getPrice().multiply(saleValue)))
                .min(Product::compareTo).get();
    }

    public static List<Product> getLastProducts(List<Product> list, BigDecimal saleValue) {
        return list.stream()
                .filter(coll -> coll.getType().equals("Book"))
                .filter(Product::isSale)
                .peek(sl -> sl.setPrice(sl.getPrice().multiply(saleValue)))
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Product> getGeneralPriceOfFiltredProducts(List<Product> list, BigDecimal saleValue) {
        return list.stream()
                .filter(coll -> coll.getType().equals("Book"))
                .filter(coll -> coll.getPrice().compareTo(BigDecimal.valueOf(75)) < 0)
                .filter(coll -> coll.getDate().getYear() == LocalDate.now().getYear())
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", sale=" + sale +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return o.date.compareTo(date);
    }
}
