package edu.wctc.my.kgable.model;

import edu.wctc.my.kgable.DatabaseBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Catalog
{
	// private int nextModelID = 1;

	private static final String DB_URL = "jdbc:derby:ShipyardDB";

	private ArrayList<ShipModel> catalog = new ArrayList<ShipModel>();

	public Catalog()
	{
		// DatabaseBuilder.buildDB(DB_URL);

		try
		{
			Connection connection = DriverManager.getConnection(DB_URL);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT catalog.ModelID, catalog.Name, shipclass.Name AS ShipClass, catalog.BasePrice FROM Catalog AS catalog " +
					"JOIN ShipClass as shipclass " +
					"ON catalog.ShipClass = shipclass.ClassID");

			while (resultSet.next())
			{
				int modelID = resultSet.getInt("ModelID");
				String name = resultSet.getString("Name");
				ShipClass shipClass = ShipClass.valueOf(resultSet.getString("ShipClass").toUpperCase().replace(' ', '_'));
				int basePrice = resultSet.getInt("BasePrice");

				catalog.add(new ShipModel(modelID, name, shipClass, basePrice));
			}

			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}

		// catalog.add(new ShipModel(nextModelID++, "Test Fighter", ShipClass.FIGHTER, 500));
		// catalog.add(new ShipModel(nextModelID++, "Test Gunship", ShipClass.GUNSHIP, 25000));
		// catalog.add(new ShipModel(nextModelID++, "Test Corvette", ShipClass.CORVETTE, 50000));
		// catalog.add(new ShipModel(nextModelID++, "Test Destroyer", ShipClass.DESTROYER, 100000));
		// catalog.add(new ShipModel(nextModelID++, "Test Light Cruiser", ShipClass.LIGHT_CRUISER, 200000));
		// catalog.add(new ShipModel(nextModelID++, "Test Heavy Cruiser", ShipClass.HEAVY_CRUISER, 300000));
		// catalog.add(new ShipModel(nextModelID++, "Test Battleship", ShipClass.BATTLESHIP, 500000));
		// catalog.add(new ShipModel(nextModelID++, "Test Dreadnought", ShipClass.DREADNOUGHT, 1000000));
	}

	public ArrayList<ShipModel> getCatalog()
	{
		return catalog;
	}

	public ShipModel getShipModel(int modelID)
	{
		for (int i = 0; i < catalog.size(); i++)
		{
			ShipModel shipModel = catalog.get(i);

			if (shipModel.getModelID() == modelID)
			{
				return shipModel;
			}
		}

		return null;
	}
}
