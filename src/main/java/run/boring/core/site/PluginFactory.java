package run.boring.core.site;

import run.boring.handler.config.PluginHandler;
import run.boring.core.plugin.PluginManager;
import run.boring.handler.plugin.PluginEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PluginFactory extends Factory {
    @Autowired
    PluginManager pluginManager;

    List<PluginHandler> pluginHandlers = null;

    public List<PluginEntry> installPlugin() {
        if (null == pluginHandlers)
            pluginHandlers = getOrderList(PluginHandler.class);
        if (null != pluginHandlers && !pluginHandlers.isEmpty()) {
            for (PluginHandler pluginHandler : pluginHandlers) {
                pluginHandler.installPlugin();
            }
        }
        return pluginManager.getPlugins();
    }

    public List<PluginEntry> uninstallPlugin() {
        List<PluginEntry> pluginEntries = pluginManager.getPlugins();
        for (PluginEntry entry : pluginEntries) {
            pluginManager.unload(entry);
        }
        return pluginManager.getPlugins();
    }
}