package com.kryptkode.cyberman.djtech.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptkode.cyberman.djtech.R;
import com.kryptkode.cyberman.djtech.utils.DummyClass;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * Created by ILENWABOR DAVID on 07/08/2017.
 */

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.FAQSHolder> {
    private List<DummyClass> list;
    private DummyClass currentData;
    public FAQsAdapter(List<DummyClass> list){
        this.list = list;
    }

    public static class FAQSHolder extends RecyclerView.ViewHolder{
        private TextView questionNumber, question;
        private ExpandableTextView answer;
        public FAQSHolder(View v){
            super(v);
            questionNumber = (TextView) v.findViewById(R.id.question_number);
            question = (TextView) v.findViewById(R.id.question);
            answer = (ExpandableTextView) v.findViewById(R.id.answer);
        }
    }

    @Override
    public FAQSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqs_cardview, parent, false);
        return new FAQSHolder(view);
    }

    @Override
    public void onBindViewHolder(FAQSHolder holder, int position) {
        String number = String.valueOf(position + 1);
        currentData = list.get(position);
        holder.question.setText(currentData.question);
        holder.answer.setText(currentData.answer);
        holder.questionNumber.setText(number);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
