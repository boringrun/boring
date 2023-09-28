package run.boring.modules.job.controller;

import run.boring.modules.job.utils.ScheduleJob;
import run.boring.core.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import run.boring.modules.job.controller.gen.ScheduleJobLogControllerGen;


/**
 * 定时任务日志
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-10
 */
@RestController
@RequestMapping("job/schedulejoblog")
public class ScheduleJobLogController extends ScheduleJobLogControllerGen {

    @ResponseBody
    @RequestMapping(value = {"/toggle"})
    public R joblog() {
        ScheduleJob.toggle();
        return R.ok();
    }
}
