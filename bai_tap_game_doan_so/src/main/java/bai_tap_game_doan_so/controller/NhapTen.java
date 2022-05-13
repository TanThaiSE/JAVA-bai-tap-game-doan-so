package bai_tap_game_doan_so.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bai_tap_game_doan_so.model.Player;

@WebServlet("/nhapten")
public class NhapTen extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("NhapHoTen.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hoTen=req.getParameter("hoten");
		if(hoTen!=null) {
			Player player=new Player(hoTen, 0);
			Gson gson=new Gson();
			String json=gson.toJson(player);
			Cookie[] cookies=req.getCookies();
			Cookie cookie;
			if(cookies==null) {
				cookie=new Cookie("player0",json);
			}
			else {
				cookie=new Cookie("player"+cookies.length,json);
			}
			cookie.setMaxAge(1800);
			resp.addCookie(cookie);
			
			resp.sendRedirect(req.getContextPath()+"/gamedoanso");
			return;
		}
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("NhapHoTen.jsp");
		requestDispatcher.forward(req, resp);
	}
}
