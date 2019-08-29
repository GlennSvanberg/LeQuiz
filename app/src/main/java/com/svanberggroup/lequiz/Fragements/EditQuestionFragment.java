package com.svanberggroup.lequiz.Fragements;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.svanberggroup.lequiz.Models.Answer;
import com.svanberggroup.lequiz.Models.Question;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.AnswerViewModel;
import com.svanberggroup.lequiz.ViewModels.QuestionViewModel;

import java.util.List;
import java.util.UUID;

public class EditQuestionFragment extends Fragment {

    private QuestionViewModel mQuestionViewModel;
    private AnswerViewModel mAnswerViewModel;

    private Question mQuestion;
    private List<Answer> mAnswers;
    private UUID mQuestionId;

    private RecyclerView mAnswersRecyclerView;
    private EditText mQuestionField;
    private Button mNewAnswerButton;
    private AnswersAdapter mAdapter;

    private static final String ARG_QUESTION_ID = "question_id";

    public static EditQuestionFragment newInstance(UUID questionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, questionId);

        EditQuestionFragment fragment = new EditQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        mAnswerViewModel = ViewModelProviders.of(this).get(AnswerViewModel.class);

        mQuestionId = (UUID) getArguments().getSerializable(ARG_QUESTION_ID);

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mQuestionViewModel.update(mQuestion);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_question, container, false);

        mQuestionField = (EditText) view.findViewById(R.id.question_title_field);
        mQuestionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mQuestion.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mQuestionViewModel.getQuestion(mQuestionId.toString()).observe(getActivity(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                mQuestion = question;
                updateUI();
            }
        });


        updateUI();
        return view;
    }

    private void updateUI(){
        if(mQuestion != null) {
            mQuestionField.setText(mQuestion.getTitle());
        }

    }

    private class AnswersHolder extends RecyclerView.ViewHolder{

        private Answer mAnswer;

        private EditText mAnswerField;
        private TextView mAnswerLabel;
        private Switch mCorrectAnswerSwitch;

        public AnswersHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_answer, parent, false));
            mAnswerLabel = (TextView) itemView.findViewById(R.id.answer_title_label);

            mAnswerField  = (EditText) itemView.findViewById(R.id.answer_title_field);
            mAnswerField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mAnswer.setTitle(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mCorrectAnswerSwitch = (Switch) itemView.findViewById(R.id.correct_answer_switch);
            mCorrectAnswerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    //mAnswer.setCorrect(b);
                }
            });

        }

        public void bind(Answer answer) {
            mAnswer = answer;
            mAnswerField.setText(mAnswer.getTitle());
            //mCorrectAnswerSwitch.setChecked(mAnswer.isCorrect());
            // mCorrectAnswerSwitch.setText("sd");
        }

    }

    public class AnswersAdapter extends RecyclerView.Adapter<AnswersHolder> {

        private List<Answer> mAnswers;

        public AnswersAdapter(List<Answer> answers) {
            mAnswers = answers;
        }

        @NonNull
        @Override
        public AnswersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new AnswersHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AnswersHolder holder, int position) {
            Answer answer = mAnswers.get(position);
            holder.bind(answer);
        }

        @Override
        public int getItemCount() {
            return mAnswers.size();
        }

        public void setAnswers(List<Answer> answers) {
            mAnswers = answers;
        }

    }

}
