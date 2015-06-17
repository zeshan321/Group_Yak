package me.zeshan.groupyak.Buttons;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.Adapters.PostText;
import me.zeshan.groupyak.R;

public class VoteListeners {

    View view;

    int position;

    public VoteListeners(View view, int position) {
        this.view = view;
        this.position = position;

        upVote();
        downVote();
    }


    private void upVote() {
        final ImageButton button = (ImageButton) view.findViewById(R.id.Thumbsup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) view.findViewById(R.id.Post_object);
                String objectID = textView.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");

                query.getInBackground(objectID, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if (e == null) {
                            parseObject.put("Votes", parseObject.getInt("Votes") + 1);
                            parseObject.saveInBackground();
                        }
                    }
                });

                // Update list view
                PostText postText = PostHandler.postArrayAdapter.getItem(position);
                postText.votes = postText.votes + 1;

                PostHandler.postArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void downVote() {
        final ImageButton button = (ImageButton) view.findViewById(R.id.Thumbsdown);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) view.findViewById(R.id.Post_object);
                String objectID = textView.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Posts");

                query.getInBackground(objectID, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if (e == null) {
                            parseObject.put("Votes", parseObject.getInt("Votes") - 1);
                            parseObject.saveInBackground();
                        }
                    }
                });

                // Update list view
                PostText postText = PostHandler.postArrayAdapter.getItem(position);
                postText.votes = postText.votes - 1;

                PostHandler.postArrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
