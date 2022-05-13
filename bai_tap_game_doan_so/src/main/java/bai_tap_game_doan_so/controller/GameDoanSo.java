package bai_tap_game_doan_so.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bai_tap_game_doan_so.model.Player;

@WebServlet("/gamedoanso")
public class GameDoanSo extends HttpServlet {
	int randomNumber = (int) (Math.random() * (1000 - 1 + 1) + 1); 
	
	Player player;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		Gson gson=new Gson();
		if(cookies!=null) {
			player=gson.fromJson(cookies[cookies.length-1].getValue(),Player.class);
		}
		
//		System.out.println("test player "+x.getHoTen());
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("GameDoanSo.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int doanSo=Integer.parseInt(req.getParameter("doanso"));
		String mess="";
		if(randomNumber>doanSo) {
			mess="So vua doan nho hon dap an ";
			player.setSolan(player.getSolan()+1);
		}
		else if(randomNumber<doanSo) {
			mess="So vua doan lon hon dap an";
			player.setSolan(player.getSolan()+1);
		}
		else {
			mess="Ban doan dung roi";
			player.setSolan(player.getSolan()+1);
			Gson gson=new Gson();
			String json=gson.toJson(player);
			Cookie[] cookies=req.getCookies();
			Cookie cookie=new Cookie(cookies[cookies.length-1].getName(),json);
			cookie.setMaxAge(1800);
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath()+"/bangxephang");
			return;
		}
		req.setAttribute("message", mess);
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("GameDoanSo.jsp");
		requestDispatcher.forward(req, resp);
	}
}
