package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;

/**
 *  바이어 관리를 위한 Business Logic Layer
 *  CRUD
 */
public interface IBuyerService {

	/**
	 *  신규 등록
	 * @param mv
	 * @return  아이디중복(PKDUPLICATED), 성공(OK) , 실패(FAILED)
	 */
	public ServiceResult createBuyer(BuyerVO bv);
	
	/**
	 * 바이어 정보 상세 조회
	 * @param member 조회할 회원에 대한 조건을 가진 VO
	 * @return 조건에 맞는 회원이 없는 경우 , UserNotFoundException 발생
	 */
	public BuyerVO retrieveBuyer(BuyerVO bv);
	
	/**
	 * 바이어목록조회
	 * @return 조건에 맞는 회원이 없는 경우 ,size()==0
	 */
	public List<BuyerVO> retrieveBuyerList();
	

	/**
	 *  바이어정보수정
	 * @param mv 수정할 정보를 가진 VO
	 * @return IdNotFoundException , INVALIDPASSWORD (인증실패), 성공(OK), 실패(FAILED),  
	 */
	public ServiceResult modifyBuyer(BuyerVO bv);
	
	/**
	 *  바이어정보삭제
	 * @param mv
	 * @return IdNotFoundException , INVALIDPASSWORD (인증실패), 성공(OK), 실패(FAILED),  
	 */
	public ServiceResult removeBuyer(BuyerVO bv);
}
