package dynamicwebpractice.test.openapi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dynamicwebpractice.test.openapi.dto.Row;
import dynamicwebpractice.test.openapi.dto.WifiInfo;
import dynamicwebpractice.test.openapi.service.CallApiService;
import dynamicwebpractice.test.openapi.service.DBService;

/**
 * Servlet implementation class WifiInfoMainServlet
 */
@WebServlet("/wfinfo")
public class WifiInfoMainServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/wifilist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. LAT, LNT null check
		if (!request.getParameter("lat").isEmpty() && !request.getParameter("lnt").isEmpty() &&
				!"undefined".equals(request.getParameter("lat")) && !"undefined".equals(request.getParameter("lnt"))) {
		
			String lat = request.getParameter("lat");
			String lnt = request.getParameter("lnt");
		
			//2. select db
			DBService dbService = new DBService();
			List<Row> wifiInfolist = dbService.getNearWifiList(lat, lnt);
			request.setAttribute("wifiInfolist", wifiInfolist);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/wifilist.jsp");		
		doGet(request, response);
	}

}
