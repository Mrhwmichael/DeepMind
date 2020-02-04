package com.example.deepmind;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deepmind.data.Constants;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity {

    private Button DetailButton;
    private Button WebButton;
    private ImageView buptView;
    private ImageView dzdpView;
    private MenuItem admin;
    private EditText editFeedback;
    private Button submitFeedback;
    private TextView adminInfo;
    private Button comment;

    private static boolean adminMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DetailButton = findViewById(R.id.show_detail);
        WebButton = findViewById(R.id.show_webpage);
        buptView = findViewById(R.id.bupt);
        dzdpView = findViewById(R.id.dzdp);
        editFeedback = findViewById(R.id.submit_feedback);
        adminInfo = findViewById(R.id.feedback);
        submitFeedback = findViewById(R.id.submit);
        comment = findViewById(R.id.comment);

        initListener();
        initData();

        Log.d("test","main");
    }

    private void initListener(){
        DetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.ACTION_DETAIL");
                startActivity(intent);
            }
        });

        WebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://mooc.study.163.com/university/deeplearning_ai#/c");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        buptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.ACTION_WEB");
                intent.putExtra("webURL","https://www.bupt.edu.cn");
                startActivity(intent);
            }
        });

        dzdpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.ACTION_WEB");
                intent.putExtra("webURL","https://m.dianping.com");
                startActivity(intent);
            }
        });

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormat = DateFormat.getDateInstance(1);
                String currentDate = dateFormat.format(new Date());
                adminInfo.append("\n"+ currentDate + "  " +editFeedback.getText());
                Toast.makeText(getApplicationContext(), "成功提交反馈" , Toast.LENGTH_LONG).show();
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initData(){
        adminInfo.setVisibility(View.INVISIBLE);
        if (Constants.DEBUG_MODE) {
            adminInfo.setVisibility(View.VISIBLE);
        }

        adminInfo.setText(R.string.admin_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        admin = menu.findItem(R.id.admin);
        admin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent("android.intent.action.Login");
                startActivity(intent);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(adminMode){
            adminInfo.setVisibility(View.VISIBLE);
            admin.setVisible(false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("feedback", adminInfo.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adminInfo.setText(savedInstanceState.getString("feedback"));
    }

    @Override
    public void hideKeyboard(IBinder token) {
        super.hideKeyboard(token);
        editFeedback.clearFocus();
    }

    public static void setAdminMode(){
        adminMode = true;
    }

}
