package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.svanberggroup.lequiz.Fragements.PlayQuizFragment;
import com.svanberggroup.lequiz.R;

import java.util.UUID;


public class PlayQuizActivity extends SingleFragmentActivity{

    public static final String EXTRA_QUIZ_ID = "com.svanberggroup.lequiz.quiz_id";

    @Override
    protected Fragment createFragment(){
        UUID quizId = (UUID) getIntent().getSerializableExtra(EXTRA_QUIZ_ID);
        return PlayQuizFragment.newInstance(quizId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

    }

}

