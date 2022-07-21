SELECT id as username 
        , CASE WHEN id='admin'
            THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' end as authority
FROM member;

SELECT *
FROM member;

DELETE member
where id = 'test';

commit;

select id as  username , case when id IN('yaliny', 'test') then 'ROLE_ADMIN' else 'ROLE_USER' end as authority from member where id = 'test';