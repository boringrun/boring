package run.boring.modules.sys.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import run.boring.modules.job.task.LoopJob;
import run.boring.core.site.InitFactory;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import run.boring.core.utils.PageUtils;
import run.boring.core.utils.Query;
import run.boring.modules.sys.dao.SysLogDao;
import run.boring.modules.sys.entity.SysLogEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SysLogService extends ServiceImpl<SysLogDao, SysLogEntity> implements LoopJob.Job, InitFactory.Init {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void init() {
        LoopJob.onOneWeek(this);
    }

    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        EntityWrapper<SysLogEntity> entityEntityWrapper = new EntityWrapper<>();
        Page<SysLogEntity> page = this.selectPage(
                new Query<SysLogEntity>(params).getPage(),
                new EntityWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
        );
        page.setTotal(baseMapper.selectCount(entityEntityWrapper));
        return new PageUtils(page);
    }

    @Override
    public void runJob() {
        baseMapper.clear();
    }

    public String changeLevel(String rootLevel, String singleLevel, String singlePath) {
        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            logger.debug("set log rootLevel:{},singleLevel:{},singlePath:{}", rootLevel, singleLevel,
                    singlePath);
            if (!StringUtils.isEmpty(rootLevel)) {
                // 设置全局日志级别
                ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
                logger.setLevel(Level.toLevel(rootLevel));
            }
            if (!StringUtils.isEmpty(singleLevel)) {
                // 设置某个类日志级别-可以实现定向日志级别调整
                ch.qos.logback.classic.Logger vLogger = loggerContext.getLogger(singlePath);
                if (vLogger != null) {
                    vLogger.setLevel(Level.toLevel(singleLevel));
                }
            }
        } catch (Exception e) {

        }
        return "success";
    }
}
