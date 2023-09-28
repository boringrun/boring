package run.boring.modules.job.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class JobSite implements SiteFactory.Site {

    public final static String siteId = "job";
    public final static String pack = "job";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/job/schedulejob")
    String schedulejob;

    @PageAware(login = true, resource = "modules/job/schedulejoblog")
    String schedulejoblog;
}
