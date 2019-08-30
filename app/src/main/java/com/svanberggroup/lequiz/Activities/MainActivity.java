package com.svanberggroup.lequiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svanberggroup.lequiz.Fragements.QuizListFragment;
import com.svanberggroup.lequiz.R;

public class MainActivity extends AppCompatActivity {

    private Button mPlayQuizButton;
    private Button mEditQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayQuizButton = (Button) findViewById(R.id.play_quiz_button);
        mPlayQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditQuizListActivity.class);
                startActivity(intent);
            }
        });

        mEditQuizButton = (Button) findViewById(R.id.edit_quiz_button);
        mEditQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
