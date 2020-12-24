package mypkg.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperClass implements SuperController, Validator{
	// PREFIX는 유효성 검사시 앞에 붙여줄 접두사
	public static final String PREFIX = "err";
	private HttpServletRequest request = null ;
	private HttpServletResponse response = null ;
	private HttpSession session = null;
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	// get, post에 상관없이 무조건 호출되도록 합니다.
	this.request = request ; 
	this.response = response ;
	this.session  = request.getSession() ;		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);	
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	@Override
	public boolean validate(HttpServletRequest request) {
		
		return false;
	}
	
	public void GotoPage(String url) {
		// dispatcher 객체를 사용하여 특정 페이지로 이동합니다.
		RequestDispatcher dispatcher = this.request.getRequestDispatcher(url);
		
		try {
			dispatcher.forward(this.request, this.response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
