package com.cafe24.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	private String writeDate;
	private Long hit;
	private Long userNo;
	private String content;
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth="
				+ depth + ", writeDate=" + writeDate + ", hit=" + hit + ", userNo=" + userNo + ", content=" + content
				+ "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
