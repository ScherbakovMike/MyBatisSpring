package ru.mikescherbakov.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private Integer id;
    private String title;
    private String content;
    private byte[] image;
}
