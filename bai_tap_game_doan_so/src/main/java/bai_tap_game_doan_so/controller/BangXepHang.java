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

@WebServlet("/bangxephang")
public class BangXepHang extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Player>lstPlayers=new ArrayList<>();
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().contains("player")) {
					Gson gson=new Gson();
					Player player=gson.fromJson(cookies[i].getValue(),Player.class);
					lstPlayers.add(player);
				}
			}
		}
		req.setAttribute("nguoichoi",lstPlayers);
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("BangXepHang.jsp");
		requestDispatcher.forward(req, resp);
	}
}
