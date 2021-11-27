package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.model.demo.TnetPiont;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.NetPiont;

/**
 * 报名网点实体类管理接口
 * 
 * @author MEI702
 * 
 */
public interface NetPiontServiceI {
	/**
	 * 添加报名网点实体类信息
	 * 
	 * @param netPiont
	 * @return
	 */
	public NetPiont save(NetPiont netPiont) throws Exception;

	/**
	 * 更新报名网点实体类信息
	 * 
	 * @param netPiont
	 * @return
	 */
	public NetPiont udpate(NetPiont netPiont) throws Exception;

	/**
	 * 根据id标识删除报名网点实体类信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有报名网点实体类的信息，并以datagrid开发传递到前台
	 * 
	 * @param netPiont
	 * @return
	 */
	public DataGrid dataGrid(NetPiont netPiont) throws Exception;

	/**
	 * 根据校区获取排序编号
	 * 
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Integer getOrderNoBySchoolArea(String schoolArea) throws Exception;

	/**
	 * 根据校区标识获取报名点实体
	 * 
	 * @param schoolArea
	 * @return 返回JSON格式
	 * @throws Exception
	 * 
	 *             2017年7月10日 List<ComboboxJson>
	 */
	public List<ComboboxJson> getMyNetPiont(String schoolArea) throws Exception;

	/**
	 * 根据标识查找出报名点实体
	 * @param schoolArea
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月12日 Double
	 */
	public TnetPiont getSingleById(String schoolArea, String id) throws Exception;
}
