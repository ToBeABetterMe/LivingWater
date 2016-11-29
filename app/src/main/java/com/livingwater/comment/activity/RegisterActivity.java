package com.livingwater.comment.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.livingwater.comment.R;
import com.livingwater.comment.User.UserData;
import com.livingwater.comment.User.UserDataManager;

public class RegisterActivity extends Activity {

    /** 年级选择对话框 **/
    private static final int DIALOG_0 = 1;
    /** 学院选择对话框 **/
    private static final int DIALOG_1 = 2;
    /** 学校选择对话框 **/
    private static final int DIALOG_2 = 3;

    private EditText mName;
    private EditText mAccount;
    private EditText mPwd;
    private EditText mPwdCheck;
    private Button mYearButton;
    private Button mDeptButton;
    private Button mRegisterButton;
    private Button mCancleButton;
    private Button mSchoolButton;

    private UserDataManager mUserDataManager;
    int mYearChoiceID = -1;
    int mDeptChoiceID = -1;
    int mSchoolChoiceID = -1;

    private static String[] mYearItems;
    private static String[] mDeptItems;
    private static String[] mSchoolItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mYearItems = getResources().getStringArray(R.array.schoolYear);
        mDeptItems = getResources().getStringArray(R.array.department);
        mSchoolItems = getResources().getStringArray(R.array.school);
        mYearButton.setOnClickListener(mListener);
        mDeptButton.setOnClickListener(mListener);
        mSchoolButton.setOnClickListener(mListener);
        mRegisterButton.setOnClickListener(mListener);
        mCancleButton.setOnClickListener(mListener);

        mCancleButton.setOnClickListener(mListener);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }

    }
    private void initView() {
        mName = (EditText)findViewById(R.id.rgName);
        mAccount = (EditText) findViewById(R.id.rgAccount);
        mPwd = (EditText) findViewById(R.id.rgPassword);
        mPwdCheck =(EditText)findViewById(R.id.rgPasswordCheck);
        mYearButton = (Button) findViewById(R.id.btnYearSelect);
        mDeptButton = (Button) findViewById(R.id.btnDeptSelect);
        mSchoolButton = (Button) findViewById(R.id.btnSchoolSelect);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mCancleButton = (Button) findViewById(R.id.btnClose);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnYearSelect:
                    CreatDialog(DIALOG_0);
                    break;
                case R.id.btnDeptSelect:
                    CreatDialog(DIALOG_1);
                    break;
                case R.id.btnSchoolSelect:
                    CreatDialog(DIALOG_2);
                    break;
                case R.id.btnRegister:
                    register();
                    break;
                case R.id.btnClose:
                    cancle();
                    break;
            }
        }
    };
    public void CreatDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                RegisterActivity.this);
        switch (id) {
            case DIALOG_0:
                mYearChoiceID = -1;
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("选择入学年份");
                builder.setSingleChoiceItems(mYearItems, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                mYearChoiceID = whichButton;
                            //    showDialog("你选择的id为" + whichButton + " , "
                            //            + mItems[whichButton]);
                            }
                        });
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                if (mYearChoiceID >= 0) {
                            //        showDialog("你选择的是" + mYearChoiceID);
                                    mYearButton.setText(mYearItems[mYearChoiceID]);
                                }
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        });
                break;
            case DIALOG_1:
                mDeptChoiceID = -1;
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("选择学院");
                builder.setSingleChoiceItems(mDeptItems, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                mDeptChoiceID = whichButton;
                            //    showDialog("你选择的id为" + whichButton + " , "
                            //            + mItems[whichButton]);
                            }
                        });
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                if (mDeptChoiceID >= 0) {
                            //        showDialog("你选择的是" + mDeptChoiceID);
                                    mDeptButton.setText(mDeptItems[mDeptChoiceID]);
                                }
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        });
                break;
            case DIALOG_2:
                mSchoolChoiceID = -1;
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("选择学校");
                builder.setSingleChoiceItems(mSchoolItems, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                mSchoolChoiceID = whichButton;
                                //    showDialog("你选择的id为" + whichButton + " , "
                                //            + mItems[whichButton]);
                            }
                        });
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                if (mSchoolChoiceID >= 0) {
                                    //        showDialog("你选择的是" + mDeptChoiceID);
                                    mSchoolButton.setText(mSchoolItems[mSchoolChoiceID]);
                                }
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                            }
                        });
                break;
        }
        builder.create().show();
    }

    public void register() {
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            //check if user name is already exist
            int count=mUserDataManager.findUserByName(userName);

            if(count>0){
                Toast.makeText(this, getString(R.string.name_exist,userName),
                        Toast.LENGTH_SHORT).show();
                return ;
            }

            UserData mUser = new UserData(userName, userPwd);
            mUserDataManager.openDataBase();
            long flag = mUserDataManager.insertUserData(mUser);
            if (flag == -1) {
                Toast.makeText(this, getString(R.string.register_fail),
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, getString(R.string.register_sucess),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void cancle() {
        mAccount.setText("");
        mPwd.setText("");
        finish();
    }

    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}