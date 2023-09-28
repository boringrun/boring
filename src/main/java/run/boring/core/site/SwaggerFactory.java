package run.boring.core.site;

import run.boring.handler.config.SwaggerHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwaggerFactory extends Factory {
    List<SwaggerHandler> swaggerHandlers = null;

    public SwaggerHandler getSwaggerHandler() {
        if (null == swaggerHandlers) {
            swaggerHandlers = getOrderList(SwaggerHandler.class);
            for (SwaggerHandler swaggerHandler : swaggerHandlers) {
                return swaggerHandler;
            }
        }
        return new SwaggerHandler() {
        };
    }
}
