package run.boring.modules.job.controller.gen;

import java.util.Arrays;
import java.util.Map;

import run.boring.core.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import run.boring.modules.job.entity.ScheduleJobLogEntity;
import run.boring.modules.job.service.ScheduleJobLogService;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.R;


/**
 * 定时任务日志
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-10
 */
public class ScheduleJobLogControllerGen {

    @Autowired
    protected ScheduleJobLogService scheduleJobLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("job:schedulejoblog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scheduleJobLogService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    @RequiresPermissions("job:schedulejoblog:info")
    public R info(@PathVariable("logId") Long logId){
        ScheduleJobLogEntity scheduleJobLog = scheduleJobLogService.selectById(logId);
        return R.ok().put("scheduleJobLog", scheduleJobLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("job:schedulejoblog:save")
    public R save(@RequestBody ScheduleJobLogEntity scheduleJobLog){
        scheduleJobLogService.insert(scheduleJobLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("job:schedulejoblog:update")
    public R update(@RequestBody ScheduleJobLogEntity scheduleJobLog){
        ValidatorUtils.validateEntity(scheduleJobLog);
        scheduleJobLogService.updateAllColumnById(scheduleJobLog);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("job:schedulejoblog:delete")
    public R delete(@RequestBody Long[] logIds){
        scheduleJobLogService.deleteBatchIds(Arrays.asList(logIds));
        return R.ok();
    }

}
