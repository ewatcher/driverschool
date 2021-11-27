package com.tuocheng.util.base;

import java.math.BigDecimal;
import java.util.UUID;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tmenu;

public class MenuInitUtils {
	public static final String SUPERID_ADMIN="nongfeng-4f14-4625-bf58-7b20171688";
	public static void initMainMemu(Tmenu root, BaseDaoI<Tmenu> menuDao) {
		// 学员管理01
		Tmenu xygl = new Tmenu();
		xygl.setCid("0100-4c26-92a6-driver170100");
		xygl.setCname("学员管理");
		xygl.setCseq(BigDecimal.valueOf(1));
		xygl.setCurl("");
		xygl.setTmenu(root);
		xygl.setCiconcls("icon-sum");
		xygl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(xygl);
		initStudentManagerDetail(xygl, menuDao);// 三级菜单
		// 教练管理02
		Tmenu trainermng = new Tmenu();
		trainermng.setCid("0200-4c26-92a6-driver170200");
		trainermng.setCname("教练管理");
		trainermng.setCseq(BigDecimal.valueOf(2));
		trainermng.setCurl("");
		trainermng.setTmenu(root);
		trainermng.setCiconcls("icon-sum");
		trainermng.setMainMenuFlag(1);
		menuDao.saveOrUpdate(trainermng);
		initTrainerManagerDetail(trainermng, menuDao);// 三级菜单
		// 车辆管理03
		Tmenu carmanager = new Tmenu();
		carmanager.setCid("0300-4c26-92a6-driver170300");
		carmanager.setCname("车辆管理");
		carmanager.setCseq(BigDecimal.valueOf(3));
		carmanager.setCurl("");
		carmanager.setTmenu(root);
		carmanager.setCiconcls("icon-sum");
		carmanager.setMainMenuFlag(1);
		menuDao.saveOrUpdate(carmanager);
		initCarManagerDetail(carmanager, menuDao);// 三级菜单
		// 考试管理04
		Tmenu exammng = new Tmenu();
		exammng.setCid("0400-ksgl-92a6-driver170400");
		exammng.setCname("考试管理");
		exammng.setCseq(BigDecimal.valueOf(4));
		exammng.setCurl("");
		exammng.setTmenu(root);
		exammng.setCiconcls("icon-sum");
		exammng.setMainMenuFlag(1);
		menuDao.saveOrUpdate(exammng);
		initExamManagerDetail(exammng, menuDao);// 三级菜单
		// 预约管理05
		Tmenu xydagl = new Tmenu();
		xydagl.setCid("0500-4c26-92a6-driver170500");
		xydagl.setCname("预约管理");
		xydagl.setCseq(BigDecimal.valueOf(5));
		xydagl.setCurl("");
		xydagl.setTmenu(root);
		xydagl.setCiconcls("icon-sum");
		xydagl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(xydagl);
		initReservationManagerDetail(xydagl, menuDao);// 三级菜单
		// 财务管理06
		Tmenu financialMenu = new Tmenu();
		financialMenu.setCid("0600-cwgl-92a6-driver170600");
		financialMenu.setCname("财务管理");
		financialMenu.setCseq(BigDecimal.valueOf(6));
		financialMenu.setCurl("");
		financialMenu.setTmenu(root);
		financialMenu.setCiconcls("icon-filter");
		financialMenu.setMainMenuFlag(1);
		menuDao.saveOrUpdate(financialMenu);
		initFeeManagerDetail(financialMenu, menuDao);// 三级菜单
		// 微信管理07
		Tmenu wxfw = new Tmenu();
		wxfw.setCid("0700-4c26-92a6-driver170700");
		wxfw.setCname("微信管理");
		wxfw.setCseq(BigDecimal.valueOf(7));
		wxfw.setCurl("");
		wxfw.setTmenu(root);
		wxfw.setCiconcls("icon-filter");
		wxfw.setMainMenuFlag(1);
		menuDao.saveOrUpdate(wxfw);
		initWechatManagerDetail(wxfw, menuDao);// 三级菜单
		// 油卡管理08
		Tmenu oilcardMenu = new Tmenu();
		oilcardMenu.setCid("0800-4c26-92a6-driver170800");
		oilcardMenu.setCname("油卡管理");
		oilcardMenu.setCseq(BigDecimal.valueOf(8));
		oilcardMenu.setCurl("");
		oilcardMenu.setTmenu(root);
		oilcardMenu.setCiconcls("icon-filter");
		oilcardMenu.setMainMenuFlag(1);
		menuDao.saveOrUpdate(oilcardMenu);
		initOilcardManagerDetail(oilcardMenu, menuDao);// 三级菜单
		// 证件管理09
		Tmenu licenseMsgMenu = new Tmenu();
		licenseMsgMenu.setCid("0900-4c26-92a6-driver170900");
		licenseMsgMenu.setCname("证件管理");
		licenseMsgMenu.setCseq(BigDecimal.valueOf(9));
		licenseMsgMenu.setCurl("");
		licenseMsgMenu.setTmenu(root);
		licenseMsgMenu.setCiconcls("icon-filter");
		licenseMsgMenu.setMainMenuFlag(1);
		menuDao.saveOrUpdate(licenseMsgMenu);
		initLicensedManagerDetail(licenseMsgMenu, menuDao);// 三级菜单
		// 短信平台10
		Tmenu smsMenu = new Tmenu();
		smsMenu.setCid("1000-4c26-92a6-driver171000");
		smsMenu.setCname("短信平台");
		smsMenu.setCseq(BigDecimal.valueOf(15));
		smsMenu.setCurl("");
		smsMenu.setTmenu(root);
		smsMenu.setCiconcls("icon-filter");
		smsMenu.setMainMenuFlag(1);
		menuDao.saveOrUpdate(smsMenu);
		initSMSManagerDetail(smsMenu, menuDao);// 三级菜单
		// 通知公告11
		Tmenu noticeMenu = new Tmenu();
		noticeMenu.setCid("1100-4c26-92a6-driver171100");
		noticeMenu.setCname("通知公告");
		noticeMenu.setCseq(BigDecimal.valueOf(16));
		noticeMenu.setCurl("");
		noticeMenu.setTmenu(root);
		noticeMenu.setCiconcls("icon-filter");
		noticeMenu.setMainMenuFlag(1);
		menuDao.saveOrUpdate(noticeMenu);
		initNoticeManagerDetail(noticeMenu, menuDao);// 三级菜单
		// 网站管理12
		Tmenu wzgl = new Tmenu();
		wzgl.setCid("1200-4c26-92a6-driver171200");
		wzgl.setCname("网站管理");
		wzgl.setCseq(BigDecimal.valueOf(12));
		wzgl.setCurl("");
		wzgl.setTmenu(root);
		wzgl.setCiconcls("icon-filter");
		wzgl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(wzgl);
		initNetworkManagerDetail(wzgl, menuDao);// 三级菜单
		// 统计分析管理13
		Tmenu tjfxgl = new Tmenu();
		tjfxgl.setCid("1300-4c26-92a6-driver171300");
		tjfxgl.setCname("统计分析");
		tjfxgl.setCseq(BigDecimal.valueOf(13));
		tjfxgl.setCurl("");
		tjfxgl.setTmenu(root);
		tjfxgl.setCiconcls("icon-filter");
		tjfxgl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(tjfxgl);
		initAnalysManagerDetail(tjfxgl, menuDao);// 三级菜单
		// 数据库管理14
		Tmenu sjkgl = new Tmenu();
		sjkgl.setCid("1400-4c26-92a6-driver171400");
		sjkgl.setCname("数据库管理");
		sjkgl.setCseq(BigDecimal.valueOf(14));
		sjkgl.setCurl("");
		sjkgl.setTmenu(root);
		sjkgl.setCiconcls("icon-filter");
		sjkgl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(sjkgl);
		initDatabaseManagerDetail(sjkgl, menuDao);// 三级菜单
		// 日志管理15
		Tmenu rzgl = new Tmenu();
		rzgl.setCid("1500-4c26-92a6-driver171500");
		rzgl.setCname("日志管理");
		rzgl.setCseq(BigDecimal.valueOf(15));
		rzgl.setCurl("");
		rzgl.setTmenu(root);
		rzgl.setCiconcls("icon-sum");
		rzgl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(rzgl);
		initLogManagerDetail(rzgl, menuDao);// 三级菜单
		// 机构管理16
		Tmenu organization = new Tmenu();
		organization.setCid("1600-4c26-92a6-driver171600");
		organization.setCname("机构管理");
		organization.setCseq(BigDecimal.valueOf(16));
		organization.setCurl("");
		organization.setTmenu(root);
		organization.setCiconcls("icon-filter");
		organization.setMainMenuFlag(1);
		menuDao.saveOrUpdate(organization);
		initOrganizationManagerDetail(organization, menuDao);// 三级菜单

		// 系统管理17
		Tmenu xtgl = new Tmenu();
		xtgl.setCid("1700-4c26-92a6-driver171700");
		xtgl.setCname("系统管理");
		xtgl.setCseq(BigDecimal.valueOf(17));
		xtgl.setCurl("");
		xtgl.setTmenu(root);
		xtgl.setCiconcls("icon-sum");
		xtgl.setMainMenuFlag(1);
		menuDao.saveOrUpdate(xtgl);
		initSystemManagerDetail(xtgl, menuDao);// 三级菜单

	}

	// 系统管理菜单明细（三级）
	private static void initSystemManagerDetail(Tmenu xtgl, BaseDaoI<Tmenu> menuDao) {
		Tmenu yhgl = new Tmenu();
		yhgl.setCid("1700-xtgl-92a6-driverl21701");
		yhgl.setCname("用户管理");
		yhgl.setCseq(BigDecimal.valueOf(1));
		yhgl.setCurl("/demo/userAction!user.action?resourceSn=1700-xtgl-92a6-driverl21701");
		yhgl.setTmenu(xtgl);
		yhgl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(yhgl);
		Tmenu jsgl = new Tmenu();
		jsgl.setCid("1700-xtgl-92a6-driverl21702");
		jsgl.setCname("角色管理");
		jsgl.setCseq(BigDecimal.valueOf(2));
		jsgl.setCurl("/demo/roleAction!role.action?resourceSn=1700-xtgl-92a6-driverl21702");
		jsgl.setCiconcls("icon-filter");
		jsgl.setTmenu(xtgl);
		menuDao.saveOrUpdate(jsgl);
		Tmenu qxgl = new Tmenu();
		qxgl.setCid("1700-xtgl-92a6-driverl21703");
		qxgl.setCname("权限管理");
		qxgl.setCseq(BigDecimal.valueOf(3));
		qxgl.setCurl("/demo/authAction!auth.action?resourceSn=1700-xtgl-92a6-driverl21703");
		qxgl.setTmenu(xtgl);
		qxgl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(qxgl);
		Tmenu cdgl = new Tmenu();
		cdgl.setCid("1700-xtgl-92a6-driverl21704");
		cdgl.setCname("菜单管理");
		cdgl.setCseq(BigDecimal.valueOf(4));
		cdgl.setCurl("/demo/menuAction!menu.action?resourceSn=1700-xtgl-92a6-driverl21704");
		cdgl.setCiconcls("icon-filter");
		cdgl.setTmenu(xtgl);
		menuDao.saveOrUpdate(cdgl);
		Tmenu buggl = new Tmenu();
		buggl.setCid("1700-xtgl-92a6-driverl21705");
		buggl.setCname("BUG管理");
		buggl.setCseq(BigDecimal.valueOf(5));
		buggl.setCurl("/demo/bugAction!bug.action?resourceSn=1700-xtgl-92a6-driverl21705");
		buggl.setCiconcls("icon-sum");
		buggl.setTmenu(xtgl);
		menuDao.saveOrUpdate(buggl);
		Tmenu sysConfig = new Tmenu();
		sysConfig.setCid("1700-xtgl-92a6-driverl21706");
		sysConfig.setCname("系统参数配置");
		sysConfig.setCseq(BigDecimal.valueOf(6));
		sysConfig.setCurl("/demo/netPiontAction!toNetPiontPage.action?resourceSn=1700-xtgl-92a6-driverl21706");
		sysConfig.setCiconcls("icon-sum");
		sysConfig.setTmenu(xtgl);
		menuDao.saveOrUpdate(sysConfig);
	}

	// 机构管理菜单明细（三级）
	private static void initOrganizationManagerDetail(Tmenu organization, BaseDaoI<Tmenu> menuDao) {

		Tmenu orgmsgmanager = new Tmenu();
		orgmsgmanager.setCid("1600-orgs-92a6-driverl21601");
		orgmsgmanager.setCname("机构信息管理");
		orgmsgmanager.setCseq(BigDecimal.valueOf(1));
		orgmsgmanager.setCurl("/demo/organizationAction!organization.action?resourceSn=1600-orgs-92a6-driverl21601");
		orgmsgmanager.setTmenu(organization);
		orgmsgmanager.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(orgmsgmanager);
		Tmenu personmsg = new Tmenu();
		personmsg.setCid("1600-orgs-92a6-driverl21602");
		personmsg.setCname("人员信息管理");
		personmsg.setCseq(BigDecimal.valueOf(2));
		personmsg.setCurl("/demo/personAction!toPersonPage.action?resourceSn=1600-orgs-92a6-driverl21602");
		personmsg.setTmenu(organization);
		personmsg.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(personmsg);

	}

	// 日志管理菜单明细（三级）
	private static void initLogManagerDetail(Tmenu rzgl, BaseDaoI<Tmenu> menuDao) {

		Tmenu yhdlrz = new Tmenu();
		yhdlrz.setCid("1500-logs-92a6-driverl21501");
		yhdlrz.setCname("日志列表");
		yhdlrz.setCseq(BigDecimal.valueOf(1));
		yhdlrz.setCurl("/demo/logAction!toLogPage.action?resourceSn=1500-logs-92a6-driverl21501");
		yhdlrz.setTmenu(rzgl);
		menuDao.saveOrUpdate(yhdlrz);
	}

	// 数据库管理菜单明细（三级）
	private static void initDatabaseManagerDetail(Tmenu sjkgl, BaseDaoI<Tmenu> menuDao) {
		Tmenu druidIndex = new Tmenu();
		druidIndex.setCid("1400-datas-92a6-driverl21401");
		druidIndex.setCname("druid监控");
		druidIndex.setCseq(BigDecimal.valueOf(1));
		druidIndex.setCurl("/datasourceAction!druid.action?resourceSn=1400-datas-92a6-driverl21401");
		druidIndex.setCiconcls("icon-filter");
		druidIndex.setTmenu(sjkgl);
		menuDao.saveOrUpdate(druidIndex);
	}

	// 统计分析管理菜单明细（三级）
	private static void initAnalysManagerDetail(Tmenu tjfxgl, BaseDaoI<Tmenu> menuDao) {
		Tmenu xytjgl = new Tmenu();
		xytjgl.setCid("1300-tjxs-92a6-driverl21301");
		xytjgl.setCname("学员统计");
		xytjgl.setCseq(BigDecimal.valueOf(1));
		xytjgl.setCurl("");
		xytjgl.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(xytjgl);
		Tmenu rxtjrbj = new Tmenu();
		rxtjrbj.setCid("1300-tjxs-92a6-driverl21302");
		rxtjrbj.setCname("报名统计日报表");
		rxtjrbj.setCseq(BigDecimal.valueOf(2));
		rxtjrbj.setCurl("");
		rxtjrbj.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(rxtjrbj);
		Tmenu rxtjzhbj = new Tmenu();
		rxtjzhbj.setCid("1300-tjxs-92a6-driverl21303");
		rxtjzhbj.setCname("报名统计周报表");
		rxtjzhbj.setCseq(BigDecimal.valueOf(3));
		rxtjzhbj.setCurl("");
		rxtjzhbj.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(rxtjzhbj);
		Tmenu rxtjybj = new Tmenu();
		rxtjybj.setCid("1300-tjxs-92a6-driverl21304");
		rxtjybj.setCname("报名统计月报表");
		rxtjybj.setCseq(BigDecimal.valueOf(4));
		rxtjybj.setCurl("");
		rxtjybj.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(rxtjybj);
		Tmenu rxtjnbj = new Tmenu();
		rxtjnbj.setCid("1300-tjxs-92a6-driverl21305");
		rxtjnbj.setCname("报名统计年报表");
		rxtjnbj.setCseq(BigDecimal.valueOf(5));
		rxtjnbj.setCurl("");
		rxtjnbj.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(rxtjnbj);
		Tmenu zhshqktj = new Tmenu();
		zhshqktj.setCid("1300-tjxs-92a6-driverl21306");
		zhshqktj.setCname("招生情况统计报表");
		zhshqktj.setCseq(BigDecimal.valueOf(6));
		zhshqktj.setCurl("");
		zhshqktj.setTmenu(tjfxgl);
		menuDao.saveOrUpdate(zhshqktj);
		Tmenu fxxycount = new Tmenu();
		fxxycount.setCid("1300-tjxs-92a6-driverl21307");
		fxxycount.setCname("分校学员统计");
		fxxycount.setCseq(BigDecimal.valueOf(7));
		fxxycount.setCurl("");
		fxxycount.setTmenu(tjfxgl);
		fxxycount.setCiconcls("");
		menuDao.saveOrUpdate(fxxycount);
		Tmenu fxexamcount = new Tmenu();
		fxexamcount.setCid("1300-tjxs-92a6-driverl21308");
		fxexamcount.setCname("分校考试通过率");
		fxexamcount.setCseq(BigDecimal.valueOf(8));
		fxexamcount.setCurl("");
		fxexamcount.setTmenu(tjfxgl);
		fxexamcount.setCiconcls("");
		menuDao.saveOrUpdate(fxexamcount);
	}

	// 证件管理菜单明细（三级）
	private static void initNetworkManagerDetail(Tmenu wzgl, BaseDaoI<Tmenu> menuDao) {
		Tmenu wznrgl = new Tmenu();
		wznrgl.setCid("1200-netm-92a6-driverl21201");
		wznrgl.setCname("网站内容管理");
		wznrgl.setCseq(BigDecimal.valueOf(1));
		wznrgl.setCurl("/demo/articleAction!article.action?resourceSn=1200-netm-92a6-driverl21201");
		wznrgl.setTmenu(wzgl);
		menuDao.saveOrUpdate(wznrgl);
	}

	// 通知公告管理菜单明细（三级）
	private static void initNoticeManagerDetail(Tmenu noticeMenu, BaseDaoI<Tmenu> menuDao) {
		Tmenu noticeMenu01 = new Tmenu();
		noticeMenu01.setCid("1100-noti-92a6-driverl21101");
		noticeMenu01.setCname("公告管理");
		noticeMenu01.setCseq(BigDecimal.valueOf(1));
		noticeMenu01.setCurl("/demo/oilcardAction!toOilcardPage.action?resourceSn=1100-noti-92a6-driverl21101");
		noticeMenu01.setTmenu(noticeMenu);
		noticeMenu01.setCiconcls("icon-man");
		menuDao.saveOrUpdate(noticeMenu01);
	}

	// 短信管理菜单明细（三级）
	private static void initSMSManagerDetail(Tmenu smsMenu, BaseDaoI<Tmenu> menuDao) {
		Tmenu smsMenu01 = new Tmenu();
		smsMenu01.setCid("1000-msgl-92a6-driverl21001");
		smsMenu01.setCname("短信记录");
		smsMenu01.setCseq(BigDecimal.valueOf(1));
		smsMenu01.setCurl("/demo/smsAction!toSMSPage.action?resourceSn=1000-msgl-92a6-driverl21001");
		smsMenu01.setTmenu(smsMenu);
		smsMenu01.setCiconcls("icon-man");
		menuDao.saveOrUpdate(smsMenu01);
	}

	// 证件管理菜单明细（三级）
	private static void initLicensedManagerDetail(Tmenu licenseMsgMenu, BaseDaoI<Tmenu> menuDao) {
		Tmenu jszgl = new Tmenu();
		jszgl.setCid("0900-zjgl-92a6-driverl20901");
		jszgl.setCname("驾驶证管理");
		jszgl.setCseq(BigDecimal.valueOf(4));
		jszgl.setCurl("/demo/driverLicenseAction!toDriverLicensePage.action?resourceSn=0900-zjgl-92a6-driverl20901");
		jszgl.setTmenu(licenseMsgMenu);
		jszgl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(jszgl);
		Tmenu zhgzgl = new Tmenu();
		zhgzgl.setCid("0900-zjgl-92a6-driverl20902");
		zhgzgl.setCname("资格证管理");
		zhgzgl.setCseq(BigDecimal.valueOf(5));
		zhgzgl.setCurl(
				"/demo/qualificationLicenseAction!toQualificationLicensePage.action?resourceSn=0900-zjgl-92a6-driverl20901");
		zhgzgl.setTmenu(licenseMsgMenu);
		zhgzgl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(zhgzgl);
	}

	// 油卡管理菜单明细（三级）
	private static void initOilcardManagerDetail(Tmenu oilcardMenu, BaseDaoI<Tmenu> menuDao) {
		Tmenu oilcardMenu01 = new Tmenu();
		oilcardMenu01.setCid("0800-oild-92a6-driverl10801");
		oilcardMenu01.setCname("油卡基本信息");
		oilcardMenu01.setCseq(BigDecimal.valueOf(1));
		oilcardMenu01.setCurl("/demo/oilcardAction!toOilcardPage.action?resourceSn=0800-oild-92a6-driverl10801");
		oilcardMenu01.setTmenu(oilcardMenu);
		oilcardMenu01.setCiconcls("icon-man");
		menuDao.saveOrUpdate(oilcardMenu01);
		Tmenu oilcardMenu02 = new Tmenu();
		oilcardMenu02.setCid("0800-oild-92a6-driverl10802");
		oilcardMenu02.setCname("充值明细列表");
		oilcardMenu02.setCseq(BigDecimal.valueOf(2));
		oilcardMenu02.setCurl(
				"/demo/rechargeOilcardAction!toRechargeOilcardListPage.action?resourceSn=0800-oild-92a6-driverl10802");
		oilcardMenu02.setTmenu(oilcardMenu);
		oilcardMenu02.setCiconcls("icon-man");
		menuDao.saveOrUpdate(oilcardMenu02);
		Tmenu oilcardMenu03 = new Tmenu();
		oilcardMenu03.setCid("0800-oild-92a6-driverl10803");
		oilcardMenu03.setCname("使用明细列表");
		oilcardMenu03.setCseq(BigDecimal.valueOf(3));
		oilcardMenu03.setCurl(
				"/demo/oilcardUsingAction!toOilcardUsingListPage.action?resourceSn=0800-oild-92a6-driverl10803");
		oilcardMenu03.setTmenu(oilcardMenu);
		oilcardMenu03.setCiconcls("icon-man");
		menuDao.saveOrUpdate(oilcardMenu03);
	}

	// 微信管理菜单明细（三级）
	private static void initWechatManagerDetail(Tmenu wxfw, BaseDaoI<Tmenu> menuDao) {
		Tmenu wxxcbm = new Tmenu();
		wxxcbm.setCid("0700-wxgl-92a6-driverl20701");
		wxxcbm.setCname("微信学车报名");
		wxxcbm.setCseq(BigDecimal.valueOf(1));
		wxxcbm.setCurl("/demo/signUpAction!signUpList.action?resourceSn=0700-wxgl-92a6-driverl20701");
		wxxcbm.setTmenu(wxfw);
		menuDao.saveOrUpdate(wxxcbm);
		Tmenu pjjl = new Tmenu();
		pjjl.setCid("0700-wxgl-92a6-driverl20702");
		pjjl.setCname("评价教练");
		pjjl.setCseq(BigDecimal.valueOf(2));
		pjjl.setCurl("/demo/assessAction!assessList.action?resourceSn=0700-wxgl-92a6-driverl20702");
		pjjl.setTmenu(wxfw);
		menuDao.saveOrUpdate(pjjl);
		Tmenu commentSalesmanMenu = new Tmenu();
		commentSalesmanMenu.setCid("0700-wxgl-92a6-driverl20703");
		commentSalesmanMenu.setCname("评价业务员");
		commentSalesmanMenu.setCseq(BigDecimal.valueOf(3));
		commentSalesmanMenu
				.setCurl("/demo/salesmanAction!toSalesmanList.action?resourceSn=0700-wxgl-92a6-driverl20703");
		commentSalesmanMenu.setTmenu(wxfw);
		menuDao.saveOrUpdate(commentSalesmanMenu);
		Tmenu ndfk = new Tmenu();
		ndfk.setCid("0700-wxgl-92a6-driverl20704");
		ndfk.setCname("难点反馈");
		ndfk.setCseq(BigDecimal.valueOf(4));
		ndfk.setCurl("/demo/difficultyAction!difficultyList.action?resourceSn=0700-wxgl-92a6-driverl20704");
		ndfk.setTmenu(wxfw);
		menuDao.saveOrUpdate(ndfk);
		Tmenu wxyhgl = new Tmenu();
		wxyhgl.setCid("0700-wxgl-92a6-driverl20705");
		wxyhgl.setCname("微信用户管理");
		wxyhgl.setCseq(BigDecimal.valueOf(5));
		wxyhgl.setCurl("/demo/weixinUserAction!userList.action?resourceSn=0700-wxgl-92a6-driverl20705");
		wxyhgl.setTmenu(wxfw);
		menuDao.saveOrUpdate(wxyhgl);
		Tmenu wxzdycd = new Tmenu();
		wxzdycd.setCid("0700-wxgl-92a6-driverl20706");
		wxzdycd.setCname("微信自定义菜单");
		wxzdycd.setCseq(BigDecimal.valueOf(6));
		wxzdycd.setCurl("/demo/weixinMenuAction!menu.action?resourceSn=0700-wxgl-92a6-driverl20706");
		wxzdycd.setTmenu(wxfw);
		menuDao.saveOrUpdate(wxzdycd);
	}

	// 财务管理菜单明细（三级）
	private static void initFeeManagerDetail(Tmenu financialMenu, BaseDaoI<Tmenu> menuDao) {
		Tmenu financialMenu01 = new Tmenu();
		financialMenu01.setCid("0600-cwgl-92a6-driverl20601");
		financialMenu01.setCname("收费配置");
		financialMenu01.setCseq(BigDecimal.valueOf(1));
		financialMenu01
				.setCurl("/demo/feeDetailTypeAction!toFeeDetailTypePage.action?resourceSn=0600-cwgl-92a6-driverl20601");
		financialMenu01.setTmenu(financialMenu);
		financialMenu01.setCiconcls("icon-man");
		menuDao.saveOrUpdate(financialMenu01);
		Tmenu financialMenu02 = new Tmenu();
		financialMenu02.setCid("0600-cwgl-92a6-driverl20602");
		financialMenu02.setCname("收费明细");
		financialMenu02.setCseq(BigDecimal.valueOf(1));
		financialMenu02
				.setCurl("/demo/collectFeeAction!toCollectFeePage.action?resourceSn=0600-cwgl-92a6-driverl20602");
		financialMenu02.setTmenu(financialMenu);
		financialMenu02.setCiconcls("icon-man");
		menuDao.saveOrUpdate(financialMenu02);
	}

	// 预约管理菜单明细（三级）
	private static void initReservationManagerDetail(Tmenu xydagl, BaseDaoI<Tmenu> menuDao) {
		Tmenu studentDagl = new Tmenu();
		studentDagl.setCid("0500-yygl-92a6-driverl20501");
		studentDagl.setCname("学员档案管理");
		studentDagl.setCseq(BigDecimal.valueOf(1));
		studentDagl.setCurl("/demo/studentFileAction!toStudentFilePage.action?resourceSn=0500-yygl-92a6-driverl20501");
		studentDagl.setTmenu(xydagl);
		studentDagl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(studentDagl);
		Tmenu studentXsgl = new Tmenu();
		studentXsgl.setCid("0500-yygl-92a6-driverl20502");
		studentXsgl.setCname("学员学时管理");
		studentXsgl.setCseq(BigDecimal.valueOf(2));
		studentXsgl.setCurl("/demo/timingAction!toTimingPage.action?resourceSn=0500-yygl-92a6-driverl20502");
		studentXsgl.setTmenu(xydagl);
		studentXsgl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(studentXsgl);
		Tmenu studentXsyygl = new Tmenu();
		studentXsyygl.setCid("0500-yygl-92a6-driverl20503");
		studentXsyygl.setCname("学员预约管理");
		studentXsyygl.setCseq(BigDecimal.valueOf(3));
		studentXsyygl
				.setCurl("/demo/reservationAction!toReservationPage.action?resourceSn=0500-yygl-92a6-driverl20503");
		studentXsyygl.setTmenu(xydagl);
		studentXsyygl.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(studentXsyygl);
		Tmenu timercard = new Tmenu();
		timercard.setCid("0500-yygl-92a6-driverl20504");
		timercard.setCname("科目学时安排表");
		timercard.setCseq(BigDecimal.valueOf(4));
		timercard.setCurl("/demo/timerCardAction!timerCard.action?resourceSn=0500-yygl-92a6-driverl20504");
		timercard.setTmenu(xydagl);
		timercard.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(timercard);
	}

	// 考试管理菜单明细（三级）
	private static void initExamManagerDetail(Tmenu exammng, BaseDaoI<Tmenu> menuDao) {
		Tmenu studentexam = new Tmenu();
		studentexam.setCid("0400-ksgl-92a6-driverl20401");
		studentexam.setCname("考试计划");
		studentexam.setCseq(BigDecimal.valueOf(1));
		studentexam.setCurl("/demo/examAnalysAction!toExamAnalysPage.action?resourceSn=0400-ksgl-92a6-driverl20401");
		studentexam.setTmenu(exammng);
		studentexam.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(studentexam);
		Tmenu studentExmaSbuject1 = new Tmenu();
		studentExmaSbuject1.setCid("0400-ksgl-92a6-driverl20402");
		studentExmaSbuject1.setCname("科目一考试");
		studentExmaSbuject1.setCseq(BigDecimal.valueOf(2));
		studentExmaSbuject1
				.setCurl("/demo/examAnalysAction!toSubject1Page.action?resourceSn=0400-ksgl-92a6-driverl20402");
		studentExmaSbuject1.setTmenu(exammng);
		studentExmaSbuject1.setCiconcls("icon-remove");
		menuDao.saveOrUpdate(studentExmaSbuject1);
		Tmenu studentExmaSbuject2 = new Tmenu();
		studentExmaSbuject2.setCid("0400-ksgl-92a6-driverl20403");
		studentExmaSbuject2.setCname("科目二考试");
		studentExmaSbuject2.setCseq(BigDecimal.valueOf(3));
		studentExmaSbuject2
				.setCurl("/demo/examAnalysAction!toSubject2Page.action?resourceSn=0400-ksgl-92a6-driverl20403");
		studentExmaSbuject2.setTmenu(exammng);
		studentExmaSbuject2.setCiconcls("icon-remove");
		menuDao.saveOrUpdate(studentExmaSbuject2);
		Tmenu studentExmaSbuject3 = new Tmenu();
		studentExmaSbuject3.setCid("0400-ksgl-92a6-driverl20404");
		studentExmaSbuject3.setCname("科目三考试");
		studentExmaSbuject3.setCseq(BigDecimal.valueOf(4));
		studentExmaSbuject3
				.setCurl("/demo/examAnalysAction!toSubject3Page.action?resourceSn=0400-ksgl-92a6-driverl20404");
		studentExmaSbuject3.setTmenu(exammng);
		studentExmaSbuject3.setCiconcls("icon-remove");
		menuDao.saveOrUpdate(studentExmaSbuject3);
		Tmenu studentExmaSbuject4 = new Tmenu();
		studentExmaSbuject4.setCid("0400-ksgl-92a6-driverl20405");
		studentExmaSbuject4.setCname("科目四考试");
		studentExmaSbuject4.setCseq(BigDecimal.valueOf(5));
		studentExmaSbuject4
				.setCurl("/demo/examAnalysAction!toSubject4Page.action?resourceSn=0400-ksgl-92a6-driverl20405");
		studentExmaSbuject4.setTmenu(exammng);
		studentExmaSbuject4.setCiconcls("icon-remove");
		menuDao.saveOrUpdate(studentExmaSbuject4);
		Tmenu studentExmaSbuject5 = new Tmenu();
		studentExmaSbuject5.setCid("0400-ksgl-92a6-driverl20406");
		studentExmaSbuject5.setCname("科目五考试");
		studentExmaSbuject5.setCseq(BigDecimal.valueOf(6));
		studentExmaSbuject5
				.setCurl("/demo/examAnalysAction!toSubject5Page.action?resourceSn=0400-ksgl-92a6-driverl20406");
		studentExmaSbuject5.setTmenu(exammng);
		studentExmaSbuject5.setCiconcls("icon-remove");
		menuDao.saveOrUpdate(studentExmaSbuject5);
		Tmenu studentexamTotal = new Tmenu();
		studentexamTotal.setCid("0400-ksgl-92a6-driverl20407");
		studentexamTotal.setCname("学员考试总列表");
		studentexamTotal.setCseq(BigDecimal.valueOf(7));
		studentexamTotal
				.setCurl("/demo/studentExamAction!toAllStudentExamPage.action?resourceSn=0400-ksgl-92a6-driverl20407");
		studentexamTotal.setTmenu(exammng);
		studentexamTotal.setCiconcls("icon-man");
		menuDao.saveOrUpdate(studentexamTotal);
	}

	// 车辆管理菜单明细（三级）
	private static void initCarManagerDetail(Tmenu carmanager, BaseDaoI<Tmenu> menuDao) {
		Tmenu newcarmanager = new Tmenu();
		newcarmanager.setCid("0300-clgl-92a6-driverl20301");
		newcarmanager.setCname("车辆信息管理");
		newcarmanager.setCseq(BigDecimal.valueOf(1));
		newcarmanager.setCurl("/demo/carAction!car.action?resourceSn=0300-clgl-92a6-driverl20301");
		newcarmanager.setTmenu(carmanager);
		newcarmanager.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(newcarmanager);
		Tmenu useringcar = new Tmenu();
		useringcar.setCid("0300-clgl-92a6-driverl20302");
		useringcar.setCname("车辆使用情况");
		useringcar.setCseq(BigDecimal.valueOf(2));
		useringcar.setCurl("/demo/usingCarAction!toUsingCarPage.action?resourceSn=0300-clgl-92a6-driverl20302");
		useringcar.setTmenu(carmanager);
		useringcar.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(useringcar);
		Tmenu autocar = new Tmenu();
		autocar.setCid("0300-clgl-92a6-driverl20303");
		autocar.setCname("自动档排班");
		autocar.setCseq(BigDecimal.valueOf(3));
		autocar.setCurl("/demo/usingCarAction!toAutoCarPage.action?resourceSn=0300-clgl-92a6-driverl20303");
		autocar.setTmenu(carmanager);
		autocar.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(autocar);
	}

	// 教练管理菜单明细（三级）
	private static void initTrainerManagerDetail(Tmenu trainermng, BaseDaoI<Tmenu> menuDao) {
		Tmenu newtrainer = new Tmenu();
		newtrainer.setCid("0200-jlgl-92a6-driverl20201");
		newtrainer.setCname("教练信息管理");
		newtrainer.setCseq(BigDecimal.valueOf(1));
		newtrainer.setCurl("/demo/trainerAction!toTrainerPage.action?resourceSn=0200-jlgl-92a6-driverl20201");
		newtrainer.setTmenu(trainermng);
		newtrainer.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(newtrainer);
		Tmenu workarrange = new Tmenu();
		workarrange.setCid("0200-jlgl-92a6-driverl20202");
		workarrange.setCname("教练排班");
		workarrange.setCseq(BigDecimal.valueOf(2));
		workarrange.setCurl(
				"/demo/trainerArrangeAction!toTrainerArrangePage.action?resourceSn=0200-jlgl-92a6-driverl20202");
		workarrange.setTmenu(trainermng);
		workarrange.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(workarrange);
		Tmenu traienrDetail = new Tmenu();
		traienrDetail.setCid("0200-jlgl-92a6-driverl20203");
		traienrDetail.setCname("教练预约");
		traienrDetail.setCseq(BigDecimal.valueOf(3));
		traienrDetail.setCurl(
				"/demo/trainerReservationAction!toTrainerReservationPage.action?resourceSn=0200-jlgl-92a6-driverl20203");
		traienrDetail.setTmenu(trainermng);
		traienrDetail.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(traienrDetail);
		Tmenu commentStudent = new Tmenu();
		commentStudent.setCid("0200-jlgl-92a6-driverl20204");
		commentStudent.setCname("点评学员");
		commentStudent.setCseq(BigDecimal.valueOf(4));
		commentStudent.setCurl("/demo/commentStudentAction!studentList.action?resourceSn=0200-jlgl-92a6-driverl20204");
		commentStudent.setTmenu(trainermng);
		commentStudent.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(commentStudent);
	}

	// 学员管理菜单明细（三级）
	private static void initStudentManagerDetail(Tmenu xygl, BaseDaoI<Tmenu> menuDao) {
		Tmenu xshbm = new Tmenu();
		xshbm.setCid("0100-xygl-92a6-driverl20101");
		xshbm.setCname("学员信息管理");
		xshbm.setCseq(BigDecimal.valueOf(1));
		xshbm.setCurl("/demo/studentAction!toStudentPage.action?resourceSn=0100-xygl-92a6-driverl20101");
		xshbm.setTmenu(xygl);
		xshbm.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(xshbm);
		Tmenu studentprogress = new Tmenu();
		studentprogress.setCid("0100-xygl-92a6-driverl20102");
		studentprogress.setCname("学员进度管理");
		studentprogress.setCseq(BigDecimal.valueOf(2));
		studentprogress.setCurl("/demo/progressAction!toProgressPage.action?resourceSn=0100-xygl-92a6-driverl20102");
		studentprogress.setTmenu(xygl);
		studentprogress.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(studentprogress);
		Tmenu txdj = new Tmenu();
		txdj.setCid("0100-xygl-92a6-driverl20103");
		txdj.setCname("学员退学登记");
		txdj.setCseq(BigDecimal.valueOf(3));
		txdj.setCurl("/demo/quitschoolAction!toQuitschoolPage.action?resourceSn=0100-xygl-92a6-driverl20103");
		txdj.setTmenu(xygl);
		txdj.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(txdj);
		Tmenu stdotimercard = new Tmenu();
		stdotimercard.setCid("0100-xygl-92a6-driverl20104");
		stdotimercard.setCname("学员计时卡管理");
		stdotimercard.setCseq(BigDecimal.valueOf(4));
		stdotimercard
				.setCurl("/demo/studentTimerCardAction!studentTimerCard.action?resourceSn=0100-xygl-92a6-driverl20104");
		stdotimercard.setTmenu(xygl);
		stdotimercard.setCiconcls("icon-filter");
		menuDao.saveOrUpdate(stdotimercard);
	}
}
