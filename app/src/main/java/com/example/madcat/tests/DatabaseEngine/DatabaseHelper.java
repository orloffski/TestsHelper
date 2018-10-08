package com.example.madcat.tests.DatabaseEngine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.madcat.tests.Common.Answer;
import com.example.madcat.tests.Common.Question;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.madcat.tests/databases/"; // полный путь к базе данных

    private static final String DATABASE_NAME = "tests.db"; // название бд
    private static final int SCHEMA_VERSION = 1; // версия базы данных
    // название таблиц в бд
    static final String TABLE_QUESTIONS = "questions";
    static final String TABLE_ANSWERS = "answers";

    Context context;
    private SQLiteDatabase myDataBase;

    // переменные для query
    private String[] columns = null;
    private String selection = null;
    private String[] selectionArgs = null;
    private String groupBy = null;
    private String having = null;
    private String orderBy = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String questionsTable = "CREATE TABLE " + TABLE_QUESTIONS +
//                "(" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "QUESTION_NUMBER INTEGER, " +
//                "QUESTION_TEXT TEXT, " +
//                "IMAGE_PATH TEXT);";
//        String answersTable = "CREATE TABLE " + TABLE_ANSWERS +
//                "(" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "QUESTION_NUMBER INTEGER, " +
//                "ANSWER_NUMBER INTEGER, " +
//                "ANSWER_TEXT TEXT, " +
//                "ANSWER_IS_CORRECT INTEGER" +
//                ");";
//
//        db.execSQL(questionsTable);
//        db.execSQL(answersTable);
//
//        addData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Question> getAllQuestions(){
        Cursor cursor = myDataBase.query(
                TABLE_QUESTIONS,
                null,
                null,
                null,
                null,
                null,
                null);

        return getQuestionList(cursor);
    }

    public List<Answer> getAnswersByQuestionNumber(int questionNumber){
        selection = "QUESTION_NUMBER = ?";
        selectionArgs = new String[] { String.valueOf(questionNumber) };

        Cursor cursor = myDataBase.query(
                TABLE_ANSWERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        return getAnswerList(cursor);
    }

    private List<Question> getQuestionList(Cursor cursor){
        List<Question> queriesList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setNumber(Integer.parseInt(cursor.getString(1)));
                question.setText(cursor.getString(2));
                question.setImage_name(cursor.getString(3));
                queriesList.add(question);
            } while (cursor.moveToNext());
        }

        return queriesList;
    }

    private List<Answer> getAnswerList(Cursor cursor){
        List<Answer> answersList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setId(Integer.parseInt(cursor.getString(0)));
                answer.setQuestion_number(Integer.parseInt(cursor.getString(1)));
                answer.setAnswer_number(Integer.parseInt(cursor.getString(2)));
                answer.setText(cursor.getString(3));
                answer.setAnswer_is_correct(Integer.parseInt(cursor.getString(4)));
                answersList.add(answer);
            } while (cursor.moveToNext());
        }

        return answersList;
    }

    private void addData(SQLiteDatabase db){
//        db.execSQL("INSERT INTO questions VALUES (null, 1, 'Вопрос 1', '');");
//        db.execSQL("INSERT INTO answers VALUES (null, 1, 1, 'Ответ 1', 0);");
//        db.execSQL("INSERT INTO answers VALUES (null, 1, 2, 'Ответ 2', 1);");
//        db.execSQL("INSERT INTO answers VALUES (null, 1, 3, 'Ответ 3', 0);");
//
//        db.execSQL("INSERT INTO questions VALUES (null, 2, 'Вопрос 2', '');");
//        db.execSQL("INSERT INTO answers VALUES (null, 2, 1, 'Ответ 1', 0);");
//        db.execSQL("INSERT INTO answers VALUES (null, 2, 2, 'Ответ 2', 0);");
//        db.execSQL("INSERT INTO answers VALUES (null, 2, 3, 'Ответ 3', 0);");
//        db.execSQL("INSERT INTO answers VALUES (null, 2, 4, 'Ответ 4', 1);");
//
//        db.execSQL("INSERT INTO questions VALUES (null, 3, 'Вопрос 3', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 4, 'Вопрос 4', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 5, 'Вопрос 5', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 6, 'Вопрос 6', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 7, 'Вопрос 7', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 8, 'Вопрос 8', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 9, 'Вопрос 9', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 10, 'Вопрос 10', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 11, 'Вопрос 11', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 12, 'Вопрос 12', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 13, 'Вопрос 13', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 14, 'Вопрос 14', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 15, 'Вопрос 15', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 16, 'Вопрос 16', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 17, 'Вопрос 17', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 18, 'Вопрос 18', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 19, 'Вопрос 19', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 20, 'Вопрос 20', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 21, 'Вопрос 21', '');");
//        db.execSQL("INSERT INTO questions VALUES (null, 22, 'Вопрос 22', '');");
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();

        if(dbExist){
            //ничего не делать - база уже есть
        }else{
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //база еще не существует
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
}
