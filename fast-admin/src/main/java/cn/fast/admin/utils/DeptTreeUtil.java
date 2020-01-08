package cn.fast.admin.utils;


import cn.fast.admin.entity.SysDepartment;
import cn.fast.admin.model.dto.DeptDTO;

import java.util.ArrayList;
import java.util.List;

public class DeptTreeUtil {


    public List<SysDepartment> SysMenusCommon;

    public List<DeptDTO> deptTree(List<SysDepartment> depts) {
        List<DeptDTO> list = new ArrayList<DeptDTO>();
        DeptDTO d = null;
        this.SysMenusCommon = depts;
        for (SysDepartment x : depts) {
            d = new DeptDTO();
            if (x.getParentId().equals("0")) {
                d.setId(x.getId());
                d.setEnabled(x.getEnabled());
                d.setTitle(x.getDeptName());
                d.setLeader(x.getLeader());
                d.setDeptName(x.getDeptName());
                d.setIsParent(x.getIsParent());
                d.setChildren(this.getDeptChild(x.getId()));
                list.add(d);
            }

        }
        return list;
    }


    public List<DeptDTO> getDeptChild(String id) {
        List<DeptDTO> lists = new ArrayList<DeptDTO>();
        DeptDTO deptDTO = null;
        for (SysDepartment a : SysMenusCommon) {
            deptDTO = new DeptDTO();
            if (a.getParentId().equals(id)) {
                deptDTO.setId(a.getId());
                deptDTO.setEnabled(a.getEnabled());
                deptDTO.setTitle(a.getDeptName());
                deptDTO.setLeader(a.getLeader());
                deptDTO.setDeptName(a.getDeptName());
                deptDTO.setIsParent(a.getIsParent());
                deptDTO.setChildren(getDeptChild(a.getId()));
                lists.add(deptDTO);
            }
        }
        return lists;

    }
}
