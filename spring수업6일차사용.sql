ALTER TABLE member
ADD (point number(10) default(0));

desc member;

ALTER TABLE notices
ADD CONSTRAINT pk_notices_seq PRIMARY KEY(seq);

ALTER TABLE member
ADD CONSTRAINT ck_member_point CHECK(point < 3);

ALTER TABLE notices
ADD CONSTRAINT uk_notices_title UNIQUE(title);


SELECT id, point
FROM member;

SELECT *
FROM notices;

UPDATE member
SET point = 1
WHERE id = 'yaliny';

commit;

ALTER TABLE member DROP CONSTRAINT ck_member_point;
ALTER TABLE notices DROP CONSTRAINT uk_notices_title;