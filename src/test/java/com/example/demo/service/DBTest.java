package com.example.demo.service;

class DBTest {

	/*private static IDatabaseConnection dbconn;
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
	}*/
	//@Test
	void test() {
		
		/*String targetTablseName = "household_account_data";
		
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
		
		assertEquals(expectTable, actualTable);*/
	}

}
