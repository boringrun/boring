package run.boring.modules.client.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientSite implements SiteFactory.Site {
    public final static String siteId = "client";
    public final static String pack = "client";

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }

    @PageAware(login = true, resource = "modules/client/webauthentication")
    String webauthentication;

    @PageAware(login = true)
    public String weboauthcombine = "modules/client/weboauthcombine";

    public String getWebOauthCombineKey() {
        return getKey("weboauthcombine");
    }

    public String getWebauthenticationKey() {
        return getKey("webauthentication");
    }
}
