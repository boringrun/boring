package run.boring.handler.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(AllInOneHandler.class)
public interface AllInOneHandler {
    default boolean is() {
        return false;
    }
}
