package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.User;

public interface UserServiceI {

	public User login(User user)throws Exception;

	public User save(User user)throws Exception;

	public DataGrid datagrid(User user)throws Exception;

	public void delete(String ids)throws Exception;

	public User update(User user)throws Exception;

	public void roleEdit(User user)throws Exception;

	public void editUserInfo(User user)throws Exception;

	public List<User> combobox(User user)throws Exception;
	
	public User getSingleById(String id);
	
	public User getUserByNamePWD(String name,String PWD)throws Exception;

}
