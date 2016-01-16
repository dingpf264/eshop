package com.rhwayfun.eshop.orders.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.rhwayfun.eshop.cart.entity.Cart;
import com.rhwayfun.eshop.cart.entity.CartItem;
import com.rhwayfun.eshop.orders.entity.Orderitem;
import com.rhwayfun.eshop.orders.entity.Orders;
import com.rhwayfun.eshop.orders.service.IOrderService;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.user.service.IUserService;
import com.rhwayfun.eshop.utils.PageBean;
import com.rhwayfun.eshop.utils.PaymentUtil;

public class OrderAction{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;

	private int currentPage;
	private int userid;
	private int oid;
	private String address;
	private String name;
	private String phone;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	// 接收支付通道编码:
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	// 接收付款成功后的参数:
	private String r3_Amt;
	private String r6_Order;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private IOrderService orderService;
	private IUserService userService;
	
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/*****************以下是action的实现******************/
	
	//生成订单
	public String save() throws Exception{
		//创建一个订单
		Orders order = new Orders();
		order.setState(1);
		order.setOrdertime(new Timestamp(new Date().getTime()));
		//获取购物车对象
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		//如果为空，直接跳转到登录页面
		if(cart == null){
			return "login";
		}
		//获取购物车的购物项
		for(CartItem cartItem : cart.getCartItems()){
			//创建订单项
			Orderitem orderitem = new Orderitem();
			orderitem.setCount(cartItem.getCount());
			orderitem.setOrders(order);
			orderitem.setProduct(cartItem.getProduct());
			orderitem.setSubtotal(cartItem.getSubTotal());
			order.getOrderitems().add(orderitem);
		}
		//获取user
		User user =(User) ActionContext.getContext().getSession().get("user");
		if(user == null){
			return "login";
		}
		User user1 = userService.findUserById(user.getUserid());
		order.setUser(user1);
		order.setTotal(cart.getTotal());
		//保存订单到数据库
		orderService.save(order);
		//保存订单后需要清空购物车
		cart.clearCart();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("order", order);
		return "save";
	}
	
	// 为订单付款:
	public String payOrder() throws IOException {
		// 1.修改数据:
		Orders currOrder = orderService.findOrderByOid(oid);
		currOrder.setAddress(address);
		currOrder.setName(name);
		currOrder.setPhone(phone);
		// 修改订单
		orderService.update(currOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = String.valueOf(oid);// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.1.104:8090/eshop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return "none";
	}

	// 付款成功后跳转回来的路径:
	public String callBack(){
		Map request = (Map) ActionContext.getContext().get("request");
		// 修改订单的状态:
		Orders currOrder = orderService.findOrderByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		//设置支付时间
		currOrder.setPaytime(new Timestamp(new Date().getTime()));
		orderService.update(currOrder);
		request.put("msg","支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	// 修改订单的状态:
	public String updateState(){
		Orders currOrder = orderService.findOrderByOid(oid);
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
	//获取用户所有的订单
	public String findOrders() throws Exception{
		PageBean<Orders> orders = orderService.findPageOrdersByUid(userid,currentPage);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orders", orders);
		return "findOrders";
	}
	
	//根据订单编号删除订单
	public String deleteOrderByOid() throws Exception{
		orderService.deleteOrderByOid(oid);
		PageBean<Orders> orders = orderService.findPageOrdersByUid(userid,currentPage);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("orders", orders);
		return "deleteOrderByOid";
	}
	
	//根据订单id获取订单详情
	public String findOrderDetailByOid() throws Exception{
		Orders order = orderService.findOrderByOid(oid);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("order", order);
		return "findOrderDetailByOid";
	}
}
