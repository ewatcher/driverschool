package com.tuocheng.action.guest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuocheng.weixin.message.EventMessage;
import com.tuocheng.weixin.message.WeixinMessageService;
import com.tuocheng.weixin.user.WeixinUserBean;
import com.tuocheng.weixin.user.WeixinUserService;
import com.tuocheng.action.base.SimpleAction;
import com.tuocheng.model.demo.TweixinMenu;
import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.service.demo.WeixinMenuServiceI;
import com.tuocheng.service.demo.WeixinMessageServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.message.Message;
import com.tuocheng.util.base.ResourceUtil;

/**
 * 微信接口action
 * 
 * @author 李杰
 * 
 */

@Namespace("/api")
@Action(value = "weixinApiAction")
public class WeixinApiAction extends SimpleAction {

	/**
	 * 测试接入地址:http://demo.tc1680.com/driverschool/api/weixinApiAction!weixin.action
	 * 正式接入地址:http://jtjx.tc1680.com/driverschool/api/weixinApiAction!weixin.action
	 */
	private static final long serialVersionUID = 1L;
	
	private WeixinMessageServiceI weixinMessageService;
	private WeixinUserServiceI weixinUserService;
	private WeixinMenuServiceI weixinMenuService;
	
	@Autowired
	public void setWeixinMessageService(WeixinMessageServiceI weixinMessageService) {
		this.weixinMessageService = weixinMessageService;
	}
	
	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	} 
	
	@Autowired
	public void setWeixinMenuService(WeixinMenuServiceI weixinMenuService) {
		this.weixinMenuService = weixinMenuService;
	} 
	
	/**
	 * 百色驾校微信接口
	 */
	public void bsWeixin(){
		weixin(WeixinUtil.BS_ORGANIZATION_ID);
	}
	
	/**
	 * 田林驾校微信接口
	 */
	public void tlWeixin(){
		weixin(WeixinUtil.TL_ORGANIZATION_ID);
	}
	
	/**
	 * 乐业微信接口
	 */
	public void leyWeixin(){
		weixin(WeixinUtil.LEY_ORGANIZATION_ID);
	}
	
	/**
	 * 德保微信接口
	 */
	public void dbWeixin(){
		weixin(WeixinUtil.DB_ORGANIZATION_ID);
	}
	
	/**
	 * 隆林微信接口
	 */
	public void llWeixin(){
		weixin(WeixinUtil.LL_ORGANIZATION_ID);
	}
	
	/**
	 * 那坡微信接口
	 */
	public void npWeixin(){
		weixin(WeixinUtil.NP_ORGANIZATION_ID);
	}
	
	/**
	 * 西林微信接口
	 */
	public void xlWeixin(){
		weixin(WeixinUtil.XL_ORGANIZATION_ID);
	}
	
	/**
	 * 凌云微信接口
	 */
	public void lyWeixin(){
		weixin(WeixinUtil.LY_ORGANIZATION_ID);
	}
	
	/**
	 * 平果微信接口
	 */
	public void pgWeixin(){
		weixin(WeixinUtil.PG_ORGANIZATION_ID);
	}
	
	/**
	 * 靖西微信接口
	 */
	public void jxWeixin(){
		weixin(WeixinUtil.JX_ORGANIZATION_ID);
	}
	
	/**
	 * 田东微信接口
	 */
	public void tdWeixin(){
		weixin(WeixinUtil.TD_ORGANIZATION_ID);
	}
	
	/**
	 * 田阳微信接口
	 */
	public void tyWeixin(){
		weixin(WeixinUtil.TY_ORGANIZATION_ID);
	}
	
	/**
	 * 微信主接口
	 */
	public void weixin(String organizationId){
		Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(organizationId);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getContentType() != null && request.getContentType().equalsIgnoreCase("text/xml")) {
			// 如果传递过来的是XML数据
			String xml = getRequestContent(request); // 读取IO流，得到xml的内容
			Message message = weixin.getMessageByXML(xml); // 解析得到消息内容
			if(message != null){
				// 判断 msgId 对应的消息是否存在，如果存在则不进行后面的操作。可能是微信服务器重发数据
				if(weixinMessageService.getByMsgId(message.getMsgId()) == null){
					TweixinMessage tMsg = weixinMessageService.save(message, xml); // 保存微信消息
					if(tMsg != null){
						TweixinUser tUser = weixinUserService.save(tMsg, organizationId); // 保存用户信息
						if(tUser != null && !Util.isNotNull(tUser.getName())){ // 如果用户昵称为空的，则认为是没获取到用户信息
							// 如果是新用户则开启单独线程获取用户信息
							GetWeixinUserInfoThread gwit = new GetWeixinUserInfoThread(ServletActionContext.getServletContext(), weixin, tUser.getOpenId(), tUser);
							new Thread(gwit).start();
						}
					}
					
					// 判断消息类型，决定是否自动回复信息
					String sendXML = analysesMessage(organizationId, message);
					if(Util.isNotNull(sendXML)){
						// 如果消息不是空的，则发送消息
						super.writeXml(sendXML);
					}
				}
			}
			System.out.println(xml);
		} else {
			// 如果不是xml，可能是接口接入配置。按接口接入配置执行
			String checkResult = null;
			String signature = Util.objToString(request.getParameter("signature"), "");
			long timestamp = Util.objToLong(request.getParameter("timestamp"),0);
			long nonce = Util.objToLong(request.getParameter("nonce"), 0);
			String echostr = Util.objToString(request.getParameter("echostr"),"");

			if (Util.isNotNull(signature)) {
				String sha1 = weixin.checkSignature(Long.toString(timestamp), Long.toString(nonce));
				if (signature.equals(sha1)) {
					checkResult = echostr;
				}
			}
			System.out.println("返回接入结果:" + checkResult);
			super.writeText(checkResult);
		}
	}
	
	/**
	 * 发送消息
	 */
	public void sendTextMessage(){
		Json json = new Json();
		HttpServletRequest request = ServletActionContext.getRequest();
		String openId = Util.objToString(request.getParameter("openId"), "");
		String content = Util.objToString(request.getParameter("content"), "");
		
        if(Util.isNotNull(openId) && Util.isNotNull(content)){
        	SessionInfo sessionInfo = null;
			Object obj = ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
			if(obj != null){
				sessionInfo = (SessionInfo)obj;
			}
			TweixinUser tWeixinUser = weixinUserService.getByOpenId(openId);
			if(tWeixinUser != null){
				Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(tWeixinUser.getOrganizationId());
	        	WeixinMessageService sm = new WeixinMessageService(weixin);
		        int n = sm.postCustomServiceMessage(ServletActionContext.getServletContext(), openId, content);
		        if(n > 0){  
		        	String fromUser = sessionInfo != null ? sessionInfo.getLoginName() : "系统";
		        	weixinMessageService.saveSendMessage(openId, fromUser, fromUser, content); // 保存
		        	
		        	JSONObject reJson = new JSONObject();
		        	reJson.put("success", true);
		        	reJson.put("msg", "消息发送成功！");
		        	reJson.put("fromNickName", fromUser);
		        	reJson.put("createTime", Util.getNowDate("yyyy-MM-dd HH:mm"));
		        	reJson.put("msgContent", content);
		        	reJson.put("icon", "../images/bjnh_icon.jpg");
		        	reJson.put("reply", 1);
		        	super.writeJson(reJson);
		        	return;
		        } else {
		        	json.setMsg("发送信息失败:错误代码：" + n);
		        }
			} else {
				json.setMsg("发送信息失败:获取不到微信用户信息！");
			}
        } else {
        	json.setMsg("发送信息失败:读取不到需要发送的内容！");
        }
		
		super.writeJson(json);
	}
	
	/**
	 * 根据微信web页面授权参数code获取配置信息
	 */
	public void getConfigByWebCode(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("weixinOpenId") != null){
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("openid", (String)session.getAttribute("weixinOpenId"));
			super.writeJson(hm);
		} else {
			String orgId = Util.objToString(session.getAttribute("orgId"), "");
			if(Util.isNotNull(code) && Util.isNotNull(orgId)){
				Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(orgId);
				String jsonStr = weixin.getConfigByWebCode(code);
				if(Util.isNotNull(jsonStr)){
					JSONObject jObj = JSON.parseObject(jsonStr);
					if(jObj.containsKey("openid")){
						session.setAttribute("weixinOpenId", jObj.getString("openid"));
					}
				}
				super.writeJson(jsonStr);
			} else {
				Json json = new Json();
				json.setMsg("参数错误！");
				super.writeJson(json);
			}
		}
	}

	/**
	 * 读取微信服务器传递过来的数据
	 * @param request
	 * @return
	 */
	private String getRequestContent(HttpServletRequest request) {
		String str = null;
		/**
		 * 得到post过来的数据，数据是xml格式；
		 **/
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			is = request.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}

			if (baos.size() > 2) {
				str = new String(baos.toByteArray(), "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {
				}
			}
		}
		return str;
	}
	
	/**
	 * 判断用户发送过来的消息，自动执行部分消息事件
	 * 
	 * @param message
	 * @return
	 */
	public String analysesMessage(String orgId, Message message) {
		String sendXML = null;
		if(message.getMsgType() == 11){
			EventMessage em = (EventMessage) message;
			if("click".equalsIgnoreCase(em.getEvent())){ // 如果是点击事件
				TweixinMenu tMenu = weixinMenuService.findByKey(orgId, em.getEventKey());
				if(tMenu != null){
					sendXML = WeixinMessageService.getTextMessageXML(message, tMenu.getContent());
				}
			}
		} 
		return sendXML;
	}
	
	/**
	 * 使用线程方式获取用户的信息
	 * @author laobu
	 *
	 */
	private class GetWeixinUserInfoThread implements Runnable {
		
		private ServletContext application;
		private Weixin weixin;
		private String openId;
		private TweixinUser t;
		
		public GetWeixinUserInfoThread(ServletContext application, Weixin weixin, String openId, TweixinUser t){
			this.application = application;
			this.weixin = weixin;
			this.openId = openId;
			this.t = t;
		}

		@Override
		public void run() {
			System.out.println("使用单独线程获取用户信息：" + openId);
			WeixinUserService wut = new WeixinUserService(weixin);
			WeixinUserBean wu = wut.getUserInfo(application, openId);
			System.out.println("获取到用户信息:" + wu);
			if(wu != null){
				t.setUnionId(wu.getUnionid());
				t.setName(wu.getNickname());
				t.setIcon(wu.getHeadimgurl());
				t.setSex(wu.getSex());
				t.setCity(wu.getCity());
				t.setCountry(wu.getCountry());
				t.setProvince(wu.getProvince());
				t.setLanguage(wu.getLanguage());
				weixinUserService.update(t);
			}
		}
		
	}

}
