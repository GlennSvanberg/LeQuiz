package com.svanberggroup.lequiz.Fragements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svanberggroup.lequiz.Activities.PlayQuizActivity;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

import java.util.List;

public class QuizListFragment extends Fragment {

    private QuizViewModel mQuizViewModel;

    private RecyclerView mQuizListRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mQuizListRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_recycler_view);
        final QuizListAdapter adapter = new QuizListAdapter(getActivity());
        mQuizListRecyclerView.setAdapter(adapter);
        mQuizListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        mQuizViewModel.getAllQuizzes().observe(getActivity(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                adapter.setQuizzes(quizzes);
            }
        });


        return view;
    }


    private class QuizViewHolder extends RecyclerView.ViewHolder {

        public static final String EXTRA_QUIZ_ID = "com.svanberggroup.lequiz.quiz_id";

        private Quiz mQuiz;

        private TextView mQuizItemText;
        private ConstraintLayout mConstraintLayoutLayout;

        public QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_quiz, parent, false));

            mQuizItemText = (TextView) itemView.findViewById(R.id.quiz_title);
            mConstraintLayoutLayout = (ConstraintLayout) itemView.findViewById(R.id.constraint_layout);
            mConstraintLayoutLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), PlayQuizActivity.class);
                    intent.putExtra(EXTRA_QUIZ_ID, mQuiz.getId());
                    startActivity(intent);
                }
            });
            /*
            quizEditButton = (ImageButton) itemView.findViewById(R.id.edit_quiz_button);
            quizEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), EditQuizActivity.class);
                    intent.putExtra(EXTRA_QUIZ_ID, mQuiz.getId());
                    startActivity(intent);
                }
            });
            */

        }

        public void bind (Quiz quiz) {
            mQuiz = quiz;
        }
    }

    private class QuizListAdapter extends RecyclerView.Adapter<QuizListFragment.QuizViewHolder> {
        private List<Quiz> mQuizzes;

        private final LayoutInflater mInflater;

        public QuizListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public QuizListFragment.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new QuizListFragment.QuizViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizListFragment.QuizViewHolder holder, int position) {
            if(mQuizzes != null) {
                Quiz quiz = mQuizzes.get(position);
                holder.mQuizItemText.setText(quiz.getTitle());
                holder.bind(quiz);
            } else {
                holder.mQuizItemText.setText("NO Quizzes");
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
