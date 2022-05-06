package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = request.getParameter("level");

		UserDao userDao = new UserDao();
		User user = userDao.register(username, password,level);

		if(user != null){
			if (level.equals("用户")) {
				request.getSession().setAttribute("user", user);
			
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("admin", user);
	
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("info"," 错误:已存在该用户,不能重复注册！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
