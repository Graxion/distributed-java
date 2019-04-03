package edu.wctc.my.kgable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseBuilder
{
	public static void buildDB(String DB_URL)
	{
		try
		{
			Connection connection = DriverManager.getConnection(DB_URL + ";create=true");
			dropTables(connection);
			buildCustomerTable(connection);
			buildShipClassTable(connection);
			buildCatalogTable(connection);
			buildCartTable(connection);
			connection.close();
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void dropTables(Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();

		try
		{
			statement.execute("DROP TABLE Cart");
			statement.execute("DROP TABLE Catalog");
			statement.execute("DROP TABLE ShipClass");
			statement.execute("DROP TABLE Customer");
		}
		catch(Exception e){}

		statement.close();
	}

	private static void buildCustomerTable(Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE Customer (" +
				"CustomerID INT GENERATED ALWAYS AS IDENTITY, " +
				"Name VARCHAR(15), " +
				"CONSTRAINT PK_Customer PRIMARY KEY (CustomerID))");

		statement.execute("INSERT INTO Customer (Name) " +
				"VALUES ('Test Customer')");

		statement.close();
	}

	private static void buildShipClassTable(Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE ShipClass(" +
				"ClassID INT GENERATED ALWAYS AS IDENTITY, " +
				"Name VARCHAR(15) NOT NULL, " +
				"CONSTRAINT PK_ShipClass PRIMARY KEY (ClassID))");

		statement.execute("INSERT INTO ShipClass (Name) " +
				"VALUES ('Fighter'), " +
				"('Gunship'), " +
				"('Corvette'), " +
				"('Destroyer'), " +
				"('Light Cruiser'), " +
				"('Heavy Cruiser'), " +
				"('Battleship'), " +
				"('Dreadnought')");

		statement.close();
	}

	private static void buildCatalogTable(Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE Catalog (" +
				"ModelID INT GENERATED ALWAYS AS IDENTITY, " +
				"Name VARCHAR(25) NOT NULL, " +
				"ShipClass INT NOT NULL, " +
				"BasePrice INT NOT NULL, " +
				"CONSTRAINT PK_Catalog PRIMARY KEY (ModelID), " +
				"CONSTRAINT FK_Catalog_ShipClass FOREIGN KEY (ShipClass) " +
				"REFERENCES ShipClass (ClassID))");

		statement.execute("INSERT INTO Catalog (Name, ShipClass, BasePrice) " +
				"VALUES ('Test Fighter', 1, 500), " +
				"('Test Gunship', 2, 25000), " +
				"('Test Corvette', 3, 50000), " +
				"('Test Destroyer', 4, 100000), " +
				"('Test Light Cruiser', 5, 200000), " +
				"('Test Heavy Cruiser', 6, 300000), " +
				"('Test Battleship', 7, 500000), " +
				"('Test Dreadnought', 8, 1000000)");

		statement.close();
	}

	private static void buildCartTable(Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE Cart (" +
				"CustomerID INT NOT NULL, " +
				"ModelID INT NOT NULL, " +
				"Quantity INT NOT NULL, " +
				"CONSTRAINT PK_Cart PRIMARY KEY (CustomerID, ModelID), " +
				"CONSTRAINT FK_Cart_Customer FOREIGN KEY (CustomerID) " +
				"REFERENCES Customer (CustomerID), " +
				"CONSTRAINT FK_Cart_Catalog FOREIGN KEY (ModelID) " +
				"REFERENCES Catalog (ModelID))");

		statement.close();
	}
}
