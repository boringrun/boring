package run.boring.modules.sys.controller;

import run.boring.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserUuid() {
        return getUser().getUuid();
    }

    protected String getDeptKey() {
        return getUser().getDeptKey();
    }
}
