package TestData;

import java.util.HashMap;

public class CreateNewUserData {

    private String name;
    private String namePatched;
    private String email;
    private String gender;
    private String genderPatched;
    private String status;
    private int userId;

    private static HashMap<String, String> getUserCreteData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "Test_2021_04_15_V_02");
        map.put("email", "Test_2021_04_15_V_02@mailinator.com");
        map.put("gender", "Male");
        map.put("status", "Active");
        map.put("namePatched", "Test_2021_04_15_V_02_patched");
        map.put("genderPatched", "Female");
        return map;
    }

    public String getUserName(){

        return getUserCreteData().get("name");
    }
    public void setUserName(String name){

        this.name = name;
    }

    public String getUserNamePatched(){

        return getUserCreteData().get("namePatched");
    }
    public void setUserNamePatched(String namePatched){

        this.namePatched = namePatched;
    }

    public String getUserEmail(){

        return getUserCreteData().get("email");
    }
    public void setUserEmail(String email){
        this.email = email;
    }

    public String getUserGender(){

        return getUserCreteData().get("gender");
    }
    public void setUserGender(String gender){

        this.gender = gender;
    }

    public String getUserGenderPatched(){

        return getUserCreteData().get("genderPatched");
    }
    public void setUserGenderPatched(String genderPatched){

        this.genderPatched = genderPatched;
    }

    public String getUserStatus(){

        return getUserCreteData().get("status");
    }
    public void setUserStatus(String status){

        this.status = status;
    }

    public int getUserId(){

        return userId;
    }
    public void setUserId(int userId){

        this.userId = userId;
    }
}
