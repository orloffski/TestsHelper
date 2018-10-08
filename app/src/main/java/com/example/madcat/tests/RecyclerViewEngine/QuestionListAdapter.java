package com.example.madcat.tests.RecyclerViewEngine;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madcat.tests.Common.Answer;
import com.example.madcat.tests.Common.Question;
import com.example.madcat.tests.Common.QuestionItems;
import com.example.madcat.tests.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater mInflater;
    private Map<String, QuestionItems> questionItemsList;
    private List<Question> questions;
    private List<Question> questionsCopy;
    private int test;

    public QuestionListAdapter(Context context, Map<String, QuestionItems> questionItemsList) {
        this.context = context;
        this.mInflater = LayoutInflater.from(this.context);
        this.questionItemsList = questionItemsList;

        questions = new ArrayList<>();
        loadQuestionsList();
        questionsCopy = new ArrayList<>(questions);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // загрузка вопроса
        holder.questionText.setText(questions.get(position).getText());

        // загрузка изображения - если нет, грузится заглушка в 1 пиксель
        String imagePath = "file:///android_asset/common_images/" + questions.get(position).getImage_name();
        Glide.with(this.context).load(Uri.parse(imagePath)).into(holder.questionImage);

        // загрузка ответов
        List<Answer> answers = questionItemsList.get(String.valueOf(questions.get(position).getNumber())).getAnswerList();
        StringBuilder answersText = new StringBuilder();
        for(Answer answer : answers){
            answersText.append(answer.getAnswer_number()).append(" ");
            answersText.append(answer.getText()).append(" ");
            answersText.append(answer.getAnswer_is_correct()).append("\n");
        }
        holder.answers.setText(answersText.toString());

        holder.questionImage.setVisibility(View.GONE);
        holder.answers.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return this.questions.size();
        //return this.questionItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView questionText;
        ImageView questionImage;
        TextView answers;

        ViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            questionImage = itemView.findViewById(R.id.questionImageView);
            answers = itemView.findViewById(R.id.answersTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.findViewById(R.id.answersTextView).getVisibility() == View.VISIBLE){
                v.findViewById(R.id.questionImageView).setVisibility(View.GONE);
                v.findViewById(R.id.answersTextView).setVisibility(View.GONE);
            }else{
                v.findViewById(R.id.questionImageView).setVisibility(View.VISIBLE);
                v.findViewById(R.id.answersTextView).setVisibility(View.VISIBLE);
            }

            notifyItemChanged(getAdapterPosition() + 1);
        }
    }

    public void filter(String text) {
        questions.clear();

        if(text.isEmpty()){
            loadQuestionsList();
        } else{
            text = text.toLowerCase();
            for(Question question : questionsCopy){
                if(question.getText().toLowerCase().contains(text)){
                    questions.add(question);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void loadQuestionsList(){
        for(Map.Entry entry : questionItemsList.entrySet()){
            questions.add(((QuestionItems)entry.getValue()).getQuestion());
        }
    }
}
