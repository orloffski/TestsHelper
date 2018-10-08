package com.example.madcat.tests.Common;

public class Question {
    private int id;
    private int number;
    private String text;
    private String image_name;

    public Question() {
    }

    public Question(int id, int number, String text, String image_name) {
        this.id = id;
        this.number = number;
        this.text = text;
        this.image_name = image_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", number=" + number +
                ", text='" + text + '\'' +
                ", image_name='" + image_name + '\'' +
                '}';
    }
}
