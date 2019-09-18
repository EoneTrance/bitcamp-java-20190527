-- member
DROP TABLE IF EXISTS TABLE RESTRICT;

-- board
DROP TABLE IF EXISTS TABLE2 RESTRICT;

-- name
DROP TABLE IF EXISTS TABLE3 RESTRICT;

-- photo
DROP TABLE IF EXISTS TABLE4 RESTRICT;

-- member
CREATE TABLE TABLE (
	member_no       INTEGER     NOT NULL, -- member_no
	id              VARCHAR(8)  NOT NULL, -- id
	name            VARCHAR(4)  NOT NULL, -- name
	password        VARCHAR(10) NOT NULL, -- password
	email           VARCHAR(20) NOT NULL, -- email
	registered_date DATE        NOT NULL  -- registered_date
);

-- member
ALTER TABLE TABLE
	ADD CONSTRAINT PK_TABLE -- member 기본키
		PRIMARY KEY (
			member_no -- member_no
		);

-- member 유니크 인덱스
CREATE UNIQUE INDEX UIX_TABLE
	ON TABLE ( -- member
		id ASC -- id
	);

ALTER TABLE TABLE
	MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT;

-- board
CREATE TABLE TABLE2 (
	board_no     INTEGER      NOT NULL, -- board_no
	title        VARCHAR(20)  NOT NULL, -- title
	contents     VARCHAR(255) NOT NULL, -- contents
	created_date DATE         NOT NULL, -- created_date
	photo_no     INTEGER      NULL,     -- photo_no
	member_no    INTEGER      NULL      -- member_no
);

-- board
ALTER TABLE TABLE2
	ADD CONSTRAINT PK_TABLE2 -- board 기본키
		PRIMARY KEY (
			board_no -- board_no
		);

ALTER TABLE TABLE2
	MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT;

-- name
CREATE TABLE TABLE3 (
);

-- photo
CREATE TABLE TABLE4 (
	photo_no  INTEGER     NOT NULL, -- photo_no
	file_name VARCHAR(50) NOT NULL, -- file_name
	file_path VARCHAR(50) NOT NULL  -- file_path
);

-- photo
ALTER TABLE TABLE4
	ADD CONSTRAINT PK_TABLE4 -- photo 기본키
		PRIMARY KEY (
			photo_no -- photo_no
		);

-- photo 유니크 인덱스
CREATE UNIQUE INDEX UIX_TABLE4
	ON TABLE4 ( -- photo
		file_path ASC -- file_path
	);

-- board
ALTER TABLE TABLE2
	ADD CONSTRAINT FK_TABLE4_TO_TABLE2 -- photo -> board
		FOREIGN KEY (
			photo_no -- photo_no
		)
		REFERENCES TABLE4 ( -- photo
			photo_no -- photo_no
		);

-- board
ALTER TABLE TABLE2
	ADD CONSTRAINT FK_TABLE_TO_TABLE2 -- member -> board
		FOREIGN KEY (
			member_no -- member_no
		)
		REFERENCES TABLE ( -- member
			member_no -- member_no
		);