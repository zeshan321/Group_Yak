package me.zeshan.groupyak.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import me.zeshan.groupyak.R;

public class GroupArrayAdapter extends ArrayAdapter<GroupText> {

    Context con;

    ImageView groupImage;
    ImageView groupChecked;
    TextView groupName;
    TextView groupID;

    List<GroupText> groupList = new ArrayList<GroupText>();

    public GroupArrayAdapter(Context con, int resource) {
        super(con, resource);

        this.con = con;
    }

    @Override
    public void add(GroupText object) {
        groupList.add(object);
        super.add(object);
    }

    @Override
    public void clear() {
        this.groupList.clear();

        super.clear();
    }

    public void add(int i, GroupText object) {
        groupList.add(i, object);
    }

    public void set(int i, GroupText object) {
        groupList.set(i, object);
    }

    public int getCount() {
        return this.groupList.size();
    }

    public GroupText getItem(int index) {
        return this.groupList.get(index);
    }

    public void removeGroup(int index) {
        groupList.remove(index);
    }

    public View getView(int position, View row, ViewGroup parent) {
        GroupText groupText = getItem(position);
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.group_layout, parent, false);

        groupImage = (ImageView) row.findViewById(R.id.groupImage);
        groupChecked = (ImageView) row.findViewById(R.id.check_icon);
        groupName = (TextView) row.findViewById(R.id.groupName);
        groupID = (TextView) row.findViewById(R.id.groupID);

        groupName.setText(groupText.groupName);
        groupID.setText(groupText.groupID);

        groupID.setVisibility(View.GONE);

        ColorGenerator generator = ColorGenerator.DEFAULT;

        String s = String.valueOf(groupText.groupName.charAt(0)).toUpperCase();
        int color2 = generator.getColor(s);

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(97)
                .height(97)
                .endConfig()
                .buildRound(s, color2);

        groupImage.setImageDrawable(drawable);

        TextDrawable drawable1 = TextDrawable.builder()
                .beginConfig()
                .width(97)
                .height(97)
                .endConfig()
                .buildRound("\u2714", Color.parseColor("#3F51B5"));

        groupChecked.setImageDrawable(drawable1);

        return row;
    }
}
