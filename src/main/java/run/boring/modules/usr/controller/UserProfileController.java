package run.boring.modules.usr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.boring.modules.usr.controller.gen.UserProfileControllerGen;

/**
 * 用户信息
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-11
 */
@RestController
@RequestMapping({"usr/userprofile"})
public class UserProfileController extends UserProfileControllerGen {

}
