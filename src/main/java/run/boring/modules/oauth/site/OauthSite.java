package run.boring.modules.oauth.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class OauthSite implements SiteFactory.Site {
    public final static String siteId = "oauth";
    public final static String pack = "oauth";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/oauth/clientdetails")
    String clientdetails;

    @PageAware(login = true, resource = "modules/oauth/tokenstore")
    String tokenstore;

    @PageAware(login = false, resource = "modules/oauth/oauth2authorizefail")
    public static String oauth2authorizefail = pack + "_oauth2authorizefail";
}
