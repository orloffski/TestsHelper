package com.example.madcat.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.madcat.tests.Common.Answer;
import com.example.madcat.tests.Common.QuestionItems;
import com.example.madcat.tests.DatabaseEngine.DatabaseHelper;
import com.example.madcat.tests.Common.Question;
import com.example.madcat.tests.RecyclerViewEngine.QuestionListAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity{

    private DatabaseHelper dbHelper;
    private SearchView questionFilter;
    private RecyclerView questionList;
    private QuestionListAdapter adapter;

    private Map<String, QuestionItems> questionItemsList = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(getApplicationContext());

        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        loadData();
        loadSearch();
        loadQuestionList();
    }

    @Override
    protected void onStop() {
        super.onStop();

        dbHelper.close();
    }

    private void loadData(){
        List<Question> questionList = dbHelper.getAllQuestions();

        for (Question question: questionList) {
            String key = String.valueOf(question.getNumber());
            QuestionItems item = new QuestionItems(question);

            List<Answer> answerList = dbHelper.getAnswersByQuestionNumber(question.getNumber());

            for(Answer answer : answerList){
                item.addAnswer(answer);
            }

            questionItemsList.put(key, item);
        }
    }

    private void loadSearch(){
        questionFilter = findViewById(R.id.questionFilter);

        questionFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    private void loadQuestionList(){
        questionList = findViewById(R.id.questionList);
        questionList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionListAdapter(this, questionItemsList);
        questionList.setAdapter(adapter);
    }
}
