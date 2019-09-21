package kr.or.ddit.buyer.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import utils.ConnectionFactory;

public class BuyerDAOImpl implements IBuyerDAO{
	
	private static BuyerDAOImpl instance;
	
	private BuyerDAOImpl() {
		
	}

	public static BuyerDAOImpl getInstance() {
		if(instance==null) {
			instance = new BuyerDAOImpl();
		}
		return instance;
	}

	@Override
	public int insertBuyer(BuyerVO bv) {
		StringBuffer sql = new StringBuffer();
		int result = 0;
		sql.append("INSERT INTO BUYER");
		sql.append("(                ");
		sql.append("BUYER_ID,        ");
		sql.append("BUYER_NAME,      ");
		sql.append("BUYER_LGU,       ");
		sql.append("BUYER_BANK,      ");
		sql.append("BUYER_BANKNO,    ");
		sql.append("BUYER_BANKNAME,  ");
		sql.append("BUYER_ZIP,       ");
		sql.append("BUYER_ADD1,      ");
		sql.append("BUYER_ADD2,      ");
		sql.append("BUYER_COMTEL,    ");
		sql.append("BUYER_FAX,       ");
		sql.append("BUYER_MAIL,      ");
		sql.append("BUYER_CHARGER,   ");
		sql.append("BUYER_TELEXT   ");
		sql.append(") VALUES(          ");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("?,");
		sql.append("? )");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			Class<BuyerVO> voType = (Class<BuyerVO>) bv.getClass();
			Field[] fds = voType.getDeclaredFields();
			Method voGetter = null;
			Method pstmtSetter = pstmt.getClass().getDeclaredMethod("setString",int.class, String.class);
			int idx = 1;
			for(Field tmp:fds) {
				String name = tmp.getName();
				PropertyDescriptor pd = new PropertyDescriptor(name, voType);
				voGetter = pd.getReadMethod();
				pstmtSetter.invoke(pstmt,idx++,voGetter.invoke(bv));
			}
			result = pstmt.executeUpdate();
		} catch (SQLException | IntrospectionException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> lbv = new ArrayList<>();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BuyerVO buyer = null;
		sql.append("SELECT                   ");
		sql.append("BUYER_ID,                ");
		sql.append("BUYER_NAME,              ");
		sql.append("BUYER_LGU,               ");
		sql.append("BUYER_BANK,              ");
		sql.append("BUYER_BANKNO,            ");
		sql.append("BUYER_BANKNAME,          ");
		sql.append("BUYER_ZIP,               ");
		sql.append("BUYER_ADD1,              ");
		sql.append("BUYER_ADD2,              ");
		sql.append("BUYER_COMTEL,            ");
		sql.append("BUYER_FAX,               ");
		sql.append("BUYER_MAIL,              ");
		sql.append("BUYER_CHARGER,           ");
		sql.append("BUYER_TELEXT             ");
		sql.append("FROM BUYER               ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			rs = pstmt.executeQuery();
			Class<BuyerVO> voType = BuyerVO.class;
			Field[] fds = voType.getDeclaredFields();
			Method voGetter = null;
			Method voSetter = null;
			Method rsGetter = rs.getClass().getDeclaredMethod("getString", String.class);
			while(rs.next()) {
				buyer = new BuyerVO();
				for(Field tmp:fds) {
					String name = tmp.getName();
					PropertyDescriptor pd = new PropertyDescriptor(name, voType);
					voGetter = pd.getReadMethod();
					voSetter = pd.getWriteMethod();
					voSetter.invoke(buyer, rsGetter.invoke(rs,name));
				}
				lbv.add(buyer);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (IntrospectionException |NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return lbv;
	}

	@Override
	public BuyerVO selectBuyer(BuyerVO bv) {
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		BuyerVO buyer = null;
		sql.append("SELECT                   ");
		sql.append("BUYER_ID,                ");
		sql.append("BUYER_NAME,              ");
		sql.append("BUYER_LGU,               ");
		sql.append("BUYER_BANK,              ");
		sql.append("BUYER_BANKNO,            ");
		sql.append("BUYER_BANKNAME,          ");
		sql.append("BUYER_ZIP,               ");
		sql.append("BUYER_ADD1,              ");
		sql.append("BUYER_ADD2,              ");
		sql.append("BUYER_COMTEL,            ");
		sql.append("BUYER_FAX,               ");
		sql.append("BUYER_MAIL,              ");
		sql.append("BUYER_CHARGER,           ");
		sql.append("BUYER_TELEXT             ");
		sql.append("FROM BUYER               ");
		sql.append("WHERE BUYER_ID= ?		 ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, bv.getBuyer_id());
			rs = pstmt.executeQuery();
			Class<BuyerVO> voType = BuyerVO.class;
			Field[] fds = voType.getDeclaredFields();
			Method voGetter = null;
			Method voSetter = null;
			Method rsGetter = rs.getClass().getDeclaredMethod("getString", String.class);
			while(rs.next()) {
				buyer = new BuyerVO();
				for(Field tmp:fds) {
					String name = tmp.getName();
					PropertyDescriptor pd = new PropertyDescriptor(name, voType);
					voGetter = pd.getReadMethod();
					voSetter = pd.getWriteMethod();
					voSetter.invoke(buyer, rsGetter.invoke(rs,name));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (IntrospectionException |NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return buyer;
	}

	@Override
	public int updateBuyer(BuyerVO bv) {
		StringBuffer sql = new StringBuffer();
		int result = 0;
		sql.append("UPDATE BUYER SET    ");
		sql.append("BUYER_ID=?,"        );
		sql.append("BUYER_NAME=?,"      );
		sql.append("BUYER_LGU=?,"       );
		sql.append("BUYER_BANK=?,"      );
		sql.append("BUYER_BANKNO=?,"    );
		sql.append("BUYER_BANKNAME=?,"  );
		sql.append("BUYER_ZIP=?,"       );
		sql.append("BUYER_ADD1=?,"      );
		sql.append("BUYER_ADD2=?,"      );
		sql.append("BUYER_COMTEL=?,"    );
		sql.append("BUYER_FAX=?,"       );
		sql.append("BUYER_MAIL=?,"      );
		sql.append("BUYER_CHARGER=?,"   );
		sql.append("BUYER_TELEXT=?"     );
		sql.append("WHERE BUYER_ID=?"  );
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				Class<BuyerVO> voType = (Class<BuyerVO>) bv.getClass();
				Field[] fds = voType.getDeclaredFields();
				Method voGetter = null;
				Method pstmtSetter = pstmt.getClass().getDeclaredMethod("setString",int.class, String.class);
				int idx = 1;
				for(Field tmp:fds) {
					String name = tmp.getName();
					PropertyDescriptor pd = new PropertyDescriptor(name, voType);
					voGetter = pd.getReadMethod();
					pstmtSetter.invoke(pstmt,idx++,voGetter.invoke(bv));
				}
				result = pstmt.executeUpdate();
			} catch (SQLException | IntrospectionException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return result;
	}

	@Override
	public int deleteBuyer(BuyerVO bv) {
		StringBuffer sql = new StringBuffer();
		int result = 0;
		sql.append("DELETE FROM BUYER WHERE BUYER_ID=?    ");
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				pstmt.setString(1, bv.getBuyer_id());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
	}
	

}
