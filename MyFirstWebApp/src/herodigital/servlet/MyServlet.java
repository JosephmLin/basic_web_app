package herodigital.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.EventBatch;
import model.event.EventDisplay;
import controller.ObjectController;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	public static final String page_arrow = "page_arrow";
	public static final String page_number_const = "page_number";
	public static final String page_response_data = "event_info";
	public static final String page_response_page_num = "current_page_number";
	public static final String page_response_page_size = "page_size";
	public static final String page_count = "page_count";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static Integer total_number_of_pages = 0;
	private ObjectController oc = new ObjectController();
	
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * Some goals behind this: First, I'm going to have to have something that saves all of the database entry information into an object. This would be a big controller.
	 * Depending on the context of this application this controller may be a singleton. For now, I'm going to make this
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) (This means refer to this documentation)
	 * Request receiving this information:
	 * Parameters: Page #, How many Entries Per Page
	 * 
	 * Response returning this information:
	 * All the Information that is supposed to be on that page
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EventDisplay current_display;
		PrintWriter out = response.getWriter();
//		String page_arrow_string = request.getParameter(MyServlet.page_arrow);
		String page_number_string = request.getParameter(MyServlet.page_number_const);
		String display_size_string = request.getParameter("entries_per_page");
		Integer display_size;
		Integer page_number = 0;
		try{
			display_size = Integer.parseInt(display_size_string);
			page_number = Integer.parseInt(page_number_string)-1;
			if (display_size < 0 || display_size > 1000)
			{
				throw new NumberFormatException();
			}
		}
		catch(NumberFormatException nfe){
			display_size = ObjectController.default_display_size;
		}
		System.out.println(page_number);
		total_number_of_pages = oc.getCount(display_size);
//		if (page_arrow_string != null)
//		{
//			switch(page_arrow_string)
//			{
//			case "Start":
//				page_number = 0;
//				break;
//			case "Prev":
//				page_number--;
//				break;
//			case "Next":
//				page_number++;
//				break;
//			case "End":
//				page_number = total_number_of_pages;
//				break;
//			case "Current":
//				break;
//			}
//		}
		if(page_number < 0)
			page_number = 0;
		if (page_number > total_number_of_pages)
			page_number = total_number_of_pages;
		//EventBatch current_page = oc.getEventBatch(page_number);
		current_display = oc.getEventDisplay(page_number, display_size);
		System.out.println("Page Number:" + (page_number+1) + ", Entries Per Page:" + display_size);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		request.setAttribute(MyServlet.page_response_data, current_display);
		request.setAttribute(MyServlet.page_response_page_num, page_number+1);
		request.setAttribute(MyServlet.page_response_page_size, display_size);
		request.setAttribute(MyServlet.page_count, total_number_of_pages+1);
		request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request,  response);
		
		
		
		//  request.setAttribute(MyServlet.page_response_title, arg1)
		//TODO: Need to forward all of this table information. Need to make a result.jsp that catches it. Need to communicate to Vadim to make sure that this is what he wants
		//TODO: Research into JSP and to html and stuff

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Integer new_size = Integer.parseInt(request.getParameter("entries_per_page"));
//		if (new_size == null)
//		{
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
//		System.out.println("New Size: " + new_size);
//		ObjectController.setDisplaySize(new_size);
//		EventBatch current_page = oc.getEventBatch(0);
//		response.setStatus(HttpServletResponse.SC_ACCEPTED);
//		
//		
//		request.setAttribute(MyServlet.page_response_data, current_page);
//		request.setAttribute(MyServlet.page_response_page_num, 1);
//		request.setAttribute(MyServlet.page_response_page_size, ObjectController.default_display_size);
//		//request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request,  response);
//		
//		response.sendRedirect("./MyServlet");
		
	}

}
