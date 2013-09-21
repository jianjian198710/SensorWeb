package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Server.TCPServer;
import Service.RegistrationServiceImp;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationServiceImp rig = new RegistrationServiceImp(request,response);
		//Registration.jsp
		if(request.getParameter("register")!=null&&request.getParameter("register").equals("register")){
			rig.register();
		}
		//
		if(request.getParameter("showAll")!=null&&request.getParameter("showAll").equals("Show All the Registration Sensor")){
			rig.showAll(request, response);
		}
		//ShowAllSensors.jsp
		if(request.getParameter("start")!=null&&request.getParameter("start").equals("StartAll")){
			TCPServer.getInstance().setFlag(true);
			TCPServer.getInstance().startAll();
		}
		if(request.getParameter("stop")!=null&&request.getParameter("stop").equals("StopAll")){
			TCPServer.getInstance().setFlag(false);
			TCPServer.getInstance().stopAll();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new RegistrationServiceImp(request,response).register();
	}
}
