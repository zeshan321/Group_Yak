package me.zeshan.groupyak.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import me.zeshan.groupyak.Buttons.VoteListeners;
import me.zeshan.groupyak.R;

public class PostArrayAdapter extends ArrayAdapter<PostText> {

    Context con;

    TextView postTitle;
    TextView postBody;
    TextView objectID;
    TextView votesAmount;

    ImageButton thumbsUp;
    ImageButton thumbsDown;


    List<PostText> groupList = new ArrayList<PostText>();

    public PostArrayAdapter(Context con, int resource) {
        super(con, resource);

        this.con = con;
    }

    @Override
    public void add(PostText object) {
        groupList.add(object);
        super.add(object);
    }

    @Override
    public void clear() {
        this.groupList.clear();

        super.clear();
    }

    public void add(int i, PostText object) {
        groupList.add(i, object);
    }

    public void set(int i, PostText object) {
        groupList.set(i, object);
    }

    public int getCount() {
        return this.groupList.size();
    }

    public PostText getItem(int index) {
        return this.groupList.get(index);
    }

    public void removeGroup(int index) {
        groupList.remove(index);
    }

    public View getView(int position, View row, ViewGroup parent) {
        PostText postText = getItem(position);
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.post_layout, parent, false);

        postTitle = (TextView) row.findViewById(R.id.Post_title);
        postBody = (TextView) row.findViewById(R.id.Post_Body);
        objectID = (TextView) row.findViewById(R.id.Post_object);
        votesAmount = (TextView) row.findViewById(R.id.Vote_amount);

        thumbsUp = (ImageButton) row.findViewById(R.id.Thumbsup);
        thumbsDown = (ImageButton) row.findViewById(R.id.Thumbsdown);

        postTitle.setText(postText.postTitle);
        postBody.setText(postText.postBody);
        objectID.setText(postText.objectID);
        votesAmount.setText(String.valueOf(postText.votes));

        // Vote buttons
        new VoteListeners(row, position);

        return row;
    }
}
