package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.svanberggroup.lequiz.Fragements.EditQuizFragment;

public class EditQuizActivity extends SingleFragmentActivity {
    @Override
    public EditQuizFragment createFragment() {
        return new EditQuizFragment();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
