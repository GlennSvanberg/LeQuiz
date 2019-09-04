package com.svanberggroup.lequiz.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.svanberggroup.lequiz.Fragements.PlayQuizFragment;
import com.svanberggroup.lequiz.R;

public class PlayQuizActivity extends SingleFragmentActivity{


    @Override
    protected Fragment createFragment() {
        return new PlayQuizFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}

