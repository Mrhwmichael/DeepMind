package com.example.deepmind;

import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class CommentActivity extends BaseActivity {
    private EditText editComment;
    private Button submitComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        editComment = findViewById(R.id.submit_comment);
        submitComment = findViewById(R.id.submit_and_get_result);

        submitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void hideKeyboard(IBinder token) {
        super.hideKeyboard(token);
        editComment.clearFocus();
    }
}
