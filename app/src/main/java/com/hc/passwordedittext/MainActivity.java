package com.hc.passwordedittext;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements CustomerKeyboard.CustomerKeyboardClickListener, PasswordEditText.PasswordFullListener, View.OnClickListener {

    private CustomerKeyboard mCustomerKeyboard;
    private PasswordEditText mPasswordEt;

    private ImageView mImageView;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image);
        mImageView.setOnClickListener(this);


    }

    @Override
    public void click(String number) {
        mPasswordEt.addPassword(number);
    }

    @Override
    public void delete() {
        mPasswordEt.deleteLastPassword();
    }

    @Override
    public void passwordFull(String password) {
        // 显示进度条去后台校验密码
        Toast.makeText(this,"密码输入完毕->"+password,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        //方式一
        // 弹出dialog  从底部并且带动画
//        CommonDialog.Builder builder = new CommonDialog.Builder(this);
//       builder.setView(R.layout.dialog_customer_keyboard).fromBottom().fullWidth().create().show();
//        mPasswordEt =  builder.getView(R.id.password_edit_text);
//        mCustomerKeyboard = builder.getView(R.id.custom_key_board);
//        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
//        mPasswordEt.setEnabled(false);
//        mPasswordEt.setOnPasswordFullListener(this);

        initDialog();
    }


    /**
     * 方式二
     */
    private PwdDialog pwdDialog;
    private void initDialog(){
        if(pwdDialog == null){
            pwdDialog = new PwdDialog();

            pwdDialog.show(getSupportFragmentManager(),"");
        }else {
            pwdDialog.show(getSupportFragmentManager(),"");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pwdDialog.dismiss();
    }
}
