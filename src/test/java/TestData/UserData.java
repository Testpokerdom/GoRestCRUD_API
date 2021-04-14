package TestData;

import java.util.HashMap;

public class UserData {

    private static HashMap<String, String> getUserLoginData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("oauth_token", "b3b7a15b12ba603930e906902bba0a88213335bdb8b32ceab67ea08e71734602");
        map.put("Accept", "application/json");
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer ACCESS-TOKEN");
        return map;
    }

    public String getOauthToken(){

        return getUserLoginData().get("oauth_token");
    }

    public String getAcceptHeader(){
        return getUserLoginData().get("Accept");
    }

    public String getContentTypeHeader(){
        return getUserLoginData().get("Content-Type");
    }

    public String getAuthorizationHeader(){
        return getUserLoginData().get("Authorization");
    }


}
