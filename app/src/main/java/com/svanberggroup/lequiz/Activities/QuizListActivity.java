package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.svanberggroup.lequiz.Fragements.QuizListFragment;
import com.svanberggroup.lequiz.R;


public class QuizListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuizListFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}
