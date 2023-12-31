package run.boring.modules.job.service.gen;

import run.boring.core.site.InitFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.Query;
import run.boring.modules.job.service.JobMenu;
import run.boring.modules.job.dao.ScheduleJobDao;
import run.boring.modules.job.entity.ScheduleJobEntity;
import run.boring.modules.sys.entity.SysMenuEntity;
import run.boring.modules.sys.service.SysMenuService;
import run.boring.modules.lan.service.Language;
import run.boring.modules.lan.service.LanguageService;

/**
 * 定时任务控制器
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2021-01
 */
public class ScheduleJobServiceGen extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements InitFactory.Init {

    @Autowired
    protected JobMenu jobMenu;

    @Autowired
    protected SysMenuService sysMenuService;

    @Autowired
    protected Language language;

    @Autowired
    protected LanguageService languageService;

    public PageUtils queryPage(Map<String, Object> params) {
        Page<ScheduleJobEntity> _page = new Query<ScheduleJobEntity>(params).getPage();
        EntityWrapper<ScheduleJobEntity> entityEntityWrapper = new EntityWrapper<>();
        Map<String,Object> condition = new HashMap<>();
        if(params.containsKey("jobId") && null != params.get("jobId") && StringUtils.isNotEmpty(params.get("jobId").toString())) {
            condition.put("job_id", params.get("jobId"));
        }
        if(params.containsKey("beanName") && null != params.get("beanName") && StringUtils.isNotEmpty(params.get("beanName").toString())) {
            condition.put("bean_name", params.get("beanName"));
        }
        if(params.containsKey("methodName") && null != params.get("methodName") && StringUtils.isNotEmpty(params.get("methodName").toString())) {
            condition.put("method_name", params.get("methodName"));
        }
        if(params.containsKey("params") && null != params.get("params") && StringUtils.isNotEmpty(params.get("params").toString())) {
            condition.put("params", params.get("params"));
        }
        if(params.containsKey("cronExpression") && null != params.get("cronExpression") && StringUtils.isNotEmpty(params.get("cronExpression").toString())) {
            condition.put("cron_expression", params.get("cronExpression"));
        }
        if(params.containsKey("status") && null != params.get("status") && StringUtils.isNotEmpty(params.get("status").toString())) {
            condition.put("status", params.get("status"));
        }
        if(params.containsKey("mode") && null != params.get("mode") && StringUtils.isNotEmpty(params.get("mode").toString())) {
            condition.put("mode", params.get("mode"));
        }
        if(params.containsKey("remark") && null != params.get("remark") && StringUtils.isNotEmpty(params.get("remark").toString())) {
            condition.put("remark", params.get("remark"));
        }
        if(params.containsKey("createTime") && null != params.get("createTime") && StringUtils.isNotEmpty(params.get("createTime").toString())) {
            condition.put("create_time", params.get("createTime"));
        }
        _page.setCondition(condition);
        Page<ScheduleJobEntity> page = this.selectPage(_page, entityEntityWrapper);
        page.setTotal(baseMapper.selectCount(entityEntityWrapper));
        return new PageUtils(page);
    }

    /**
    * need implement it in the subclass.
    * @return
    */
    public int menuOrder(){
        return 0;
    }

    /**
    * need implement it in the subclass.
    * @return
    */
    public String parentMenu(){
        jobMenu.init();
        SysMenuEntity sysMenuEntity = sysMenuService.getByMenuKey(jobMenu.getMenu());
        if(null != sysMenuEntity)
            return sysMenuEntity.getMenuKey();
        return "";
    }

    public String menu() {
        String menu = SysMenuService.getMenuKey("Job", "ScheduleJob");
        return menu;
    }

    public String button(String button) {
        String menu = menu() + button;
        return menu;
    }

    public String ico(){
        return "fa-file-code-o";
    }

    protected String order(){
        return String.valueOf(menuOrder());
    }

    public void init() {
        sysMenuService.put(getMenuItemsInternal(), getMenuItems(), getMenuList());
        language.put(getLanguageItemsInternal(), getLanguageItems(), getLanguageList());
    }

    public List<String[]> getLanguageList() {
        return null;
    }

    public String[][] getLanguageItems() {
        return null;
    }

    private String[][] getLanguageItemsInternal() {
        String[][] items = new String[][]{
                {"job_schedulejob_table_comment", "定时任务"},
                {"job_schedulejob_column_job_id", "任务id"},
                {"job_schedulejob_column_bean_name", "BeanName"},
                {"job_schedulejob_column_method_name", "方法名"},
                {"job_schedulejob_column_params", "参数"},
                {"job_schedulejob_column_cron_expression", "Cron表达式"},
                {"job_schedulejob_column_status", "任务状态,0:正常,1:暂停"},
                {"job_schedulejob_column_mode", "任务执行模式"},
                {"job_schedulejob_column_remark", "备注"},
                {"job_schedulejob_column_create_time", "创建时间"},
        };
        return items;
    }

    public List<String[]> getMenuList() {
        return null;
    }

    public String[][] getMenuItems() {
        return null;
    }

    private String[][] getMenuItemsInternal() {
        String[][] menus = new String[][]{
                //{0:菜单名字,1:URL,2:权限,3:菜单类型,4:ICON,5:排序,6:MenuKey,7:ParentKey,8:Language}
                {"定时任务", "modules/job/schedulejob", "job:schedulejob:list,job:schedulejob:info,job:schedulejob:save,job:schedulejob:update,job:schedulejob:delete", "1", "fa " + ico(), order(), menu(), parentMenu(), "job_schedulejob_table_comment"},
                {"查看", null, "job:schedulejob:list,job:schedulejob:info", "2", null, order(), button("List"), menu(), "sys_string_lookup"},
                {"新增", null, "job:schedulejob:save", "2", null, order(), button("Save"), menu(), "sys_string_add"},
                {"修改", null, "job:schedulejob:update", "2", null, order(), button("Update"), menu(), "sys_string_change"},
                {"删除", null, "job:schedulejob:delete", "2", null, order(), button("Delete"), menu(), "sys_string_delete"},
        };
        return menus;
    }
}
