package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.svanberggroup.lequiz.Fragements.EditQuizFragment;
import com.svanberggroup.lequiz.Models.Quiz;

import java.util.UUID;

public class EditQuizActivity extends SingleFragmentActivity {

    public static final String EXTRA_QUIZ_ID = "com.svanberggroup.lequiz.quiz_id";

    @Override
    public EditQuizFragment createFragment() {
        UUID quizId = (UUID) getIntent().getSerializableExtra(EXTRA_QUIZ_ID);
        return EditQuizFragment.newInstance(quizId);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
