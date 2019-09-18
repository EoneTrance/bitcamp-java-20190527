-- member
DROP TABLE IF EXISTS member RESTRICT;

-- board
DROP TABLE IF EXISTS board RESTRICT;

-- name
DROP TABLE IF EXISTS TABLE3 RESTRICT;

-- photo
DROP TABLE IF EXISTS photo RESTRICT;

-- member
CREATE TABLE member (
  member_no       INTEGER     NOT NULL, -- member_no
  id              VARCHAR(8)  NOT NULL, -- id
  name            VARCHAR(4)  NOT NULL, -- name
  password        VARCHAR(10) NOT NULL, -- password
  email           VARCHAR(20) NOT NULL, -- email
  registered_date DATE        NOT NULL  -- registered_date
);

-- member
ALTER TABLE member
  ADD CONSTRAINT PK_member -- member 기본키
    PRIMARY KEY (
      member_no -- member_no
    );

-- member 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
  ON member ( -- member
    id ASC -- id
  );

ALTER TABLE member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT;

-- board
CREATE TABLE board (
  board_no     INTEGER      NOT NULL, -- board_no
  member_no    INTEGER      NOT NULL, -- member_no
  photo_no     INTEGER      NOT NULL, -- photo_no
  title        VARCHAR(20)  NOT NULL, -- title
  contents     VARCHAR(255) NOT NULL, -- contents
  created_date DATE         NOT NULL  -- created_date
);

-- board
ALTER TABLE board
  ADD CONSTRAINT PK_board -- board 기본키
    PRIMARY KEY (
      board_no,  -- board_no
      member_no, -- member_no
      photo_no   -- photo_no
    );

ALTER TABLE board
  MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT;

-- name
CREATE TABLE TABLE3 (
);

-- photo
CREATE TABLE photo (
  photo_no  INTEGER     NOT NULL, -- photo_no
  file_name VARCHAR(50) NOT NULL, -- file_name
  file_path VARCHAR(50) NOT NULL  -- file_path
);

-- photo
ALTER TABLE photo
  ADD CONSTRAINT PK_photo -- photo 기본키
    PRIMARY KEY (
      photo_no -- photo_no
    );

-- photo 유니크 인덱스
CREATE UNIQUE INDEX UIX_photo
  ON photo ( -- photo
    file_path ASC -- file_path
  );

ALTER TABLE photo
  MODIFY COLUMN photo_no INTEGER NOT NULL AUTO_INCREMENT;

-- board
ALTER TABLE board
  ADD CONSTRAINT FK_photo_TO_board -- photo -> board
    FOREIGN KEY (
      photo_no -- photo_no
    )
    REFERENCES photo ( -- photo
      photo_no -- photo_no
    );

-- board
ALTER TABLE board
  ADD CONSTRAINT FK_member_TO_board -- member -> board
    FOREIGN KEY (
      member_no -- member_no
    )
    REFERENCES member ( -- member
      member_no -- member_no
    );