package run.boring.modules.usr.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class UsrSite implements SiteFactory.Site {
    public final static String siteId = "usr";
    public final static String pack = siteId;

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/usr/userprofile")
    String userprofile;

    @PageAware(login = true, resource = "modules/usr/usertoken")
    String usertoken;

    @PageAware(login = true, resource = "modules/usr/userloginlog")
    String userloginlog;
}
