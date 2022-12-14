package dynamicwebpractice.test.openapi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dynamicwebpractice.test.openapi.service.CallApiService;

/**
 * Servlet implementation class WifiInfoLoadServlet
 */
@WebServlet("/wfinfo-load")
public class WifiInfoLoadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/infodownloadsucceess.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CallApiService callApiService = new CallApiService();
		long result = callApiService.saveWifiInfo();
		request.setAttribute("totalSaveedInfo", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/infodownloadsucceess.jsp"); 
		
		doGet(request, response);
	}

}
