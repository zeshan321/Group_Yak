package me.zeshan.groupyak.Dialogs;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import me.zeshan.groupyak.R;

public class CreateListener {

    Context con;
    View view;

    public CreateListener(Context con, View view) {
        this.con = con;
        this.view = view;

        setListener();
    }

    private void setListener() {
        final EditText editText = (EditText) view.findViewById(R.id.CreateID);
        final EditText editText1 = (EditText) view.findViewById(R.id.CreateName);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                editText1.setText(editText.getText().toString());
            }

            public void afterTextChanged(Editable s) {


            }
            });
        }
    }
