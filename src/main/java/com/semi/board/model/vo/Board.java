package com.semi.board.model.vo;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter; 
	private String createDate;
	private int count;
	private String status;
	private String saleYn;
	private int amount;
	private String titleImg;
	private String address;
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public Board() {}
	
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, String createDate, int count,
			String status, String saleYn) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.count = count;
		this.status = status;
		this.saleYn = saleYn;
	}

	public Board(int boardNo, String boardTitle, String createDate, int count, int amount, String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createDate = createDate;
		this.count = count;
		this.amount = amount;
		this.titleImg = titleImg;
	}
	
	//내글보기 받아주는 생성자
	public Board(int boardNo, String boardTitle, String boardWriter, String createDate, int count, 
			int amount,  String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.count = count;
		this.amount = amount;
		this.titleImg = titleImg;
	}
	//Ajax 로 내글보기 받아오는VO
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, String createDate, int count,
			String status, int amount, String titleImg, String address) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.count = count;
		this.status = status;
		this.amount = amount;
		this.titleImg = titleImg;
		this.address=address;
	}
	
	//나의 판매내역 담아오는 vo (ajax)
	public Board(int boardNo, String boardTitle, int amount, String titleImg, String address) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.amount = amount;
		this.titleImg = titleImg;
		this.address = address;
	}
	
	//나의 구매내역 담아오는 vo
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, String createDate, int count,
			int amount, String titleImg, String address) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.count = count;
		this.amount = amount;
		this.titleImg = titleImg;
		this.address = address;
	}
	
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter,
			String createDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}
	
	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSaleYn() {
		return saleYn;
	}

	public void setSaleYn(String saleYn) {
		this.saleYn = saleYn;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", createDate=" + createDate + ", count=" + count + ", status="
				+ status + ", saleYn=" + saleYn + "]";
	}



	

}
