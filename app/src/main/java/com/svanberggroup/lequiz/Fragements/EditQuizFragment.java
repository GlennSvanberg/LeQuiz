package com.svanberggroup.lequiz.Fragements;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.svanberggroup.lequiz.Activities.EditQuizActivity;
import com.svanberggroup.lequiz.Models.Question;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.QuestionViewModel;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditQuizFragment extends Fragment {


    private EditText mQuizTitleField;
    private RecyclerView mQuestionsRecyclerView;
    private FloatingActionButton mNewQuestionFab;

    private QuizViewModel mQuizViewModel;
    private QuestionViewModel mQuestionViewModel;
    private QuestionsAdapter mQuestionsAdapter;

    private Quiz mQuiz;
    private List<Question> mQuestions;

    private static final String ARG_QUIZ_ID = "quiz_id";


    public static EditQuizFragment newInstance(UUID quizId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUIZ_ID, quizId);

        EditQuizFragment fragment = new EditQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        if(getArguments().getSerializable(ARG_QUIZ_ID) == null) {
            mQuiz = new Quiz();
            mQuizViewModel.insert(mQuiz);
        } else {
            mQuiz = new Quiz();
            mQuiz.setId((UUID) getArguments().getSerializable(ARG_QUIZ_ID));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_edit_quiz,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_quiz:

                mQuizViewModel.delete(mQuiz);
                Toast.makeText(getActivity(), mQuiz.getTitle() + " has been deleted", Toast.LENGTH_SHORT).show();
                getActivity().finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mQuizViewModel.update(mQuiz);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_quiz, container, false);
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        mQuizViewModel.getAllQuizzes().observe(getActivity(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                for(Quiz quiz : quizzes) {
                    if(quiz.getId().equals(mQuiz.getId())) {
                        mQuiz = quiz;
                        updateUI();
                        return;
                    }
                }

            }
        });
        mQuestionViewModel.getAllQuestions().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                mQuestions = new ArrayList<>();
                for (Question question : questions) {
                    if(question.getQuizId().equals(mQuiz.getId())) {
                        mQuestions.add(question);
                    }
                }
                updateUI();
            }
        });

        mQuizTitleField = (EditText) view.findViewById(R.id.quiz_title);
        mQuizTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mQuiz.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mQuestionsRecyclerView = (RecyclerView) view.findViewById(R.id.questions_recycler_view);
        mQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewQuestionFab = (FloatingActionButton) view.findViewById(R.id.new_question_fab);
        mNewQuestionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question q = new Question(mQuiz.getId());
                q.setTitle("First Question" + q.getId());

                mQuestionViewModel.insert(q);
            }
        });


        updateUI();
        return view;
    }
    private void updateUI() {

        if(mQuiz != null) {
            mQuizTitleField.setText(mQuiz.getTitle());
        }

        if(mQuestionsAdapter == null) {
            mQuestionsAdapter = new QuestionsAdapter(mQuestions);
            mQuestionsRecyclerView.setAdapter(mQuestionsAdapter);
        } else {
            mQuestionsAdapter.setQuestions(mQuestions);
            mQuestionsAdapter.notifyDataSetChanged();
        }
    }

    private class QuestionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Question mQuestion;

        private TextView mQuestionTextView;

        public QuestionsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_question,parent,false));
            itemView.setOnClickListener(this);

            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_title);
        }

        @Override
        public void onClick(View view) {

            Log.i("TESTTESTTEST", "EditQuizFragment QuestionHolder clicked");
            //Intent intent = QuestionsPagerActivity.newIntent(getActivity(), mQuiz.getId(), mQuestion.getId());
            //startActivity(intent);
        }

        public void bind(Question question) {
            mQuestion = question;
            mQuestionTextView.setText(mQuestion.getTitle());
        }
    }
    private class QuestionsAdapter extends RecyclerView.Adapter<QuestionsHolder> {

        private List<Question> mQuestions;

        public QuestionsAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new QuestionsHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionsHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(question);
        }

        @Override
        public int getItemCount() {
            if(mQuestions == null) {
                return 0;
            }
            return mQuestions.size();
        }

        public void setQuestions(List<Question> questions) {
            mQuestions = questions;
        }
    }
}
