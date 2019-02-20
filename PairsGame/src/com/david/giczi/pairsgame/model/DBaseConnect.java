package com.david.giczi.pairsgame.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DBaseConnect {

	
	private static DBaseConnect connect;
	
	
	private DBaseConnect() {};
	
	public static DBaseConnect getInstace() {
		
		if(connect==null) {
			
			connect=new DBaseConnect();
			
		}
		
		return connect;
		
	}
	
	
	private static Connection getConnection() 
	{
		
		Connection con=null;
		
		
		try {
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/pairsgame", "postgres", "dave");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	
	
	public void createTable(){
		
		Connection con=null;
		Statement stmt=null;
		
		try {
			
			con=DBaseConnect.getConnection();
			stmt=con.createStatement();
			
			 String sql="CREATE TABLE RESULTS "+
						"(id INTEGER NOT NULL, "+
						"name TEXT, "+
						"steps INTEGER, "+
						"period INTEGER, "+
						"date TEXT, "+
						"PRIMARY KEY ( id ))";
			 
			 stmt.executeUpdate(sql);
			
		} catch (SQLException e) {


			e.printStackTrace();
		}
		finally {
			

			if(stmt!=null) {
				
				try {
					stmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				}
			
			if(con!=null) {
				
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
			
		}	
	
}

	
	public void truncateTable(){
		
		Connection con=null;
		Statement stmt=null;
		
		try {
			
			con=DBaseConnect.getConnection();
			stmt=con.createStatement();
			 
			 stmt.executeUpdate("TRUNCATE results");
			
			
		} catch (SQLException e) {


			e.printStackTrace();
		}
		finally {
			

			if(stmt!=null) {
				
				try {
					stmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				}
			
			if(con!=null) {
				
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
			
		}
	
}
	
	
	public int insert(String name,  int step, int period, String date) throws SQLException
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		int recordCounter=0;
		int id;
		
		
			
			con=DBaseConnect.getConnection();
			id=getMaxId(con)+1;
			ps=con.prepareStatement("insert into results(id, name, steps, period, date)values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, step);
			ps.setInt(4, period);
			ps.setString(5, date);
			recordCounter=ps.executeUpdate();
			
		
			if(ps!=null) {
				
				
			ps.close();
			}
				
			
			if(con!=null) {
				
			
			con.close();

			}
		
		return recordCounter;
	}
	
	
	public List<Result> getAllResults() throws SQLException
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Result> results=new ArrayList<>();
		
			con=DBaseConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM results");
				
			rs=ps.executeQuery();
				
			while(rs.next()) {
				
			results.add(new Result(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
				
		
			if(rs!=null) {
				
				rs.close();
			}
			if(ps!=null) {
				
				ps.close();
				
			}
			if(con!=null) {
				
				con.close();
			} 
			
		return results;
	}
	
	

	private int getMaxId(Connection con) throws SQLException
	{
	
		Statement stmt=null;
		ResultSet rs=null;
		int maxId=0;
		
		
			stmt=con.createStatement();
			
			rs=stmt.executeQuery("SELECT MAX(id) FROM results");
			
			while(rs.next()){
				
			maxId=rs.getInt(1);	
			
			}
			
		
		
		return maxId;
	}
	

	
}
