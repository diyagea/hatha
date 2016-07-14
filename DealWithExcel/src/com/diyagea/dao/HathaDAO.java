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
		Connection conn = DBConnection.getConnection();	//������Ӷ���
		String addSQL = "insert into " +
				"tb_hatha(name,code,date,shop,totalPrice,cost,amount,turnover,quotedPrice) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;					//����Ԥ�������
		try {
			pstmt = conn.prepareStatement(addSQL);		//���Ԥ������󲢸�ֵ
			//pstmt.setString(1, vote.getVoteName());		//����ͶƱ����
			//pstmt.setInt(2, vote.getChannelID());		//����Ƶ��ID
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
			
			pstmt.executeUpdate();								//ִ�����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(pstmt);							//�ر�Ԥ�������
			DBConnection.close(conn);							//�ر����Ӷ���
		}
	}
	
	public List<Hatha> findByPage(Integer curPage, Integer num) {
		Connection conn = DBConnection.getConnection();		//������Ӷ���
		String findSQL = "select * from tb_hatha limit ?,?";		//��ѯSQL���
		PreparedStatement pstmt = null;	//����Ԥ�������
		ResultSet rs = null;
		List<Hatha> hathas = new ArrayList<Hatha>();
		try {
			pstmt = conn.prepareStatement(findSQL);		//���Ԥ������󲢸�ֵ
			pstmt.setInt(1, curPage);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();						//ִ�в�ѯ
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
			DBConnection.close(rs);								//�رս��������
			DBConnection.close(pstmt);							//�ر�Ԥ�������
			DBConnection.close(conn);							//�ر����Ӷ���
		}
		return hathas;
	}
	
	public int getCount(){
		Connection conn = DBConnection.getConnection();		//������Ӷ���
		String findSQL = "select count(name) from tb_hatha";		//��ѯSQL���
		PreparedStatement pstmt = null;	//����Ԥ�������
		ResultSet rs = null;
		List<Hatha> hathas = new ArrayList<Hatha>();
		try {
			pstmt = conn.prepareStatement(findSQL);
			rs = pstmt.executeQuery();//ִ�в�ѯ
			
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(rs);								//�رս��������
			DBConnection.close(pstmt);							//�ر�Ԥ�������
			DBConnection.close(conn);							//�ر����Ӷ���
		}
		return 0;
	}

}
