-- ��������
CREATE TABLE NOTICES
(
	"SEQ" VARCHAR2(10 BYTE), --�۹�ȣ
	"TITLE" VARCHAR2(200 BYTE), --����
	"WRITER" VARCHAR2(50 BYTE), --�ۼ���
	"CONTENT" VARCHAR2(4000 BYTE), --����
	"REGDATE" TIMESTAMP (6), --�ۼ���
	"HIT" NUMBER, --��ȸ��
	"FILESRC" VARCHAR2(500 BYTE)-- ÷������
);

-- ȸ�����̺�
CREATE TABLE "MEMBER"
(	
    "ID" VARCHAR2(50 BYTE), -- ȸ��ID(���� UID�ε� ������)
    "PWD" VARCHAR2(50 BYTE), -- ��й�ȣ
    "NAME" VARCHAR2(50 BYTE), -- �̸�
    "GENDER" VARCHAR2(10 BYTE), --����
    "BIRTH" VARCHAR2(10 BYTE), --����
    "IS_LUNAR" VARCHAR2(10 BYTE),  --���,�����Ǵ�
    "CPHONE" VARCHAR2(15 BYTE),  --�޴�����ȣ
    "EMAIL" VARCHAR2(200 BYTE), --�̸���
    "HABIT" VARCHAR2(200 BYTE), --���
    "REGDATE" DATE --������
);

INSERT INTO NOTICES
  ( SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)  
 VALUES 
 (  1 , 'ù ��° ��������', '���� ��', 'yaliny', SYSDATE, 0, null );
 
 commit;
 
 SELECT *
 FROM NOTICES;
