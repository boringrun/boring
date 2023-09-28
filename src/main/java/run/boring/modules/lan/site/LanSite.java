package run.boring.modules.lan.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class LanSite implements SiteFactory.Site {

    public final static String siteId = "lan";
    public final static String pack = "lan";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/lan/language")
    String language;

    @PageAware(login = true, resource = "modules/lan/supportedlanguage")
    String supportedlanguage;
}
