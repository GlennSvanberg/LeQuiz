package com.svanberggroup.lequiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;

public class QuizListFragment extends Fragment {

    private QuizViewModel mQuizViewModel;

    private RecyclerView mQuizListRecyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mQuizListRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_recycler_view);
        final QuizListAdapter adapter = new QuizListAdapter(getActivity());
        mQuizListRecyclerView.setAdapter(adapter);
        mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        //Add observer with LiveData here
        mQuizViewModel.getAllQuizzes().observe(getActivity(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                adapter.setQuizzes(quizzes);
            }
        });

        return view;
    }

    private class QuizViewHolder extends RecyclerView.ViewHolder {

        private TextView quizItemView;

        public QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_quiz, parent, false));

            quizItemView = (TextView) itemView.findViewById(R.id.quiz_title);

        }
    }

    private class QuizListAdapter extends RecyclerView.Adapter<QuizViewHolder> {
        private List<Quiz> mQuizzes;

        private final LayoutInflater mInflater;

        public QuizListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new QuizViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
            if(mQuizzes != null) {
                Quiz quiz = mQuizzes.get(position);
                holder.quizItemView.setText(quiz.getTitle());
            } else {
                holder.quizItemView.setText("NO Quizzes");
            }

        }

        @Override
        public int getItemCount() {
            if(mQuizzes != null) {
                return mQuizzes.size();
            }
            return 0;

        }

        public void setQuizzes(List<Quiz> quizzes) {
            mQuizzes = quizzes;
            notifyDataSetChanged();
        }
    }
}
