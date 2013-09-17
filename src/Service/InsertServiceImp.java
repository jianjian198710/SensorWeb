package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServiceImp {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public InsertServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public void insert(){
		
	}
}
