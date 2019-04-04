package edu.wctc.my.kgable.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie cookie;
		Cookie[] cookies;

		cookies = request.getCookies();

		String model = request.getQueryString();
		Pattern pattern = Pattern.compile("model=[0-9]+");
		Matcher matcher = pattern.matcher(model);

		if (matcher.find())
		{
			model = matcher.group(0).substring(6);
		}

		String cart = "";

		if (cookies != null)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				cookie = cookies[i];

				if (cookie.getName().equals("cart"))
				{
					String[] values = cookie.getValue().split(",");

					for (int n = 0; n < values.length; n++)
					{

						if (!values[n].equals(model))
						{
							cart += values[n] + ",";
						}

					}
					cart = cart.substring(0, cart.length() - 1);
				}
			}
		}

		Cookie cartCookie = new Cookie("cart", cart);
		cartCookie.setMaxAge(60*60*24);
		response.addCookie(cartCookie);

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "cart.go");
	}
}
