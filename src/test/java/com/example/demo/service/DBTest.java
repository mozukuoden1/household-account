package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DBTest {

	private static IDatabaseConnection dbconn;
	private static Connection conn;
	
	@BeforeAll
	static void setup() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection
				("jdbc:mysql://localhost/test?user=root&password=root&serverTimezone=JST");
		
		dbconn = new DatabaseConnection(conn, "TEST");
		//メタデータ取得に必要これがないとエラー
		DatabaseConfig config = dbconn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER,
				new MySqlMetadataHandler());
	}
	@Test
	void test() {
		
		String targetTablseName = "household_account_data";
		
		IDataSet dataset = new XlsDataSet(new File(null));//null => new File("exclファイルの階層")
		DatabaseOperation.DELETE_ALL.execute(dbconn, dataset);
		DatabaseOperation.INSERT.execute(dbconn, dataset);
		
		Statement st = conn.createStatement();
		st.execute("select * from " + targetTablseName);
		st.close();
		
		IDataSet databaseDataset = dbconn.createDataSet();
		ITable actualTable = databaseDataset.getTable(targetTablseName);
		
		IDataSet expecteDataset = new XlsDataSet(new File(null));
		ITable expectTable = expecteDataset.getTable(targetTablseName);
		
		assertEquals(expectTable, actualTable);
	}

}
