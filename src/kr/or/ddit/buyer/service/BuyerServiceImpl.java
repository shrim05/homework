package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;

public class BuyerServiceImpl implements IBuyerService{
	
	private static BuyerServiceImpl instance;
	private IBuyerDAO dao = BuyerDAOImpl.getInstance();
	private BuyerServiceImpl() {}
	
	public static BuyerServiceImpl getInstance() {
		if(instance==null) {
			instance = new BuyerServiceImpl();
		}
		return instance;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO bv) {
		int result = dao.insertBuyer(bv);
		if(result>0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAILED;
		}
	}

	@Override
	public BuyerVO retrieveBuyer(BuyerVO bv) {
		return dao.selectBuyer(bv);
		
	}

	@Override
	public List<BuyerVO> retrieveBuyerList() {
		return dao.selectBuyerList();
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO bv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO bv) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
