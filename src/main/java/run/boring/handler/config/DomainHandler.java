package run.boring.handler.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(DomainHandler.class)
public interface DomainHandler {
    default boolean isSiteDomain(String domain) {
        return false;
    }

    default boolean isBindDomain(String domain) {
        return false;
    }
}
