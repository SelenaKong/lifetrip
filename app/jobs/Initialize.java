package jobs;

import models.security.Resource;
import models.security.User;
import models.security.UserGroup;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.Globals;

@OnApplicationStart
public class Initialize extends Job<String> {

    @Override
    public void doJob() throws Exception {
        if (User.count() > 0) return;
        Logger.info("Data Initialize");
        // 初始化用户
        User admin = new User("admin@admin.com", "admin");
        admin.save();
        // 用户组
        UserGroup anonymous = new UserGroup();
        anonymous.name = Globals.ANONYMOUS;
        anonymous.save();
        // 初始化匿名资源库
        initAnonymous(anonymous);
    }

    private void initAnonymous(UserGroup anonymous) {
        anonymous.resources.add(new Resource("Application.index", "Application.index"));
        anonymous.resources.add(new Resource("Users.register", "Users.register"));
        anonymous.resources.add(new Resource("Users.add", "Users.add"));
        anonymous.resources.add(new Resource("Users.checkEmail", "Users.checkEmail"));
        anonymous.save();
    }
}
