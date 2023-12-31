package run.boring.modules.lan.service.gen;

import run.boring.modules.sys.service.SysMenuService;
import run.boring.core.site.InitFactory;
import org.springframework.beans.factory.annotation.Autowired;
import run.boring.modules.lan.service.Language;
import run.boring.modules.lan.service.LanguageService;

import java.util.List;
/**
 * 国家语言
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2021-01
 */
public class LanMenuGen implements InitFactory.Init {

    public static final String lan_menu = "lan_menu";

    @Autowired
    protected SysMenuService sysMenuService;

    @Autowired
    protected Language language;

    @Autowired
    protected LanguageService languageService;

    protected String order(){
        return "0";
    }

    protected String ico(){
        return "fa-file-code-o";
    }

    public String getMenu() {
        return SysMenuService.getMenuKey("Lan", "LanMenu");
    }

    public String getParentMenu() {
        return "";
    }

    public void init() {
        sysMenuService.put(getMenuItemsInternal(), getMenuItems(), getMenuList());
        language.put(getLanguageItemsInternal(), getLanguageItems(), getLanguageList());
    }

    public List<String[]> getMenuList() {
        return null;
    }

    public String[][] getMenuItems() {
        return null;
    }

    private String[][] getMenuItemsInternal() {
        String[][] menus = new String[][]{
                //{0:菜单名字,1:URL,2:权限,3:菜单类型,4:ICON,5:排序,6:MenuKey,7:ParentKey,8:Language}
                {"系统管理", "", "", "0", "fa " + ico(), order(), getMenu(), getParentMenu(), lan_menu + "_text"},
        };
        return menus;
    }

    public List<String[]> getLanguageList() {
        return null;
    }

    public String[][] getLanguageItems() {
        return null;
    }

    private String[][] getLanguageItemsInternal() {
        String[][] items = new String[][]{
                {lan_menu + "_text", "系统管理"},
        };
        return items;
    }
}
