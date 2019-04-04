package edu.wctc.my.kgable.controller;

import edu.wctc.my.kgable.model.Catalog;
import edu.wctc.my.kgable.model.ShipClass;
import edu.wctc.my.kgable.model.ShipModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CartController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie cookie;
		Cookie[] cookies;

		cookies = request.getCookies();

		ArrayList<ShipModel> cart = new ArrayList();

		if (cookies != null )
		{
			for (int i = 0; i < cookies.length; i++)
			{
				cookie = cookies[i];

				if (cookie.getName().equals("cart"))
				{
					Catalog catalog = new Catalog();
					String[] values = cookie.getValue().split(",");

					for (int n = 0; n < values.length; n++)
					{
						cart.add(catalog.getShipModel(Integer.parseInt(values[n])));
					}
				}
			}
		}

		if (cart.isEmpty())
		{
			cart.add(new ShipModel(0, "", ShipClass.GUNSHIP, 0));
		}

		request.setAttribute("cart", cart);

		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
