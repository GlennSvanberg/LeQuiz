package com.svanberggroup.lequiz;

import android.os.Bundle;

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
