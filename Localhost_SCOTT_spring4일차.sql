
INSERT INTO MEMBER (ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE)
VALUES( 'yaliny', '1234', '관리자', '여성', '1995-12-17', 'Solar', '010-1234-1234', 'yaliny@naver.com', 'music,trip', SYSDATE);

commit;

desc member;

select *
from member;

select *
from notices;