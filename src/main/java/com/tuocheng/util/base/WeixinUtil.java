package com.tuocheng.util.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.tuocheng.weixin.base.Weixin;

/**
 * 跟应用相关的一些公用方法
 * 
 * @author laobu
 * 
 */
public class WeixinUtil {

	public static String BS_ORGANIZATION_ID = "b78ffef2-7c54-40fe-be4b-1910a87c8bbs"; // 百色校区
	public static String TL_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb4e9tl"; // 田林校区
	public static String DB_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb4e9db"; // 德保校区
	public static String LL_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb4e9ll"; // 隆林校区
	public static String LEY_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb888ly"; // 乐业校区
	public static String NP_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb4e9np"; // 那坡校区
	public static String XL_ORGANIZATION_ID = "357c1d29-c991-4eed-a331-949f3eb4e9xl"; // 西林校区
	public static String LY_ORGANIZATION_ID = "357c1d29-c991-4eed-a881-949f3eb008ly"; // 凌云校区
	public static String PG_ORGANIZATION_ID = "606437a6-574c-4b0b-b79f-1a98bd4a45pg"; // 平果校区
	public static String JX_ORGANIZATION_ID = "6b698583-3aad-4702-8aa5-6e55b27df8jx"; // 靖西校区
	public static String TD_ORGANIZATION_ID = "7d15f11b-2928-41e8-8406-8b49cf3939td"; // 田东校区
	public static String TY_ORGANIZATION_ID = "c0510169-8edc-470e-b038-c299c7735bty"; // 田阳校区
	
	private static String URL="http://ccjx.tc1680.cn";

	private List<WeixinParams> list = null;

	/**
	 * 初始化微信配置
	 */
	public void initWeixinParams() {
		// 百色驾校微信配置
		WeixinParams bsWX = new WeixinParams();
		bsWX.setOrganizationId(BS_ORGANIZATION_ID);
		bsWX.setToken("driverschool");
		bsWX.setAppId("wxada38c36dfc50310");
		bsWX.setSecret("8b60982e0a9e6f2b80e079d98c06f54d");
		bsWX.setEncodingAesKey("JcD1aXEtYgh5dcq0ZH3qn6leG15vxqeC399jlmzRhXQ");
		bsWX.setDomainName(URL);
		// 田阳
		WeixinParams tyWX = new WeixinParams();
		tyWX.setOrganizationId(TY_ORGANIZATION_ID);
		tyWX.setToken("driverschool");
		tyWX.setAppId("wxcbd7746ec6884f8c");
		tyWX.setSecret("956eaf940abcb5bdb5d05e84d848455f");
		tyWX.setEncodingAesKey("6qNgkSZX0vC7JX5NjcfaJchsBVs6EeLR7MRwgAz185l");
		tyWX.setDomainName(URL);
		// 田东校区
		WeixinParams tdWX = new WeixinParams();
		tdWX.setOrganizationId(TD_ORGANIZATION_ID);
		tdWX.setToken("driverschool");
		tdWX.setAppId("wx08ebd13371a0c737");
		tdWX.setSecret("094fdf8b7ff67a8903dc43c60e0f30ea");
		tdWX.setEncodingAesKey("OshQ868tXe5oY6kzYUsb5Y4VjjxoahKZxLHqsN8YaZW");
		tdWX.setDomainName(URL);
		// 平果
		WeixinParams pgWX = new WeixinParams();
		pgWX.setOrganizationId(PG_ORGANIZATION_ID);
		pgWX.setToken("driverschool");
		pgWX.setAppId("wxb84c87315db3e280");
		pgWX.setSecret("d02966c471ba04d61eaa04757dff304c");
		pgWX.setEncodingAesKey("");
		pgWX.setDomainName(URL);
		// 德保驾校微信配置(已正式)
		WeixinParams dbWX = new WeixinParams();
		dbWX.setOrganizationId(DB_ORGANIZATION_ID);
		dbWX.setToken("driverschool");
		dbWX.setAppId("wxb12613a36acd349e");
		dbWX.setSecret("e3e4e969ecb382f958485678914a2d8c");
		dbWX.setEncodingAesKey("ArdQrpfVrQyiRdq0iz7RDTtQghYhiodtr4g75ojcJiu");
		dbWX.setDomainName(URL);
		// 靖西众安驾校
		WeixinParams jxWX = new WeixinParams();
		jxWX.setOrganizationId(JX_ORGANIZATION_ID);
		jxWX.setToken("driverschool");
		jxWX.setAppId("wx885f4020763fd74a");
		jxWX.setSecret("e25cf04222038859853cfcb335177a94");
		jxWX.setEncodingAesKey("rGEbJNHjZlck1U9BnslLywVnC3VhL4B4fnxN5PphANd");
		jxWX.setDomainName(URL);
		// 那坡校区
		WeixinParams npWX = new WeixinParams();
		npWX.setOrganizationId(NP_ORGANIZATION_ID);
		npWX.setToken("driverschool");
		npWX.setAppId("wxbdaa0785e183ba17");
		npWX.setSecret("3e2d4192968ca25289ffb42eac175880");
		npWX.setEncodingAesKey("");
		npWX.setDomainName(URL);

		// 田林驾校微信配置(已正式)
		WeixinParams tlWX = new WeixinParams();
		tlWX.setOrganizationId(TL_ORGANIZATION_ID);
		tlWX.setToken("driverschool");
		tlWX.setAppId("wx8219260e340c6eca");
		tlWX.setSecret("b5531d1367623087f30204bd8d3a4059");
		tlWX.setEncodingAesKey("I4hyODREaOLFsDNOexugrJpM59amJjiD4ipZ67hUVUU");
		tlWX.setDomainName(URL);
		// 西林(已正式)
		WeixinParams xlWX = new WeixinParams();
		xlWX.setOrganizationId(XL_ORGANIZATION_ID);
		xlWX.setToken("driverschool");
		xlWX.setAppId("wxe8c0886cb55f54ab");
		xlWX.setSecret("5c1b4765c8aa840dbfbb230fe02e516d");
		xlWX.setEncodingAesKey("4cu8lHlLeTeaGMJRUsG9Bdh66TZ5m1m5naSEdB1pHL0");
		xlWX.setDomainName(URL);
		// 隆林驾校微信配置
		WeixinParams llWX = new WeixinParams();
		llWX.setOrganizationId(LL_ORGANIZATION_ID);
		llWX.setToken("driverschool");
		llWX.setAppId("wxb84c87315db3e280");
		llWX.setSecret("d02966c471ba04d61eaa04757dff304c");
		llWX.setEncodingAesKey("");
		llWX.setDomainName(URL);
		// 凌云
		WeixinParams lyWX = new WeixinParams();
		lyWX.setOrganizationId(LY_ORGANIZATION_ID);
		lyWX.setToken("driverschool");
		lyWX.setAppId("wxb84c87315db3e280");
		lyWX.setSecret("d02966c471ba04d61eaa04757dff304c");
		lyWX.setEncodingAesKey("");
		lyWX.setDomainName(URL);
		// 乐业驾校微信配置
		WeixinParams leyWX = new WeixinParams();
		leyWX.setOrganizationId(LEY_ORGANIZATION_ID);
		leyWX.setToken("driverschool");
		leyWX.setAppId("wx6a50d47637224b68");
		leyWX.setSecret("d7df15f483e483a9f3bc457731e05a5a");
		leyWX.setEncodingAesKey("");
		leyWX.setDomainName(URL);

		list = new ArrayList<WeixinParams>();
		list.add(bsWX);
		list.add(tlWX);
		list.add(leyWX);
		list.add(dbWX);
		list.add(llWX);
		list.add(npWX);
		list.add(xlWX);
		list.add(lyWX);
		list.add(pgWX);
		list.add(jxWX);
		list.add(tdWX);
		list.add(tyWX);
	}

	/**
	 * 根据机构ID,获取机构的微信类
	 * 
	 * @param organizationId
	 * @return
	 */
	public Weixin getWeixinByOrganizationId(String organizationId) {
		WeixinParams wp = getWeixinParamsByOrganizationId(organizationId);
		if (wp != null) {
			return new Weixin(wp.getToken(), wp.getAppId(), wp.getSecret(),
					wp.getEncodingAesKey());
		} else {
			return null;
		}
	}

	/**
	 * 根据机构ID,获取机构的微信配置信息
	 * 
	 * @param organizationId
	 * @return
	 */
	public WeixinParams getWeixinParamsByOrganizationId(String organizationId) {
		if (Util.isNotNull(organizationId)) {
			if (list == null)
				initWeixinParams();
			for (int i = 0; list != null && i < list.size(); i++) {
				WeixinParams wp = list.get(i);
				if (organizationId.equals(wp.getOrganizationId())) {
					return wp;
				}
			}
		}
		return null;
	}

	public class WeixinParams {
		private String organizationId;
		private String token;
		private String appId;
		private String secret;
		private String encodingAesKey;
		private String domainName;

		public String getOrganizationId() {
			return organizationId;
		}

		public void setOrganizationId(String organizationId) {
			this.organizationId = organizationId;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public String getEncodingAesKey() {
			return encodingAesKey;
		}

		public void setEncodingAesKey(String encodingAesKey) {
			this.encodingAesKey = encodingAesKey;
		}

		public String getDomainName() {
			return domainName;
		}

		public void setDomainName(String domainName) {
			try {
				URL url = new URL(domainName);
				this.domainName = url.getProtocol() + "://"
						+ url.getAuthority();
			} catch (MalformedURLException e) {
				this.domainName = domainName;
			}
		}
	}

}
