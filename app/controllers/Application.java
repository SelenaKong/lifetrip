package controllers;

import models.security.Resource;
import models.security.User;
import models.security.UserGroup;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import utils.Globals;

public class Application extends Controller {

    /**
     * 权限控制
     */
    @Before
    public static void checkPermission() {
        Logger.info(request.action);
        // 匿名用户可访问的页面
        if (checkAnonymous()) return;
        User user = currentUser();
        if (user != null) {
            // 如果是管理员则直接通过
            if (Globals.ADMIN.equals(user.userGroup.name)) return;
            for (Resource r : user.userGroup.resources) {
                if (request.action.equals(r.resource)) return;
            }
        }
        forbidden("没有此权限");
    }

    private static boolean checkAnonymous() {
        UserGroup anonymous = UserGroup.find("name = ? ", Globals.ANONYMOUS).first();
        for (Resource r : anonymous.resources) {
            if (request.action.equals(r.resource)) {
                return true;
            }
        }
        return false;
    }

    private static User currentUser() {
        String userId = session.get(Globals.USER_ID);
        if (userId != null) {
            User user = User.findById(Long.parseLong(userId));
            return user;
        }
        return null;
    }

    /**
     * 默认的Action
     */
    public static void index() {
        render();
    }

}
