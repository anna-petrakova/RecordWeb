package web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Education;
import common.ValidationException;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet(
		urlPatterns = { "/RecordServlet", "/records", RecordServlet.URL_MAPPING + "/*" } 
		
		)
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL_MAPPING = "/records";
	private static final String FORM_JSP = "/form.jsp";
	private RecordManager rM;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
        super();
        rM = new RecordManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("records", rM.getRecords());
		request.setAttribute("educationValues", Education.values());
        request.getRequestDispatcher(FORM_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//support non-ASCII characters in form
        request.setCharacterEncoding("utf-8");
        //action specified by pathInfo
        String action = request.getPathInfo();
        
        switch (action) {
        case "/add":
            //getting POST parameters from form
            String nameStr = request.getParameter("name");
            String email = request.getParameter("email");
            String bornStr = request.getParameter("birthday");            
            String gender = request.getParameter("gender");
            String[] interests = request.getParameterValues("interest");
            String[] interestsFinal;
            boolean otherInterestSelected = (request.getParameter("interestOther") == null) ? false : true;
            if (otherInterestSelected) {
            	
            	String otherInterestsStr = request.getParameter("interestOtherVal");
            	String[] otherInterests = otherInterestsStr.split(",");
            	interestsFinal = new String[interests.length + otherInterests.length];
            	
            	for (int i = 0; i < interests.length; i++) {
            		interestsFinal[i] = interests[i];
            	}
            	int idx = interests.length;
            	for (String str : otherInterests) {
            		interestsFinal[idx] = str;
            		idx++;
            	}
            } else {
            	interestsFinal = interests;
            }
            String education = request.getParameter("education");
            
            //form data processing
            Record record = new Record();
            record.setNameSurnameStr(nameStr);
            record.setEmail(email);
            record.setDateStr(bornStr);
            record.setGender(gender);
            record.setInterests(interestsFinal);
            record.setEducation(education);
            
            try {
            	rM.createRecord(record);
            } catch (IllegalArgumentException | ValidationException ex) {
            	request.setAttribute("error", ex.getMessage());
            	doGet(request, response);
            	return;
            }
            
            request.setAttribute("alert", "true");
            request.getSession().setAttribute("alert", "true");
            //redirect-after-POST protects from multiple submission
            response.sendRedirect(request.getContextPath()+URL_MAPPING);
            return;
        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
        
	}

}
