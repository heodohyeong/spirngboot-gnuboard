package com.gnuboard.demo.util.redis.booktest;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "price")
    private int price;


    @Column(name = "stock")
    private Long stock;//재고

    public void purchase(final long quantity) {
        if((stock - quantity) < 0){
            throw new IllegalArgumentException();
        }
        stock -= quantity;
    }

}
