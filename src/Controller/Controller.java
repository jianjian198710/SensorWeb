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
import Service.UnregistrationServiceImp;

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
//		UnregistrationServiceImp urig = new UnregistrationServiceImp(request,response);
		//Registration.jsp
		if(request.getParameter("register")!=null&&request.getParameter("register").equals("Register")){
			rig.register();
		}
		//
		if(request.getParameter("showAll")!=null&&request.getParameter("showAll").equals("Show All the Registration Sensor")){
			rig.showAll(request, response);
		}
		//Unregistration.jsp
		if(request.getParameter("unregister")!=null&&request.getParameter("unregister").equals("Unregister")){
//			urig.unregister();
		}
		//ShowAllSensors.jsp
		if(request.getParameter("start")!=null&&request.getParameter("start").equals("Start")){
			//TODO TCPServer.getInstance().startSome(selectedSensorIDs);
		}
		if(request.getParameter("stop")!=null&&request.getParameter("stop").equals("StopAll")){
			//TODO TCPServer.getInstance().stop(selectedSensorIDs);
		}
		if(request.getParameter("startAll")!=null&&request.getParameter("startAll").equals("StartAll")){
			TCPServer.getInstance().start();
			RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("stopAll")!=null&&request.getParameter("stopAll").equals("StopAll")){
			TCPServer.getInstance().stop();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new RegistrationServiceImp(request,response).register();
	}
}
