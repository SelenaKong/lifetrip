package controllers;

import models.security.User;
import play.Logger;
import play.data.validation.Valid;


public class Users extends Application {

    public static void register() {
        render();
    }

    public static void add(@Valid User user) {
        if (validation.hasErrors()) {
            badRequest();
        }
        Logger.info(user.toString());
        Application.index();
    }
}
