package com.example.springboot.dto;

public record BookSearchParameters(String[] titles, String[] authors,
                                   String[] prices, String[] isbns) {
}
