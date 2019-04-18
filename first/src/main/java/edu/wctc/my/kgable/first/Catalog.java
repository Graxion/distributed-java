package edu.wctc.my.kgable.first;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Catalog
{
	private int nextModelID;

	@RequestMapping("/catalog")

	public List<ShipModel> getCatalog()
	{
		List<ShipModel> catalog = new ArrayList<>();

		nextModelID = 1;

		catalog.add(new ShipModel(nextModelID++, "Test Fighter", ShipClass.FIGHTER, 500));
		catalog.add(new ShipModel(nextModelID++, "Test Gunship", ShipClass.GUNSHIP, 25000));
		catalog.add(new ShipModel(nextModelID++, "Test Corvette", ShipClass.CORVETTE, 50000));
		catalog.add(new ShipModel(nextModelID++, "Test Destroyer", ShipClass.DESTROYER, 100000));
		catalog.add(new ShipModel(nextModelID++, "Test Light Cruiser", ShipClass.LIGHT_CRUISER, 200000));
		catalog.add(new ShipModel(nextModelID++, "Test Heavy Cruiser", ShipClass.HEAVY_CRUISER, 300000));
		catalog.add(new ShipModel(nextModelID++, "Test Battleship", ShipClass.BATTLESHIP, 500000));
		catalog.add(new ShipModel(nextModelID++, "Test Dreadnought", ShipClass.DREADNOUGHT, 1000000));

		return catalog;
	}
}
