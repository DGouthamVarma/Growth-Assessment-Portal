package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CenturyFluenciesBean;
import DAO.CenturyFluenciesDAO;

@WebServlet("/CenturyFluenciesRating")
public class CenturyFluenciesRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CenturyFluenciesRating() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String traineeId = request.getParameter("trainee");
		int week = Integer.parseInt(request.getParameter("week"));
		String otherPerson = request.getParameter("otherperson");
		String grammar = request.getParameter("grammar");
		String conversation = request.getParameter("conversation");
		String writtenInfo = request.getParameter("writteninfo");
		String questioning = request.getParameter("questioning");
		String emailWriting = request.getParameter("emailwriting");
		String mentorId = (String) session.getAttribute("mentorID");
		CenturyFluenciesBean cfBean = new CenturyFluenciesBean();
		cfBean.setOtherPerson(Integer.parseInt(otherPerson));
		cfBean.setConversation(Integer.parseInt(conversation));
		cfBean.setEmailWriting(Integer.parseInt(emailWriting));
		cfBean.setGrammar(Integer.parseInt(grammar));
		cfBean.setQuestioning(Integer.parseInt(questioning));
		cfBean.setWrittenInfo(Integer.parseInt(writtenInfo));
		
		CenturyFluenciesDAO centuryFluenciesDao = new CenturyFluenciesDAO();
		String status = centuryFluenciesDao.insertRatings(cfBean, mentorId, traineeId, week);
		
		if(status.equals("already")) {
			out.write("<h3 style=color:red>You already updated ratings for this trainee for this week</h3>");
		}
		else if(status.equals("yes")) {
			out.write("<h3 style=color:green>Your ratings are updated</h3>");
		}
		else {
			out.write("<h3 style=color:red>The values could not be updated. Please visit later.</h3>");
		}
	}

}
