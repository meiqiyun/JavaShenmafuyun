package com.smfy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smfy.biz.impl.CodeTypeBizImpl;
import com.smfy.entity.CodeType;

/**
 * Servlet implementation class CodeType_Del_Servlet
 */
public class CodeType_Del_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeType_Del_Servlet() {
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
		request.setCharacterEncoding("UTF-8");
		int did=Integer.parseInt(request.getParameter("did")); //���ID
		CodeType codeType=new CodeType();
		codeType.setId(did);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		int result=new CodeTypeBizImpl().delCodeType(codeType);
		if(result>0){
			out.write("ok");
		}else{
			out.write("error");
		}		
		out.flush();
		out.close();
	}

}
