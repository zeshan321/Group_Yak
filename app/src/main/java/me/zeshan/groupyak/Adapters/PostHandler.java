package me.zeshan.groupyak.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import me.zeshan.groupyak.R;

public class PostHandler {

    public static PostArrayAdapter postArrayAdapter;
    public static ListView postList;
    
    Context con;
    String group;
    Type type = Type.NEW;
    List<ParseQuery> runningQuery = new ArrayList<>();

    public PostHandler(Context con, String group) {
        this.con = con;
        this.group = group;

    }

    public PostHandler(Context con, String group, Type type) {
        this.con = con;
        this.group = group;
        this.type = type;

    }

    public enum Type {
        NEW, HOT, TOP
    }

    public void initialSetup() {
        // Stop other runing queries
        clearQueries();

        postArrayAdapter = new PostArrayAdapter(con, R.layout.post_layout);
        postList = (ListView) ((Activity) con).findViewById(R.id.postList);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
        runningQuery.add(query);

        query.whereEqualTo("Group", group);
        switch (type) {
            case HOT:
                query.addDescendingOrder("createdAt");
                query.addDescendingOrder("Votes");
                break;
            case TOP:
                query.addDescendingOrder("Votes");
                break;
            default:
                query.addDescendingOrder("createdAt");
                break;

        }
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
                runningQuery.remove(query);
            }
        });

        postList.setAdapter(postArrayAdapter);
    }

    public void refreshList(final SwipeRefreshLayout swipeRefreshLayout) {
        // Stop other runing queries
        clearQueries();

        postArrayAdapter = new PostArrayAdapter(con, R.layout.post_layout);
        postList = (ListView) ((Activity) con).findViewById(R.id.postList);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");
        runningQuery.add(query);

        query.whereEqualTo("Group", group);
        switch (type) {
            case HOT:
                query.addAscendingOrder("createdAt");
                query.addAscendingOrder("Votes");
                break;
            case TOP:
                query.addAscendingOrder("Votes");
                break;
            default:
                query.addAscendingOrder("createdAt");
                break;

        }
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
                swipeRefreshLayout.setRefreshing(false);
                runningQuery.remove(query);
            }
        });

        postList.setAdapter(postArrayAdapter);
    }

    public void tempAdd(String title, String body, String objectID) {
        postArrayAdapter.add(new PostText(title, body, objectID, 0, 0));
    }

    private void clearQueries() {
        for (ParseQuery parseQuery: runningQuery) {
            parseQuery.cancel();
        }
    }
}
