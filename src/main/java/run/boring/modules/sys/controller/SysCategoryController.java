package run.boring.modules.sys.controller;

import run.boring.modules.sys.entity.SysCategoryEntity;
import run.boring.modules.sys.service.SysCategoryService;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.R;
import run.boring.core.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 系统配置类型表
 *
 * @author User
 * @email henryxm@163.com
 * @date 2022-12
 */
@RestController
@RequestMapping("sys/category")
public class SysCategoryController {

    @Autowired
    protected SysCategoryService sysCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:category:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysCategoryService.queryPage(params);
        return R.ok().put("page" , page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:category:info")
    public R info(@PathVariable("id") Long id) {
        SysCategoryEntity category = sysCategoryService.selectById(id);
        return R.ok().put("category" , category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:category:save")
    public R save(@RequestBody SysCategoryEntity category) {
        sysCategoryService.insert(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:category:update")
    public R update(@RequestBody SysCategoryEntity category) {
        ValidatorUtils.validateEntity(category);
        sysCategoryService.updateAllColumnById(category);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:category:delete")
    public R delete(@RequestBody Long[] ids) {
        sysCategoryService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
