package com.vinsofts.login2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.vinsofts.login2.ListCountry.ListContriesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends FullScreenActivity {

    @BindView(R.id.bt_List)
    Button btList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_List)
    public void onViewClicked() {
        Intent listcontries = new Intent(HomeActivity.this, ListContriesActivity.class);
        startActivity(listcontries);
    }
}
