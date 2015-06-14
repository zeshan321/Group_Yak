package me.zeshan.groupyak.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import me.zeshan.groupyak.R;

public class PostHandler {

    public static PostArrayAdapter postArrayAdapter;
    public static ListView postList;

    Context con;
    String group;

    public PostHandler(Context con, String group) {
        this.con = con;
        this.group = group;

    }

    public void initialSetup() {
        postArrayAdapter = new PostArrayAdapter(con, R.layout.post_layout);
        postList = (ListView) ((Activity) con).findViewById(R.id.postList);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");

        query.whereEqualTo("Group", group);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : parseObjects) {
                        if (parseObject != null) {
                            String title = parseObject.getString("Title");
                            String body = parseObject.getString("Post");
                            int votes = parseObject.getInt("Votes");

                            postArrayAdapter.add(new PostText(title, body, parseObject.getObjectId(), votes, 0));

                        }
                    }
                }
                ((Activity) con).findViewById(R.id.loading).setVisibility(View.GONE);
            }
        });

        postList.setAdapter(postArrayAdapter);
    }
}
