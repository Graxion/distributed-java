package edu.wctc.my.kgable.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ProcessCartController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Enumeration parameterNames = request.getParameterNames();
		String parameterName;
		String cart = "";

		while (parameterNames.hasMoreElements())
		{
			parameterName = parameterNames.nextElement().toString();

			if (parameterName.equals("cartItem"))
			{
				String[] values = request.getParameterValues(parameterName);

				if (values.length == 1)
				{
					String value = values[0];

					if (value.length() == 0)
					{
						cart += "Empty Cart";
					}
					else
					{
						cart += value;
					}
				}
				else
				{
					for (int i = 0; i < values.length; i++)
					{
						cart += values[i] + ",";
					}

					cart = cart.substring(0, cart.length() - 1);
				}
			}
		}

		Cookie cartCookie = new Cookie("cart", cart);
		cartCookie.setMaxAge(60*60*24);
		response.addCookie(cartCookie);

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "index.jsp");
	}
}
