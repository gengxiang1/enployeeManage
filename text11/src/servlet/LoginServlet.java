package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.LoginDao;
import entity.User;
import entity.ValidateCode;
import until.MD5;
import until.RandomNumber;

public class LoginServlet extends HttpServlet {

	LoginDao dao = new LoginDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type == null) {
			entry(request, response);
		} else if ("log".equals(type)) {

			log(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		}

	}

	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// ‘O÷√Ìì√Ê≤ªæè¥Ê
		try {

			RandomNumber rn = new RandomNumber();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);

			ValidateCode vc = rn.generateImage();

			ServletOutputStream outStream = response.getOutputStream();
			// ›î≥ˆàDœÒΩÁ√Ê
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void log(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			String random = (String) request.getSession().getAttribute("rand");
			String random1 = request.getParameter("random");
			if (random.equals(random1)) {
				String name = request.getParameter("name");
				boolean fal = dao.searchName(name);
				if (fal) {

					String password = request.getParameter("password");
					String salt = dao.searchSalt(name);
					password = password + salt;

					User user = new User();
					user.setPassword(MD5.MD5(password));
					user.setUserName(name);
					Cookie cookie = new Cookie("username", name);
					cookie.setMaxAge(60 * 60);
					response.addCookie(cookie);
					boolean flag = dao.search(user);

					if (flag) {
						HttpSession session = request.getSession();
						session.setAttribute("username", name);
						response.sendRedirect("index");
					} else {

						request.setAttribute("str", "µ«¬º ß∞‹£¨√‹¬Î¥ÌŒÛ");
						request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
					}
				} else {

					request.setAttribute("str", "”√ªß≤ª¥Ê‘⁄");
					request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);

				}
			} else {
				request.setAttribute("str", "µ«¬º ß∞‹£¨—È÷§¬Î¥ÌŒÛ");
				request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);

			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void entry(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = "";
		try {
			Cookie[] cookie = request.getCookies();
			if (cookie != null) {
				for (int i = 0; i < cookie.length; i++) {
					if ("username".equals(cookie[i].getName())) {
						name = cookie[i].getValue();
					}

				}
			}

			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
