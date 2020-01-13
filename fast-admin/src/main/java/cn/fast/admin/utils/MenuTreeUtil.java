package cn.fast.admin.utils;


import cn.fast.admin.entity.SysPermission;
import cn.fast.admin.model.dto.MenuTreeDTO;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeUtil{

    public List<SysPermission> SysMenusCommon;

    public List<MenuTreeDTO> MenusTree(List<SysPermission> SysMenus){
        List<MenuTreeDTO> list = new ArrayList<MenuTreeDTO>();
        MenuTreeDTO m = null;
        this.SysMenusCommon = SysMenus;
        for (SysPermission x : SysMenus) {
            m = new MenuTreeDTO();
            if(x.getParentId().equals("0")){
                m = setMenuTreeInfo(m,x);
                m.setChildren(this.getMenusChild(x.getId()));
                list.add(m);
            }

        }
        return list;
    }


    public List<MenuTreeDTO> getMenusChild(String id){
        List<MenuTreeDTO> lists = new ArrayList<MenuTreeDTO>();
        MenuTreeDTO menuTreeDTO = null;
        for(SysPermission a:SysMenusCommon){
            menuTreeDTO = new MenuTreeDTO();
            if(a.getParentId().equals(id) ){
                menuTreeDTO = setMenuTreeInfo(menuTreeDTO,a);
                menuTreeDTO.setChildren(getMenusChild(a.getId()));
                lists.add(menuTreeDTO);
            }
        }
        return lists;
    }

    public MenuTreeDTO setMenuTreeInfo(MenuTreeDTO menuTreeDTO,SysPermission a){
        menuTreeDTO.setId(a.getId());
        menuTreeDTO.setDisableCheckbox(false);
        menuTreeDTO.setTitle(a.getName());
        menuTreeDTO.setLevel(a.getLevel());
        menuTreeDTO.setType(a.getType());
        menuTreeDTO.setPath(a.getPath());
        menuTreeDTO.setBtnType(a.getBtnType());
        menuTreeDTO.setName(a.getName());
        menuTreeDTO.setIcon(a.getIcon());
        menuTreeDTO.setComponent(a.getComponent());
        menuTreeDTO.setSort(a.getSort());
        menuTreeDTO.setEnabled(a.getEnabled());
        menuTreeDTO.setParentId(a.getParentId());
        return menuTreeDTO;
    }

}
