package run.boring.modules.wall.site;

import run.boring.core.menu.Menu;
import run.boring.base.ModuleMenu;
import org.springframework.stereotype.Service;

/**
 * @author User
 * @email henryxm@163.com
 * @date 2021-11
 */
@Service
@Menu(name = "防火墙", order = 777777, ico = "fa-firefox")
public class WallMenu extends ModuleMenu {

    public String[][] getLanguageItems() {
        return new String[][]{
                {getLanguageKey(), "防火墙", "Fire Wall"},
        };
    }
}