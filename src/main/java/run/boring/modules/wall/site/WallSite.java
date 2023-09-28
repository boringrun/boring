package run.boring.modules.wall.site;

import run.boring.core.annotation.PageAware;
import run.boring.core.site.SiteFactory;
import org.springframework.stereotype.Component;

@Component
public class WallSite implements SiteFactory.Site {
    public final static String siteId = "wall";
    public final static String pack = "wall";

    @PageAware(login = true)
    public String ipvisit = "modules/wall/ipvisit";

    @PageAware(login = true)
    public String ipwhite = "modules/wall/ipwhite";

    @PageAware(login = true)
    public String urlblack = "modules/wall/urlblack";

    @PageAware(login = true)
    public String ipblack = "modules/wall/ipblack";

    @PageAware(login = true)
    public String host = "modules/wall/host";

    public String getIpVisitKey() {
        return getKey("ipvisit");
    }

    public String getIpWhiteKey() {
        return getKey("ipwhite");
    }

    public String getUrlBlackKey() {
        return getKey("urlblack");
    }

    public String getIpBlackKey() {
        return getKey("ipblack");
    }

    public String getHostKey() {
        return getKey("host");
    }

    @Override
    public String getId() {
        return siteId;
    }

    @Override
    public String getPack() {
        return pack;
    }
}
