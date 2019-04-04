package edu.wctc.my.kgable.controller;

import edu.wctc.my.kgable.model.Catalog;

import java.io.IOException;

public class CatalogController extends javax.servlet.http.HttpServlet
{
	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{

	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		request.setAttribute("catalog", new Catalog().getCatalog());

		request.getRequestDispatcher("catalog.jsp").forward(request, response);
	}
}
