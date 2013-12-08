package sensorweb.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import sensorweb.server.TCPServer;
import sensorweb.service.CreateSensorMLServiceImp;
import sensorweb.service.QueryServiceImp;
import sensorweb.service.RegistrationServiceImp;
import sensorweb.service.UnregistrationServiceImp;


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
		if(request.getParameter("register")!=null&&request.getParameter("register").equals("Register")){
			rig.register();
		}
		//Unregistration.jsp
		if(request.getParameter("unregister")!=null){
			UnregistrationServiceImp urig = new UnregistrationServiceImp(request, response);
			urig.unregister();
			RequestDispatcher dispathcer = request.getRequestDispatcher("UnregistrationSuccess.jsp");
			dispathcer.forward(request, response);
		}
		//RegisterSuccess.jsp
		if(request.getParameter("startNow")!=null){
			String sensorID = request.getParameter("sensorID");
			HashSet<String> sensorids = new HashSet<String>();
				sensorids.add(sensorID.trim());
			TCPServer.getInstance().startSome(sensorids);
			rig.showAll(request, response);
		}
		if(request.getParameter("showAll")!=null){
			rig.showAll(request, response);
		}
		if(request.getParameter("registeAnother")!=null){
			RequestDispatcher dispathcer = request.getRequestDispatcher("Registration.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("createSensorML")!=null){
			CreateSensorMLServiceImp csm = new CreateSensorMLServiceImp(request,response);
			try {
				csm.outputToSensroML(request, response);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		//ShowAllSensors.jsp
		if(request.getParameter("start")!=null&&request.getParameter("start").equals("Start")){
			String[] sensors = request.getParameterValues("sid");
			if(sensors==null){
				RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
				dispathcer.forward(request, response);
				return;
			}
			System.out.println("要启动的Sensor为:"+Arrays.toString(sensors));
			HashSet<String> sensorids = new HashSet<String>();
			for(String sensorid: sensors){
				sensorids.add(sensorid.trim());
			}
			TCPServer.getInstance().startSome(sensorids);
			RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("stop")!=null&&request.getParameter("stop").equals("Stop")){
			String[] sensors = request.getParameterValues("sid");
			if(sensors==null){
				RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
				dispathcer.forward(request, response);
				return;
			}
			System.out.println("要停止的Sensor为:"+Arrays.toString(sensors));
			HashSet<String> sensorids = new HashSet<String>();
			for(String sensorid: sensors){
				sensorids.add(sensorid.trim());
			}
			TCPServer.getInstance().stopSome(sensorids);
			RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("startAll")!=null&&request.getParameter("startAll").equals("StartAll")){
			TCPServer.getInstance().startAll();
			RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("stopAll")!=null&&request.getParameter("stopAll").equals("StopAll")){
			TCPServer.getInstance().stopAll();
			RequestDispatcher dispathcer = request.getRequestDispatcher("ShowAllSensors.jsp");
			dispathcer.forward(request, response);
		}
		if(request.getParameter("detailedData")!=null&&request.getParameter("detailedData").equals("Detail")){
			QueryServiceImp qs = new QueryServiceImp(request, response);
			qs.getAllData(request, response);
			RequestDispatcher dispathcer = request.getRequestDispatcher("DetailedData.jsp");
			dispathcer.forward(request, response);
		}
		//DetailedData.jsp Ajax
		if(request.getParameter("count")!=null){
			QueryServiceImp qs = new QueryServiceImp(request, response);
			qs.getNewData(request, response);
			RequestDispatcher dispathcer = request.getRequestDispatcher("NewData.jsp");
			dispathcer.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new RegistrationServiceImp(request,response).register();
	}
	
	@Override
	public void destroy(){
		super.destroy();
		try {
			System.out.println("关闭ServerSocket!!!");
			TCPServer.getInstance().getServerSocket().close();
		} catch(IOException e) {
			System.out.println("关闭ServerSockety异常!!!");
		}
	}

	
}
