package edu.wctc.my.kgable.model;

import java.util.ArrayList;

public class Catalog
{
	private int nextModelID = 1;

	private ArrayList<ShipModel> catalog = new ArrayList<ShipModel>();

	public Catalog()
	{
		catalog.add(new ShipModel(nextModelID++, "Test Fighter", ShipClass.FIGHTER, 500));
		catalog.add(new ShipModel(nextModelID++, "Test Gunship", ShipClass.GUNSHIP, 25000));
		catalog.add(new ShipModel(nextModelID++, "Test Corvette", ShipClass.CORVETTE, 50000));
		catalog.add(new ShipModel(nextModelID++, "Test Destroyer", ShipClass.DESTROYER, 100000));
		catalog.add(new ShipModel(nextModelID++, "Test Light Cruiser", ShipClass.LIGHT_CRUISER, 200000));
		catalog.add(new ShipModel(nextModelID++, "Test Heavy Cruiser", ShipClass.HEAVY_CRUISER, 300000));
		catalog.add(new ShipModel(nextModelID++, "Test Battleship", ShipClass.BATTLESHIP, 500000));
		catalog.add(new ShipModel(nextModelID++, "Test Dreadnought", ShipClass.DREADNOUGHT, 1000000));
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
