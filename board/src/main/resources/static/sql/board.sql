CREATE TABLE TB_BOARD(
     boardIdx NUMBER PRIMARY KEY,
	 title varchar2(10) NOT NULL,
	 content varchar2(40) NOT NULL,
	 hitCnt NUMBER default 0,
	 creatorId varchar2(10),
	 createdDatetime varchar2(10),
	 updaterId varchar2(10),
	 updatedDatetime varchar2(10),
	 deleteId varchar2(10),
	 deletedDatetime varchar2(10),
	 deleted_yn varchar(2) default 'N'
);

SELECT * FROM tb_Board;