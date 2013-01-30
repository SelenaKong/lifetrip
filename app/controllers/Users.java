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

    /**
     * 检查Email是否已经存在
     * @param email
     */
    public static void checkEmail(String email) {
        long count = User.count("email = ?", email);
        if (count > 0) {
            renderJSON("{\"valid\":0}");
        } else {
            renderJSON("{\"valid\":1}");
        }
    }
}
