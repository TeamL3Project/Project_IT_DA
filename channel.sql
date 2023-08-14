select * from CHANNELLIST;
select * from CHBOARD;

select * from ITDA_USER;

SELECT * FROM chboard
ORDER BY boardNum DESC;

SELECT s.*
FROM channellist c
INNER JOIN seller s ON c.ownerid = s.userid
WHERE c.chNum = 21;


select * from seller;
select * from ITDA_USER;
select * from chboardcategory;


select * from chboardcategory 
where chnum = 21 order by chcate_name;

desc CHANNELLIST;

UPDATE channellist
SET chname = '책장위고양이'
WHERE chnum = 29 AND ownerid = 'itda2';

UPDATE channellist
SET chinfo = '안녕하세요. 고양이 집사입니다:)'
WHERE chnum = 29 AND ownerid = 'itda2';


UPDATE channellist
SET chname = '삼색고양이좋아'
WHERE chnum = 28 AND ownerid = 'itda3';

UPDATE channellist
SET chinfo = '안녕? 나는 삼색 고양이 "캐비"란다. 내가 "캐비"인 이유는 모자를 쓴 것처럼 머리 위에 무늬가 있기 때문이지 훗 멋지냐?'
WHERE chnum = 28 AND ownerid = 'itda3';

UPDATE channellist
SET chname = '밀림의냥'
WHERE chnum = 21 AND ownerid = 'itda';

UPDATE channellist
SET chprofile = 'fff.jpg'
WHERE chnum = 21 AND ownerid = 'itda';

UPDATE channellist
SET chinfo = '안녕하세요. [밀림의냥]채널입니다.'
WHERE chnum = 21 AND ownerid = 'itda';

select * 
from (SELECT * from chboard 
	where chnum = 21
	order by boardnum desc)
where rownum <= 3;	




UPDATE channellist
SET chname = '이불밖은위험하냥'
WHERE chnum = 31 AND ownerid = 'test4';

UPDATE channellist
SET chprofile = 'eee.jpeg'
WHERE chnum = 31 AND ownerid = 'test4';

UPDATE channellist
SET chinfo = '모두 감기조심하세요~!'
WHERE chnum = 31 AND ownerid = 'test4';

select * from chboard;

UPDATE chboard 
SET boardTitle='안녕하세요 반갑습니다!' 
WHERE boardNum=29;



INSERT INTO chboard (writer, boardNum, boardTitle) VALUES ('itda', 29, '안녕하세요 반갑습니다!');
INSERT INTO chboard (writer, boardNum, boardTitle) VALUES ('itda', 30, '안녕하세요 반갑습니다!');
INSERT INTO chboard (writer, boardNum, chNum, boardTitle) VALUES ('itda', 1, 29, '안녕하세요 반갑습니다!');



SELECT *
FROM channellist
WHERE chNum = 21;

select * 
from chboard
where boardNum = 21;


select * from chboardcategory;

select * from chboardcategory where chNum = 21
order by chcate_id asc;

select * from chboard where chNum = 21 order by chcate_id asc;

select * from chboardcategory where chNum = 21 order by chcate_id asc;