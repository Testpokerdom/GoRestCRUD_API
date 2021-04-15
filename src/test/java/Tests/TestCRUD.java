package Tests;

import TestData.CreateNewUserData;
import TestData.CreateUserPostData;
import TestData.UserData;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Helpers.HelpGetSetValues.convertIntToString;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.StringContains.containsString;

public class TestCRUD {

    private static final Logger logger = LogManager.getLogger(TestCRUD.class);

    private ValidatableResponse response;
    private AuthenticationScheme authScheme;
    private UserData userData = new UserData();
    private CreateNewUserData createNewUserData = new CreateNewUserData();
    private CreateUserPostData createUserPostData = new CreateUserPostData();

    RequestSpecBuilder requestBuilder;
    static RequestSpecification requestSpec;

    private String authToken = userData.getOauthToken();
    private String accept = userData.getAcceptHeader();
    private String contentType = userData.getContentTypeHeader();
    private String authorise = userData.getAuthorizationHeader();

    private String userName = createNewUserData.getUserName();
    private String userEmail = createNewUserData.getUserEmail();
    private String userGender = createNewUserData.getUserGender();
    private String userStatus = createNewUserData.getUserStatus();
    private String namePatched = createNewUserData.getUserNamePatched();
    private String genderPatched = createNewUserData.getUserGenderPatched();
    private int userId;
    private int postId;

    private String textTitle = createUserPostData.getTitle();
    private String bodyTitle = createUserPostData.getBody();


    @BeforeClass
    public void setBaseUrl() {
        setRequestSpec();
    }

    @Test(priority = 0)
    public void createUser(){
        response = given()
                .spec(requestSpec)
                .queryParam("name", userName)
                .queryParam("email", userEmail)
                .queryParam("gender", userGender)
                .queryParam("status", userStatus)
                .expect()
                .spec(getExpectedAuthorizationSpec())
                .when()
                .post()
                .then();
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        createNewUserData.setUserId(userId = data.getInt("id"));
        createNewUserData.setUserName(data.getString("name"));
        Assert.assertEquals(createNewUserData.getUserName(), "Test_2021_04_15_V_02");
        logger.info(userId);
    }

    @Test(priority = 1)
    public void updateUser(){
        response = given()
                .spec(requestSpec)
                .queryParam("name", namePatched)
                .queryParam("gender", genderPatched)
                .when()
                .patch(convertIntToString(userId))
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("Test_2021_04_15_V_02_patched"));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        createNewUserData.setUserNamePatched(data.getString("name"));
        Assert.assertEquals(createNewUserData.getUserNamePatched(), "Test_2021_04_15_V_02_patched");
    }

    @Test(priority = 2)
    public void receiveUserInfo(){
        response = given()
                .spec(requestSpec)
                .when()
                .get(convertIntToString(userId))
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("Female"));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        createNewUserData.setUserNamePatched(data.getString("name"));
        Assert.assertEquals(createNewUserData.getUserNamePatched(), "Test_2021_04_15_V_02_patched");
    }

    @Test(priority = 3)
    public void createUserPost(){
        response = given()
                .spec(requestSpec)
                .queryParam("title", textTitle)
                .queryParam("body", bodyTitle)
                .when()
                .post(convertIntToString(userId) + "/posts")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("201"));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        createUserPostData.setPostId(postId = data.getInt("id"));
        createUserPostData.setTitle(data.getString("title"));
        Assert.assertEquals(createUserPostData.getTitle(), "Title_text_should_be_provided");
    }

    @Test(priority = 4)
    public void deleteUser(){
        response = given()
                .spec(requestSpec)
                .when()
                .delete(convertIntToString(userId))
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("204"));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        Assert.assertEquals(jsonResponse.getInt("code"), 204);
    }


    private void setRequestSpec(){
        authScheme = RestAssured.oauth2(authToken);
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://gorest.co.in/public-api/users/");
        requestBuilder.addHeader("Content-Type", contentType);
        requestBuilder.addHeader("Authorization", authorise);
        requestBuilder.setAuth(authScheme);
        requestSpec = requestBuilder.build();
    }

    private ResponseSpecification getExpectedAuthorizationSpec() {
        return expect()
                .statusCode(200)
                .body("data.id", notNullValue());
    }

}
