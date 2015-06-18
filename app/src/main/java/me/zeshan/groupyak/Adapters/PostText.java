package me.zeshan.groupyak.Adapters;

public class PostText {

    public String postTitle;
    public String postBody;
    public String objectID;
    public String postOwner;

    public int votes;
    public int voteType;

    public PostText(String postTitle, String postBody, String objectID, String postOwner, int votes, int voteType) {
        super();

        this.postTitle = postTitle;
        this.postBody = postBody;
        this.objectID = objectID;
        this.postOwner = postOwner;
        this.votes = votes;
        this.voteType = voteType;
    }
}

