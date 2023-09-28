package run.boring.core.view;

import run.boring.handler.config.ViewHandler;
import run.boring.core.site.ViewFactory;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class NameBasedViewResolver extends FreeMarkerViewResolver {

    ViewFactory viewFactory;

    public NameBasedViewResolver(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView basedView = super.buildView(viewName);
        ViewHandler viewHandler = viewFactory.getShould(viewName);
        if (null != viewHandler) {
            String url = viewHandler.getUrl(getPrefix(), viewName, getSuffix());
            basedView.setUrl(url);
        }
        return basedView;
    }
}