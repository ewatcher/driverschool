package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Role;

public interface RoleServiceI {

	public DataGrid datagrid(Role role)throws Exception;

	public Role add(Role role)throws Exception;

	public Role edit(Role role)throws Exception;

	public void delete(String ids)throws Exception;

	public List<Role> combobox(String schoolArea)throws Exception;

}
