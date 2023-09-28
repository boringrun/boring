package run.boring.core.config;

import run.boring.handler.cluster.HealthHandler;
import run.boring.handler.cluster.VersionHandler;
import org.springframework.stereotype.Component;

@Component
public class BoringVersion implements VersionHandler, HealthHandler {

    @Override
    public Object value() {
        return "1.0.0";
    }

    @Override
    public String name() {
        return VersionHandler.super.name();
    }

    @Override
    public Object version() {
        return value();
    }
}
