package com.vinsofts.login2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends FullScreenActivity {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.fl_regis)
    FrameLayout flRegis;
    @BindView(R.id.tv_basicinformation)
    TextView tvBasicinformation;
    @BindView(R.id.et_Name)
    EditText etName;
    @BindView(R.id.et_Email)
    EditText etEmail;
    @BindView(R.id.tv_privateinformation)
    TextView tvPrivateinformation;
    @BindView(R.id.et_RegisterPass)
    EditText etRegisterPass;
    @BindView(R.id.et_Phonenumber)
    EditText etPhonenumber;
    @BindView(R.id.bt_Regis)
    Button btRegis;
    @BindView(R.id.tv_haveAcc)
    TextView tvHaveAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_Regis, R.id.tv_haveAcc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_Regis:
                register(view);
                break;
            case R.id.tv_haveAcc:
                haveUser(view);
                break;
        }
    }

    private void haveUser(View view) {
        finish();
    }

    private void register(View view) {
        String email;
        String password;
        email = etEmail.getText().toString().trim();
        password = etRegisterPass.getText().toString().trim();
        if (email.equals("") || password.equals("")) {
            Toast.makeText(RegisterActivity.this, "You must enter the email and pass", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(FinalVariable.INTENT_EMAIL, email);
            intent.putExtra(FinalVariable.INTENT_PASSWORD, password);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
