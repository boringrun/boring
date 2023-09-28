package run.boring.modules.sys.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class SysSite implements SiteFactory.Site {

    public final static String siteId = "sys";
    public final static String pack = "sys";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/sys/config")
    String config;

    @PageAware(login = true, resource = "modules/sys/dept")
    String dept;

    @PageAware(login = true, resource = "modules/sys/dict")
    String dict;

    @PageAware(login = true, resource = "modules/sys/log")
    String log;

    @PageAware(login = true, resource = "modules/sys/menu")
    String menu;

    @PageAware(login = true, resource = "modules/sys/role")
    String role;

    @PageAware(login = true, resource = "modules/sys/user")
    String user;

    @PageAware(login = true, resource = "modules/sys/category")
    String category;
}
