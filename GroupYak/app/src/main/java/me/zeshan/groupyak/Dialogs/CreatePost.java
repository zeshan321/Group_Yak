package me.zeshan.groupyak.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.R;
import me.zeshan.groupyak.SettingsManager;
import me.zeshan.groupyak.Util.ToastMessage;

public class CreatePost {

    Context con;

    String group;

    public CreatePost(Context con, String group) {
        this.con = con;
        this.group = group;

        createListener();
    }

    private void createListener() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.dialog_createpost, null);

        final EditText postTitle = (EditText) v.findViewById(R.id.create_post_title);
        final EditText postBody = (EditText) v.findViewById(R.id.create_post_body);

        builder.setView(v).setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        final String title = postTitle.getText().toString();
                        final String body = postBody.getText().toString();

                        if (title.length() < 1 || title.replace(" ", "").length() < 1) {
                            new ToastMessage(con, con.getString(R.string.group_length_title), 0).sendToast();
                            return;
                        }

                        if (body.length() < 1  || body.replace(" ", "").length() < 1) {
                            new ToastMessage(con, con.getString(R.string.group_length_body), 0).sendToast();
                            return;
                        }

                        new ToastMessage(con, con.getString(R.string.loading), 0).sendToast();

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                final ParseObject addPost = new ParseObject("Posts");
                                addPost.put("Owner", new SettingsManager(con).getString("ID"));
                                addPost.put("Group", group);
                                addPost.put("Title", title);
                                addPost.put("Post", body);
                                addPost.put("Votes", 0);
                                try {
                                    addPost.save();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                ((Activity) con).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        new PostHandler(con, group).tempAdd(title, body, addPost.getObjectId());
                                    }
                                });

                                Looper.prepare();
                                new ToastMessage(con, con.getString(R.string.post_created), 0).sendToast();
                            }
                        };
                        thread.start();
                    }
                }

        ).

                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }

                );

        builder.create();
        builder.show();
    }
}
