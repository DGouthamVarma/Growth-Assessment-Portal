package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ProjectInsertBean;
import DAO.ProjectInsertDAO;

/**
 * Servlet implementation class InsertProjects
 */
@WebServlet("/InsertProjects")
public class InsertProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertProjects() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String projectType = request.getParameter("projecttype");
		String domain = request.getParameter("domain");
		String projectTitle = request.getParameter("projecttitle");
		String traineeTeamId = (String) session.getAttribute("traineeTeamID");
		String traineeId = (String) session.getAttribute("traineeID");
		ProjectInsertBean projectBean = new ProjectInsertBean();
		projectBean.setProjectType(projectType);
		projectBean.setProjectTitle(projectTitle);
		projectBean.setDomain(domain);
		
		ProjectInsertDAO projectDao = new ProjectInsertDAO();
		String status = projectDao.insertProject(projectBean, traineeTeamId, traineeId);
		
		if(status.equals("inserted")) {
			out.write("<h3 style=color:green>Your project details are updated</h3>");
		}
		else if(status.equals("already")) {
			out.write("<h3 style=color:red>Your already updated "+projectType+" project details.</h3>");
		}
		else {
			out.write("<h3 style=color:red>Your project details are not updated</h3>");
		}
	}

}
