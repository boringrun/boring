package run.boring.modules.sys.redis;

import run.boring.modules.sys.service.SysConfigService;
import run.boring.core.utils.RedisKeys;
import run.boring.modules.sys.entity.SysConfigEntity;
import run.boring.core.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SysConfigRedis {
    @Autowired
    @Lazy
    private RedisUtils redisUtils;

    @Autowired
    @Lazy
    SysConfigService sysConfigService;

    public void saveOrUpdate(SysConfigEntity config) {
        if (config == null) {
            return;
        }
        String key = RedisKeys.getSysConfigKey(sysConfigService.getNameSpace(), config.getParamKey());
        redisUtils.set(key, config, 120);
    }

    public void delete(String configKey) {
        redisUtils.delete(RedisKeys.getSysConfigKey(sysConfigService.getNameSpace(), configKey));
    }

    public SysConfigEntity get(String configKey) {
        Object o = redisUtils.get(RedisKeys.getSysConfigKey(sysConfigService.getNameSpace(), configKey));
        if (o instanceof SysConfigEntity) {
            return (SysConfigEntity) o;
        }
        return null;
    }
}
