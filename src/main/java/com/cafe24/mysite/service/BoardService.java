package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.BoardUsersDto;
import com.cafe24.mysite.vo.BoardUsersJoinVo;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardUsersDto boardUesrDto;

	public Map<String, Object> getList(String pageNum, String kwd) {
		Long numberPerPage = 5L; // 페이지당 글수
		Long pagePerBlock = 3L; // 블록당 페이지수
		if (pageNum == null) {
			pageNum = "1";
		}

		Long totalRecord = (long) (boardDao).getList().size();

		if (kwd == null) {
			kwd = "";
		} else {
			Long maxSize = (long) boardDao.getList().size();
			totalRecord = (long) (boardUesrDto.getList(0L, maxSize, kwd).size());
		}

		Long totalPage = ((totalRecord - 1) / numberPerPage) + 1; // ex)pagePerBlock = 5L인 경우 총 6개의 데이터는 두페이지
		// Long totalBlock = (totalRecord/(numberPerPage*pagePerBlock))+1; //전체 블록 개수
		// ex)총2페이지일경우 1블록 [<1,2>]
		Long totalBlock = ((totalRecord - 1) / (numberPerPage * pagePerBlock)) + 1; // 전체 블록 개수 ex)총2페이지일경우 1블록 [<1,2>]
		Long nowPage = Long.parseLong(pageNum);

		// nowBlock 구하는 공식 이유
		// 세개의 페이지로 구성된 블록의 경우, 3번째 페이지는 1블록이기때문에
		// 1페이지 -> 0/3+1 =1 Page
		// 2페이지 -> 1/3+1 =1 Page
		// 3페이지 -> 2/3+1 =1 Page
		// 4페이지 -> 3/3+1 =2 Page
		// 5페이지 -> 4/3+1 =2 Page
		// 6페이지 -> 5/3+1 =2 Page
		// 7페이지 -> 6/3+1 =3 http://localhost:8080/mysite/boardPage
		// 그래서 nowPage-1을 블록의 페이지수로 나누고 +1

		Long nowBlock = ((nowPage - 1) / pagePerBlock) + 1;
		Long nowBlockPageStartNum = (nowBlock - 1) * pagePerBlock + 1;
		Long nowBlockPageFinishNum = nowBlock * pagePerBlock;
		if (nowBlockPageFinishNum > totalPage)
			nowBlockPageFinishNum = totalPage;

		// System.out.println("------------------------------------");
		// System.out.println("kwd : "+kwd);
		// System.out.println("nowPage : "+ nowPage);
		// System.out.println("totalRecord : "+ totalRecord);
		// System.out.println("totalPage : "+ totalPage);
		// System.out.println("totalBlock : "+ totalBlock);
		// System.out.println("nowBlock : "+ nowBlock);
		// System.out.println("nowBlockPageStartNum : "+ nowBlockPageStartNum);
		// System.out.println("nowBlockPageFinishNum : "+ nowBlockPageFinishNum);

		List<BoardUsersJoinVo> list = boardUesrDto.getList(nowPage - 1, numberPerPage, kwd);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalRecord", totalRecord);
		map.put("numberPerPage", numberPerPage);
		map.put("pagePerBlock", pagePerBlock);
		map.put("totalPage", totalPage);
		map.put("totalBlock", totalBlock);
		map.put("nowPage", nowPage);
		map.put("nowBlock", nowBlock);
		map.put("nowBlockPageStartNum", nowBlockPageStartNum);
		map.put("nowBlockPageFinishNum", nowBlockPageFinishNum);
		map.put("kwd", kwd);
		return map;

	}

	public void write(BoardVo vo) {

		Long groupNo = boardDao.getMaxGroupNo();
		if(groupNo==null)
			groupNo=1L;
		vo.setGroupNo(++groupNo);
		vo.setOrderNo(1L);
		vo.setDepth(0L);
		vo.setHit(0L);

		boardDao.insert(vo);
	}

	public void delete(BoardVo vo) {
		boardDao.delete(vo);
	}
	public BoardVo getSameNoVo(BoardVo vo) {
		return boardDao.getSameNoVo(vo.getNo());
	}
	public BoardVo view(BoardVo vo) {
		vo = boardDao.getSameNoVo(vo.getNo());
		boardDao.upHit(vo);
		vo.setHit(vo.getHit()+1); //디비에 조회수 올린만큼  현재 객체에도 반영
		return vo;
	}
	
	public BoardVo modify(BoardVo vo) {
		BoardVo temp = boardDao.getSameNoVo(vo.getNo());
		temp.setTitle(vo.getTitle());
		temp.setContent(vo.getContent());
		boardDao.modify(temp);
		return temp;
	}
	
	public void reply(BoardVo vo) {
		vo.setOrderNo(vo.getOrderNo() + 1);
		vo.setDepth(vo.getDepth() + 1);
		vo.setHit(0L);
		boardDao.upOrderNo(vo);
		boardDao.insert(vo);
	}
}
