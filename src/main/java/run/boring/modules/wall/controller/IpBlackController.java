package run.boring.modules.wall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.boring.modules.wall.controller.gen.IpBlackControllerGen;

/**
 * IP黑名单
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-11
 */
@RestController
@RequestMapping("wall/ipblack")
public class IpBlackController extends IpBlackControllerGen {

}
