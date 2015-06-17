package me.zeshan.groupyak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.Buttons.PostListener;
import me.zeshan.groupyak.Buttons.SwipeListener;
import me.zeshan.groupyak.Buttons.SwipeRefresh;
import me.zeshan.groupyak.Buttons.VoteListeners;
import me.zeshan.groupyak.Util.KitKatUI;

public class PostActivity extends ActionBarActivity {

    String display;
    String ID;
    boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Get selected group data
        Intent intent = getIntent();
        display = intent.getStringExtra("Display");
        ID = intent.getStringExtra("ID");
        isOwner = intent.getBooleanExtra("Owner", false);

        // Load posts
        new PostHandler(this, ID).initialSetup();

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // KitKat UI
        new KitKatUI(this);

        // Set title
        getSupportActionBar().setTitle("New");

        // Refresh listener
        //new SwipeRefresh(this, ID);

        // Button listener
        new PostListener(this, ID);

        // Swipe listener
        new SwipeListener(this, ID, getSupportActionBar());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(R.transition.activity_from_1, R.transition.activity_from_2);
                return true;
            case 1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Settings");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.transition.activity_from_1, R.transition.activity_from_2);
    }
}
