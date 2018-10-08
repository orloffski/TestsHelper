package com.example.madcat.tests.Common;

import java.util.ArrayList;
import java.util.List;

public class QuestionItems {
    Question question;
    List<Answer> answerList;

    public QuestionItems(Question question) {
        this.question = question;
        this.answerList = new ArrayList<>();
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void addAnswer(Answer answer){
        this.answerList.add(answer);
    }

    public void addAnswers(List<Answer> answers){
        this.answerList.addAll(answers);
    }
}
