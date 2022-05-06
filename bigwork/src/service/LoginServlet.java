package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = null;

		UserDao userDao = new UserDao();
		User user = userDao.login(username, password);

		if(user != null){
			level = user.getLevel();
			if(level.equals("用户")){
				request.getSession().setAttribute("user", user);
	
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("admin", user);
		
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}	
		}else {
			request.setAttribute("info"," 错误:用户名或密码错误！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
