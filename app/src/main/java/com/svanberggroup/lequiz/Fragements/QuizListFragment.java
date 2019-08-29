package com.svanberggroup.lequiz.Fragements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svanberggroup.lequiz.Activities.EditQuizActivity;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;
import com.svanberggroup.lequiz.R;

import java.util.List;

public class QuizListFragment extends Fragment {

    private QuizViewModel mQuizViewModel;

    private RecyclerView mQuizListRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        //Add observer with LiveData here
        mQuizViewModel.getAllQuizzes().observe(getActivity(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                adapter.setQuizzes(quizzes);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_quiz_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_quiz:
                Intent intent = new Intent(getActivity(), EditQuizActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class QuizViewHolder extends RecyclerView.ViewHolder {

        public static final String EXTRA_QUIZ_ID = "com.svanberggroup.lequiz.quiz_id";

        private Quiz mQuiz;

        private TextView quizItemText;
        private Button quizEditButton;

        public QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_quiz, parent, false));

            quizItemText = (TextView) itemView.findViewById(R.id.quiz_title);
            quizEditButton = (Button) itemView.findViewById(R.id.edit_quiz_button);
            quizEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), EditQuizActivity.class);
                    intent.putExtra(EXTRA_QUIZ_ID, mQuiz.getId());
                    startActivity(intent);
                }
            });
        }

        public void bind (Quiz quiz) {
            mQuiz = quiz;
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
                holder.quizItemText.setText(quiz.getTitle());
                holder.bind(quiz);
            } else {
                holder.quizItemText.setText("NO Quizzes");
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
