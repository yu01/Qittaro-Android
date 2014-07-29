package com.ryochin.qittaro.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.ryochin.qittaro.R;
import com.ryochin.qittaro.fragments.LoginFragment;

/**
 * Created by kosugeryou on 2014/07/23.
 */
public class LoginActivity extends ActionBarActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private final LoginActivity self = this;

    private static final String LOGIN_RESPONSE_TOKEN_KEY = "token";
    public static final int LOGIN_RESULT_OK = 200;
    public static final int LOGIN_RESULT_NG = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            LoginFragment loginFragment = LoginFragment.getInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, loginFragment, null)
                    .commit();
        }
    }
/*
    @Override
    public void onSuccessLogin(String jsonResponse) {
        Log.e(TAG, jsonResponse);
        boolean result = false;
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String token = jsonObject.getString(LOGIN_RESPONSE_TOKEN_KEY);
            result = AppSharedPreference.setToken(this, token);
        } catch (JSONException e) {
            Log.e(TAG, "JSONException", e);
        }
        if (result) {
            this.setResult(LOGIN_RESULT_OK);
        } else {
            this.setResult(LOGIN_RESULT_NG);
        }
        this.finish();
    }

    @Override
    public void onErrorLogin() {

    }
    */
}
