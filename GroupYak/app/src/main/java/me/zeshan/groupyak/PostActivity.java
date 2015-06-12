package me.zeshan.groupyak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class PostActivity extends ActionBarActivity {

    String display;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Get selected group data
        Intent intent = getIntent();
        display = intent.getStringExtra("Display");
        ID = intent.getStringExtra("ID");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(display + " : " + ID);


    }
}
