package run.boring.modules.wall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.boring.modules.wall.controller.gen.IpWhiteControllerGen;

/**
 * IP白名单
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-11
 */
@RestController
@RequestMapping("wall/ipwhite")
public class IpWhiteController extends IpWhiteControllerGen {

}
