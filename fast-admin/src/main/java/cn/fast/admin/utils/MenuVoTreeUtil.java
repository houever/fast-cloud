package cn.fast.admin.utils;

import cn.fast.web.common.model.MenuVo;

import java.util.ArrayList;
import java.util.List;

public class MenuVoTreeUtil {

    public List<MenuVo> menusCommon;

    public List<MenuVo> menusList(List<MenuVo> menus){
        List<MenuVo> list = new ArrayList<MenuVo>();
        MenuVo m = null;
        this.menusCommon = menus;
        for (MenuVo x : menus) {
            m = new MenuVo();
            if(x.getParentId().equals("0")){
                m.setId(x.getId());
                m.setName(x.getName());
                m.setCode(x.getCode());
                m.setPath(x.getPath());
                m.setComponent(x.getComponent());
                m.setHidden(x.isHidden());
                m.setRedirect(x.getRedirect());
                m.setMeta(x.getMeta());
                m.setKeepalived(x.isKeepalived());
                m.setParentId(x.getParentId());
                m.setEnabled(x.isEnabled());
                m.setChildren(menusChild(x.getId()));
                list.add(m);
            }

        }
        return list;
    }


    public List<MenuVo> menusChild(String id){
        List<MenuVo> lists = new ArrayList<MenuVo>();
        MenuVo menus = null;
        for(MenuVo a:menusCommon){
            menus = new MenuVo();
            if(a.getParentId().equals(id) ){
                menus.setId(a.getId());
                menus.setName(a.getName());
                menus.setCode(a.getCode());
                menus.setPath(a.getPath());
                menus.setComponent(a.getComponent());
                menus.setHidden(a.isHidden());
                menus.setRedirect(a.getRedirect());
                menus.setMeta(a.getMeta());
                menus.setKeepalived(a.isKeepalived());
                menus.setParentId(a.getParentId());
                menus.setEnabled(a.isEnabled());
                menus.setChildren(menusChild(a.getId()));
                lists.add(menus);
            }
        }
        return lists;

    }
}
