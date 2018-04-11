package com.turastory.jamquery.presentation.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.turastory.jamquery.R;
import com.turastory.jamquery.presentation.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * 로그인을 하는 액티비티.
 * TODO: 추가해야함
 */
public class LoginActivity extends BaseActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
