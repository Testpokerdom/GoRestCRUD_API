package TestData;

import java.util.HashMap;

public class CreateUserPostData {

    private String title;
    private String body;
    private int postId;


    private static HashMap<String, String> getUserPostData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("title", "Title_text_should_be_provided");
        map.put("body", "Body_text_should_be_provided");
        return map;
    }

    public String getTitle(){
        return getUserPostData().get("title");
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return getUserPostData().get("body");
    }
    public void setBody(String body){
        this.body = body;
    }

    public int getPostId(){

        return postId;
    }
    public void setPostId(int postId){

        this.postId = postId;
    }

}
