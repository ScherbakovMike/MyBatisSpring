package ru.mikescherbakov.models;

import lombok.Getter;

@Getter
public class Question {
    private Integer id;
    private String title;
    private String content;
    private byte[] image;
}
