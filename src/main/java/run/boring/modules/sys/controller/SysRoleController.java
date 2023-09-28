package run.boring.modules.sys.controller;

import run.boring.core.annotation.SysLog;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.R;
import run.boring.core.validator.ValidatorUtils;
import run.boring.modules.sys.entity.SysRoleEntity;
import run.boring.modules.sys.service.SysRoleDeptService;
import run.boring.modules.sys.service.SysRoleMenuService;
import run.boring.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    @Lazy
    private SysRoleService sysRoleService;
    @Autowired
    @Lazy
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    @Lazy
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRoleService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public R select() {
        List<SysRoleEntity> list = sysRoleService.selectList(null);
        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleKey}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleKey") String roleKey) {
        SysRoleEntity role = sysRoleService.getByRoleKey(roleKey);
        //查询角色对应的菜单
        List<String> menuKeys = sysRoleMenuService.getMenuKeys(roleKey);
        role.setMenuKeys(menuKeys);
        //查询角色对应的部门
        List<String> deptKeys = sysRoleDeptService.getDeptKeys(new String[]{roleKey});
        role.setDeptKeys(deptKeys);
        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);
        sysRoleService.save(role);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody String[] roleKeys) {
        sysRoleService.deleteBatch(roleKeys);
        return R.ok();
    }
}
