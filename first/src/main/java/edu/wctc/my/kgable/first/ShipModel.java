package edu.wctc.my.kgable.first;

public class ShipModel
{
	private int modelID;
	private String name;
	private ShipClass shipClass;
	private int basePrice;

	public ShipModel(int modelID, String name, ShipClass shipClass, int basePrice)
	{
		setModelID(modelID);
		setName(name);
		setShipClass(shipClass);
		setBasePrice(basePrice);
	}

	public void setModelID(int modelID)
	{
		this.modelID = modelID;
	}

	public int getModelID()
	{
		return modelID;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setShipClass(ShipClass shipClass)
	{
		this.shipClass = shipClass;
	}

	public ShipClass getShipClass()
	{
		return shipClass;
	}

	public void setBasePrice(int basePrice)
	{
		this.basePrice = basePrice;
	}

	public int getBasePrice()
	{
		return basePrice;
	}
}
