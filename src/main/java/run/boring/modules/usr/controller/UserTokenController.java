package run.boring.modules.usr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.boring.modules.usr.controller.gen.UserTokenControllerGen;

/**
 * 用户Token
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-11
 */
@RestController
@RequestMapping("usr/usertoken")
public class UserTokenController extends UserTokenControllerGen {

}
