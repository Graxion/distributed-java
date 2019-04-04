package edu.wctc.my.kgable.controller;

import edu.wctc.my.kgable.model.Catalog;
import edu.wctc.my.kgable.model.ShipModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		ArrayList<ShipModel> catalog = new Catalog().getCatalog();
		ArrayList<ShipModel> result = new ArrayList<>();

		ShipModel shipModel;

		for (int i = 0; i < catalog.size(); i++)
		{
			shipModel = catalog.get(i);

			if (shipModel.getName().toLowerCase().contains(request.getParameter("search").toLowerCase()))
			{
				result.add(shipModel);
			}
		}

		request.setAttribute("catalog", result);

		request.getRequestDispatcher("catalog.jsp").forward(request, response);
	}
}
