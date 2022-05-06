package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import dao.CourseDao;
import dao.DepartmentDao;
import dao.SCDao;
import dao.StudentDao;
import dao.UserDao;
import model.Class;
import model.Course;
import model.Course_avg;
import model.Course_fail_rate;
import model.Course_ranking;
import model.Department;
import model.SC;
import model.Student;
import model.User;


public class AdminDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String action;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		action = request.getParameter("action");
		
		switch (action) {
		
		case "query_all_user":
			query_all_user(request, response);break;
		case "insert_user":
			insert_user(request,response);break;
		case "delete_user":
			delete_user(request, response);break;
		case "alter_user":
			alter_user(request, response);break;
		
		case "query_all_department":
			query_all_department(request, response);break;
		case "insert_department":
			insert_department(request, response);break;
		case "delete_department":
			delete_department(request, response);break;
		case "alter_department":
			alter_department(request, response);break;
		
		case "query_all_class":
			query_all_class(request, response);break;
		case "insert_class":
			insert_class(request, response);break;	
		case "delete_class":
			delete_class(request, response);break;
		case "alter_class":
			alter_class(request, response);break;
		
		case "query_all_student":
			query_all_student(request, response);break;
		case "insert_student":
			insert_student(request, response);break;	
		case "delete_student":
			delete_student(request, response);break;
		case "alter_student":
			alter_student(request, response);break;
		
		case "course_avg":
			course_avg(request, response);break;
		case "fail_rate":
			fail_rate(request, response);break;
		case "course_ranking":
			course_ranking(request, response);break;
		case "query_all_course":
			query_all_course(request, response);break;
		case "insert_course":
			insert_course(request, response);break;
		case "delete_course":
			delete_course(request, response);break;
		case "alter_course":
			alter_course(request, response);break;
		
		case "query_all_sc":
			query_all_sc(request, response);break;
		case "insert_sc":
			insert_sc(request, response);break;
		case "delete_sc":
			delete_sc(request, response);break;
		case "alter_sc":
			alter_sc(request, response);break;
		default:
			break;
		}
	}
	
	protected void query_all_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		UserDao userDao = new UserDao();
		
		ArrayList<User> results = userDao.query_all_user();
		PrintWriter out = response.getWriter();
		
		if(results != null){
			out.write("<div class='all'>");
			out.write("<div><span>�û���</span><span>����</span><span>Ȩ�޼���</span></div>");
			for(User i: results){
				out.write("<div>");
				out.write("<span>"+i.getUsername()+"</span>");
				out.write("<span>"+i.getPassword()+"</span>");
				out.write("<span>"+i.getLevel()+"</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		
		out.flush();
		out.close();
	}
	
	protected void insert_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = request.getParameter("level");
		
		int flag =  new UserDao().insert_user(username, password, level);
		String info = null;
		PrintWriter out =  response.getWriter();
		if(flag == 1){
			info = "�û�����ɹ���";
		}else{
			info = "�����û�����ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>"+info+"</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		
		int flag =  new UserDao().delete_user(username);
		String info = null;
		PrintWriter out =  response.getWriter();
		if(flag == 1){
			info = "�ɹ�ɾ����Ϊ"+username+"�û���";
		}else{
			info = "����ɾ���û�ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>"+info+"</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void alter_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String after_username = request.getParameter("after_username");
		String after_password = request.getParameter("after_password");
		String after_level = request.getParameter("after_level");
		
		int flag =  new UserDao().alter_user(username,after_username,after_password,after_level);
		String info = null;
		PrintWriter out =  response.getWriter();
		if(flag == 1){
			info = "��Ϊ"+username+"�û���Ϣ�޸ĳɹ���";
		}else{
			info = "�����޸��û�ʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>"+info+"</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	
	protected void query_all_department(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		ArrayList<Department> results = new DepartmentDao().query_all_department();
		PrintWriter out = response.getWriter();
		
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>ϵ���</span><span>ϵ��</span></div>");
			for (Department i : results) {
				out.write("<div>");
				out.write("<span>" + i.getDno() + "</span>");
				out.write("<span>" + i.getDname() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}
	
	protected void insert_department(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		String dname = request.getParameter("dname");
		int flag = new DepartmentDao().insert_department(dno, dname);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "Ժϵ"+dname+"����ɹ���";
		} else {
			info = "����Ժϵ����ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void delete_department(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		int flag = new DepartmentDao().delete_department(dno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�ɹ�ɾ��" + dno + "��Ժϵ��";
		} else {
			info = "����ɾ��Ժϵʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void alter_department(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		String after_dno = request.getParameter("after_dno");
		String after_dname = request.getParameter("after_dname");
		int flag = new DepartmentDao().alter_department(dno, after_dno, after_dname);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = dno + "��ϵ�޸ĳɹ���";
		} else {
			info = "�����޸�Ժϵʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	
	protected void query_all_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Class> results = new ClassDao().query_all_class();
		PrintWriter out = response.getWriter();
		
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>�༶���</span><span>�༶��</span><span>����Ժϵ</span></div>");
			for (Class i : results) {
				out.write("<div>");
				out.write("<span>" + i.getClno() + "</span>");
				out.write("<span>" + i.getClname() + "</span>");
				out.write("<span>" + i.getDno() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}
	
	protected void insert_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		String clname = request.getParameter("clname");
		String dno = request.getParameter("dno");
		int flag = new ClassDao().insert_class(clno, clname, dno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�༶"+clname+"����ɹ���";
		} else {
			info = "���󣺰༶����ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void delete_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		int flag = new ClassDao().delete_class(clno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�ɹ�ɾ��" + clno + "�༶��";
		} else {
			info = "����ɾ���༶ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
	
	protected void alter_class(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		String after_clno = request.getParameter("after_clno");
		String after_clname = request.getParameter("after_clname");
		String after_dno = request.getParameter("after_dno");
		int flag = new ClassDao().alter_class(clno, after_clno, after_clname, after_dno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�༶"+clno+"�޸ĳɹ���";
		} else {
			info = "�����޸İ༶ʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	
	protected void query_all_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Student> results = new StudentDao().query_all_student();
		PrintWriter out = response.getWriter();

		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>ѧ��</span><span>����</span><span>�Ա�</span><span>����</span><span>���ڰ༶���</span></div>");
			for (Student i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getClno() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	protected void insert_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String ssex = request.getParameter("ssex");
		int sage = Integer.parseInt(request.getParameter("sage"));
		String clno = request.getParameter("clno");
		int flag = new StudentDao().insert_student(sno, sname, ssex, sage, clno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "ѧ��"+sname+"����ɹ���";
		} else {
			info = "����ѧ������ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void delete_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		int flag = new StudentDao().delete_student(sno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�ɹ�ɾ��" + sno + "��ѧ����";
		} else {
			info = "����ɾ��ѧ��ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void alter_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String after_sno = request.getParameter("after_sno");
		String after_sname = request.getParameter("after_sname");
		String after_ssex = request.getParameter("after_ssex");
		int after_sage = Integer.parseInt(request.getParameter("after_sage"));
		String after_clno = request.getParameter("after_clno");
		int flag = new StudentDao().alter_class(sno, after_sno, after_sname, after_ssex, after_sage, after_clno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "ѧ��"+sno+"��Ϣ�޸ĳɹ���";
		} else {
			info = "�����޸�ѧ����Ϣʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}


	protected void course_avg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		ArrayList<Course_avg> results = new CourseDao().course_avg();
		PrintWriter out = response.getWriter();
		if(results != null){
			
			if(results != null){
				out.write("<div class='all'>");
				out.write("<div><span>�γ̺�</span><span>�γ�����</span><span>ƽ����</span></div>");
				for(Course_avg i:results) {
					out.write("<div>");
					out.write("<span>"+i.getCno()+"</span>");
					out.write("<span>"+i.getCname()+"</span>");
					out.write("<span>"+i.getAvg()+"</span>");
					out.write("</div>");
				}
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}

	protected void fail_rate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Course_fail_rate> results = new CourseDao().fail_rate();
		PrintWriter out = response.getWriter();
	
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>�γ̺�</span><span>�γ�����</span><span>��������</span></div>");
			for (Course_fail_rate i : results) {
				out.write("<div>");
				out.write("<span>" + i.getCno() + "</span>");
				out.write("<span>" + i.getCname() + "</span>");
				out.write("<span>" + i.getFail_rate() + "%</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	protected void course_ranking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String Cno = request.getParameter("cno");
		ArrayList<Course_ranking> results = new CourseDao().course_ranking(Cno);
		PrintWriter out = response.getWriter();

		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>ѧ��</span><span>����ϵ</span><span>�༶����</span><span>����</span><span>�Ա�</span><span>����</span><span>�ɼ�</span><span>����</span></div>");
			int no = 1;
			for (Course_ranking i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getDname() + "</span>");
				out.write("<span>" + i.getClname() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getGrade() + "</span>");
				out.write("<span>"+(no++)+"</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	protected void query_all_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Course> results = new CourseDao().query_all_course();
		PrintWriter out = response.getWriter();
		if(results != null){

			if(results != null){
				out.write("<div class='all'>");
				out.write("<div><span>�γ̺�</span><span>�γ�����</span><span>ִ����ʦ</span><span>ѧ��</span></div>");
				for(Course i:results) {
					out.write("<div>");
					out.write("<span>"+i.getCno()+"</span>");
					out.write("<span>"+i.getCname()+"</span>");
					out.write("<span>"+i.getCteacher()+"</span>");
					out.write("<span>"+i.getCcredit()+"</span>");
					out.write("</div>");
				}
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}

	protected void insert_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String Cno = request.getParameter("cno");
		String Cname = request.getParameter("cname");
		String Cteacher = request.getParameter("cteacher");
		int Ccredit = Integer.parseInt(request.getParameter("ccredit"));
		int flag =  new CourseDao().insert_course(Cno, Cname, Cteacher, Ccredit);
		String info = null;
		PrintWriter out =  response.getWriter();
		if(flag == 1){
			info = "�γ�"+Cname+"����ɹ���";
		}else{
			info = "���󣺿γ̲���ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>"+info+"</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void delete_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cno = request.getParameter("cno");
		int flag =  new CourseDao().delete_course(cno);
		String info = null;
		PrintWriter out =  response.getWriter();
		if(flag == 1){
			info = "�ɹ�ɾ��"+cno+"�γ̣�";
		}else{
			info = "����ɾ���γ�ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>"+info+"</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void alter_course(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String cno = request.getParameter("cno");
		String after_cno = request.getParameter("after_cno");
		String after_cname = request.getParameter("after_cname");
		String after_cteacher = request.getParameter("after_cteacher");
		double after_ccredit = Double.parseDouble(request.getParameter("after_ccredit"));
		int flag = new CourseDao().alter_course(cno, after_cno, after_cname, after_cteacher, after_ccredit);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = cno + "�ſγ��޸ĳɹ���";
		} else {
			info = "�����޸Ŀγ���Ϣʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}


	protected void query_all_sc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<SC> results = new SCDao().query_all_sc();
		PrintWriter out = response.getWriter();
		
		if (results != null) {
			out.write("<div id='all_sc' class='all'>");
			out.write("<div><span>ѧ��</span><span>����</span><span>�Ա�</span><span>����</span><span>�γ̺�</span><span>�γ�����</span><span>����</span></div>");
			for (SC i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getCno() + "</span>");
				out.write("<span>" + i.getCname() + "</span>");
				out.write("<span>" + i.getGrade() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	protected void insert_sc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		double grade = Double.parseDouble(request.getParameter("grade"));
		int flag = new SCDao().insert_sc(sno, cno, grade);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = sno + "��ѧ��" + cno + "�ſγ̳ɼ�"+grade+"����ɹ���";
		} else {
			info = "���󣺳ɼ���Ϣ����ʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void delete_sc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		int flag = new SCDao().delete_sc(sno, cno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "�ɹ�ɾ��" + sno + "��ѧ��"+cno+"�ſγ̳ɼ���";
		} else {
			info = "����ɾ���ɼ���Ϣʧ�ܣ�";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	protected void alter_sc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		double after_grade = Double.parseDouble(request.getParameter("after_grade"));
		int flag = new SCDao().alter_sc(sno, cno, after_grade);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = sno + "��ѧ��" + cno + "�ſγ̳ɼ��޸ĳɹ���";
		} else {
			info = "�����޸�ѧ���ɼ�ʧ��!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

}
