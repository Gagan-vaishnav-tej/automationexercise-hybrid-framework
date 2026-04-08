package hooks;

import builder.RequestBuilder;
import constants.ApiEndpoints;
import io.cucumber.java.Before;
import pojo.User;
import util.UserDataBuilder;

public class Hooks {

    @Before("@update or @delete")
    public void createUserBeforeScenario() {
        User createdUser = UserDataBuilder.getRegisterUser();

        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.postCreateAccount(ApiEndpoints.registerUser_url, createdUser);

        UserDataBuilder.setUser(createdUser);
    }
}