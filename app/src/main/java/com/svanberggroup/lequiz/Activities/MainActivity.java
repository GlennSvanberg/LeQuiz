package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import com.svanberggroup.lequiz.Fragements.QuizListFragment;
import com.svanberggroup.lequiz.R;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected QuizListFragment createFragment() {
        return new QuizListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


    }


}
