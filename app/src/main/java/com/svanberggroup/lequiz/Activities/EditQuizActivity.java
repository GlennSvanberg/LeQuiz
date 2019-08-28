package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.svanberggroup.lequiz.Fragements.EditQuizFragment;
import com.svanberggroup.lequiz.Models.Quiz;

public class EditQuizActivity extends SingleFragmentActivity implements EditQuizFragment.Callbacks {
    @Override
    public EditQuizFragment createFragment() {
        return new EditQuizFragment();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onQuizUpdated(Quiz quiz){

    }
}
