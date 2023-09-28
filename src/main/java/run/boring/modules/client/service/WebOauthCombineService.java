package run.boring.modules.client.service;

import run.boring.base.ModuleService;
import run.boring.core.config.ClientType;
import run.boring.handler.config.DomainHandler;
import run.boring.modules.client.entity.WebAuthenticationEntity;
import run.boring.modules.oauth.entity.ClientDetailsEntity;
import run.boring.modules.oauth.service.ClientDetailsService;
import run.boring.core.utils.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.boring.modules.client.dao.WebOauthCombineDao;
import run.boring.modules.client.entity.WebOauthCombineEntity;

import java.util.Date;
import java.util.Objects;

@Service
public class WebOauthCombineService extends ModuleService<WebOauthCombineDao, WebOauthCombineEntity> implements DomainHandler {

    @Autowired
    protected ClientDetailsService clientDetailsService;

    @Autowired
    protected WebAuthenticationService webAuthenticationService;

    public void createClient(String domain) {
        String secret = Uuid.uuid();
        String url = sysConfigService.getScheme() + "://" + domain;
        clientDetailsService.create(url, domain, secret, ClientType.ManualCreate, domain, domain);
        webAuthenticationService.create(url, domain, secret, ClientType.ManualCreate, domain, "basic", "normal");
    }

    public void updateClientDetails(String uuid, String domain) {
        clientDetailsService.update(uuid, domain);
    }

    public void updateWebAuthentication(String uuid, String domain) {
        webAuthenticationService.update(uuid, domain);
    }

    public ClientDetailsEntity getClientDetails(String domain) {
        return clientDetailsService.findByClientId(domain);
    }

    public WebAuthenticationEntity getWebAuthentication(String domain) {
        return webAuthenticationService.getByClientId(domain);
    }

    @Override
    public boolean insert(WebOauthCombineEntity entity) {
        WebOauthCombineEntity combineEntity = baseMapper.getByClientId(entity.getClientId());
        if (null != combineEntity)
            return false;
        String clientId = entity.getClientId();
        entity.setUuid(Uuid.uuid());
        createClient(clientId);
        ClientDetailsEntity details = getClientDetails(clientId);
        WebAuthenticationEntity webAuthentication = getWebAuthentication(clientId);
        entity.setClientDetailsUuid(details.getUuid());
        entity.setWebAuthenticationUuid(webAuthentication.getUuid());
        entity.setCreateTime(new Date());
        return super.insert(entity);
    }

    @Override
    public boolean updateAllColumnById(WebOauthCombineEntity entity) {
        WebOauthCombineEntity current = selectById(entity.getId());
        if (null == current)
            return false;
        if (!Objects.equals(entity.getClientId(), current.getClientId())) {
            updateWebAuthentication(entity.getWebAuthenticationUuid(), entity.getClientId());
            updateClientDetails(entity.getClientDetailsUuid(), entity.getClientId());
        }
        if (null == entity.getCreateTime())
            entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return super.updateAllColumnById(entity);
    }

    @Override
    public boolean isSiteDomain(String domain) {
        return baseMapper.count(domain) > 0;
    }
}
