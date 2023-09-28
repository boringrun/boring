package run.boring.modules.spm.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class SpmSite implements SiteFactory.Site {
    public final static String siteId = "spm";
    public final static String pack = "spm";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/spm/superpositionmodel")
    String superpositionmodel;

    @PageAware(login = true, resource = "modules/spm/visitlog")
    String visitlog;
}
