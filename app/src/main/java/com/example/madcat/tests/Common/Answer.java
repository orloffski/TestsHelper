package com.example.madcat.tests.Common;

public class Answer {
    private int id;
    private int question_number;
    private int answer_number;
    private String text;
    private int answer_is_correct;

    public Answer() {
    }

    public Answer(int id, int question_number, int answer_number, String text, int answer_is_correct) {
        this.id = id;
        this.question_number = question_number;
        this.answer_number = answer_number;
        this.text = text;
        this.answer_is_correct = answer_is_correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public int getAnswer_number() {
        return answer_number;
    }

    public void setAnswer_number(int answer_number) {
        this.answer_number = answer_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswer_is_correct() {
        return answer_is_correct;
    }

    public void setAnswer_is_correct(int answer_is_correct) {
        this.answer_is_correct = answer_is_correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question_number=" + question_number +
                ", answer_number=" + answer_number +
                ", text='" + text + '\'' +
                ", answer_is_correct=" + answer_is_correct +
                '}';
    }
}
