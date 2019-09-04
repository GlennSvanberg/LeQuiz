package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import com.svanberggroup.lequiz.Fragements.EditQuizListFragment;
import com.svanberggroup.lequiz.R;

public class EditQuizListActivity extends SingleFragmentActivity{
    @Override
    protected EditQuizListFragment createFragment() {
        return new EditQuizListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


    }
}
