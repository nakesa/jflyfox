package com.jflyfox.modules;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.article.ArticleService;
import com.jflyfox.modules.article.TbArticle;
import com.jflyfox.modules.folder.TbFolder;
import com.jflyfox.modules.web.service.WebService;
import com.jflyfox.system.dict.DictCache;
import com.jflyfox.system.menu.SysMenu;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.system.user.UserSvc;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;

import java.util.List;
import java.util.Map;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/")
public class CommonController extends BaseController {
    public static final String loginPage = "/login.html";
    public static final String mainPage = "/article/list";
    public static final String firstPage = "/web";

    public void index() {
        Integer folder_id = getParaToInt();
        if (folder_id == null || folder_id <= 0) {
            folder_id = TbFolder.ROOT;
        }
        // 目录列表，缓存
        new WebService().showDirectory(this, folder_id);
        // 文章数据列表，缓存
        Page<TbArticle> articles = new ArticleService().getArticlePage(getPaginator(), folder_id);
        setAttr("page", articles);
        renderAuto("/pages/web/home.html");
    }

    public void admin() {
        if (getSessionUser() != null) {
            // 如果session存在，不再验证
            redirect(mainPage);
        } else {
            render(loginPage);
        }
    }

    /**
     * 登录
     *
     * @author flyfox 2013-11-11
     */
    public void login() {
        // 初始化数据字典Map
        String username = getPara("username");
        String password = getPara("password");
        // 新加入，判断是否有上一个页面
        String prePage = getPara("pre_page");
        String toPage = StrUtils.isEmpty(prePage) || prePage.indexOf("login") >= 0 ? mainPage : prePage;
        setAttr("pre_page", prePage); // 如果密码错误还需要用到
        if (StrUtils.isEmpty(username)) {
            setAttr("msg", "用户名不能为空");
            render(loginPage);
            return;
        } else if (StrUtils.isEmpty(password)) {
            setAttr("msg", "密码不能为空");
            render(loginPage);
            return;
        }
        String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); // 加密
        SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? ", username, encryptPassword);
        if (user == null || user.getInt("userid") <= 0) {
            setAttr("msg", "认证失败，请您重新输入。");
            render(loginPage);
            return;
        } else {
            // 管理员，后台用才需要注册菜单
            if (user.getInt("usertype") == 1 || user.getInt("usertype") == 2) {
                Map<Integer, List<SysMenu>> map = new UserSvc().getAuthMap(user);
                if (map == null) {
                    setAttr("msg", "没有权限，请联系管理员");
                    render(loginPage);
                    return;
                }
                // 注入菜单
                setSessionAttr("menu", map);
            }
            setSessionUser(user);
        }
        redirect(toPage);
    }

    /**
     * 登出
     *
     * @author flyfox 2013-11-13
     */
    public void logout() {
        removeSessionUser();
        setAttr("msg", "您已退出");
        render(loginPage);
    }

    public void update_cache() {
        DictCache.init();
        UserCache.init();
        renderHtml("1");
    }

    public void trans() {
        String redirectPath = getPara();
        if (StrUtils.isEmpty(redirectPath)) {
            redirectPath = Config.getStr("PAGES.TRANS");
        } else if (redirectPath.equals("auth")) {
            redirectPath = "/pages/error/trans_no_auth.html";
        }
        render(redirectPath);
    }
}
