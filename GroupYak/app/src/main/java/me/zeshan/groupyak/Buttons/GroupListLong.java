package me.zeshan.groupyak.Buttons;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.R;

public class GroupListLong {

    Context con;

    public GroupListLong(Context con) {
        this.con = con;

        onHold();
    }

    private void onHold() {
        GroupHandler.groupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageView imageView = (ImageView) view.findViewById(R.id.groupImage);
                ImageView imageView1 = (ImageView) view.findViewById(R.id.check_icon);

                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.GONE);
                    imageView1.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }
}
