package com.tuocheng.util.base;

import java.math.BigDecimal;
import java.util.UUID;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tauth;

public class AuthInitUtils {
	public static final String SUPERID_ADMIN="nongfeng-4f14-4625-bf58-7b20171688";
	public static void initMainAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {

		// 初始化学员管理权限01
		initAuthStudentMng(sshe, authDao);
		// 教练管理权限初始化02
		initTrainerAuth(sshe, authDao);
		// 车辆管理权限初始化03
		initCarManger(sshe, authDao);
		// 考试管理权限初始化04
		initExaminationManger(sshe, authDao);
		// 预约管理权限05
		initFileMsgAuth(sshe, authDao);
		// 财务管理权限06
		initFeeMsgAuth(sshe, authDao);
		// 微信服务权限07
		initWechatAuth(sshe, authDao);
		// 油卡权限配置08
		initOilcard(sshe, authDao);
		// 网站管理权限09
		initLicenseAuth(sshe, authDao);
		// 网站管理权限10
		initNetAuth(sshe, authDao);
		// 数据库权限（一级）12
		Tauth sjkgl = new Tauth();
		sjkgl.setCid("sjkgl");
		sjkgl.setTauth(sshe);
		sjkgl.setCname("数据库管理");
		sjkgl.setCurl("");
		sjkgl.setCseq(BigDecimal.valueOf(12));
		sjkgl.setCdesc("可查看数据库链接信息，SQL语句执行情况");
		sjkgl.setMenuId("sjkgl");// this id control auth
		authDao.saveOrUpdate(sjkgl);
		initDatabaseAuth(sjkgl, authDao);// 数据库权限（二级）12
		// 初始化日志13
		initAuthLog(sshe, authDao);
		// 公共资源
		initCommon(sshe, authDao);
		// 短信平台14
		initSMSAuth(sshe, authDao);
		// 机构管理权限16
		initOrganizationAuth(sshe, authDao);
		// 系统管理权限（一级）17
		Tauth xtgl = new Tauth();
		xtgl.setCid("xtgl");
		xtgl.setTauth(sshe);
		xtgl.setCname("系统管理");
		xtgl.setCurl("");
		xtgl.setCseq(BigDecimal.valueOf(17));
		xtgl.setCdesc("");
		authDao.saveOrUpdate(xtgl);
		initSecondSystemAuth(xtgl, authDao);

	}

	// 证件管理权限09
	private static void initLicenseAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth licenseMsgAuth = new Tauth();
		licenseMsgAuth.setCid("licenseMsgAuth");
		licenseMsgAuth.setTauth(sshe);
		licenseMsgAuth.setCname("证件管理");
		licenseMsgAuth.setCurl("");
		licenseMsgAuth.setCseq(BigDecimal.valueOf(9));
		licenseMsgAuth.setCdesc("");
		licenseMsgAuth.setMenuId("licenseMsgMenu");
		authDao.saveOrUpdate(licenseMsgAuth);

		// 学员驾驶证管理权限
		Tauth driverLicenseMsg = new Tauth();
		driverLicenseMsg.setCid("driverLicenseMsg");
		driverLicenseMsg.setTauth(licenseMsgAuth);
		driverLicenseMsg.setCname("驾驶证管理");
		driverLicenseMsg.setCurl("");
		driverLicenseMsg.setCseq(BigDecimal.valueOf(1));
		driverLicenseMsg.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg);

		Tauth driverLicenseMsg01 = new Tauth();
		driverLicenseMsg01.setCid("driverLicenseMsg01");
		driverLicenseMsg01.setTauth(driverLicenseMsg);
		driverLicenseMsg01.setCname("驾驶证列表");
		driverLicenseMsg01.setCurl("/demo/driverLicenseAction!datagrid.action");
		driverLicenseMsg01.setCseq(BigDecimal.valueOf(1));
		driverLicenseMsg01.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg01);
		Tauth driverLicenseMsg02 = new Tauth();
		driverLicenseMsg02.setCid("driverLicenseMsg02");
		driverLicenseMsg02.setTauth(driverLicenseMsg);
		driverLicenseMsg02.setCname("更新驾驶证");
		driverLicenseMsg02.setCurl("/demo/driverLicenseAction!edit.action");
		driverLicenseMsg02.setCseq(BigDecimal.valueOf(2));
		driverLicenseMsg02.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg02);
		Tauth driverLicenseMsg03 = new Tauth();
		driverLicenseMsg03.setCid("driverLicenseMsg03");
		driverLicenseMsg03.setTauth(driverLicenseMsg);
		driverLicenseMsg03.setCname("驾驶证管理");
		driverLicenseMsg03.setCurl("/demo/driverLicenseAction!delete.action");
		driverLicenseMsg03.setCseq(BigDecimal.valueOf(3));
		driverLicenseMsg03.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg03);
		Tauth driverLicenseMsg04 = new Tauth();
		driverLicenseMsg04.setCid("driverLicenseMsg04");
		driverLicenseMsg04.setTauth(driverLicenseMsg);
		driverLicenseMsg04.setCname("获取学员信息");
		driverLicenseMsg04.setCurl("/demo/driverLicenseAction!getAllGraduateStudents.action");
		driverLicenseMsg04.setCseq(BigDecimal.valueOf(4));
		driverLicenseMsg04.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg04);
		Tauth driverLicenseMsg05 = new Tauth();
		driverLicenseMsg05.setCid("driverLicenseMsg05");
		driverLicenseMsg05.setTauth(driverLicenseMsg);
		driverLicenseMsg05.setCname("获取单个学员");
		driverLicenseMsg05.setCurl("/demo/driverLicenseAction!getSingStudentInformation.action");
		driverLicenseMsg05.setCseq(BigDecimal.valueOf(5));
		driverLicenseMsg05.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg05);
		Tauth driverLicenseMsg06 = new Tauth();
		driverLicenseMsg06.setCid("driverLicenseMsg06");
		driverLicenseMsg06.setTauth(driverLicenseMsg);
		driverLicenseMsg06.setCname("添加");
		driverLicenseMsg06.setCurl("/demo/driverLicenseAction!add.action");
		driverLicenseMsg06.setCseq(BigDecimal.valueOf(6));
		driverLicenseMsg06.setCdesc("");
		authDao.saveOrUpdate(driverLicenseMsg06);
		// 资格证权限
		Tauth qualiLicenseMsg = new Tauth();
		qualiLicenseMsg.setCid("qualiLicenseMsg");
		qualiLicenseMsg.setTauth(licenseMsgAuth);
		qualiLicenseMsg.setCname("资格证管理");
		qualiLicenseMsg.setCurl("");
		qualiLicenseMsg.setCseq(BigDecimal.valueOf(2));
		qualiLicenseMsg.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg);

		Tauth qualiLicenseMsg06 = new Tauth();
		qualiLicenseMsg06.setCid("qualiLicenseMsg06");
		qualiLicenseMsg06.setTauth(qualiLicenseMsg);
		qualiLicenseMsg06.setCname("资格证列表");
		qualiLicenseMsg06.setCurl("/demo/qualificationLicenseAction!datagrid.action");
		qualiLicenseMsg06.setCseq(BigDecimal.valueOf(1));
		qualiLicenseMsg06.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg06);
		Tauth qualiLicenseMsg07 = new Tauth();
		qualiLicenseMsg07.setCid("qualiLicenseMsg07");
		qualiLicenseMsg07.setTauth(qualiLicenseMsg);
		qualiLicenseMsg07.setCname("添加资格证");
		qualiLicenseMsg07.setCurl("/demo/qualificationLicenseAction!add.action");
		qualiLicenseMsg07.setCseq(BigDecimal.valueOf(2));
		qualiLicenseMsg07.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg07);
		Tauth qualiLicenseMsg08 = new Tauth();
		qualiLicenseMsg08.setCid("qualiLicenseMsg08");
		qualiLicenseMsg08.setTauth(qualiLicenseMsg);
		qualiLicenseMsg08.setCname("更新资格证");
		qualiLicenseMsg08.setCurl("/demo/qualificationLicenseAction!edit.action");
		qualiLicenseMsg08.setCseq(BigDecimal.valueOf(3));
		qualiLicenseMsg08.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg08);
		Tauth qualiLicenseMsg09 = new Tauth();
		qualiLicenseMsg09.setCid("qualiLicenseMsg09");
		qualiLicenseMsg09.setTauth(qualiLicenseMsg);
		qualiLicenseMsg09.setCname("删除资格证");
		qualiLicenseMsg09.setCurl("/demo/qualificationLicenseAction!delete.action");
		qualiLicenseMsg09.setCseq(BigDecimal.valueOf(4));
		qualiLicenseMsg09.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg09);
		Tauth qualiLicenseMsg10 = new Tauth();
		qualiLicenseMsg10.setCid("qualiLicenseMsg10");
		qualiLicenseMsg10.setTauth(qualiLicenseMsg);
		qualiLicenseMsg10.setCname("获取学员信息");
		qualiLicenseMsg10.setCurl("/demo/qualificationLicenseAction!getAllGraduateStudents.action");
		qualiLicenseMsg10.setCseq(BigDecimal.valueOf(5));
		qualiLicenseMsg10.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg10);
		Tauth qualiLicenseMsg11 = new Tauth();
		qualiLicenseMsg11.setCid("qualiLicenseMsg11");
		qualiLicenseMsg11.setTauth(qualiLicenseMsg);
		qualiLicenseMsg11.setCname("查看学员信息");
		qualiLicenseMsg11.setCurl("/demo/qualificationLicenseAction!getSingStudentInformation.action");
		qualiLicenseMsg11.setCseq(BigDecimal.valueOf(6));
		qualiLicenseMsg11.setCdesc("");
		authDao.saveOrUpdate(qualiLicenseMsg11);
	}

	// 财务管理权限06
	private static void initFeeMsgAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth feeAuth = new Tauth();
		feeAuth.setCid("feeAuth");
		feeAuth.setTauth(sshe);
		feeAuth.setCname("财务管理");
		feeAuth.setCurl("");
		feeAuth.setCseq(BigDecimal.valueOf(6));
		feeAuth.setCdesc("");
		feeAuth.setMenuId("financialMenu");
		authDao.saveOrUpdate(feeAuth);

		Tauth feeAuthSecond01 = new Tauth();// 二极模块
		feeAuthSecond01.setCid("feeAuthSecond01");
		feeAuthSecond01.setTauth(feeAuth);
		feeAuthSecond01.setCname("收费配置");
		feeAuthSecond01.setCurl("");
		feeAuthSecond01.setCseq(BigDecimal.valueOf(1));
		feeAuthSecond01.setCdesc("");
		authDao.saveOrUpdate(feeAuthSecond01);
		
		Tauth feeDetailType01 = new Tauth();
		feeDetailType01.setCid("feeDetailType01");
		feeDetailType01.setTauth(feeAuthSecond01);
		feeDetailType01.setCname("添加");
		feeDetailType01.setCurl("/demo/feeDetailTypeAction!add.action");
		feeDetailType01.setCseq(BigDecimal.valueOf(1));
		feeDetailType01.setCdesc("");
		authDao.saveOrUpdate(feeDetailType01);
		Tauth feeDetailType02 = new Tauth();
		feeDetailType02.setCid("feeDetailType02");
		feeDetailType02.setTauth(feeAuthSecond01);
		feeDetailType02.setCname("修改");
		feeDetailType02.setCurl("/demo/feeDetailTypeAction!edit.action");
		feeDetailType02.setCseq(BigDecimal.valueOf(2));
		feeDetailType02.setCdesc("");
		authDao.saveOrUpdate(feeDetailType02);
		Tauth feeDetailType03 = new Tauth();
		feeDetailType03.setCid("feeDetailType03");
		feeDetailType03.setTauth(feeAuthSecond01);
		feeDetailType03.setCname("删除");
		feeDetailType03.setCurl("/demo/feeDetailTypeAction!delete.action");
		feeDetailType03.setCseq(BigDecimal.valueOf(3));
		feeDetailType03.setCdesc("");
		authDao.saveOrUpdate(feeDetailType03);
		
		
		Tauth feeAuthSecond02 = new Tauth();// 二极模块
		feeAuthSecond02.setCid("feeAuthSecond02");
		feeAuthSecond02.setTauth(feeAuth);
		feeAuthSecond02.setCname("收费明细");
		feeAuthSecond02.setCurl("");
		feeAuthSecond02.setCseq(BigDecimal.valueOf(2));
		feeAuthSecond02.setCdesc("");
		authDao.saveOrUpdate(feeAuthSecond02);
		Tauth collectFee01 = new Tauth();// 三极模块
		collectFee01.setCid("collectFee01");
		collectFee01.setTauth(feeAuthSecond02);
		collectFee01.setCname("添加");
		collectFee01.setCurl("/demo/collectFeeAction!add.action");
		collectFee01.setCseq(BigDecimal.valueOf(1));
		collectFee01.setCdesc("");
		authDao.saveOrUpdate(collectFee01);
		Tauth collectFee02 = new Tauth();// 三极模块
		collectFee02.setCid("collectFee02");
		collectFee02.setTauth(feeAuthSecond02);
		collectFee02.setCname("添加");
		collectFee02.setCurl("/demo/collectFeeAction!edit.action");
		collectFee02.setCseq(BigDecimal.valueOf(1));
		collectFee02.setCdesc("");
		authDao.saveOrUpdate(collectFee02);
		Tauth collectFee03 = new Tauth();// 三极模块
		collectFee03.setCid("collectFee03");
		collectFee03.setTauth(feeAuthSecond02);
		collectFee03.setCname("删除");
		collectFee03.setCurl("/demo/collectFeeAction!delete.action");
		collectFee03.setCseq(BigDecimal.valueOf(1));
		collectFee03.setCdesc("");
		authDao.saveOrUpdate(collectFee03);
	}

	// 二级短信平台
	private static void initSMSAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth smsAuth = new Tauth();
		smsAuth.setCid("smsAuth");
		smsAuth.setTauth(sshe);
		smsAuth.setCname("短信平台");
		smsAuth.setCurl("");
		smsAuth.setCseq(BigDecimal.valueOf(14));
		smsAuth.setCdesc("");
		smsAuth.setMenuId("smsMenu");
		authDao.saveOrUpdate(smsAuth);

		Tauth smsAuth01 = new Tauth();// 二极模块
		smsAuth01.setCid("smsAuth01");
		smsAuth01.setTauth(smsAuth);
		smsAuth01.setCname("添加");
		smsAuth01.setCurl("/demo/smsAction!add.action");
		smsAuth01.setCseq(BigDecimal.valueOf(1));
		smsAuth01.setCdesc("");
		authDao.saveOrUpdate(smsAuth01);
		Tauth smsAuth02 = new Tauth();// 二极模块
		smsAuth02.setCid("smsAuth02");
		smsAuth02.setTauth(smsAuth);
		smsAuth02.setCname("删除");
		smsAuth02.setCurl("/demo/smsAction!delete.action");
		smsAuth02.setCseq(BigDecimal.valueOf(2));
		smsAuth02.setCdesc("");
		authDao.saveOrUpdate(smsAuth02);
		Tauth smsAuth03 = new Tauth();// 二极模块
		smsAuth03.setCid("smsAuth03");
		smsAuth03.setTauth(smsAuth);
		smsAuth03.setCname("更新");
		smsAuth03.setCurl("/demo/smsAction!edit.action");
		smsAuth03.setCseq(BigDecimal.valueOf(3));
		smsAuth03.setCdesc("");
		authDao.saveOrUpdate(smsAuth03);
		Tauth smsAuth05 = new Tauth();// 二极模块
		smsAuth05.setCid("smsAuth05");
		smsAuth05.setTauth(smsAuth);
		smsAuth05.setCname("微信通知");
		smsAuth05.setCurl("/demo/smsAction!sendMsgByWechat.action");
		smsAuth05.setCseq(BigDecimal.valueOf(5));
		smsAuth05.setCdesc("");
		authDao.saveOrUpdate(smsAuth05);
	}

	// 公共资源
	private static void initOilcard(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth oilcardAuth = new Tauth();
		oilcardAuth.setCid("oilcardAuth");
		oilcardAuth.setTauth(sshe);
		oilcardAuth.setCname("油卡管理");
		oilcardAuth.setCurl("");
		oilcardAuth.setCseq(BigDecimal.valueOf(8));
		oilcardAuth.setCdesc("");
		oilcardAuth.setMenuId("oilcardMenu");
		authDao.saveOrUpdate(oilcardAuth);

		Tauth oilcardAuthMng1 = new Tauth();// 二极模块
		oilcardAuthMng1.setCid("oilcardAuthMng1");
		oilcardAuthMng1.setTauth(oilcardAuth);
		oilcardAuthMng1.setCname("油卡基本信息");
		oilcardAuthMng1.setCurl("");
		oilcardAuthMng1.setCseq(BigDecimal.valueOf(1));
		oilcardAuthMng1.setCdesc("");
		authDao.saveOrUpdate(oilcardAuthMng1);
		Tauth oilcardAuthMng2 = new Tauth();
		oilcardAuthMng2.setCid("oilcardAuthMng2");
		oilcardAuthMng2.setTauth(oilcardAuth);
		oilcardAuthMng2.setCname("油卡充值信息");
		oilcardAuthMng2.setCurl("");
		oilcardAuthMng2.setCseq(BigDecimal.valueOf(2));
		oilcardAuthMng2.setCdesc("");
		authDao.saveOrUpdate(oilcardAuthMng2);
		Tauth oilcardAuthMng3 = new Tauth();
		oilcardAuthMng3.setCid("oilcardAuthMng3");
		oilcardAuthMng3.setTauth(oilcardAuth);
		oilcardAuthMng3.setCname("油卡使用信息");
		oilcardAuthMng3.setCurl("");
		oilcardAuthMng3.setCseq(BigDecimal.valueOf(3));
		oilcardAuthMng3.setCdesc("");
		authDao.saveOrUpdate(oilcardAuthMng3);

		Tauth oilcardAuth01 = new Tauth();// 三级模块
		oilcardAuth01.setCid("oilcardAuth01");
		oilcardAuth01.setTauth(oilcardAuthMng1);
		oilcardAuth01.setCname("油卡信息列表");
		oilcardAuth01.setCurl("/demo/oilcardAction!datagrid.action");
		oilcardAuth01.setCseq(BigDecimal.valueOf(1));
		oilcardAuth01.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardAuth01);
		Tauth oilcardAuth04 = new Tauth();
		oilcardAuth04.setCid("oilcardAuth04");
		oilcardAuth04.setTauth(oilcardAuthMng1);
		oilcardAuth04.setCname("油卡添加");
		oilcardAuth04.setCurl("/demo/oilcardAction!add.action");
		oilcardAuth04.setCseq(BigDecimal.valueOf(2));
		oilcardAuth04.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardAuth04);
		Tauth oilcardAuth06 = new Tauth();
		oilcardAuth06.setCid("oilcardAuth06");
		oilcardAuth06.setTauth(oilcardAuthMng1);
		oilcardAuth06.setCname("修改油卡");
		oilcardAuth06.setCurl("/demo/oilcardAction!edit.action");
		oilcardAuth06.setCseq(BigDecimal.valueOf(3));
		oilcardAuth06.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardAuth06);
		Tauth oilcardAuth07 = new Tauth();
		oilcardAuth07.setCid("oilcardAuth07");
		oilcardAuth07.setTauth(oilcardAuthMng1);
		oilcardAuth07.setCname("删除油卡");
		oilcardAuth07.setCurl("/demo/oilcardAction!delete.action");
		oilcardAuth07.setCseq(BigDecimal.valueOf(4));
		oilcardAuth07.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardAuth07);

		// 充值卡明细
		Tauth rechargeOilcard1 = new Tauth();// 二级模块
		rechargeOilcard1.setCid("rechargeOilcard1");
		rechargeOilcard1.setTauth(oilcardAuthMng2);
		rechargeOilcard1.setCname("油卡充值明细列表");
		rechargeOilcard1.setCurl("/demo/rechargeOilcardAction!getRechargeDetailByCardId.action");
		rechargeOilcard1.setCseq(BigDecimal.valueOf(1));
		rechargeOilcard1.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard1);

		Tauth rechargeOilcard5 = new Tauth();// 三级模块
		rechargeOilcard5.setCid("rechargeOilcard5");
		rechargeOilcard5.setTauth(oilcardAuthMng2);
		rechargeOilcard5.setCname("添加充值");
		rechargeOilcard5.setCurl("/demo/rechargeOilcardAction!add.action");
		rechargeOilcard5.setCseq(BigDecimal.valueOf(2));
		rechargeOilcard5.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard5);
		Tauth rechargeOilcard6 = new Tauth();// 三级模块
		rechargeOilcard6.setCid("rechargeOilcard6");
		rechargeOilcard6.setTauth(oilcardAuthMng2);
		rechargeOilcard6.setCname("修改充值");
		rechargeOilcard6.setCurl("/demo/rechargeOilcardAction!edit.action");
		rechargeOilcard6.setCseq(BigDecimal.valueOf(3));
		rechargeOilcard6.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard6);
		Tauth rechargeOilcard7 = new Tauth();// 三级模块
		rechargeOilcard7.setCid("rechargeOilcard7");
		rechargeOilcard7.setTauth(oilcardAuthMng2);
		rechargeOilcard7.setCname("删除充值");
		rechargeOilcard7.setCurl("/demo/rechargeOilcardAction!delete.action");
		rechargeOilcard7.setCseq(BigDecimal.valueOf(4));
		rechargeOilcard7.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard7);
		Tauth rechargeOilcard8 = new Tauth();// 三级模块
		rechargeOilcard8.setCid("rechargeOilcard8");
		rechargeOilcard8.setTauth(oilcardAuthMng2);
		rechargeOilcard8.setCname("获取最新余额");
		rechargeOilcard8.setCurl("/demo/rechargeOilcardAction!getLatelyBalanceByOilcardId.action");
		rechargeOilcard8.setCseq(BigDecimal.valueOf(5));
		rechargeOilcard8.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard8);
		Tauth rechargeOilcard10 = new Tauth();// 三级模块
		rechargeOilcard10.setCid("rechargeOilcard10");
		rechargeOilcard10.setTauth(oilcardAuthMng2);
		rechargeOilcard10.setCname("明细列表");
		rechargeOilcard10.setCurl("/demo/rechargeOilcardAction!datagrid.action");
		rechargeOilcard10.setCseq(BigDecimal.valueOf(6));
		rechargeOilcard10.setCdesc("icon-filter");
		authDao.saveOrUpdate(rechargeOilcard10);

		// 油卡使用明细
		Tauth oilcardUsing1 = new Tauth();// 二级模块
		oilcardUsing1.setCid("oilcardUsing1");
		oilcardUsing1.setTauth(oilcardAuthMng3);
		oilcardUsing1.setCname("油卡使用明细列表");
		oilcardUsing1.setCurl("/demo/oilcardUsingAction!getOilcardUsingDetailByCardId.action");
		oilcardUsing1.setCseq(BigDecimal.valueOf(1));
		oilcardUsing1.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardUsing1);
		Tauth oilcardUsing5 = new Tauth();// 三级模块
		oilcardUsing5.setCid("oilcardUsing5");
		oilcardUsing5.setTauth(oilcardAuthMng3);
		oilcardUsing5.setCname("添加油卡明细");
		oilcardUsing5.setCurl("/demo/oilcardUsingAction!add.action");
		oilcardUsing5.setCseq(BigDecimal.valueOf(2));
		oilcardUsing5.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardUsing5);
		Tauth oilcardUsing6 = new Tauth();// 三级模块
		oilcardUsing6.setCid("oilcardUsing6");
		oilcardUsing6.setTauth(oilcardAuthMng3);
		oilcardUsing6.setCname("修改油卡明细");
		oilcardUsing6.setCurl("/demo/oilcardUsingAction!edit.action");
		oilcardUsing6.setCseq(BigDecimal.valueOf(3));
		oilcardUsing6.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardUsing6);
		Tauth oilcardUsing7 = new Tauth();// 三级模块
		oilcardUsing7.setCid("oilcardUsing7");
		oilcardUsing7.setTauth(oilcardAuthMng3);
		oilcardUsing7.setCname("删除油卡明细");
		oilcardUsing7.setCurl("/demo/oilcardUsingAction!delete.action");
		oilcardUsing7.setCseq(BigDecimal.valueOf(4));
		oilcardUsing7.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardUsing7);

		Tauth oilcardUsing9 = new Tauth();// 三级模块
		oilcardUsing9.setCid("oilcardUsing9");
		oilcardUsing9.setTauth(oilcardAuthMng3);
		oilcardUsing9.setCname("明细列表");
		oilcardUsing9.setCurl("/demo/oilcardUsingAction!datagrid.action");
		oilcardUsing9.setCseq(BigDecimal.valueOf(5));
		oilcardUsing9.setCdesc("icon-filter");
		authDao.saveOrUpdate(oilcardUsing9);

	}

	// 机构管理权限
	private static void initOrganizationAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth organizationMangerMsg = new Tauth();
		organizationMangerMsg.setCid("organizationMangerMsg");
		organizationMangerMsg.setTauth(sshe);
		organizationMangerMsg.setCname("机构管理");
		organizationMangerMsg.setCurl("");
		organizationMangerMsg.setCseq(BigDecimal.valueOf(16));
		organizationMangerMsg.setCdesc("");
		authDao.saveOrUpdate(organizationMangerMsg);

		Tauth organizationMsgGl = new Tauth();
		organizationMsgGl.setCid("organizationMsgGl");
		organizationMsgGl.setTauth(organizationMangerMsg);
		organizationMsgGl.setCname("机构信息管理");
		organizationMsgGl.setCurl("");
		organizationMsgGl.setCseq(BigDecimal.valueOf(1));
		organizationMsgGl.setCdesc("");
		organizationMsgGl.setMenuId("orgmsgmanager");
		authDao.saveOrUpdate(organizationMsgGl);

		Tauth organizationMsg01 = new Tauth();
		organizationMsg01.setCid("organizationMsg01");
		organizationMsg01.setTauth(organizationMsgGl);
		organizationMsg01.setCname("机构管理主页");
		organizationMsg01.setCurl("/demo/organizationAction!organization.action");
		organizationMsg01.setCseq(BigDecimal.valueOf(1));
		organizationMsg01.setCdesc("");
		authDao.saveOrUpdate(organizationMsg01);
		Tauth organizationMsg02 = new Tauth();
		organizationMsg02.setCid("organizationMsg02");
		organizationMsg02.setTauth(organizationMsgGl);
		organizationMsg02.setCname("机构添加页面页");
		organizationMsg02.setCurl("/demo/organizationAction!organizationAdd.action");
		organizationMsg02.setCseq(BigDecimal.valueOf(2));
		organizationMsg02.setCdesc("");
		authDao.saveOrUpdate(organizationMsg02);
		Tauth organizationMsg03 = new Tauth();
		organizationMsg03.setCid("organizationMsg03");
		organizationMsg03.setTauth(organizationMsgGl);
		organizationMsg03.setCname("机构编辑页面");
		organizationMsg03.setCurl("/demo/organizationAction!organizationEdit.action");
		organizationMsg03.setCseq(BigDecimal.valueOf(3));
		organizationMsg03.setCdesc("");
		authDao.saveOrUpdate(organizationMsg03);
		Tauth organizationMsg04 = new Tauth();
		organizationMsg04.setCid("organizationMsg04");
		organizationMsg04.setTauth(organizationMsgGl);
		organizationMsg04.setCname("机构管理列表");
		organizationMsg04.setCurl("/demo/organizationAction!treegrid.action");
		organizationMsg04.setCseq(BigDecimal.valueOf(4));
		organizationMsg04.setCdesc("");
		authDao.saveOrUpdate(organizationMsg04);
		Tauth organizationMsg05 = new Tauth();
		organizationMsg05.setCid("organizationMsg05");
		organizationMsg05.setTauth(organizationMsgGl);
		organizationMsg05.setCname("添加机构管理");
		organizationMsg05.setCurl("/demo/organizationAction!add.action");
		organizationMsg05.setCseq(BigDecimal.valueOf(5));
		organizationMsg05.setCdesc("");
		authDao.saveOrUpdate(organizationMsg05);
		Tauth organizationMsg06 = new Tauth();
		organizationMsg06.setCid("organizationMsg06");
		organizationMsg06.setTauth(organizationMsgGl);
		organizationMsg06.setCname("编辑机构管理");
		organizationMsg06.setCurl("/demo/organizationAction!edit.action");
		organizationMsg06.setCseq(BigDecimal.valueOf(6));
		organizationMsg06.setCdesc("");
		authDao.saveOrUpdate(organizationMsg06);
		Tauth organizationMsg07 = new Tauth();
		organizationMsg07.setCid("organizationMsg07");
		organizationMsg07.setTauth(organizationMsgGl);
		organizationMsg07.setCname("删除机构管理");
		organizationMsg07.setCurl("/demo/organizationAction!delete.action");
		organizationMsg07.setCseq(BigDecimal.valueOf(7));
		organizationMsg07.setCdesc("");
		authDao.saveOrUpdate(organizationMsg07);
		Tauth organizationMsg08 = new Tauth();
		organizationMsg08.setCid("organizationMsg08");
		organizationMsg08.setTauth(organizationMsgGl);
		organizationMsg08.setCname("按标识获取机构");
		organizationMsg08.setCurl("/demo/organizationAction!getDepartmentNameById.action");
		organizationMsg08.setCseq(BigDecimal.valueOf(8));
		organizationMsg08.setCdesc("");
		authDao.saveOrUpdate(organizationMsg08);
		Tauth organizationMsg09 = new Tauth();
		organizationMsg09.setCid("organizationMsg09");
		organizationMsg09.setTauth(organizationMsgGl);
		organizationMsg09.setCname("获取机构名称");
		organizationMsg09.setCurl("/demo/organizationAction!getAllOrganization.action");
		organizationMsg09.setCseq(BigDecimal.valueOf(9));
		organizationMsg09.setCdesc("");
		authDao.saveOrUpdate(organizationMsg09);
		Tauth organizationMsg10 = new Tauth();
		organizationMsg10.setCid("organizationMsg10");
		organizationMsg10.setTauth(organizationMsgGl);
		organizationMsg10.setCname("获取机构树");
		organizationMsg10.setCurl("/demo/organizationAction!doNotNeedSession_tree.action");
		organizationMsg10.setCseq(BigDecimal.valueOf(10));
		organizationMsg10.setCdesc("");
		authDao.saveOrUpdate(organizationMsg10);
		Tauth organizationMsg11 = new Tauth();
		organizationMsg11.setCid("organizationMsg11");
		organizationMsg11.setTauth(organizationMsgGl);
		organizationMsg11.setCname("获取递归树");
		organizationMsg11.setCurl("/demo/organizationAction!doNotNeedSession_treeRecursive.action");
		organizationMsg11.setCseq(BigDecimal.valueOf(11));
		organizationMsg11.setCdesc("");
		authDao.saveOrUpdate(organizationMsg11);

		// 人员信息管理
		Tauth personalMsgGl = new Tauth();
		personalMsgGl.setCid("personalMsgGl");
		personalMsgGl.setTauth(organizationMangerMsg);
		personalMsgGl.setCname("人员信息管理");
		personalMsgGl.setCurl("");
		personalMsgGl.setCseq(BigDecimal.valueOf(2));
		personalMsgGl.setCdesc("");
		personalMsgGl.setMenuId("personmsg");
		authDao.saveOrUpdate(personalMsgGl);

		Tauth personalMsgGl04 = new Tauth();
		personalMsgGl04.setCid("personalMsgGl04");
		personalMsgGl04.setTauth(personalMsgGl);
		personalMsgGl04.setCname("人员信息主页");
		personalMsgGl04.setCurl("/demo/personAction!datagrid.action");
		personalMsgGl04.setCseq(BigDecimal.valueOf(1));
		personalMsgGl04.setCdesc("");
		authDao.saveOrUpdate(personalMsgGl04);
		Tauth personalMsgGl05 = new Tauth();
		personalMsgGl05.setCid("personalMsgGl05");
		personalMsgGl05.setTauth(personalMsgGl);
		personalMsgGl05.setCname("添加人员信息");
		personalMsgGl05.setCurl("/demo/personAction!add.action");
		personalMsgGl05.setCseq(BigDecimal.valueOf(2));
		personalMsgGl05.setCdesc("");
		authDao.saveOrUpdate(personalMsgGl05);
		Tauth personalMsgGl06 = new Tauth();
		personalMsgGl06.setCid("personalMsgGl06");
		personalMsgGl06.setTauth(personalMsgGl);
		personalMsgGl06.setCname("编辑人员信息");
		personalMsgGl06.setCurl("/demo/personAction!edit.action");
		personalMsgGl06.setCseq(BigDecimal.valueOf(3));
		personalMsgGl06.setCdesc("");
		authDao.saveOrUpdate(personalMsgGl06);
		Tauth personalMsgGl07 = new Tauth();
		personalMsgGl07.setCid("personalMsgGl07");
		personalMsgGl07.setTauth(personalMsgGl);
		personalMsgGl07.setCname("删除人员信息");
		personalMsgGl07.setCurl("/demo/personAction!delete.action");
		personalMsgGl07.setCseq(BigDecimal.valueOf(4));
		personalMsgGl07.setCdesc("");
		authDao.saveOrUpdate(personalMsgGl07);
		Tauth personalMsgGl08 = new Tauth();
		personalMsgGl08.setCid("personalMsgGl08");
		personalMsgGl08.setTauth(personalMsgGl);
		personalMsgGl08.setCname("获取机构");
		personalMsgGl08.setCurl("/demo/personAction!getDepartmentNameById.action");
		personalMsgGl08.setCseq(BigDecimal.valueOf(5));
		personalMsgGl08.setCdesc("");
		authDao.saveOrUpdate(personalMsgGl08);
		
	}

	// 网站管理权限
	private static void initNetAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth netMsg = new Tauth();
		netMsg.setCid("netMsg");
		netMsg.setTauth(sshe);
		netMsg.setCname("网站管理");
		netMsg.setCurl("");
		netMsg.setCseq(BigDecimal.valueOf(10));
		netMsg.setCdesc("");
		netMsg.setMenuId("wzgl");
		authDao.saveOrUpdate(netMsg);

		Tauth netMsg01 = new Tauth();
		netMsg01.setCid("netMsg01");
		netMsg01.setTauth(netMsg);
		netMsg01.setCname("网站管理主页");
		netMsg01.setCurl("/demo/articleAction!article.action");
		netMsg01.setCseq(BigDecimal.valueOf(1));
		netMsg01.setCdesc("");
		authDao.saveOrUpdate(netMsg01);
		Tauth netMsg02 = new Tauth();
		netMsg02.setCid("netMsg02");
		netMsg02.setTauth(netMsg);
		netMsg02.setCname("网站添加页面");
		netMsg02.setCurl("/demo/articleAction!articleAdd.action");
		netMsg02.setCseq(BigDecimal.valueOf(2));
		netMsg02.setCdesc("");
		authDao.saveOrUpdate(netMsg02);
		Tauth netMsg03 = new Tauth();
		netMsg03.setCid("netMsg03");
		netMsg03.setTauth(netMsg);
		netMsg03.setCname("网站更新页面");
		netMsg03.setCurl("/demo/articleAction!articleEdit.action");
		netMsg03.setCseq(BigDecimal.valueOf(3));
		netMsg03.setCdesc("");
		authDao.saveOrUpdate(netMsg03);
		Tauth netMsg04 = new Tauth();
		netMsg04.setCid("netMsg04");
		netMsg04.setTauth(netMsg);
		netMsg04.setCname("网站管理列表");
		netMsg04.setCurl("/demo/articleAction!datagrid.action");
		netMsg04.setCseq(BigDecimal.valueOf(4));
		netMsg04.setCdesc("");
		authDao.saveOrUpdate(netMsg04);
		Tauth netMsg05 = new Tauth();
		netMsg05.setCid("netMsg05");
		netMsg05.setTauth(netMsg);
		netMsg05.setCname("添加网站信息");
		netMsg05.setCurl("/demo/articleAction!save.action");
		netMsg05.setCseq(BigDecimal.valueOf(5));
		netMsg05.setCdesc("");
		authDao.saveOrUpdate(netMsg05);
		Tauth netMsg06 = new Tauth();
		netMsg06.setCid("netMsg06");
		netMsg06.setTauth(netMsg);
		netMsg06.setCname("删除网站信息");
		netMsg06.setCurl("/demo/articleAction!delete.action");
		netMsg06.setCseq(BigDecimal.valueOf(6));
		netMsg06.setCdesc("");
		authDao.saveOrUpdate(netMsg06);
	}

	// 微信服务权限
	private static void initWechatAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth wechatMsg = new Tauth();
		wechatMsg.setCid("wechatMsg");
		wechatMsg.setTauth(sshe);
		wechatMsg.setCname("微信管理");
		wechatMsg.setCurl("");
		wechatMsg.setCseq(BigDecimal.valueOf(7));
		wechatMsg.setCdesc("");
		wechatMsg.setMenuId("wxfw");
		authDao.saveOrUpdate(wechatMsg);

		// 微信学车报名
		Tauth wechatSingupMsg = new Tauth();
		wechatSingupMsg.setCid("wechatSingupMsg");
		wechatSingupMsg.setTauth(wechatMsg);
		wechatSingupMsg.setCname("微信学车报名");
		wechatSingupMsg.setCurl("/demo/signUpAction!signUpList.action");
		wechatSingupMsg.setCseq(BigDecimal.valueOf(1));
		wechatSingupMsg.setCdesc("");
		authDao.saveOrUpdate(wechatSingupMsg);

		Tauth wechatSingupMsg01 = new Tauth();
		wechatSingupMsg01.setCid("wechatSingupMsg01");
		wechatSingupMsg01.setTauth(wechatSingupMsg);
		wechatSingupMsg01.setCname("添加报名学员");
		wechatSingupMsg01.setCurl("/guest/signUpAction!add.action");
		wechatSingupMsg01.setCseq(BigDecimal.valueOf(1));
		wechatSingupMsg01.setCdesc("");
		authDao.saveOrUpdate(wechatSingupMsg01);
		Tauth wechatSingupMsg02 = new Tauth();
		wechatSingupMsg02.setCid("wechatSingupMsg02");
		wechatSingupMsg02.setTauth(wechatSingupMsg);
		wechatSingupMsg02.setCname("报名学员列表");
		wechatSingupMsg02.setCurl("/demo/signUpAction!datagrid.action");
		wechatSingupMsg02.setCseq(BigDecimal.valueOf(2));
		wechatSingupMsg02.setCdesc("");
		authDao.saveOrUpdate(wechatSingupMsg02);
		Tauth wechatSingupMsg03 = new Tauth();
		wechatSingupMsg03.setCid("wechatSingupMsg03");
		wechatSingupMsg03.setTauth(wechatSingupMsg);
		wechatSingupMsg03.setCname("编辑报名学员");
		wechatSingupMsg03.setCurl("/demo/signUpAction!edit.action");
		wechatSingupMsg03.setCseq(BigDecimal.valueOf(3));
		wechatSingupMsg03.setCdesc("");
		authDao.saveOrUpdate(wechatSingupMsg03);
		Tauth wechatSingupMsg04 = new Tauth();
		wechatSingupMsg04.setCid("wechatSingupMsg04");
		wechatSingupMsg04.setTauth(wechatSingupMsg);
		wechatSingupMsg04.setCname("删除报名学员");
		wechatSingupMsg04.setCurl("/demo/signUpAction!delete.action");
		wechatSingupMsg04.setCseq(BigDecimal.valueOf(4));
		wechatSingupMsg04.setCdesc("");
		authDao.saveOrUpdate(wechatSingupMsg04);

		// 评价教练
		Tauth wechatCommentTrainerMsg = new Tauth();
		wechatCommentTrainerMsg.setCid("wechatCommentTrainerMsg");
		wechatCommentTrainerMsg.setTauth(wechatMsg);
		wechatCommentTrainerMsg.setCname("评价教练");
		wechatCommentTrainerMsg.setCurl("/demo/assessAction!assessList.action");
		wechatCommentTrainerMsg.setCseq(BigDecimal.valueOf(2));
		wechatCommentTrainerMsg.setCdesc("");
		authDao.saveOrUpdate(wechatCommentTrainerMsg);

		Tauth wechatCommentTrainerMsg01 = new Tauth();
		wechatCommentTrainerMsg01.setCid("wechatCommentTrainerMsg01");
		wechatCommentTrainerMsg01.setTauth(wechatCommentTrainerMsg);
		wechatCommentTrainerMsg01.setCname("评价教练列表");
		wechatCommentTrainerMsg01.setCurl("/demo/assessAction!datagrid.action");
		wechatCommentTrainerMsg01.setCseq(BigDecimal.valueOf(1));
		wechatCommentTrainerMsg01.setCdesc("");
		authDao.saveOrUpdate(wechatCommentTrainerMsg01);

		// 评价业务员
		Tauth wechatCommentSalesman = new Tauth();
		wechatCommentSalesman.setCid("wechatCommentSalesman");
		wechatCommentSalesman.setTauth(wechatMsg);
		wechatCommentSalesman.setCname("评价业务员");
		wechatCommentSalesman.setCurl("/demo/salesmanAction!salesmanList.action");
		wechatCommentSalesman.setCseq(BigDecimal.valueOf(3));
		wechatCommentSalesman.setCdesc("");
		authDao.saveOrUpdate(wechatCommentSalesman);

		Tauth wechatCommentSalesman001 = new Tauth();
		wechatCommentSalesman001.setCid("wechatCommentSalesman001");
		wechatCommentSalesman001.setTauth(wechatCommentSalesman);
		wechatCommentSalesman001.setCname("评价业务员列表");
		wechatCommentSalesman001.setCurl("/demo/salesmanAction!datagrid.action");
		wechatCommentSalesman001.setCseq(BigDecimal.valueOf(1));
		wechatCommentSalesman001.setCdesc("");
		authDao.saveOrUpdate(wechatCommentSalesman001);
		Tauth wechatCommentSalesman002 = new Tauth();
		wechatCommentSalesman002.setCid("wechatCommentSalesman002");
		wechatCommentSalesman002.setTauth(wechatCommentSalesman);
		wechatCommentSalesman002.setCname("更新");
		wechatCommentSalesman002.setCurl("/demo/salesmanAction!edit.action");
		wechatCommentSalesman002.setCseq(BigDecimal.valueOf(2));
		wechatCommentSalesman002.setCdesc("");
		authDao.saveOrUpdate(wechatCommentSalesman002);
		Tauth wechatCommentSalesman003 = new Tauth();
		wechatCommentSalesman003.setCid("wechatCommentSalesman003");
		wechatCommentSalesman003.setTauth(wechatCommentSalesman);
		wechatCommentSalesman003.setCname("删除");
		wechatCommentSalesman003.setCurl("/demo/salesmanAction!delete.action");
		wechatCommentSalesman003.setCseq(BigDecimal.valueOf(3));
		wechatCommentSalesman003.setCdesc("");
		authDao.saveOrUpdate(wechatCommentSalesman003);

		// 难点反馈
		Tauth wechatHardReverseMsg = new Tauth();
		wechatHardReverseMsg.setCid("wechatHardReverseMsg");
		wechatHardReverseMsg.setTauth(wechatMsg);
		wechatHardReverseMsg.setCname("难点反馈");
		wechatHardReverseMsg.setCurl("/demo/difficultyAction!difficultyList.action");
		wechatHardReverseMsg.setCseq(BigDecimal.valueOf(4));
		wechatHardReverseMsg.setCdesc("");
		authDao.saveOrUpdate(wechatHardReverseMsg);

		Tauth wechatHardReverseMsg01 = new Tauth();
		wechatHardReverseMsg01.setCid("wechatHardReverseMsg01");
		wechatHardReverseMsg01.setTauth(wechatHardReverseMsg);
		wechatHardReverseMsg01.setCname("难点反馈列表");
		wechatHardReverseMsg01.setCurl("/demo/difficultyAction!datagrid.action");
		wechatHardReverseMsg01.setCseq(BigDecimal.valueOf(1));
		wechatHardReverseMsg01.setCdesc("");
		authDao.saveOrUpdate(wechatHardReverseMsg01);

		// 微信用户管理
		Tauth wechatUserMsg = new Tauth();
		wechatUserMsg.setCid("wechatUserMsg");
		wechatUserMsg.setTauth(wechatMsg);
		wechatUserMsg.setCname("微信用户管理");
		wechatUserMsg.setCurl("/demo/weixinUserAction!userList.action");
		wechatUserMsg.setCseq(BigDecimal.valueOf(5));
		wechatUserMsg.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg);

		Tauth wechatUserMsg01 = new Tauth();
		wechatUserMsg01.setCid("wechatUserMsg01");
		wechatUserMsg01.setTauth(wechatMsg);
		wechatUserMsg01.setCname("微信用户管理");
		wechatUserMsg01.setCurl("/demo/weixinUserAction!datagrid.action");
		wechatUserMsg01.setCseq(BigDecimal.valueOf(1));
		wechatUserMsg01.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg01);
		Tauth wechatUserMsg02 = new Tauth();
		wechatUserMsg02.setCid("wechatUserMsg02");
		wechatUserMsg02.setTauth(wechatMsg);
		wechatUserMsg02.setCname("微信用户列表");
		wechatUserMsg02.setCurl("/demo/weixinUserAction!datagrid.action");
		wechatUserMsg02.setCseq(BigDecimal.valueOf(2));
		wechatUserMsg02.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg02);
		Tauth wechatUserMsg03 = new Tauth();
		wechatUserMsg03.setCid("wechatUserMsg03");
		wechatUserMsg03.setTauth(wechatMsg);
		wechatUserMsg03.setCname("微信用户主页");
		wechatUserMsg03.setCurl("/demo/weixinUserAction!userList.action");
		wechatUserMsg03.setCseq(BigDecimal.valueOf(3));
		wechatUserMsg03.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg03);
		Tauth wechatUserMsg04 = new Tauth();
		wechatUserMsg04.setCid("wechatUserMsg04");
		wechatUserMsg04.setTauth(wechatMsg);
		wechatUserMsg04.setCname("用户消息主页");
		wechatUserMsg04.setCurl("/demo/weixinUserAction!message.action");
		wechatUserMsg04.setCseq(BigDecimal.valueOf(4));
		wechatUserMsg04.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg04);
		Tauth wechatUserMsg05 = new Tauth();
		wechatUserMsg05.setCid("wechatUserMsg05");
		wechatUserMsg05.setTauth(wechatMsg);
		wechatUserMsg05.setCname("获取用户信息");
		wechatUserMsg05.setCurl("/demo/weixinUserAction!getMessageListByOpenId.action");
		wechatUserMsg05.setCseq(BigDecimal.valueOf(5));
		wechatUserMsg05.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg05);
		Tauth wechatUserMsg06 = new Tauth();
		wechatUserMsg06.setCid("wechatUserMsg06");
		wechatUserMsg06.setTauth(wechatMsg);
		wechatUserMsg06.setCname("删除用户信息");
		wechatUserMsg06.setCurl("/demo/weixinUserAction!delete.action");
		wechatUserMsg06.setCseq(BigDecimal.valueOf(6));
		wechatUserMsg06.setCdesc("");
		authDao.saveOrUpdate(wechatUserMsg06);

		// 微信自定义菜单
		Tauth wechatDiyMenuMsg = new Tauth();
		wechatDiyMenuMsg.setCid("wechatDiyMenuMsg");
		wechatDiyMenuMsg.setTauth(wechatMsg);
		wechatDiyMenuMsg.setCname("微信自定义菜单");
		wechatDiyMenuMsg.setCurl("");
		wechatDiyMenuMsg.setCseq(BigDecimal.valueOf(6));
		wechatDiyMenuMsg.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg);

		Tauth wechatDiyMenuMsg01 = new Tauth();
		wechatDiyMenuMsg01.setCid("wechatDiyMenuMsg01");
		wechatDiyMenuMsg01.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg01.setCname("微信菜单主页");
		wechatDiyMenuMsg01.setCurl("/demo/weixinMenuAction!menu.action");
		wechatDiyMenuMsg01.setCseq(BigDecimal.valueOf(1));
		wechatDiyMenuMsg01.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg01);
		Tauth wechatDiyMenuMsg02 = new Tauth();
		wechatDiyMenuMsg02.setCid("wechatDiyMenuMsg02");
		wechatDiyMenuMsg02.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg02.setCname("微信菜单列表");
		wechatDiyMenuMsg02.setCurl("/demo/weixinMenuAction!datagrid.action");
		wechatDiyMenuMsg02.setCseq(BigDecimal.valueOf(2));
		wechatDiyMenuMsg02.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg02);
		Tauth wechatDiyMenuMsg03 = new Tauth();
		wechatDiyMenuMsg03.setCid("wechatDiyMenuMsg03");
		wechatDiyMenuMsg03.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg03.setCname("编辑微信菜单");
		wechatDiyMenuMsg03.setCurl("/demo/weixinMenuAction!edit.action");
		wechatDiyMenuMsg03.setCseq(BigDecimal.valueOf(3));
		wechatDiyMenuMsg03.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg03);
		Tauth wechatDiyMenuMsg04 = new Tauth();
		wechatDiyMenuMsg04.setCid("wechatDiyMenuMsg04");
		wechatDiyMenuMsg04.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg04.setCname("自定菜单到微信");
		wechatDiyMenuMsg04.setCurl("/demo/weixinMenuAction!push.action");
		wechatDiyMenuMsg04.setCseq(BigDecimal.valueOf(4));
		wechatDiyMenuMsg04.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg04);

		Tauth wechatDiyMenuMsg05 = new Tauth();
		wechatDiyMenuMsg05.setCid("wechatDiyMenuMsg05");
		wechatDiyMenuMsg05.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg05.setCname("获取菜单列表");
		wechatDiyMenuMsg05.setCurl("/demo/weixinMenuAction!getListByCidOrderBy.action");
		wechatDiyMenuMsg05.setCseq(BigDecimal.valueOf(5));
		wechatDiyMenuMsg05.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg05);
		Tauth wechatDiyMenuMsg06 = new Tauth();
		wechatDiyMenuMsg06.setCid("wechatDiyMenuMsg06");
		wechatDiyMenuMsg06.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg06.setCname("获取菜单信息");
		wechatDiyMenuMsg06.setCurl("/demo/weixinMenuAction!findSettingsByList.action");
		wechatDiyMenuMsg06.setCseq(BigDecimal.valueOf(6));
		wechatDiyMenuMsg06.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg06);
		Tauth wechatDiyMenuMsg07 = new Tauth();
		wechatDiyMenuMsg07.setCid("wechatDiyMenuMsg07");
		wechatDiyMenuMsg07.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg07.setCname("获取菜单URL");
		wechatDiyMenuMsg07.setCurl("/demo/weixinMenuAction!getUrl.action");
		wechatDiyMenuMsg07.setCseq(BigDecimal.valueOf(7));
		wechatDiyMenuMsg07.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg07);
		Tauth wechatDiyMenuMsg08 = new Tauth();
		wechatDiyMenuMsg08.setCid("wechatDiyMenuMsg08");
		wechatDiyMenuMsg08.setTauth(wechatDiyMenuMsg);
		wechatDiyMenuMsg08.setCname("获取菜单选项");
		wechatDiyMenuMsg08.setCurl("/demo/weixinSettingsAction!comboboxData.action");
		wechatDiyMenuMsg08.setCseq(BigDecimal.valueOf(8));
		wechatDiyMenuMsg08.setCdesc("");
		authDao.saveOrUpdate(wechatDiyMenuMsg08);

	}

	// 预约管理权限
	private static void initFileMsgAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth fileMsgAuth = new Tauth();
		fileMsgAuth.setCid("fileMsgAuth");
		fileMsgAuth.setTauth(sshe);
		fileMsgAuth.setCname("档案管理");
		fileMsgAuth.setCurl("");
		fileMsgAuth.setCseq(BigDecimal.valueOf(5));
		fileMsgAuth.setCdesc("");
		authDao.saveOrUpdate(fileMsgAuth);

		// 学员档案管理权限
		Tauth studentfileMsgAuth = new Tauth();
		studentfileMsgAuth.setCid("studentfileMsgAuth");
		studentfileMsgAuth.setTauth(fileMsgAuth);
		studentfileMsgAuth.setCname("学员档案管理");
		studentfileMsgAuth.setCurl("");
		studentfileMsgAuth.setCseq(BigDecimal.valueOf(1));
		studentfileMsgAuth.setCdesc("");
		studentfileMsgAuth.setMenuId("studentDagl");
		authDao.saveOrUpdate(studentfileMsgAuth);

		Tauth studentfileMsgAuth01 = new Tauth();
		studentfileMsgAuth01.setCid("studentfileMsgAuth01");
		studentfileMsgAuth01.setTauth(studentfileMsgAuth);
		studentfileMsgAuth01.setCname("档案列表");
		studentfileMsgAuth01.setCurl("/demo/studentFileAction!datagrid.action");
		studentfileMsgAuth01.setCseq(BigDecimal.valueOf(1));
		studentfileMsgAuth01.setCdesc("");
		authDao.saveOrUpdate(studentfileMsgAuth01);

		Tauth studentfileMsgAuth04 = new Tauth();
		studentfileMsgAuth04.setCid("studentfileMsgAuth04");
		studentfileMsgAuth04.setTauth(studentfileMsgAuth);
		studentfileMsgAuth04.setCname("更新学员档案");
		studentfileMsgAuth04.setCurl("/demo/studentFileAction!edit.action");
		studentfileMsgAuth04.setCseq(BigDecimal.valueOf(2));
		studentfileMsgAuth04.setCdesc("");
		authDao.saveOrUpdate(studentfileMsgAuth04);
		Tauth studentfileMsgAuth05 = new Tauth();
		studentfileMsgAuth05.setCid("studentfileMsgAuth05");
		studentfileMsgAuth05.setTauth(studentfileMsgAuth);
		studentfileMsgAuth05.setCname("更新学员档案");
		studentfileMsgAuth05.setCurl("/demo/studentFileAction!toStudentFileDetailPage.action");
		studentfileMsgAuth05.setCseq(BigDecimal.valueOf(3));
		studentfileMsgAuth05.setCdesc("");
		authDao.saveOrUpdate(studentfileMsgAuth05);

		// 学员学时计时权限
		Tauth studentTimeCount = new Tauth();
		studentTimeCount.setCid("studentTimeCount");
		studentTimeCount.setTauth(fileMsgAuth);
		studentTimeCount.setCname("学员学时管理");
		studentTimeCount.setCurl("");
		studentTimeCount.setCseq(BigDecimal.valueOf(2));
		studentTimeCount.setCdesc("");
		studentTimeCount.setMenuId("studentXsgl");
		authDao.saveOrUpdate(studentTimeCount);

		Tauth studentTimeCount02 = new Tauth();
		studentTimeCount02.setCid("studentTimeCount02");
		studentTimeCount02.setTauth(studentTimeCount);
		studentTimeCount02.setCname("学时计时列表");
		studentTimeCount02.setCurl("/demo/timingAction!datagrid.action");
		studentTimeCount02.setCseq(BigDecimal.valueOf(1));
		studentTimeCount02.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount02);

		Tauth studentTimeCount05 = new Tauth();
		studentTimeCount05.setCid("studentTimeCount05");
		studentTimeCount05.setTauth(studentTimeCount);
		studentTimeCount05.setCname("修改学时计时");
		studentTimeCount05.setCurl("/demo/timingAction!edit.action");
		studentTimeCount05.setCseq(BigDecimal.valueOf(2));
		studentTimeCount05.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount05);

		Tauth studentTimeCount06 = new Tauth();
		studentTimeCount06.setCid("studentTimeCount06");
		studentTimeCount06.setTauth(studentTimeCount);
		studentTimeCount06.setCname("删除学时计时");
		studentTimeCount06.setCurl("/demo/timingAction!edit.action");
		studentTimeCount06.setCseq(BigDecimal.valueOf(3));
		studentTimeCount06.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount06);
		Tauth studentTimeCount10 = new Tauth();
		studentTimeCount10.setCid("studentTimeCount10");
		studentTimeCount10.setTauth(studentTimeCount);
		studentTimeCount10.setCname("学时明细列表");
		studentTimeCount10.setCurl("/demo/personalTimingAction!datagrid.action");
		studentTimeCount10.setCseq(BigDecimal.valueOf(4));
		studentTimeCount10.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount10);
		Tauth studentTimeCount11 = new Tauth();
		studentTimeCount11.setCid("studentTimeCount11");
		studentTimeCount11.setTauth(studentTimeCount);
		studentTimeCount11.setCname("添加学时明细");
		studentTimeCount11.setCurl("/demo/personalTimingAction!add.action");
		studentTimeCount11.setCseq(BigDecimal.valueOf(5));
		studentTimeCount11.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount11);
		Tauth studentTimeCount12 = new Tauth();
		studentTimeCount12.setCid("studentTimeCount12");
		studentTimeCount12.setTauth(studentTimeCount);
		studentTimeCount12.setCname("更新学时明细");
		studentTimeCount12.setCurl("/demo/personalTimingAction!edit.action");
		studentTimeCount12.setCseq(BigDecimal.valueOf(6));
		studentTimeCount12.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount12);
		Tauth studentTimeCount13 = new Tauth();
		studentTimeCount13.setCid("studentTimeCount13");
		studentTimeCount13.setTauth(studentTimeCount);
		studentTimeCount13.setCname("删除学时明细");
		studentTimeCount13.setCurl("/demo/personalTimingAction!delete.action");
		studentTimeCount13.setCseq(BigDecimal.valueOf(7));
		studentTimeCount13.setCdesc("");
		authDao.saveOrUpdate(studentTimeCount13);

		// 学员预约管理权限
		Tauth studentReservationMsg = new Tauth();
		studentReservationMsg.setCid("studentReservationMsg");
		studentReservationMsg.setTauth(fileMsgAuth);
		studentReservationMsg.setCname("学员预约管理");
		studentReservationMsg.setCurl("");
		studentReservationMsg.setCseq(BigDecimal.valueOf(3));
		studentReservationMsg.setCdesc("");
		studentReservationMsg.setMenuId("studentXsyygl");
		authDao.saveOrUpdate(studentReservationMsg);

		Tauth studentReservationMsg08 = new Tauth();
		studentReservationMsg08.setCid("studentReservationMsg08");
		studentReservationMsg08.setTauth(studentReservationMsg);
		studentReservationMsg08.setCname("学员预约列表");
		studentReservationMsg08.setCurl("/demo/reservationAction!datagrid.action");
		studentReservationMsg08.setCseq(BigDecimal.valueOf(1));
		studentReservationMsg08.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg08);
		Tauth studentReservationMsg09 = new Tauth();
		studentReservationMsg09.setCid("studentReservationMsg09");
		studentReservationMsg09.setTauth(studentReservationMsg);
		studentReservationMsg09.setCname("添加预约");
		studentReservationMsg09.setCurl("/demo/reservationAction!add.action");
		studentReservationMsg09.setCseq(BigDecimal.valueOf(2));
		studentReservationMsg09.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg09);
		Tauth studentReservationMsg10 = new Tauth();
		studentReservationMsg10.setCid("studentReservationMsg10");
		studentReservationMsg10.setTauth(studentReservationMsg);
		studentReservationMsg10.setCname("编辑教练预约");
		studentReservationMsg10.setCurl("/demo/reservationAction!editReservationDetail.action");
		studentReservationMsg10.setCseq(BigDecimal.valueOf(3));
		studentReservationMsg10.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg10);
		Tauth studentReservationMsg11 = new Tauth();
		studentReservationMsg11.setCid("studentReservationMsg11");
		studentReservationMsg11.setTauth(studentReservationMsg);
		studentReservationMsg11.setCname("更新预约");
		studentReservationMsg11.setCurl("/demo/reservationAction!edit.action");
		studentReservationMsg11.setCseq(BigDecimal.valueOf(4));
		studentReservationMsg11.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg11);
		Tauth studentReservationMsg12 = new Tauth();
		studentReservationMsg12.setCid("studentReservationMsg12");
		studentReservationMsg12.setTauth(studentReservationMsg);
		studentReservationMsg12.setCname("删除预约");
		studentReservationMsg12.setCurl("/demo/reservationAction!delete.action");
		studentReservationMsg12.setCseq(BigDecimal.valueOf(5));
		studentReservationMsg12.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg12);
		Tauth studentReservationMsg13 = new Tauth();
		studentReservationMsg13.setCid("studentReservationMsg13");
		studentReservationMsg13.setTauth(studentReservationMsg);
		studentReservationMsg13.setCname("获取预约学员信息");
		studentReservationMsg13.setCurl("/demo/reservationAction!getAllReservationStudents.action");
		studentReservationMsg13.setCseq(BigDecimal.valueOf(6));
		studentReservationMsg13.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg13);
		Tauth studentReservationMsg14 = new Tauth();
		studentReservationMsg14.setCid("studentReservationMsg14");
		studentReservationMsg14.setTauth(studentReservationMsg);
		studentReservationMsg14.setCname("获取预约教练信息");
		studentReservationMsg14.setCurl("/demo/reservationAction!getTrainerArrangeForReservation.action");
		studentReservationMsg14.setCseq(BigDecimal.valueOf(7));
		studentReservationMsg14.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg14);
		Tauth studentReservationMsg15 = new Tauth();
		studentReservationMsg15.setCid("studentReservationMsg15");
		studentReservationMsg15.setTauth(studentReservationMsg);
		studentReservationMsg15.setCname("读取单个学员");
		studentReservationMsg15.setCurl("/demo/reservationAction!getSingStudentInformation.action");
		studentReservationMsg15.setCseq(BigDecimal.valueOf(8));
		studentReservationMsg15.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg15);
		Tauth studentReservationMsg16 = new Tauth();
		studentReservationMsg16.setCid("studentReservationMsg16");
		studentReservationMsg16.setTauth(studentReservationMsg);
		studentReservationMsg16.setCname("读取学员教练");
		studentReservationMsg16.setCurl("/demo/reservationDetailAction!getStudentByTrainerId.action");
		studentReservationMsg16.setCseq(BigDecimal.valueOf(9));
		studentReservationMsg16.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg16);
		Tauth studentReservationMsg17 = new Tauth();
		studentReservationMsg17.setCid("studentReservationMsg17");
		studentReservationMsg17.setTauth(studentReservationMsg);
		studentReservationMsg17.setCname("读取教练预约明细");
		studentReservationMsg17.setCurl("/demo/reservationDetailAction!getDetailReservationByTrainerId.action");
		studentReservationMsg17.setCseq(BigDecimal.valueOf(10));
		studentReservationMsg17.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg17);
		Tauth studentReservationMsg18 = new Tauth();
		studentReservationMsg18.setCid("studentReservationMsg18");
		studentReservationMsg18.setTauth(studentReservationMsg);
		studentReservationMsg18.setCname("读取学员信息");
		studentReservationMsg18.setCurl("/demo/reservationAction!getStudentByTrainerId.action");
		studentReservationMsg18.setCseq(BigDecimal.valueOf(11));
		studentReservationMsg18.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg18);
		Tauth studentReservationMsg19 = new Tauth();
		studentReservationMsg19.setCid("studentReservationMsg19");
		studentReservationMsg19.setTauth(studentReservationMsg);
		studentReservationMsg19.setCname("读取学员信息2");
		studentReservationMsg19.setCurl("/demo/reservationAction!getTrainerByStudentId.action");
		studentReservationMsg19.setCseq(BigDecimal.valueOf(12));
		studentReservationMsg19.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg19);

		Tauth studentReservationMsg20 = new Tauth();
		studentReservationMsg20.setCid("studentReservationMsg20");
		studentReservationMsg20.setTauth(studentReservationMsg);
		studentReservationMsg20.setCname("预约明细信息");
		studentReservationMsg20.setCurl("/demo/reservationAction!getDetailReservationByTrainerId.action");
		studentReservationMsg20.setCseq(BigDecimal.valueOf(13));
		studentReservationMsg20.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg20);
		Tauth studentReservationMsg22 = new Tauth();
		studentReservationMsg22.setCid("studentReservationMsg22");
		studentReservationMsg22.setTauth(studentReservationMsg);
		studentReservationMsg22.setCname("取消预约");
		studentReservationMsg22.setCurl("/demo/reservationAction!cancelReservation.action");
		studentReservationMsg22.setCseq(BigDecimal.valueOf(14));
		studentReservationMsg22.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg22);
		Tauth studentReservationMsg23 = new Tauth();
		studentReservationMsg23.setCid("studentReservationMsg23");
		studentReservationMsg23.setTauth(studentReservationMsg);
		studentReservationMsg23.setCname("异步校验");
		studentReservationMsg23.setCurl("/demo/reservationAction!reservationExit.action");
		studentReservationMsg23.setCseq(BigDecimal.valueOf(15));
		studentReservationMsg23.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg23);
		Tauth studentReservationMsg24 = new Tauth();
		studentReservationMsg24.setCid("studentReservationMsg24");
		studentReservationMsg24.setTauth(studentReservationMsg);
		studentReservationMsg24.setCname("确认预约");
		studentReservationMsg24.setCurl("/demo/reservationAction!confirmReservation.action");
		studentReservationMsg24.setCseq(BigDecimal.valueOf(16));
		studentReservationMsg24.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg24);
		Tauth studentReservationMsg25 = new Tauth();
		studentReservationMsg25.setCid("studentReservationMsg25");
		studentReservationMsg25.setTauth(studentReservationMsg);
		studentReservationMsg25.setCname("两小时预约");
		studentReservationMsg25.setCurl("/demo/reservationAction!checkTotalByDate.action");
		studentReservationMsg25.setCseq(BigDecimal.valueOf(17));
		studentReservationMsg25.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg25);
		Tauth studentReservationMsg26 = new Tauth();
		studentReservationMsg26.setCid("studentReservationMsg26");
		studentReservationMsg26.setTauth(studentReservationMsg);
		studentReservationMsg26.setCname("获取电车模拟教练");
		studentReservationMsg26.setCurl("/demo/reservationAction!getElectricAndSimulations.action");
		studentReservationMsg26.setCseq(BigDecimal.valueOf(18));
		studentReservationMsg26.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg26);
		Tauth studentReservationMsg27 = new Tauth();
		studentReservationMsg27.setCid("studentReservationMsg27");
		studentReservationMsg27.setTauth(studentReservationMsg);
		studentReservationMsg27.setCname("获取C2预约车");
		studentReservationMsg27.setCurl("/demo/reservationAction!getAutocarForReservation.action");
		studentReservationMsg27.setCseq(BigDecimal.valueOf(19));
		studentReservationMsg27.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg27);
		Tauth studentReservationMsg29 = new Tauth();
		studentReservationMsg29.setCid("studentReservationMsg29");
		studentReservationMsg29.setTauth(studentReservationMsg);
		studentReservationMsg29.setCname("查找预约车");
		studentReservationMsg29.setCurl("/demo/reservationAction!checkAutocarFree.action");
		studentReservationMsg29.setCseq(BigDecimal.valueOf(20));
		studentReservationMsg29.setCdesc("");
		authDao.saveOrUpdate(studentReservationMsg29);

		// 科目学时安排权限
		Tauth subjectTimeArrageMsg = new Tauth();
		subjectTimeArrageMsg.setCid("subjectTimeArrageMsg");
		subjectTimeArrageMsg.setTauth(fileMsgAuth);
		subjectTimeArrageMsg.setCname("科目学员安排");
		subjectTimeArrageMsg.setCurl("");
		subjectTimeArrageMsg.setCseq(BigDecimal.valueOf(6));
		subjectTimeArrageMsg.setCdesc("");
		subjectTimeArrageMsg.setMenuId("timercard");
		authDao.saveOrUpdate(subjectTimeArrageMsg);

		Tauth subjectTimeArrageMsg01 = new Tauth();
		subjectTimeArrageMsg01.setCid("subjectTimeArrageMsg01");
		subjectTimeArrageMsg01.setTauth(subjectTimeArrageMsg);
		subjectTimeArrageMsg01.setCname("科目学员安排表");
		subjectTimeArrageMsg01.setCurl("/demo/timerCardAction!timerCard.action");
		subjectTimeArrageMsg01.setCseq(BigDecimal.valueOf(1));
		subjectTimeArrageMsg01.setCdesc("");
		authDao.saveOrUpdate(subjectTimeArrageMsg01);
		Tauth subjectTimeArrageMsg02 = new Tauth();
		subjectTimeArrageMsg02.setCid("subjectTimeArrageMsg02");
		subjectTimeArrageMsg02.setTauth(subjectTimeArrageMsg);
		subjectTimeArrageMsg02.setCname("科目安排列表");
		subjectTimeArrageMsg02.setCurl("/demo/timerCardAction!datagrid.action");
		subjectTimeArrageMsg02.setCseq(BigDecimal.valueOf(2));
		subjectTimeArrageMsg02.setCdesc("");
		authDao.saveOrUpdate(subjectTimeArrageMsg02);
		Tauth subjectTimeArrageMsg03 = new Tauth();
		subjectTimeArrageMsg03.setCid("subjectTimeArrageMsg03");
		subjectTimeArrageMsg03.setTauth(subjectTimeArrageMsg);
		subjectTimeArrageMsg03.setCname("添加科目安排");
		subjectTimeArrageMsg03.setCurl("/demo/timerCardAction!add.action");
		subjectTimeArrageMsg03.setCseq(BigDecimal.valueOf(3));
		subjectTimeArrageMsg03.setCdesc("");
		authDao.saveOrUpdate(subjectTimeArrageMsg03);
		Tauth subjectTimeArrageMsg04 = new Tauth();
		subjectTimeArrageMsg04.setCid("subjectTimeArrageMsg04");
		subjectTimeArrageMsg04.setTauth(subjectTimeArrageMsg);
		subjectTimeArrageMsg04.setCname("更新科目安排");
		subjectTimeArrageMsg04.setCurl("/demo/timerCardAction!edit.action");
		subjectTimeArrageMsg04.setCseq(BigDecimal.valueOf(4));
		subjectTimeArrageMsg04.setCdesc("");
		authDao.saveOrUpdate(subjectTimeArrageMsg04);
		Tauth subjectTimeArrageMsg05 = new Tauth();
		subjectTimeArrageMsg05.setCid("subjectTimeArrageMsg05");
		subjectTimeArrageMsg05.setTauth(subjectTimeArrageMsg);
		subjectTimeArrageMsg05.setCname("删除科目安排");
		subjectTimeArrageMsg05.setCurl("/demo/timerCardAction!delete.action");
		subjectTimeArrageMsg05.setCseq(BigDecimal.valueOf(5));
		subjectTimeArrageMsg05.setCdesc("");
		authDao.saveOrUpdate(subjectTimeArrageMsg05);

	}

	// 公共资源
	private static void initAuthLog(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth logMsg = new Tauth();
		logMsg.setCid("logMsg");
		logMsg.setTauth(sshe);
		logMsg.setCname("日志管理");
		logMsg.setCurl("");
		logMsg.setCseq(BigDecimal.valueOf(13));
		logMsg.setCdesc("");
		logMsg.setMenuId("rzgl");
		authDao.saveOrUpdate(logMsg);

		Tauth logMsg02 = new Tauth();
		logMsg02.setCid("logMsg02");
		logMsg02.setTauth(logMsg);
		logMsg02.setCname("日志列表");
		logMsg02.setCurl("/demo/logAction!datagrid.action");
		logMsg02.setCseq(BigDecimal.valueOf(1));
		logMsg02.setCdesc("icon-filter");
		authDao.saveOrUpdate(logMsg02);

	}

	// 公共资源
	private static void initCommon(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth commonMsg = new Tauth();
		commonMsg.setCid("commonMsg");
		commonMsg.setTauth(sshe);
		commonMsg.setCname("公共资源");
		commonMsg.setCurl("");
		commonMsg.setCseq(BigDecimal.valueOf(18));
		commonMsg.setCdesc("");
		authDao.saveOrUpdate(commonMsg);

		Tauth commonMsg01 = new Tauth();
		commonMsg01.setCid("commonMsg01");
		commonMsg01.setTauth(commonMsg);
		commonMsg01.setCname("学员校区标识");
		commonMsg01.setCurl("/demo/studentAnalysisAction!getStudentBySchoolArea.action");
		commonMsg01.setCseq(BigDecimal.valueOf(1));
		commonMsg01.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg01);

		Tauth commonMsg02 = new Tauth();
		commonMsg02.setCid("commonMsg02");
		commonMsg02.setTauth(commonMsg);
		commonMsg02.setCname("获取学员总数");
		commonMsg02.setCurl("/demo/studentAnalysisAction!getAllStudentCount.action");
		commonMsg02.setCseq(BigDecimal.valueOf(2));
		commonMsg02.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg02);

		Tauth commonMsg03 = new Tauth();
		commonMsg03.setCid("commonMsg03");
		commonMsg03.setTauth(commonMsg);
		commonMsg03.setCname("用户校区标识");
		commonMsg03.setCurl("/demo/studentAction!getSchoolArea.action");
		commonMsg03.setCseq(BigDecimal.valueOf(3));
		commonMsg03.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg03);

		Tauth commonMsg04 = new Tauth();
		commonMsg04.setCid("commonMsg04");
		commonMsg04.setTauth(commonMsg);
		commonMsg04.setCname("读取考试科目");
		commonMsg04.setCurl("/demo/studentAnalysisAction!getSubjectCountBySchoolId.action");
		commonMsg04.setCseq(BigDecimal.valueOf(4));
		commonMsg04.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg04);

		Tauth commonMsg05 = new Tauth();
		commonMsg05.setCid("commonMsg05");
		commonMsg05.setTauth(commonMsg);
		commonMsg05.setCname("读取文明考试");
		commonMsg05.setCurl("/demo/studentAnalysisAction!getSubjectFourCountBySchoolId.action");
		commonMsg05.setCseq(BigDecimal.valueOf(5));
		commonMsg05.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg05);

		Tauth commonMsg06 = new Tauth();
		commonMsg06.setCid("commonMsg06");
		commonMsg06.setTauth(commonMsg);
		commonMsg06.setCname("读取路考信息");
		commonMsg06.setCurl("/demo/studentAnalysisAction!getSubjectThreeCountBySchoolId.action");
		commonMsg06.setCseq(BigDecimal.valueOf(6));
		commonMsg06.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg06);

		Tauth commonMsg07 = new Tauth();
		commonMsg07.setCid("commonMsg07");
		commonMsg07.setTauth(commonMsg);
		commonMsg07.setCname("读取五项信息");
		commonMsg07.setCurl("/demo/studentAnalysisAction!getSubjectTwoCountBySchoolId.action");
		commonMsg07.setCseq(BigDecimal.valueOf(7));
		commonMsg07.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg07);

		Tauth commonMsg08 = new Tauth();
		commonMsg08.setCid("commonMsg08");
		commonMsg08.setTauth(commonMsg);
		commonMsg08.setCname("单个学员查找");
		commonMsg08.setCurl("/demo/studentAnalysisAction!getSignupStudent.action");
		commonMsg08.setCseq(BigDecimal.valueOf(8));
		commonMsg08.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg08);

		Tauth commonMsg09 = new Tauth();
		commonMsg09.setCid("commonMsg09");
		commonMsg09.setTauth(commonMsg);
		commonMsg09.setCname("分类统计学员");
		commonMsg09.setCurl("/demo/studentAnalysisAction!getStudentCountByDriverType.action");
		commonMsg09.setCseq(BigDecimal.valueOf(9));
		commonMsg09.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg09);

		Tauth commonMsg10 = new Tauth();
		commonMsg10.setCid("commonMsg10");
		commonMsg10.setTauth(commonMsg);
		commonMsg10.setCname("分类统计车辆");
		commonMsg10.setCurl("/demo/studentAnalysisAction!getCarBySchoolArea.action");
		commonMsg10.setCseq(BigDecimal.valueOf(10));
		commonMsg10.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg10);

		Tauth commonMsg11 = new Tauth();
		commonMsg11.setCid("commonMsg11");
		commonMsg11.setTauth(commonMsg);
		commonMsg11.setCname("统计车辆总数");
		commonMsg11.setCurl("/demo/studentAnalysisAction!getAllCarCount.action");
		commonMsg11.setCseq(BigDecimal.valueOf(11));
		commonMsg11.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg11);

		Tauth commonMsg12 = new Tauth();
		commonMsg12.setCid("commonMsg12");
		commonMsg12.setTauth(commonMsg);
		commonMsg12.setCname("统计教练总数");
		commonMsg12.setCurl("/demo/studentAnalysisAction!getAllTrainerCount.action");
		commonMsg12.setCseq(BigDecimal.valueOf(12));
		commonMsg12.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg12);

		Tauth commonMsg13 = new Tauth();
		commonMsg13.setCid("commonMsg13");
		commonMsg13.setTauth(commonMsg);
		commonMsg13.setCname("分类统计教练");
		commonMsg13.setCurl("/demo/studentAnalysisAction!getTrainerBySchoolArea.action");
		commonMsg13.setCseq(BigDecimal.valueOf(13));
		commonMsg13.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg13);

		Tauth commonMsg14 = new Tauth();
		commonMsg14.setCid("commonMsg14");
		commonMsg14.setTauth(commonMsg);
		commonMsg14.setCname("分类统计教练2");
		commonMsg14.setCurl("/demo/studentAnalysisAction!getTrainerCountByDriverType.action");
		commonMsg14.setCseq(BigDecimal.valueOf(14));
		commonMsg14.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg14);

		Tauth commonMsg16 = new Tauth();
		commonMsg16.setCid("commonMsg16");
		commonMsg16.setTauth(commonMsg);
		commonMsg16.setCname("读取机构信息");
		commonMsg16.setCurl("/demo/organizationAction!getAllOrganization.action");
		commonMsg16.setCseq(BigDecimal.valueOf(16));
		commonMsg16.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg16);

		Tauth commonMsg17 = new Tauth();
		commonMsg17.setCid("commonMsg17");
		commonMsg17.setTauth(commonMsg);
		commonMsg17.setCname("校验身份证");
		commonMsg17.setCurl("/demo/studentAction!identityIdExistOrNot.action");
		commonMsg17.setCseq(BigDecimal.valueOf(17));
		commonMsg17.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg17);

		Tauth commonMsg18 = new Tauth();
		commonMsg18.setCid("commonMsg18");
		commonMsg18.setTauth(commonMsg);
		commonMsg18.setCname("校验身份证");
		commonMsg18.setCurl("/demo/trainerAction!identityIdExistOrNot.action");
		commonMsg18.setCseq(BigDecimal.valueOf(18));
		commonMsg18.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg18);
		Tauth commonMsg19 = new Tauth();
		commonMsg19.setCid("commonMsg19");
		commonMsg19.setTauth(commonMsg);
		commonMsg19.setCname("校验身份证");
		commonMsg19.setCurl("/demo/personAction!identityIdExistOrNot.action");
		commonMsg19.setCseq(BigDecimal.valueOf(19));
		commonMsg19.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg19);
		Tauth commonMsg20 = new Tauth();
		commonMsg20.setCid("commonMsg20");
		commonMsg20.setTauth(commonMsg);
		commonMsg20.setCname("获取车牌号");
		commonMsg20.setCurl("/demo/carAction!getAllCarsForCombobox.action");
		commonMsg20.setCseq(BigDecimal.valueOf(20));
		commonMsg20.setCdesc("icon-filter");
		authDao.saveOrUpdate(commonMsg20);
	}

	// 教练管理权限初始化
	private static void initTrainerAuth(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth trainerMsg = new Tauth();
		trainerMsg.setCid("trainerMsg");
		trainerMsg.setTauth(sshe);
		trainerMsg.setCname("教练管理");
		trainerMsg.setCurl("");
		trainerMsg.setCseq(BigDecimal.valueOf(2));
		trainerMsg.setCdesc("");
		authDao.saveOrUpdate(trainerMsg);

		Tauth trainerMangerMsg = new Tauth();
		trainerMangerMsg.setCid("trainerMangerMsg");
		trainerMangerMsg.setTauth(trainerMsg);
		trainerMangerMsg.setCname("教练信息管理");
		trainerMangerMsg.setCurl("");
		trainerMangerMsg.setCseq(BigDecimal.valueOf(1));
		trainerMangerMsg.setCdesc("");
		trainerMangerMsg.setMenuId("newtrainer");
		authDao.saveOrUpdate(trainerMangerMsg);

		Tauth trainerMangerMsg04 = new Tauth();
		trainerMangerMsg04.setCid("trainerMangerMsg04");
		trainerMangerMsg04.setTauth(trainerMangerMsg);
		trainerMangerMsg04.setCname("教练信息表列");
		trainerMangerMsg04.setCurl("/demo/trainerAction!datagrid.action");
		trainerMangerMsg04.setCseq(BigDecimal.valueOf(1));
		trainerMangerMsg04.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg04);
		Tauth trainerMangerMsg05 = new Tauth();
		trainerMangerMsg05.setCid("trainerMangerMsg05");
		trainerMangerMsg05.setTauth(trainerMangerMsg);
		trainerMangerMsg05.setCname("添加教练");
		trainerMangerMsg05.setCurl("/demo/trainerAction!add.action");
		trainerMangerMsg05.setCseq(BigDecimal.valueOf(2));
		trainerMangerMsg05.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg05);
		Tauth trainerMangerMsg06 = new Tauth();
		trainerMangerMsg06.setCid("trainerMangerMsg06");
		trainerMangerMsg06.setTauth(trainerMangerMsg);
		trainerMangerMsg06.setCname("更新教练");
		trainerMangerMsg06.setCurl("/demo/trainerAction!edit.action");
		trainerMangerMsg06.setCseq(BigDecimal.valueOf(3));
		trainerMangerMsg06.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg06);
		Tauth trainerMangerMsg07 = new Tauth();
		trainerMangerMsg07.setCid("trainerMangerMsg07");
		trainerMangerMsg07.setTauth(trainerMangerMsg);
		trainerMangerMsg07.setCname("教练信息表列");
		trainerMangerMsg07.setCurl("/demo/trainerAction!delete.action");
		trainerMangerMsg07.setCseq(BigDecimal.valueOf(4));
		trainerMangerMsg07.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg07);
		Tauth trainerMangerMsg08 = new Tauth();
		trainerMangerMsg08.setCid("trainerMangerMsg08");
		trainerMangerMsg08.setTauth(trainerMangerMsg);
		trainerMangerMsg08.setCname("获取教练编号");
		trainerMangerMsg08.setCurl("/demo/trainerAction!getMaxTrainerNo.action");
		trainerMangerMsg08.setCseq(BigDecimal.valueOf(5));
		trainerMangerMsg08.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg08);
		Tauth trainerMangerMsg10 = new Tauth();
		trainerMangerMsg10.setCid("trainerMangerMsg10");
		trainerMangerMsg10.setTauth(trainerMangerMsg);
		trainerMangerMsg10.setCname("导入教练信息");
		trainerMangerMsg10.setCurl("/demo/trainerAction!importTrainerDatas.action");
		trainerMangerMsg10.setCseq(BigDecimal.valueOf(6));
		trainerMangerMsg10.setCdesc("");
		authDao.saveOrUpdate(trainerMangerMsg10);

		Tauth trainerArrangeMsg = new Tauth();
		trainerArrangeMsg.setCid("trainerArrangeMsg");
		trainerArrangeMsg.setTauth(trainerMsg);
		trainerArrangeMsg.setCname("教练排班");
		trainerArrangeMsg.setCurl("");
		trainerArrangeMsg.setCseq(BigDecimal.valueOf(2));
		trainerArrangeMsg.setCdesc("");
		trainerArrangeMsg.setMenuId("workarrange");
		authDao.saveOrUpdate(trainerArrangeMsg);
		Tauth trainerArrangeMsg05 = new Tauth();
		trainerArrangeMsg05.setCid("trainerArrangeMsg05");
		trainerArrangeMsg05.setTauth(trainerArrangeMsg);
		trainerArrangeMsg05.setCname("添加排班");
		trainerArrangeMsg05.setCurl("/demo/trainerArrangeAction!add.action");
		trainerArrangeMsg05.setCseq(BigDecimal.valueOf(1));
		trainerArrangeMsg05.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg05);
		Tauth trainerArrangeMsg06 = new Tauth();
		trainerArrangeMsg06.setCid("trainerArrangeMsg06");
		trainerArrangeMsg06.setTauth(trainerArrangeMsg);
		trainerArrangeMsg06.setCname("更新排班");
		trainerArrangeMsg06.setCurl("/demo/trainerArrangeAction!arrangeDetailChange.action");
		trainerArrangeMsg06.setCseq(BigDecimal.valueOf(2));
		trainerArrangeMsg06.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg06);
		Tauth trainerArrangeMsg07 = new Tauth();
		trainerArrangeMsg07.setCid("trainerArrangeMsg07");
		trainerArrangeMsg07.setTauth(trainerArrangeMsg);
		trainerArrangeMsg07.setCname("删除排班");
		trainerArrangeMsg07.setCurl("/demo/trainerArrangeAction!delete.action");
		trainerArrangeMsg07.setCseq(BigDecimal.valueOf(3));
		trainerArrangeMsg07.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg07);
		Tauth trainerArrangeMsg08 = new Tauth();
		trainerArrangeMsg08.setCid("trainerArrangeMsg08");
		trainerArrangeMsg08.setTauth(trainerArrangeMsg);
		trainerArrangeMsg08.setCname("获取教练名");
		trainerArrangeMsg08.setCurl("/demo/trainerArrangeAction!getTrainterNames.action");
		trainerArrangeMsg08.setCseq(BigDecimal.valueOf(4));
		trainerArrangeMsg08.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg08);
		Tauth trainerArrangeMsg09 = new Tauth();
		trainerArrangeMsg09.setCid("trainerArrangeMsg09");
		trainerArrangeMsg09.setTauth(trainerArrangeMsg);
		trainerArrangeMsg09.setCname("获取教练信息");
		trainerArrangeMsg09.setCurl("/demo/trainerArrangeAction!getTrainerDatabyType.action");
		trainerArrangeMsg09.setCseq(BigDecimal.valueOf(5));
		trainerArrangeMsg09.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg09);

		Tauth trainerArrangeMsg11 = new Tauth();
		trainerArrangeMsg11.setCid("trainerArrangeMsg11");
		trainerArrangeMsg11.setTauth(trainerArrangeMsg);
		trainerArrangeMsg11.setCname("排班明细列表");
		trainerArrangeMsg11.setCurl("/demo/trainerArrangeAction!detailDatagrid.action");
		trainerArrangeMsg11.setCseq(BigDecimal.valueOf(6));
		trainerArrangeMsg11.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg11);
		Tauth trainerArrangeMsg12 = new Tauth();
		trainerArrangeMsg12.setCid("trainerArrangeMsg12");
		trainerArrangeMsg12.setTauth(trainerArrangeMsg);
		trainerArrangeMsg12.setCname("排班排班条件");
		trainerArrangeMsg12.setCurl("/demo/trainerArrangeAction!trainerArrangeOrNot.action");
		trainerArrangeMsg12.setCseq(BigDecimal.valueOf(7));
		trainerArrangeMsg12.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg12);
		Tauth trainerArrangeMsg14 = new Tauth();
		trainerArrangeMsg14.setCid("trainerArrangeMsg14");
		trainerArrangeMsg14.setTauth(trainerArrangeMsg);
		trainerArrangeMsg14.setCname("教练排班明细");
		trainerArrangeMsg14.setCurl("/demo/trainerArrangeAction!getDetailArrangeByTrainerId.action");
		trainerArrangeMsg14.setCseq(BigDecimal.valueOf(8));
		trainerArrangeMsg14.setCdesc("");
		authDao.saveOrUpdate(trainerArrangeMsg14);

		Tauth trainerReservationMsg = new Tauth();
		trainerReservationMsg.setCid("trainerReservationMsg");
		trainerReservationMsg.setTauth(trainerMsg);
		trainerReservationMsg.setCname("教练预约");
		trainerReservationMsg.setCurl("");
		trainerReservationMsg.setCseq(BigDecimal.valueOf(3));
		trainerReservationMsg.setCdesc("");
		trainerReservationMsg.setMenuId("traienrDetail");
		authDao.saveOrUpdate(trainerReservationMsg);
		Tauth trainerReservationMsg02 = new Tauth();
		trainerReservationMsg02.setCid("trainerReservationMsg02");
		trainerReservationMsg02.setTauth(trainerReservationMsg);
		trainerReservationMsg02.setCname("预约列表");
		trainerReservationMsg02.setCurl("/demo/trainerReservationAction!datagrid.action");
		trainerReservationMsg02.setCseq(BigDecimal.valueOf(1));
		trainerReservationMsg02.setCdesc("");
		authDao.saveOrUpdate(trainerReservationMsg02);
		Tauth trainerReservationMsg03 = new Tauth();
		trainerReservationMsg03.setCid("trainerReservationMsg03");
		trainerReservationMsg03.setTauth(trainerReservationMsg);
		trainerReservationMsg03.setCname("添加预约");
		trainerReservationMsg03.setCurl("/demo/trainerReservationAction!add.action");
		trainerReservationMsg03.setCseq(BigDecimal.valueOf(2));
		trainerReservationMsg03.setCdesc("");
		authDao.saveOrUpdate(trainerReservationMsg03);
		Tauth trainerReservationMsg04 = new Tauth();
		trainerReservationMsg04.setCid("trainerReservationMsg04");
		trainerReservationMsg04.setTauth(trainerReservationMsg);
		trainerReservationMsg04.setCname("更新预约");
		trainerReservationMsg04.setCurl("/demo/trainerReservationAction!edit.action");
		trainerReservationMsg04.setCseq(BigDecimal.valueOf(3));
		trainerReservationMsg04.setCdesc("");
		authDao.saveOrUpdate(trainerReservationMsg04);
		Tauth trainerReservationMsg05 = new Tauth();
		trainerReservationMsg05.setCid("trainerReservationMsg05");
		trainerReservationMsg05.setTauth(trainerReservationMsg);
		trainerReservationMsg05.setCname("删除预约");
		trainerReservationMsg05.setCurl("/demo/trainerReservationAction!delete.action");
		trainerReservationMsg05.setCseq(BigDecimal.valueOf(4));
		trainerReservationMsg05.setCdesc("");
		authDao.saveOrUpdate(trainerReservationMsg05);
		Tauth trainerReservationMsg06 = new Tauth();
		trainerReservationMsg06.setCid("trainerReservationMsg06");
		trainerReservationMsg06.setTauth(trainerReservationMsg);
		trainerReservationMsg06.setCname("删除预约");
		trainerReservationMsg06.setCurl("/demo/reservationDetailAction!datagrid.action");
		trainerReservationMsg06.setCseq(BigDecimal.valueOf(5));
		trainerReservationMsg06.setCdesc("");
		authDao.saveOrUpdate(trainerReservationMsg06);

		Tauth commentStudentMsg = new Tauth();
		commentStudentMsg.setCid("commentStudentMsg");
		commentStudentMsg.setTauth(trainerMsg);
		commentStudentMsg.setCname("点评学员");
		commentStudentMsg.setCurl("");
		commentStudentMsg.setCseq(BigDecimal.valueOf(5));
		commentStudentMsg.setCdesc("");
		commentStudentMsg.setMenuId("commentStudent");
		authDao.saveOrUpdate(commentStudentMsg);
		Tauth commentStudentMsg01 = new Tauth();
		commentStudentMsg01.setCid("commentStudentMsg01");
		commentStudentMsg01.setTauth(commentStudentMsg);
		commentStudentMsg01.setCname("点评页面");
		commentStudentMsg01.setCurl("/demo/commentStudentAction!studentList.action");
		commentStudentMsg01.setCseq(BigDecimal.valueOf(1));
		commentStudentMsg01.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg01);
		Tauth commentStudentMsg02 = new Tauth();
		commentStudentMsg02.setCid("commentStudentMsg02");
		commentStudentMsg02.setTauth(commentStudentMsg);
		commentStudentMsg02.setCname("读取学员");
		commentStudentMsg02.setCurl("/demo/commentStudentAction!getStudentDatagrid.action");
		commentStudentMsg02.setCseq(BigDecimal.valueOf(2));
		commentStudentMsg02.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg02);
		Tauth commentStudentMsg03 = new Tauth();
		commentStudentMsg03.setCid("commentStudentMsg03");
		commentStudentMsg03.setTauth(commentStudentMsg);
		commentStudentMsg03.setCname("添加点评");
		commentStudentMsg03.setCurl("/demo/commentStudentAction!addComment.action");
		commentStudentMsg03.setCseq(BigDecimal.valueOf(3));
		commentStudentMsg03.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg01);
		Tauth commentStudentMsg04 = new Tauth();
		commentStudentMsg04.setCid("commentStudentMsg04");
		commentStudentMsg04.setTauth(commentStudentMsg);
		commentStudentMsg04.setCname("点评列表");
		commentStudentMsg04.setCurl("/demo/commentStudentAction!datagrid.action");
		commentStudentMsg04.setCseq(BigDecimal.valueOf(4));
		commentStudentMsg04.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg04);
		Tauth commentStudentMsg05 = new Tauth();
		commentStudentMsg05.setCid("commentStudentMsg05");
		commentStudentMsg05.setTauth(commentStudentMsg);
		commentStudentMsg05.setCname("更新点评");
		commentStudentMsg05.setCurl("/demo/commentStudentAction!edit.action");
		commentStudentMsg05.setCseq(BigDecimal.valueOf(5));
		commentStudentMsg05.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg05);
		Tauth commentStudentMsg06 = new Tauth();
		commentStudentMsg06.setCid("commentStudentMsg06");
		commentStudentMsg06.setTauth(commentStudentMsg);
		commentStudentMsg06.setCname("删除点评");
		commentStudentMsg06.setCurl("/demo/commentStudentAction!delete.action");
		commentStudentMsg06.setCseq(BigDecimal.valueOf(6));
		commentStudentMsg06.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg06);
		Tauth commentStudentMsg07 = new Tauth();
		commentStudentMsg07.setCid("commentStudentMsg07");
		commentStudentMsg07.setTauth(commentStudentMsg);
		commentStudentMsg07.setCname("获取URL");
		commentStudentMsg07.setCurl("/demo/commentStudentAction!getUrl.action");
		commentStudentMsg07.setCseq(BigDecimal.valueOf(7));
		commentStudentMsg07.setCdesc("");
		authDao.saveOrUpdate(commentStudentMsg07);
	}

	// 车辆管理权限初始化
	private static void initCarManger(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth carMsg = new Tauth();
		carMsg.setCid("carMsg");
		carMsg.setTauth(sshe);
		carMsg.setCname("车辆管理");
		carMsg.setCurl("");
		carMsg.setCseq(BigDecimal.valueOf(3));
		carMsg.setCdesc("");
		carMsg.setMenuId("carmanager");
		authDao.saveOrUpdate(carMsg);

		Tauth carMangerMsg = new Tauth();
		carMangerMsg.setCid("carMangerMsg");
		carMangerMsg.setTauth(carMsg);
		carMangerMsg.setCname("车辆信息管理");
		carMangerMsg.setCurl("");
		carMangerMsg.setCseq(BigDecimal.valueOf(1));
		carMangerMsg.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg);

		Tauth carMangerMsg01 = new Tauth();
		carMangerMsg01.setCid("carMangerMsg01");
		carMangerMsg01.setTauth(carMangerMsg);
		carMangerMsg01.setCname("车辆信息主页");
		carMangerMsg01.setCurl("/demo/carAction!car.action");
		carMangerMsg01.setCseq(BigDecimal.valueOf(1));
		carMangerMsg01.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg01);
		Tauth carMangerMsg04 = new Tauth();
		carMangerMsg04.setCid("carMangerMsg04");
		carMangerMsg04.setTauth(carMangerMsg);
		carMangerMsg04.setCname("车辆信息列表");
		carMangerMsg04.setCurl("/demo/carAction!datagrid.action");
		carMangerMsg04.setCseq(BigDecimal.valueOf(2));
		carMangerMsg04.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg04);
		Tauth carMangerMsg05 = new Tauth();
		carMangerMsg05.setCid("carMangerMsg05");
		carMangerMsg05.setTauth(carMangerMsg);
		carMangerMsg05.setCname("添加车辆信息");
		carMangerMsg05.setCurl("/demo/carAction!add.action");
		carMangerMsg05.setCseq(BigDecimal.valueOf(3));
		carMangerMsg05.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg05);
		Tauth carMangerMsg06 = new Tauth();
		carMangerMsg06.setCid("carMangerMsg06");
		carMangerMsg06.setTauth(carMangerMsg);
		carMangerMsg06.setCname("更新车辆信息");
		carMangerMsg06.setCurl("/demo/carAction!edit.action");
		carMangerMsg06.setCseq(BigDecimal.valueOf(4));
		carMangerMsg06.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg06);
		Tauth carMangerMsg07 = new Tauth();
		carMangerMsg07.setCid("carMangerMsg07");
		carMangerMsg07.setTauth(carMangerMsg);
		carMangerMsg07.setCname("删除车辆");
		carMangerMsg07.setCurl("/demo/carAction!delete.action");
		carMangerMsg07.setCseq(BigDecimal.valueOf(5));
		carMangerMsg07.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg07);
		Tauth carMangerMsg09 = new Tauth();
		carMangerMsg09.setCid("carMangerMsg09");
		carMangerMsg09.setTauth(carMangerMsg);
		carMangerMsg09.setCname("导入车辆");
		carMangerMsg09.setCurl("/demo/carAction!importCarDatas.action");
		carMangerMsg09.setCseq(BigDecimal.valueOf(6));
		carMangerMsg09.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg09);
		Tauth carMangerMsg10 = new Tauth();
		carMangerMsg10.setCid("carMangerMsg10");
		carMangerMsg10.setTauth(carMangerMsg);
		carMangerMsg10.setCname("导入车辆");
		carMangerMsg10.setCurl("/demo/carAction!exportCarDatas.action");
		carMangerMsg10.setCseq(BigDecimal.valueOf(7));
		carMangerMsg10.setCdesc("");
		authDao.saveOrUpdate(carMangerMsg10);

		Tauth carUsingMsg = new Tauth();
		carUsingMsg.setCid("carUsingMsg");
		carUsingMsg.setTauth(carMsg);
		carUsingMsg.setCname("车辆使用情况");
		carUsingMsg.setCurl("");
		carUsingMsg.setCseq(BigDecimal.valueOf(2));
		carUsingMsg.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg);

		Tauth carUsingMsg01 = new Tauth();
		carUsingMsg01.setCid("carUsingMsg01");
		carUsingMsg01.setTauth(carUsingMsg);
		carUsingMsg01.setCname("查询信息");
		carUsingMsg01.setCurl("/demo/usingCarAction!datagrid.action");
		carUsingMsg01.setCseq(BigDecimal.valueOf(1));
		carUsingMsg01.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg01);
		Tauth carUsingMsg04 = new Tauth();
		carUsingMsg04.setCid("carUsingMsg04");
		carUsingMsg04.setTauth(carUsingMsg);
		carUsingMsg04.setCname("使用明细列表");
		carUsingMsg04.setCurl("/demo/usingCarAction!getCarusingDetailByCarId.action");
		carUsingMsg04.setCseq(BigDecimal.valueOf(2));
		carUsingMsg04.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg04);
		Tauth carUsingMsg06 = new Tauth();
		carUsingMsg06.setCid("carUsingMsg06");
		carUsingMsg06.setTauth(carUsingMsg);
		carUsingMsg06.setCname("更新信息");
		carUsingMsg06.setCurl("/demo/usingCarAction!edit.action");
		carUsingMsg06.setCseq(BigDecimal.valueOf(3));
		carUsingMsg06.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg06);
		Tauth carUsingMsg07 = new Tauth();
		carUsingMsg07.setCid("carUsingMsg07");
		carUsingMsg07.setTauth(carUsingMsg);
		carUsingMsg07.setCname("查询信息");
		carUsingMsg07.setCurl("/demo/usingCarAction!delete.action");
		carUsingMsg07.setCseq(BigDecimal.valueOf(4));
		carUsingMsg07.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg07);
		Tauth carUsingMsg09 = new Tauth();
		carUsingMsg09.setCid("carUsingMsg09");
		carUsingMsg09.setTauth(carUsingMsg);
		carUsingMsg09.setCname("获取车辆信息");
		carUsingMsg09.setCurl("/demo/usingCarAction!getCarDataBySchoolArea.action");
		carUsingMsg09.setCseq(BigDecimal.valueOf(5));
		carUsingMsg09.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg09);
		Tauth carUsingMsg11 = new Tauth();
		carUsingMsg11.setCid("carUsingMsg11");
		carUsingMsg11.setTauth(carUsingMsg);
		carUsingMsg11.setCname("获取教练信息");
		carUsingMsg11.setCurl("/demo/usingCarAction!getTrainerDataBySchoolArea.action");
		carUsingMsg11.setCseq(BigDecimal.valueOf(6));
		carUsingMsg11.setCdesc("");
		authDao.saveOrUpdate(carUsingMsg11);

		Tauth autoCarArrange = new Tauth();
		autoCarArrange.setCid("autoCarArrange");
		autoCarArrange.setTauth(carMsg);
		autoCarArrange.setCname("自动档车辆排班");
		autoCarArrange.setCurl("");
		autoCarArrange.setCseq(BigDecimal.valueOf(3));
		autoCarArrange.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange);

		Tauth autoCarArrange01 = new Tauth();
		autoCarArrange01.setCid("autoCarArrange01");
		autoCarArrange01.setTauth(autoCarArrange);
		autoCarArrange01.setCname("排班明细");
		autoCarArrange01.setCurl("/demo/usingCarAction!getAutoCarDatagrid.action");
		autoCarArrange01.setCseq(BigDecimal.valueOf(1));
		autoCarArrange01.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange01);
		Tauth autoCarArrange04 = new Tauth();
		autoCarArrange04.setCid("autoCarArrange04");
		autoCarArrange04.setTauth(autoCarArrange);
		autoCarArrange04.setCname("排班列表主页");
		autoCarArrange04.setCurl("/demo/autocarArrangeAction!getAutocarArrangeByCarId.action");
		autoCarArrange04.setCseq(BigDecimal.valueOf(2));
		autoCarArrange04.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange04);
		Tauth autoCarArrange08 = new Tauth();
		autoCarArrange08.setCid("autoCarArrange08");
		autoCarArrange08.setTauth(autoCarArrange);
		autoCarArrange08.setCname("添加排班");
		autoCarArrange08.setCurl("/demo/autocarArrangeAction!add.action");
		autoCarArrange08.setCseq(BigDecimal.valueOf(3));
		autoCarArrange08.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange08);
		Tauth autoCarArrange09 = new Tauth();
		autoCarArrange09.setCid("autoCarArrange09");
		autoCarArrange09.setTauth(autoCarArrange);
		autoCarArrange09.setCname("修改排班");
		autoCarArrange09.setCurl("/demo/autocarArrangeAction!edit.action");
		autoCarArrange09.setCseq(BigDecimal.valueOf(4));
		autoCarArrange09.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange09);
		Tauth autoCarArrange10 = new Tauth();
		autoCarArrange10.setCid("autoCarArrange10");
		autoCarArrange10.setTauth(autoCarArrange);
		autoCarArrange10.setCname("删除排班");
		autoCarArrange10.setCurl("/demo/autocarArrangeAction!delete.action");
		autoCarArrange10.setCseq(BigDecimal.valueOf(5));
		autoCarArrange10.setCdesc("");
		authDao.saveOrUpdate(autoCarArrange10);

	}

	// 考试管理权限初始化
	private static void initExaminationManger(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth examinationMsg = new Tauth();
		examinationMsg.setCid("examinationMsg");
		examinationMsg.setTauth(sshe);
		examinationMsg.setCname("考试管理");
		examinationMsg.setCurl("");
		examinationMsg.setCseq(BigDecimal.valueOf(4));
		examinationMsg.setCdesc("");
		examinationMsg.setMenuId("exammng");
		authDao.saveOrUpdate(examinationMsg);

		Tauth studentExamMsg = new Tauth();
		studentExamMsg.setCid("studentExamMsg");
		studentExamMsg.setTauth(examinationMsg);
		studentExamMsg.setCname("学员考试列表");
		studentExamMsg.setCurl("");
		studentExamMsg.setCseq(BigDecimal.valueOf(1));
		studentExamMsg.setCdesc("");
		authDao.saveOrUpdate(studentExamMsg);
		initStudentExam(studentExamMsg, authDao);
	}

	// 考试计划权限明细初始化
	// 学员考试列表权限明细初始化
	private static void initStudentExam(Tauth examinationSub1Msg, BaseDaoI<Tauth> authDao) {

		Tauth studentExam2 = new Tauth();
		studentExam2.setCid("studentExam2");
		studentExam2.setTauth(examinationSub1Msg);
		studentExam2.setCname("学员考试列表");
		studentExam2.setCurl("/demo/studentExamAction!datagrid.action");
		studentExam2.setCseq(BigDecimal.valueOf(1));
		studentExam2.setCdesc("");
		authDao.saveOrUpdate(studentExam2);
		Tauth studentExam3 = new Tauth();
		studentExam3.setCid("studentExam3");
		studentExam3.setTauth(examinationSub1Msg);
		studentExam3.setCname("更新");
		studentExam3.setCurl("/demo/studentExamAction!edit.action");
		studentExam3.setCseq(BigDecimal.valueOf(2));
		studentExam3.setCdesc("");
		authDao.saveOrUpdate(studentExam3);

		Tauth studentExam4 = new Tauth();
		studentExam4.setCid("studentExam4");
		studentExam4.setTauth(examinationSub1Msg);
		studentExam4.setCname("添加");
		studentExam4.setCurl("/demo/studentExamAction!add.action");
		studentExam4.setCseq(BigDecimal.valueOf(3));
		studentExam4.setCdesc("");
		authDao.saveOrUpdate(studentExam4);
		Tauth studentExam5 = new Tauth();
		studentExam5.setCid("studentExam5");
		studentExam5.setTauth(examinationSub1Msg);
		studentExam5.setCname("删除");
		studentExam5.setCurl("/demo/studentExamAction!delete.action");
		studentExam5.setCseq(BigDecimal.valueOf(4));
		studentExam5.setCdesc("");
		authDao.saveOrUpdate(studentExam5);
		Tauth studentExam7 = new Tauth();
		studentExam7.setCid("studentExam7");
		studentExam7.setTauth(examinationSub1Msg);
		studentExam7.setCname("导入学员信息");
		studentExam7.setCurl("/demo/studentExamAction!importExamStudentDatas.action");
		studentExam7.setCseq(BigDecimal.valueOf(5));
		studentExam7.setCdesc("");
		authDao.saveOrUpdate(studentExam7);

		Tauth examAnalys02 = new Tauth();
		examAnalys02.setCid("examAnalys02");
		examAnalys02.setTauth(examinationSub1Msg);
		examAnalys02.setCname("考试信息列表");
		examAnalys02.setCurl("/demo/examAnalysAction!datagrid.action");
		examAnalys02.setCseq(BigDecimal.valueOf(6));
		examAnalys02.setCdesc("");
		authDao.saveOrUpdate(examAnalys02);
		Tauth examAnalys04 = new Tauth();
		examAnalys04.setCid("examAnalys04");
		examAnalys04.setTauth(examinationSub1Msg);
		examAnalys04.setCname("添加");
		examAnalys04.setCurl("/demo/examAnalysAction!add.action");
		examAnalys04.setCseq(BigDecimal.valueOf(7));
		examAnalys04.setCdesc("");
		authDao.saveOrUpdate(examAnalys04);
		Tauth examAnalys06 = new Tauth();
		examAnalys06.setCid("examAnalys06");
		examAnalys06.setTauth(examinationSub1Msg);
		examAnalys06.setCname("更新");
		examAnalys06.setCurl("/demo/examAnalysAction!edit.action");
		examAnalys06.setCseq(BigDecimal.valueOf(8));
		examAnalys06.setCdesc("");
		authDao.saveOrUpdate(examAnalys06);
		Tauth examAnalys07 = new Tauth();
		examAnalys07.setCid("examAnalys07");
		examAnalys07.setTauth(examinationSub1Msg);
		examAnalys07.setCname("删除");
		examAnalys07.setCurl("/demo/examAnalysAction!delete.action");
		examAnalys07.setCseq(BigDecimal.valueOf(9));
		examAnalys07.setCdesc("");
		authDao.saveOrUpdate(examAnalys07);
		Tauth examAnalys08 = new Tauth();
		examAnalys08.setCid("examAnalys08");
		examAnalys08.setTauth(examinationSub1Msg);
		examAnalys08.setCname("添加考试学员");
		examAnalys08.setCurl("/demo/studentExamAction!addStudentToExamAnalys.action");
		examAnalys08.setCseq(BigDecimal.valueOf(10));
		examAnalys08.setCdesc("");
		authDao.saveOrUpdate(examAnalys08);
		Tauth examAnalys09 = new Tauth();
		examAnalys09.setCid("examAnalys09");
		examAnalys09.setTauth(examinationSub1Msg);
		examAnalys09.setCname("删除考试学员");
		examAnalys09.setCurl("/demo/studentExamAction!deleteStudentExam.action");
		examAnalys09.setCseq(BigDecimal.valueOf(11));
		examAnalys09.setCdesc("");
		authDao.saveOrUpdate(examAnalys09);
		Tauth examAnalys10 = new Tauth();
		examAnalys10.setCid("examAnalys10");
		examAnalys10.setTauth(examinationSub1Msg);
		examAnalys10.setCname("移动学员");
		examAnalys10.setCurl("/demo/studentExamAction!addAndMoveStudentToNewBatch.action");
		examAnalys10.setCseq(BigDecimal.valueOf(12));
		examAnalys10.setCdesc("");
		authDao.saveOrUpdate(examAnalys10);
		Tauth examAnalys11 = new Tauth();
		examAnalys11.setCid("examAnalys11");
		examAnalys11.setTauth(examinationSub1Msg);
		examAnalys11.setCname("批量修改");
		examAnalys11.setCurl("/demo/studentExamAction!batchUpdateStudentExam.action");
		examAnalys11.setCseq(BigDecimal.valueOf(13));
		examAnalys11.setCdesc("");
		authDao.saveOrUpdate(examAnalys11);
		Tauth studentExam8 = new Tauth();
		studentExam8.setCid("studentExam8");
		studentExam8.setTauth(examinationSub1Msg);
		studentExam8.setCname("添加");
		studentExam8.setCurl("/demo/studentExamAction!batchCancelHandup.action");
		studentExam8.setCseq(BigDecimal.valueOf(14));
		studentExam8.setCdesc("");
		authDao.saveOrUpdate(studentExam8);
		Tauth studentExamImport01 = new Tauth();
		studentExamImport01.setCid("studentExamImport01");
		studentExamImport01.setTauth(examinationSub1Msg);
		studentExamImport01.setCname("导入学成绩");
		studentExamImport01.setCurl("/demo/studentExamAction!importExamScores.action");
		studentExamImport01.setCseq(BigDecimal.valueOf(15));
		studentExamImport01.setCdesc("");
		authDao.saveOrUpdate(studentExamImport01);

	}

	// 系统管理权限(二级)17
	private static void initSecondSystemAuth(Tauth xtgl, BaseDaoI<Tauth> authDao) {
		// 用户管理
		Tauth yhgl = new Tauth();
		yhgl.setCid("yhgl");
		yhgl.setTauth(xtgl);
		yhgl.setCname("用户管理");
		yhgl.setCurl("");
		yhgl.setCseq(BigDecimal.valueOf(1));
		yhgl.setCdesc("");
		yhgl.setMenuId("yhgl");// this id control auth
		authDao.saveOrUpdate(yhgl);
		initThirdUserAuth(yhgl, authDao);
		// 角色管理
		Tauth jsgl = new Tauth();
		jsgl.setCid("jsgl");
		jsgl.setTauth(xtgl);
		jsgl.setCname("角色管理");
		jsgl.setCurl("");
		jsgl.setCseq(BigDecimal.valueOf(2));
		jsgl.setCdesc("");
		jsgl.setMenuId("jsgl");// this id control auth
		authDao.saveOrUpdate(jsgl);
		initThirdRoleAuth(jsgl, authDao);
		// 权限管理
		Tauth qxgl = new Tauth();
		qxgl.setCid("qxgl");
		qxgl.setTauth(xtgl);
		qxgl.setCname("权限管理");
		qxgl.setCurl("");
		qxgl.setCseq(BigDecimal.valueOf(3));
		qxgl.setCdesc("");
		qxgl.setMenuId("qxgl");
		authDao.saveOrUpdate(qxgl);
		initThirdAuth(qxgl, authDao);
		// 菜单管理
		Tauth cdgl = new Tauth();
		cdgl.setCid("cdgl");
		cdgl.setTauth(xtgl);
		cdgl.setCname("菜单管理");
		cdgl.setCurl("");
		cdgl.setCseq(BigDecimal.valueOf(4));
		cdgl.setCdesc("");
		cdgl.setMenuId("cdgl");
		authDao.saveOrUpdate(cdgl);
		initThirdMenuAuth(cdgl, authDao);
		// bug管理
		Tauth buggl = new Tauth();
		buggl.setCid("buggl");
		buggl.setTauth(xtgl);
		buggl.setCname("BUG管理");
		buggl.setCurl("");
		buggl.setCseq(BigDecimal.valueOf(5));
		buggl.setCdesc("");
		buggl.setMenuId("buggl");
		authDao.saveOrUpdate(buggl);
		initThirdBugAuth(buggl, authDao);
		
		Tauth netPiont = new Tauth();// 二极模块
		netPiont.setCid("netPiont");
		netPiont.setTauth(xtgl);
		netPiont.setCname("报名网点配置");
		netPiont.setCurl("");
		netPiont.setCseq(BigDecimal.valueOf(6));
		netPiont.setCdesc("");
		buggl.setMenuId("sysConfig");
		authDao.saveOrUpdate(netPiont);
		Tauth netPiont01 = new Tauth();// 三极模块
		netPiont01.setCid("netPiont01");
		netPiont01.setTauth(netPiont);
		netPiont01.setCname("添加");
		netPiont01.setCurl("/demo/netPiontAction!add.action");
		netPiont01.setCseq(BigDecimal.valueOf(1));
		netPiont01.setCdesc("");
		authDao.saveOrUpdate(netPiont01);
		Tauth netPiont02 = new Tauth();// 三极模块
		netPiont02.setCid("netPiont02");
		netPiont02.setTauth(netPiont);
		netPiont02.setCname("添加");
		netPiont02.setCurl("/demo/netPiontAction!edit.action");
		netPiont02.setCseq(BigDecimal.valueOf(1));
		netPiont02.setCdesc("");
		authDao.saveOrUpdate(netPiont02);
		Tauth netPiont03 = new Tauth();// 三极模块
		netPiont03.setCid("netPiont03");
		netPiont03.setTauth(netPiont);
		netPiont03.setCname("删除");
		netPiont03.setCurl("/demo/netPiontAction!delete.action");
		netPiont03.setCseq(BigDecimal.valueOf(1));
		netPiont03.setCdesc("");
		authDao.saveOrUpdate(netPiont03);

	}

	private static void initThirdBugAuth(Tauth buggl, BaseDaoI<Tauth> authDao) {
		Tauth bugglym = new Tauth();
		bugglym.setCid("bugglym");
		bugglym.setTauth(buggl);
		bugglym.setCname("BUG管理页面");
		bugglym.setCurl("/demo/bugAction!bug.action");
		bugglym.setCseq(BigDecimal.valueOf(1));
		bugglym.setCdesc("");
		authDao.saveOrUpdate(bugglym);

		Tauth bugaddym = new Tauth();
		bugaddym.setCid("bugaddym");
		bugaddym.setTauth(buggl);
		bugaddym.setCname("上报BUG页面");
		bugaddym.setCurl("/demo/bugAction!bugAdd.action");
		bugaddym.setCseq(BigDecimal.valueOf(3));
		bugaddym.setCdesc("");
		authDao.saveOrUpdate(bugaddym);

		Tauth bugadd = new Tauth();
		bugadd.setCid("bugadd");
		bugadd.setTauth(buggl);
		bugadd.setCname("上报BUG");
		bugadd.setCurl("/demo/bugAction!add.action");
		bugadd.setCseq(BigDecimal.valueOf(4));
		bugadd.setCdesc("");
		authDao.saveOrUpdate(bugadd);

		Tauth bugeditym = new Tauth();
		bugeditym.setCid("bugeditym");
		bugeditym.setTauth(buggl);
		bugeditym.setCname("编辑BUG页面");
		bugeditym.setCurl("/demo/bugAction!bugEdit.action");
		bugeditym.setCseq(BigDecimal.valueOf(5));
		bugeditym.setCdesc("");
		authDao.saveOrUpdate(bugeditym);

		Tauth bugedit = new Tauth();
		bugedit.setCid("bugedit");
		bugedit.setTauth(buggl);
		bugedit.setCname("BUG编辑");
		bugedit.setCurl("/demo/bugAction!edit.action");
		bugedit.setCseq(BigDecimal.valueOf(6));
		bugedit.setCdesc("");
		authDao.saveOrUpdate(bugedit);

		Tauth bugdelete = new Tauth();
		bugdelete.setCid("bugdelete");
		bugdelete.setTauth(buggl);
		bugdelete.setCname("BUG删除");
		bugdelete.setCurl("/demo/bugAction!delete.action");
		bugdelete.setCseq(BigDecimal.valueOf(7));
		bugdelete.setCdesc("");
		authDao.saveOrUpdate(bugdelete);

		Tauth bugupload = new Tauth();
		bugupload.setCid("bugupload");
		bugupload.setTauth(buggl);
		bugupload.setCname("BUG上传");
		bugupload.setCurl("/demo/bugAction!upload.action");
		bugupload.setCseq(BigDecimal.valueOf(8));
		bugupload.setCdesc("");
		authDao.saveOrUpdate(bugupload);

		Tauth bugdesc = new Tauth();
		bugdesc.setCid("bugdesc");
		bugdesc.setTauth(buggl);
		bugdesc.setCname("查看BUG描述");
		bugdesc.setCurl("/demo/bugAction!showCdesc.action");
		bugdesc.setCseq(BigDecimal.valueOf(9));
		bugdesc.setCdesc("");
		authDao.saveOrUpdate(bugdesc);
	}

	private static void initThirdMenuAuth(Tauth cdgl, BaseDaoI<Tauth> authDao) {
		Tauth cdglym = new Tauth();
		cdglym.setCid("cdglym");
		cdglym.setTauth(cdgl);
		cdglym.setCname("菜单管理页面");
		cdglym.setCurl("/demo/menuAction!menu.action");
		cdglym.setCseq(BigDecimal.valueOf(1));
		cdglym.setCdesc("");
		authDao.saveOrUpdate(cdglym);

		Tauth cdcx = new Tauth();
		cdcx.setCid("cdcx");
		cdcx.setTauth(cdgl);
		cdcx.setCname("菜单查询");
		cdcx.setCurl("/demo/menuAction!treegrid.action");
		cdcx.setCseq(BigDecimal.valueOf(2));
		cdcx.setCdesc("");
		authDao.saveOrUpdate(cdcx);

		Tauth cdaddym = new Tauth();
		cdaddym.setCid("cdaddym");
		cdaddym.setTauth(cdgl);
		cdaddym.setCname("添加菜单页面");
		cdaddym.setCurl("/demo/menuAction!menuAdd.action");
		cdaddym.setCseq(BigDecimal.valueOf(3));
		cdaddym.setCdesc("");
		authDao.saveOrUpdate(cdaddym);

		Tauth cdadd = new Tauth();
		cdadd.setCid("cdadd");
		cdadd.setTauth(cdgl);
		cdadd.setCname("菜单添加");
		cdadd.setCurl("/demo/menuAction!add.action");
		cdadd.setCseq(BigDecimal.valueOf(4));
		cdadd.setCdesc("");
		authDao.saveOrUpdate(cdadd);

		Tauth cdeditym = new Tauth();
		cdeditym.setCid("cdeditym");
		cdeditym.setTauth(cdgl);
		cdeditym.setCname("编辑菜单页面");
		cdeditym.setCurl("/demo/menuAction!menuEdit.action");
		cdeditym.setCseq(BigDecimal.valueOf(5));
		cdeditym.setCdesc("");
		authDao.saveOrUpdate(cdeditym);

		Tauth cdedit = new Tauth();
		cdedit.setCid("cdedit");
		cdedit.setTauth(cdgl);
		cdedit.setCname("菜单编辑");
		cdedit.setCurl("/demo/menuAction!edit.action");
		cdedit.setCseq(BigDecimal.valueOf(6));
		cdedit.setCdesc("");
		authDao.saveOrUpdate(cdedit);

		Tauth cddelete = new Tauth();
		cddelete.setCid("cddelete");
		cddelete.setTauth(cdgl);
		cddelete.setCname("菜单删除");
		cddelete.setCurl("/demo/menuAction!delete.action");
		cddelete.setCseq(BigDecimal.valueOf(7));
		cddelete.setCdesc("");
		authDao.saveOrUpdate(cddelete);
	}

	private static void initThirdAuth(Tauth qxgl, BaseDaoI<Tauth> authDao) {
		Tauth qxglym = new Tauth();
		qxglym.setCid("qxglym");
		qxglym.setTauth(qxgl);
		qxglym.setCname("权限管理页面");
		qxglym.setCurl("/demo/authAction!auth.action");
		qxglym.setCseq(BigDecimal.valueOf(1));
		qxglym.setCdesc("");
		authDao.saveOrUpdate(qxglym);

		Tauth qxcx = new Tauth();
		qxcx.setCid("qxcx");
		qxcx.setTauth(qxgl);
		qxcx.setCname("权限查询");
		qxcx.setCurl("/demo/authAction!treegrid.action");
		qxcx.setCseq(BigDecimal.valueOf(2));
		qxcx.setCdesc("");
		authDao.saveOrUpdate(qxcx);

		Tauth qxaddym = new Tauth();
		qxaddym.setCid("qxaddym");
		qxaddym.setTauth(qxgl);
		qxaddym.setCname("添加权限页面");
		qxaddym.setCurl("/demo/authAction!authAdd.action");
		qxaddym.setCseq(BigDecimal.valueOf(3));
		qxaddym.setCdesc("");
		authDao.saveOrUpdate(qxaddym);

		Tauth qxadd = new Tauth();
		qxadd.setCid("qxadd");
		qxadd.setTauth(qxgl);
		qxadd.setCname("权限添加");
		qxadd.setCurl("/demo/authAction!add.action");
		qxadd.setCseq(BigDecimal.valueOf(4));
		qxadd.setCdesc("");
		authDao.saveOrUpdate(qxadd);

		Tauth qxeditym = new Tauth();
		qxeditym.setCid("qxeditym");
		qxeditym.setTauth(qxgl);
		qxeditym.setCname("编辑权限页面");
		qxeditym.setCurl("/demo/authAction!authEdit.action");
		qxeditym.setCseq(BigDecimal.valueOf(5));
		qxeditym.setCdesc("");
		authDao.saveOrUpdate(qxeditym);

		Tauth qxedit = new Tauth();
		qxedit.setCid("qxedit");
		qxedit.setTauth(qxgl);
		qxedit.setCname("权限编辑");
		qxedit.setCurl("/demo/authAction!edit.action");
		qxedit.setCseq(BigDecimal.valueOf(6));
		qxedit.setCdesc("");
		authDao.saveOrUpdate(qxedit);

		Tauth qxdelete = new Tauth();
		qxdelete.setCid("qxdelete");
		qxdelete.setTauth(qxgl);
		qxdelete.setCname("权限删除");
		qxdelete.setCurl("/demo/authAction!delete.action");
		qxdelete.setCseq(BigDecimal.valueOf(7));
		qxdelete.setCdesc("");
		authDao.saveOrUpdate(qxdelete);
	}

	private static void initThirdRoleAuth(Tauth jsgl, BaseDaoI<Tauth> authDao) {
		Tauth jsglym = new Tauth();
		jsglym.setCid("jsglym");
		jsglym.setTauth(jsgl);
		jsglym.setCname("角色管理页面");
		jsglym.setCurl("/demo/roleAction!role.action");
		jsglym.setCseq(BigDecimal.valueOf(1));
		jsglym.setCdesc("");
		authDao.saveOrUpdate(jsglym);
		Tauth jsaddym = new Tauth();
		jsaddym.setCid("jsaddym");
		jsaddym.setTauth(jsgl);
		jsaddym.setCname("添加角色页面");
		jsaddym.setCurl("/demo/roleAction!roleAdd.action");
		jsaddym.setCseq(BigDecimal.valueOf(3));
		jsaddym.setCdesc("");
		authDao.saveOrUpdate(jsaddym);
		Tauth jsadd = new Tauth();
		jsadd.setCid("jsadd");
		jsadd.setTauth(jsgl);
		jsadd.setCname("角色添加");
		jsadd.setCurl("/demo/roleAction!add.action");
		jsadd.setCseq(BigDecimal.valueOf(4));
		jsadd.setCdesc("");
		authDao.saveOrUpdate(jsadd);
		Tauth jseditym = new Tauth();
		jseditym.setCid("jseditym");
		jseditym.setTauth(jsgl);
		jseditym.setCname("编辑角色页面");
		jseditym.setCurl("/demo/roleAction!roleEdit.action");
		jseditym.setCseq(BigDecimal.valueOf(5));
		jseditym.setCdesc("");
		authDao.saveOrUpdate(jseditym);
		Tauth jsedit = new Tauth();
		jsedit.setCid("jsedit");
		jsedit.setTauth(jsgl);
		jsedit.setCname("角色编辑");
		jsedit.setCurl("/demo/roleAction!edit.action");
		jsedit.setCseq(BigDecimal.valueOf(6));
		jsedit.setCdesc("");
		authDao.saveOrUpdate(jsedit);
		Tauth jsdelete = new Tauth();
		jsdelete.setCid("jsdelete");
		jsdelete.setTauth(jsgl);
		jsdelete.setCname("角色删除");
		jsdelete.setCurl("/demo/roleAction!delete.action");
		jsdelete.setCseq(BigDecimal.valueOf(7));
		jsdelete.setCdesc("");
		authDao.saveOrUpdate(jsdelete);
		Tauth roleDetail = new Tauth();
		roleDetail.setCid("roleDetail");
		roleDetail.setTauth(jsgl);
		roleDetail.setCname("角色详细信息");
		roleDetail.setCurl("/demo/roleAction!roleDetailPage.action");
		roleDetail.setCseq(BigDecimal.valueOf(8));
		roleDetail.setCdesc("");
		authDao.saveOrUpdate(roleDetail);
	}

	private static void initThirdUserAuth(Tauth yhgl, BaseDaoI<Tauth> authDao) {
		Tauth yhglym = new Tauth();
		yhglym.setCid("yhglym");
		yhglym.setTauth(yhgl);
		yhglym.setCname("用户管理页面");
		yhglym.setCurl("/demo/userAction!user.action");
		yhglym.setCseq(BigDecimal.valueOf(1));
		yhglym.setCdesc("");
		authDao.saveOrUpdate(yhglym);

		Tauth yhaddym = new Tauth();
		yhaddym.setCid("yhaddym");
		yhaddym.setTauth(yhgl);
		yhaddym.setCname("添加用户页面");
		yhaddym.setCurl("/demo/userAction!userAdd.action");
		yhaddym.setCseq(BigDecimal.valueOf(3));
		yhaddym.setCdesc("");
		authDao.saveOrUpdate(yhaddym);

		Tauth yhadd = new Tauth();
		yhadd.setCid("yhadd");
		yhadd.setTauth(yhgl);
		yhadd.setCname("用户添加");
		yhadd.setCurl("/demo/userAction!add.action");
		yhadd.setCseq(BigDecimal.valueOf(4));
		yhadd.setCdesc("");
		authDao.saveOrUpdate(yhadd);

		Tauth yheditym = new Tauth();
		yheditym.setCid("yheditym");
		yheditym.setTauth(yhgl);
		yheditym.setCname("修改用户页面");
		yheditym.setCurl("/demo/userAction!userEdit.action");
		yheditym.setCseq(BigDecimal.valueOf(5));
		yheditym.setCdesc("");
		authDao.saveOrUpdate(yheditym);

		Tauth yhedit = new Tauth();
		yhedit.setCid("yhedit");
		yhedit.setTauth(yhgl);
		yhedit.setCname("用户修改");
		yhedit.setCurl("/demo/userAction!edit.action");
		yhedit.setCseq(BigDecimal.valueOf(6));
		yhedit.setCdesc("");
		authDao.saveOrUpdate(yhedit);

		Tauth yhsc = new Tauth();
		yhsc.setCid("yhsc");
		yhsc.setTauth(yhgl);
		yhsc.setCname("用户删除");
		yhsc.setCurl("/demo/userAction!delete.action");
		yhsc.setCseq(BigDecimal.valueOf(7));
		yhsc.setCdesc("");
		authDao.saveOrUpdate(yhsc);

		Tauth yhxgjsym = new Tauth();
		yhxgjsym.setCid("yhxgjsym");
		yhxgjsym.setTauth(yhgl);
		yhxgjsym.setCname("修改角色页面");
		yhxgjsym.setCurl("/demo/userAction!userRoleEdit.action");
		yhxgjsym.setCseq(BigDecimal.valueOf(8));
		yhxgjsym.setCdesc("批量修改用户角色");
		authDao.saveOrUpdate(yhxgjsym);

		Tauth yhxgjs = new Tauth();
		yhxgjs.setCid("yhxgjs");
		yhxgjs.setTauth(yhgl);
		yhxgjs.setCname("修改角色");
		yhxgjs.setCurl("/demo/userAction!roleEdit.action");
		yhxgjs.setCseq(BigDecimal.valueOf(9));
		yhxgjs.setCdesc("批量修改用户角色");
		authDao.saveOrUpdate(yhxgjs);
	}

	// 数据库权限（二级）12
	private static void initDatabaseAuth(Tauth sjkgl, BaseDaoI<Tauth> authDao) {
		Tauth ljcjk = new Tauth();
		ljcjk.setCid("ljcjk");
		ljcjk.setTauth(sjkgl);
		ljcjk.setCname("连接池监控");
		ljcjk.setCurl("/datasourceAction!druid.action");
		ljcjk.setCseq(BigDecimal.valueOf(1));
		ljcjk.setCdesc("可查看数据库链接信息");
		authDao.saveOrUpdate(ljcjk);
	}

	// 初始化权限
	private static void initAuthStudentMng(Tauth sshe, BaseDaoI<Tauth> authDao) {
		Tauth studentMsg = new Tauth();
		studentMsg.setCid("studentMsgmy");
		studentMsg.setTauth(sshe);
		studentMsg.setCname("学员管理");
		studentMsg.setCurl("");
		studentMsg.setCseq(BigDecimal.valueOf(1));
		studentMsg.setCdesc("");
		authDao.saveOrUpdate(studentMsg);

		Tauth studentMsgMng = new Tauth();
		studentMsgMng.setCid("studentMsgMng");
		studentMsgMng.setTauth(studentMsg);
		studentMsgMng.setCname("学员信息管理");
		studentMsgMng.setCurl("");
		studentMsgMng.setCseq(BigDecimal.valueOf(1));
		studentMsgMng.setCdesc("");
		studentMsgMng.setMenuId("xshbm");
		authDao.saveOrUpdate(studentMsgMng);

		Tauth studentMsgMngAdd = new Tauth();
		studentMsgMngAdd.setCid("studentMsgMngAdd");
		studentMsgMngAdd.setTauth(studentMsgMng);
		studentMsgMngAdd.setCname("添加学员");
		studentMsgMngAdd.setCurl("/demo/studentAction!add.action");
		studentMsgMngAdd.setCseq(BigDecimal.valueOf(1));
		studentMsgMngAdd.setCdesc("");
		authDao.saveOrUpdate(studentMsgMngAdd);

		Tauth organizationMsg = new Tauth();
		organizationMsg.setCid("organizationMsg");
		organizationMsg.setTauth(studentMsgMng);
		organizationMsg.setCname("组织机构");
		organizationMsg.setCurl("/demo/organizationAction!getAllOrganization.action");
		organizationMsg.setCseq(BigDecimal.valueOf(2));
		organizationMsg.setCdesc("");
		authDao.saveOrUpdate(organizationMsg);

		Tauth studentListMsg = new Tauth();
		studentListMsg.setCid("studentListMsg");
		studentListMsg.setTauth(studentMsgMng);
		studentListMsg.setCname("学员信息列表");
		studentListMsg.setCurl("/demo/studentAction!datagrid.action");
		studentListMsg.setCseq(BigDecimal.valueOf(3));
		studentListMsg.setCdesc("");
		authDao.saveOrUpdate(studentListMsg);

		Tauth trainerListMsg = new Tauth();
		trainerListMsg.setCid("trainerListMsg");
		trainerListMsg.setTauth(studentMsgMng);
		trainerListMsg.setCname("教练选择菜单");
		trainerListMsg.setCurl("/demo/studentAction!getAllTrainersForCombobox.action");
		trainerListMsg.setCseq(BigDecimal.valueOf(4));
		trainerListMsg.setCdesc("");
		authDao.saveOrUpdate(trainerListMsg);

		Tauth maxIdListMsg = new Tauth();
		maxIdListMsg.setCid("maxIdListMsg");
		maxIdListMsg.setTauth(studentMsgMng);
		maxIdListMsg.setCname("学编号");
		maxIdListMsg.setCurl("/demo/studentAction!getMaxStudentNo.action");
		maxIdListMsg.setCseq(BigDecimal.valueOf(5));
		maxIdListMsg.setCdesc("");
		authDao.saveOrUpdate(maxIdListMsg);

		Tauth studentMsg005 = new Tauth();
		studentMsg005.setCid("studentMsg005");
		studentMsg005.setTauth(studentMsgMng);
		studentMsg005.setCname("更新学员");
		studentMsg005.setCurl("/demo/studentAction!edit.action");
		studentMsg005.setCseq(BigDecimal.valueOf(6));
		studentMsg005.setCdesc("");
		authDao.saveOrUpdate(studentMsg005);

		Tauth studentMsg006 = new Tauth();
		studentMsg006.setCid("studentMsg006");
		studentMsg006.setTauth(studentMsgMng);
		studentMsg006.setCname("选择班级");
		studentMsg006.setCurl("/demo/studentAction!getAllClass.action");
		studentMsg006.setCseq(BigDecimal.valueOf(7));
		studentMsg006.setCdesc("");
		authDao.saveOrUpdate(studentMsg006);

		Tauth studentMsg007 = new Tauth();
		studentMsg007.setCid("studentMsg007");
		studentMsg007.setTauth(studentMsgMng);
		studentMsg007.setCname("业务员选择");
		studentMsg007.setCurl("/demo/studentAction!getAllPersonsForCombobox.action");
		studentMsg007.setCseq(BigDecimal.valueOf(8));
		studentMsg007.setCdesc("");
		authDao.saveOrUpdate(studentMsg007);

		Tauth studentMsg008 = new Tauth();
		studentMsg008.setCid("studentMsg008");
		studentMsg008.setTauth(studentMsgMng);
		studentMsg008.setCname("获取班级");
		studentMsg008.setCurl("/demo/studentAction!getClazzDatabyType.action");
		studentMsg008.setCseq(BigDecimal.valueOf(9));
		studentMsg008.setCdesc("");
		authDao.saveOrUpdate(studentMsg008);

		Tauth studentMsg009 = new Tauth();
		studentMsg009.setCid("studentMsg009");
		studentMsg009.setTauth(studentMsgMng);
		studentMsg009.setCname("获取教练");
		studentMsg009.setCurl("/demo/studentAction!getTrainerDatabyType.action");
		studentMsg009.setCseq(BigDecimal.valueOf(10));
		studentMsg009.setCdesc("");
		authDao.saveOrUpdate(studentMsg009);

		Tauth studentMsg010 = new Tauth();
		studentMsg010.setCid("studentMsg010");
		studentMsg010.setTauth(studentMsgMng);
		studentMsg010.setCname("获取业务员");
		studentMsg010.setCurl("/demo/studentAction!getAllPersons.action");
		studentMsg010.setCseq(BigDecimal.valueOf(11));
		studentMsg010.setCdesc("");
		authDao.saveOrUpdate(studentMsg010);

		Tauth studentMsg011 = new Tauth();
		studentMsg011.setCid("studentMsg011");
		studentMsg011.setTauth(studentMsgMng);
		studentMsg011.setCname("查询学员编号");
		studentMsg011.setCurl("/demo/studentAction!studentNoExistOrNot.action");
		studentMsg011.setCseq(BigDecimal.valueOf(12));
		studentMsg011.setCdesc("");
		authDao.saveOrUpdate(studentMsg011);

		Tauth studentMsg012 = new Tauth();
		studentMsg012.setCid("studentMsg012");
		studentMsg012.setTauth(studentMsgMng);
		studentMsg012.setCname("删除学员");
		studentMsg012.setCurl("/demo/studentAction!delete.action");
		studentMsg012.setCseq(BigDecimal.valueOf(13));
		studentMsg012.setCdesc("");
		authDao.saveOrUpdate(studentMsg012);
		Tauth studentMsg15 = new Tauth();
		studentMsg15.setCid("studentMsg15");
		studentMsg15.setTauth(studentMsgMng);
		studentMsg15.setCname("导入信息");
		studentMsg15.setCurl("/demo/studentAction!importStudentDatas.action");
		studentMsg15.setCseq(BigDecimal.valueOf(14));
		studentMsg15.setCdesc("");
		authDao.saveOrUpdate(studentMsg15);
		// 退学登记
		initAuthQickschoolMng(studentMsg, authDao);
		// 初始化学员进度
		initAuthProgressMng(studentMsg, authDao);

	};

	// 学员进度
	private static void initAuthProgressMng(Tauth studentMsg, BaseDaoI<Tauth> authDao) {
		Tauth progressSchoolMsg = new Tauth();
		progressSchoolMsg.setCid("progressSchoolMsg");
		progressSchoolMsg.setTauth(studentMsg);
		progressSchoolMsg.setCname("学员进度管理");
		progressSchoolMsg.setCurl("");
		progressSchoolMsg.setCseq(BigDecimal.valueOf(5));
		progressSchoolMsg.setCdesc("");
		progressSchoolMsg.setMenuId("studentprogress");
		authDao.saveOrUpdate(progressSchoolMsg);
		Tauth progressSchoolMsg02 = new Tauth();
		progressSchoolMsg02.setCid("progressSchoolMsg02");
		progressSchoolMsg02.setTauth(progressSchoolMsg);
		progressSchoolMsg02.setCname("学员进度列表");
		progressSchoolMsg02.setCurl("/demo/progressAction!datagrid.action");
		progressSchoolMsg02.setCseq(BigDecimal.valueOf(1));
		progressSchoolMsg02.setCdesc("");
		authDao.saveOrUpdate(progressSchoolMsg02);
	}

	// 退学登记
	private static void initAuthQickschoolMng(Tauth studentMsg, BaseDaoI<Tauth> authDao) {
		Tauth quickSchoolMsg = new Tauth();
		quickSchoolMsg.setCid("quickSchoolMsg");
		quickSchoolMsg.setTauth(studentMsg);
		quickSchoolMsg.setCname("退学登记");
		quickSchoolMsg.setCurl("");
		quickSchoolMsg.setCseq(BigDecimal.valueOf(3));
		quickSchoolMsg.setCdesc("");
		quickSchoolMsg.setMenuId("txdj");
		authDao.saveOrUpdate(quickSchoolMsg);

		Tauth quickSchoolMsg05 = new Tauth();
		quickSchoolMsg05.setCid("quickSchoolMsg05");
		quickSchoolMsg05.setTauth(quickSchoolMsg);
		quickSchoolMsg05.setCname("退学列表");
		quickSchoolMsg05.setCurl("/demo/quitschoolAction!datagrid.action");
		quickSchoolMsg05.setCseq(BigDecimal.valueOf(1));
		quickSchoolMsg05.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg05);

		Tauth quickSchoolMsg06 = new Tauth();
		quickSchoolMsg06.setCid("quickSchoolMsg06");
		quickSchoolMsg06.setTauth(quickSchoolMsg);
		quickSchoolMsg06.setCname("添加信息");
		quickSchoolMsg06.setCurl("/demo/quitschoolAction!add.action");
		quickSchoolMsg06.setCseq(BigDecimal.valueOf(2));
		quickSchoolMsg06.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg06);

		Tauth quickSchoolMsg07 = new Tauth();
		quickSchoolMsg07.setCid("quickSchoolMsg07");
		quickSchoolMsg07.setTauth(quickSchoolMsg);
		quickSchoolMsg07.setCname("删除退学");
		quickSchoolMsg07.setCurl("/demo/quitschoolAction!delete.action");
		quickSchoolMsg07.setCseq(BigDecimal.valueOf(3));
		quickSchoolMsg07.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg07);

		Tauth quickSchoolMsg08 = new Tauth();
		quickSchoolMsg08.setCid("quickSchoolMsg08");
		quickSchoolMsg08.setTauth(quickSchoolMsg);
		quickSchoolMsg08.setCname("获取学员");
		quickSchoolMsg08.setCurl("/demo/quitschoolAction!getAllStudentByState.action");
		quickSchoolMsg08.setCseq(BigDecimal.valueOf(4));
		quickSchoolMsg08.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg08);

		Tauth quickSchoolMsg09 = new Tauth();
		quickSchoolMsg09.setCid("quickSchoolMsg09");
		quickSchoolMsg09.setTauth(quickSchoolMsg);
		quickSchoolMsg09.setCname("获取1个学员");
		quickSchoolMsg09.setCurl("/demo/quitschoolAction!getSingStudentInformation.action");
		quickSchoolMsg09.setCseq(BigDecimal.valueOf(5));
		quickSchoolMsg09.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg09);

		Tauth quickSchoolMsg10 = new Tauth();
		quickSchoolMsg10.setCid("quickSchoolMsg10");
		quickSchoolMsg10.setTauth(quickSchoolMsg);
		quickSchoolMsg10.setCname("更新退学");
		quickSchoolMsg10.setCurl("/demo/quitschoolAction!edit.action");
		quickSchoolMsg10.setCseq(BigDecimal.valueOf(6));
		quickSchoolMsg10.setCdesc("");
		authDao.saveOrUpdate(quickSchoolMsg10);

	}
}
