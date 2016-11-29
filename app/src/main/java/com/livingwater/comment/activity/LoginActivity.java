package com.livingwater.comment.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.livingwater.comment.R;
import com.livingwater.comment.ui.UIHelper;


/**
 * Created by tiansj on 15/7/31.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnReg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showRegister(LoginActivity.this);
                finish();
            }
        });
    }

}
