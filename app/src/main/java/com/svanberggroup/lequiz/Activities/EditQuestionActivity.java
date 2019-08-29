package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.svanberggroup.lequiz.Fragements.EditQuestionFragment;
import com.svanberggroup.lequiz.Fragements.EditQuizFragment;

import java.util.UUID;

public class EditQuestionActivity extends SingleFragmentActivity {
    public static final String EXTRA_QUESTION_ID = "com.svanberggroup.lequiz.question_id";

    @Override
    public EditQuestionFragment createFragment() {
        UUID questionId = (UUID) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);
        return EditQuestionFragment.newInstance(questionId);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
