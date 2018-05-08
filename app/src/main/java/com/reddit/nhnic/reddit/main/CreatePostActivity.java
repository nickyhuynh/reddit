package com.reddit.nhnic.reddit.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reddit.nhnic.reddit.R;
import com.reddit.nhnic.reddit.app.Application;
import com.reddit.nhnic.reddit.app.Constants;
import com.reddit.nhnic.reddit.app.GenericActivity;

/**
 * Created by nhnic on 5/7/2018.
 */

public class CreatePostActivity extends GenericActivity {
    private final String TAG = "CreatePostActivity";

    private EditText editTitle;

    private TextView cancelButton;
    private TextView confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        /*
            These private functions are meant to add uniformity and classify code into usable chunks.
         */
        assignViews();
        assignVariables(savedInstanceState);
        assignHandlers();
    }

    private void assignViews() {
        editTitle = findViewById(R.id.edit_title);

        cancelButton = findViewById(R.id.cancel_button);
        confirmButton = findViewById(R.id.confirm_button);
    }

    private void assignVariables(Bundle savedInstanceState) {
        setFinishOnTouchOutside(false);
    }

    private void assignHandlers() {
        /*
            Results are handled in callback in MainActivity.
            Constants.RESULT_OK leads to a topic being added.
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Constants.RESULT_BAD);
                finish();
            }
        });

        /*
            Checks to see if the title editText is empty. If not, then send data through the callback and add it to the topic list.
         */
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTitle.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent();
                    intent.putExtra("POST_DATA", editTitle.getText().toString());
                    setResult(Constants.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(Application.getInstance(), getString(R.string.empty_title_message), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
