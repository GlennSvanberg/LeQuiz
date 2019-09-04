package com.svanberggroup.lequiz.Fragements;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.svanberggroup.lequiz.Activities.EditQuestionActivity;
import com.svanberggroup.lequiz.Models.Answer;
import com.svanberggroup.lequiz.Models.Question;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.AnswerViewModel;
import com.svanberggroup.lequiz.ViewModels.QuestionViewModel;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

import java.util.List;
import java.util.UUID;

public class EditQuizFragment extends Fragment {


    private EditText mQuizTitleField;
    private RecyclerView mQuestionsRecyclerView;
    private FloatingActionButton mNewQuestionFab;

    private QuizViewModel mQuizViewModel;
    private QuestionViewModel mQuestionViewModel;
    private AnswerViewModel mAnswerViewModel;
    private QuestionsAdapter mQuestionsAdapter;

    private Quiz mQuiz;
    private List<Question> mQuestions;
    private List<Answer> mAnswers;

    private static final String ARG_QUIZ_ID = "quiz_id";
    public static final String EXTRA_QUESTION_ID = "com.svanberggroup.lequiz.question_id";


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

                Toast.makeText(getActivity(), mQuiz.getTitle() + " has been deleted", Toast.LENGTH_SHORT).show();
                mQuizViewModel.delete(mQuiz);

                getActivity().finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        storeQuiz();
    }

    @Override
    public void onPause() {
        super.onPause();
        storeQuiz();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_quiz, container, false);

        mQuizViewModel.getQuiz(mQuiz.getId().toString()).observe(getActivity(), new Observer<Quiz>() {
            @Override
            public void onChanged(Quiz quiz) {
                mQuiz = quiz;
                updateUI();
                return;


            }
        });
        mQuestionViewModel.getQuestions(mQuiz.getId().toString()).observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {

                mQuestions = questions;
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

                Question question = new Question(mQuiz.getId());
                Intent intent = new Intent(getActivity(), EditQuestionActivity.class);
                intent.putExtra(EXTRA_QUESTION_ID, question.getId());
                startActivity(intent);

                storeQuiz();
                mQuestionViewModel.insert(question);
            }
        });

        updateUI();
        return view;
    }

    private void storeQuiz() {
        if(mQuiz != null) {
            mQuizViewModel.update(mQuiz);
        }
    }
    public void updateUI() {

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
            super(inflater.inflate(R.layout.list_item_edit_question,parent,false));
            itemView.setOnClickListener(this);

            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_title);

        }

        @Override
        public void onClick(View view) {
            storeQuiz();
            Intent intent = new Intent(getActivity(), EditQuestionActivity.class);
            intent.putExtra(EXTRA_QUESTION_ID, mQuestion.getId());
            startActivity(intent);

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
