package run.boring.modules.client.controller.gen;

import java.util.Arrays;
import java.util.Map;
import run.boring.core.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import run.boring.modules.client.entity.WebOauthCombineEntity;
import run.boring.modules.client.service.WebOauthCombineService;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.R;

/**
 * 授权登录合并
 *
 * @author User
 * @email henryxm@163.com
 * @date 2023-04
 */
public class WebOauthCombineControllerGen {

    @Autowired
    protected WebOauthCombineService webOauthCombineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("client:weboauthcombine:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = webOauthCombineService.queryPage(params);
        return R.ok().put("page" , page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("client:weboauthcombine:info")
    public R info(@PathVariable("id") Long id) {
        WebOauthCombineEntity webOauthCombine = webOauthCombineService.selectById(id);
        return R.ok().put("webOauthCombine" , webOauthCombine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("client:weboauthcombine:save")
    public R save(@RequestBody WebOauthCombineEntity webOauthCombine) {
        webOauthCombineService.insert(webOauthCombine);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("client:weboauthcombine:update")
    public R update(@RequestBody WebOauthCombineEntity webOauthCombine) {
        ValidatorUtils.validateEntity(webOauthCombine);
        webOauthCombineService.updateAllColumnById(webOauthCombine);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("client:weboauthcombine:delete")
    public R delete(@RequestBody Long[] ids) {
        webOauthCombineService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
