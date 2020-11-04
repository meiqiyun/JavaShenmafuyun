package com.smfy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.smfy.biz.impl.OrderBizImpl;
import com.smfy.entity.Order;

/**
 * Servlet implementation class Order_List_Servlet
 */
public class Order_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order_List_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status=request.getParameter("status");
		//当前页码
		int pageIndex=1;//默认显示第一页
		String pageIndexString=request.getParameter("pageIndex");
		if(pageIndexString!=null&&pageIndexString.length()>0){
			pageIndex=Integer.parseInt(pageIndexString);
		}
		//每页显示的记录数
		int pageSize=5;//默认每页显示5条
		String pageSizeString=request.getParameter("pageSize");
		if(pageIndexString!=null&&pageIndexString.length()>0){
			pageSize=Integer.parseInt(pageSizeString);
		}
		//总页数
		int allPage=0;
		//总条数
		int count=new OrderBizImpl().findAllOrderCount(status);
		if(count>0){
			//计算总页数
			if(count%pageSize==0){
				allPage=count/pageSize;
			}else{
				allPage=count/pageSize+1;
			}
			//检测页码
			if(pageIndex<1){
				pageIndex=1;
			}else if(pageIndex>allPage){
				pageIndex=allPage;
			}
			//保存数据				
			//request.setAttribute("pageIndex", pageIndex);
			//request.setAttribute("allPage", allPage);
		}
				
		List<Order> orderList=null;//先初始化
		orderList= new OrderBizImpl().findAllOrder(status,pageIndex, pageSize);

		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("allPage", allPage);
		map.put("orderList",orderList);
		//使用JSON返回用户对象
		String json=JSON.toJSONString(map);
		//把数据写到web网页
		response.setCharacterEncoding("utf-8");             //防止ajax返回的数据乱码（有中文的情况下，就必须要写）
		response.setContentType("text/html; charset=UTF-8");//和jsp页面本身的文本格式一样		
		PrintWriter out= response.getWriter();              //获取PrintWriter对象，便于向web写数据
		out.write(json);                                  //将数据写到web页面,最终写到callBack(data)的data里	
		out.flush();                                        //清空流
		out.close();                                        //关闭流
	}

}
