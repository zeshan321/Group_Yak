package me.zeshan.groupyak.Adapters;

public class GroupText {

    public String groupName;
    public String groupID;
    public boolean selected;
    public boolean isOwner;

    public GroupText(String groupName, String groupID, boolean selected, boolean isOwner) {
        super();

        this.groupName = groupName;
        this.groupID = groupID;
        this.selected = selected;
        this.isOwner = isOwner;
    }
}
