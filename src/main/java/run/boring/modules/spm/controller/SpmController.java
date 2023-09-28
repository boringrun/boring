package run.boring.modules.spm.controller;

import run.boring.modules.spm.service.SuperPositionModelService;
import run.boring.modules.sys.entity.SysUserEntity;
import run.boring.modules.sys.service.SysUserRoleService;
import run.boring.modules.sys.shiro.ShiroUtils;
import run.boring.core.site.LoadFactory;
import run.boring.core.site.MappingFactory;
import run.boring.core.site.PageFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpmController {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SuperPositionModelService superPositionModelService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    PageFactory pageFactory;

    @Autowired
    MappingFactory mappingFactory;

    @Autowired
    LoadFactory loadFactory;

    List<String> active = new ArrayList<>();

    public void active(HttpServletRequest request) {
        String param = request.getParameter("active");
        if (StringUtils.isBlank(param) || !"admin".equals(param))
            return;
        String sessionId = request.getSession().getId();
        active.add(sessionId);
    }

    public boolean isActive(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        return active.contains(sessionId);
    }

    public void inactive(HttpServletRequest request) {
        String param = request.getParameter("inactive");
        if (StringUtils.isNotBlank(param) && "admin".equals(param)) {
            String sessionId = request.getSession().getId();
            active.remove(sessionId);
        }
    }

    @RequestMapping("admin")
    public String admin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model, String spm) {
        active(httpServletRequest);
        inactive(httpServletRequest);
        if (isActive(httpServletRequest)) {
            SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
            if (sysUserRoleService.isSystemAdministrator(sysUserEntity)) {
                String resourceId = superPositionModelService.getResourceId(httpServletRequest, httpServletResponse, model, spm);
                if (log.isDebugEnabled())
                    log.debug("管理资源ID:{}", resourceId);
                return resourceId;
            }
        }
        if (log.isDebugEnabled())
            log.debug("未经授权访问");
        return pageFactory._404(httpServletRequest, httpServletResponse, model);
    }

    @RequestMapping("/")
    public String spm(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model, String spm) {
        String resourceId = superPositionModelService.getResourceId(httpServletRequest, httpServletResponse, model, spm);
        if (log.isDebugEnabled())
            log.debug("资源ID:{}", resourceId);
        return resourceId;
    }

    @RequestMapping(value = "/{value}", method = RequestMethod.GET)
    public String mapping(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("value") String value) {
        if (!loadFactory.isDone()) {
            if (log.isDebugEnabled())
                log.debug("启动中:{}", request.getRequestURL());
            return pageFactory.loading(request, response, model);
        }
        String resourceId = mappingFactory.mapping(request, response, model, value);
        if (log.isDebugEnabled())
            log.debug("路径:{}, 资源ID:{}", value, resourceId);
        return resourceId;
    }
}
