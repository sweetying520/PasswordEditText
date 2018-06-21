package com.hc.passwordedittext;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/21.
 */

public class PwdDialog extends DialogFragment implements CustomerKeyboard.CustomerKeyboardClickListener, PasswordEditText.PasswordFullListener {

    private PasswordEditText mPasswordEt;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPasswordEt = rootView.findViewById(R.id.password_edit_text);
        CustomerKeyboard mCustomerKeyboard = rootView.findViewById(R.id.custom_key_board);
        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
        mPasswordEt.setEnabled(false);
        mPasswordEt.setOnPasswordFullListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        assert window != null;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animstyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_customer_keyboard, container, false);
        return rootView;
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
        Toast.makeText(getActivity(),"密码输入完毕->"+password,Toast.LENGTH_LONG).show();
    }
}
