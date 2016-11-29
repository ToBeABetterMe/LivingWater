package com.livingwater.comment.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.livingwater.comment.activity.CommentActivity;
import com.livingwater.comment.activity.CourseDetailActivity;
import com.livingwater.comment.activity.HouseDetailActivity;
import com.livingwater.comment.activity.LoginActivity;
import com.livingwater.comment.activity.MainActivity;
import com.livingwater.comment.activity.RankDetailActivity;
import com.livingwater.comment.activity.RegisterActivity;
import com.livingwater.comment.activity.SearchActivity;
import com.livingwater.comment.activity.TeacherDetailActivity;

/**
 * 应用程序UI工具包：封装UI相关的一些操作
 */
public class UIHelper {

	public final static String TAG = "UIHelper";

	public final static int RESULT_OK = 0x00;
	public final static int REQUEST_CODE = 0x01;

	public static void ToastMessage(Context cont, String msg) {
        if(cont == null || msg == null) {
            return;
        }
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, int msg) {
        if(cont == null || msg <= 0) {
            return;
        }
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, String msg, int time) {
        if(cont == null || msg == null) {
            return;
        }
		Toast.makeText(cont, msg, time).show();
	}

    public static void showHome(Activity context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void showLogin(Activity context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    public static void showRegister(Activity context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void showHouseDetailActivity(Activity context){
        Intent intent = new Intent(context, HouseDetailActivity.class);
        context.startActivity(intent);
    }
    public static void showTeacherDetailActivity(Activity context){
        Intent intent = new Intent(context, TeacherDetailActivity.class);
        context.startActivity(intent);
    }
    public static void showCourseDetailActivity(Activity context){
        Intent intent = new Intent(context, CourseDetailActivity.class);
        context.startActivity(intent);
    }
    public static void showSearchActivity(Activity context){
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void showRankActivity(Activity context){
        Intent intent = new Intent(context, RankDetailActivity.class);
        context.startActivity(intent);
    }
    public static void showCommentActivity(Activity context){
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }

}
