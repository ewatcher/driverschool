package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.DriverCarType;

public interface DriverCarTypeServiceI {
	public DriverCarType save(DriverCarType driverCarType);
	public DriverCarType update(DriverCarType driverCarType);
	public void delete(int id);
	public DataGrid dataGrid(DriverCarType driverCarType);
}
