package dynamicwebpractice.test.openapi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dynamicwebpractice.test.openapi.dto.MyLocHistory;
import dynamicwebpractice.test.openapi.service.DBService;

/**
 * Servlet implementation class WifiInfoHistoryServlet
 */
@WebServlet("/wfinfo-myhistory")
public class WifiInfoHistoryServlet extends HttpServlet {

	private DBService dbService = new DBService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String historyId = String.valueOf(request.getParameter("historyId"));
		
		if (!"".equals(historyId) && !historyId.isEmpty() && historyId != null && !"null".equals(historyId)) {
			dbService.myLocHistoryDelete(historyId);
		}
				
		List<MyLocHistory> myHistoryList = dbService.selectMyLocHisList();
		
		request.setAttribute("myHistoryList", myHistoryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/locationhistorylist.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
