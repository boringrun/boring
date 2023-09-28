package run.boring.modules.usr.service;

import run.boring.modules.usr.entity.UserLoginLogEntity;
import run.boring.modules.usr.entity.UserProfileEntity;
import run.boring.modules.usr.service.gen.UserLoginLogServiceGen;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserLoginLogService extends UserLoginLogServiceGen {

    @Override
    public int menuOrder() {
        return super.menuOrder();
    }

    @Override
    public String ico() {
        return "fa-sun-o";
    }

    public void login(String username) {
        UserLoginLogEntity userLoginLogEntity = new UserLoginLogEntity();
        userLoginLogEntity.setUsername(username);
        userLoginLogEntity.setLoginTime(new Date());
        insert(userLoginLogEntity);
    }

    public void login(UserProfileEntity userProfileEntity) {
        login(userProfileEntity.getUsername());
    }

    public void logout(String username) {
        UserLoginLogEntity userLoginLogEntity = new UserLoginLogEntity();
        userLoginLogEntity.setUsername(username);
        userLoginLogEntity.setLogoutTime(new Date());
        insert(userLoginLogEntity);
    }
}
