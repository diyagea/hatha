package com.diyagea.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diyagea.pojo.Hatha;
import com.diyagea.util.DBConnection;

public class HathaDAO {

	public void addHatha(Hatha ha){
		Connection conn = DBConnection.getConnection();	//获得连接对象
		String addSQL = "insert into " +
				"tb_hatha(name,code,date,shop,totalPrice,cost,amount,turnover,quotedPrice) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;					//声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL);		//获得预处理对象并赋值
			//pstmt.setString(1, vote.getVoteName());		//设置投票名称
			//pstmt.setInt(2, vote.getChannelID());		//设置频道ID
			pstmt.setString(1, ha.getName());
			pstmt.setString(2, ha.getCode());
			Date date=new Date(ha.getDate().getTime());
			pstmt.setDate(3, date);
			pstmt.setString(4, ha.getShop());
			pstmt.setDouble(5, ha.getTotalPrice());
			pstmt.setDouble(6, ha.getCost());
			pstmt.setDouble(7, ha.getAmount());
			pstmt.setDouble(8, ha.getTurnover());
			pstmt.setDouble(9, ha.getQuotedPrice());
			
			pstmt.executeUpdate();								//执行添加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
	}
	
	public List<Hatha> findByPage(Integer curPage, Integer num) {
		Connection conn = DBConnection.getConnection();		//获得连接对象
		String findSQL = "select * from tb_hatha limit ?,?";		//查询SQL语句
		PreparedStatement pstmt = null;	//声明预处理对象
		ResultSet rs = null;
		List<Hatha> hathas = new ArrayList<Hatha>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//获得预处理对象并赋值
			pstmt.setInt(1, curPage);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();						//执行查询
			while(rs.next()) {
				Hatha h = new Hatha();
				h.setName(rs.getString(1));
				h.setCode(rs.getString(2));
				h.setDate(rs.getDate(3));
				h.setShop(rs.getString(4));
				h.setTotalPrice(rs.getDouble(5));
				h.setCost(rs.getDouble(6));
				h.setAmount(rs.getDouble(7));
				h.setTurnover(rs.getDouble(8));
				h.setQuotedPrice(rs.getDouble(9));
				hathas.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return hathas;
	}
	
	public int getCount(){
		Connection conn = DBConnection.getConnection();		//获得连接对象
		String findSQL = "select count(name) from tb_hatha";		//查询SQL语句
		PreparedStatement pstmt = null;	//声明预处理对象
		ResultSet rs = null;
		List<Hatha> hathas = new ArrayList<Hatha>();
		try {
			pstmt = conn.prepareStatement(findSQL);
			rs = pstmt.executeQuery();//执行查询
			
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//关闭结果集对象
			DBConnection.close(pstmt);							//关闭预处理对象
			DBConnection.close(conn);							//关闭连接对象
		}
		return 0;
	}

}
