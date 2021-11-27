package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Online;

public interface OnlineServiceI {

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName, String ip,String schoolArea)throws Exception;

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip)throws Exception;

	public DataGrid datagrid(Online online)throws Exception;

}
