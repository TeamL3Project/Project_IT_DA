select * from chboard;

-- 카테고리 id 별 게시물을 나타내는 쿼리문
"select * "
"from chboard "
"where chcate_id = ? ";

-- 
select * from chboard;

select * from chcategory;

select * from chboardcategory;
select * from tag;

insert into chcategory
value(1,  '고양이');

desc tag;
desc chboard;



select * from tab;

select * from chboardcategory where chnum = 43;
select * from chboard
where chnum=43;


select * from chboardcategory where chnum = 21 order by chcate_name;

select * from chboard;
select * from tag;
CREATE SEQUENCE tag_SEQ;

select * from chboard;
select * from chboardcategory; 
where chnum=21;

select ownerid
from channellist
where chnum=21;

-- 게시물 리스트에서 출력하기
select *
from chboardcategory A natural join chcategory
where chcate_name='테스트1';

-- 게시물 작성에서 출력하기
select *
from chboardcategory A natural join chcategory
where chnum = 21;

insert into tag
values (tag_seq, 7, '#테스트1'); 


select * from chboard where boardnum = 21

-- 게시판 view에서 보드와 관련된 카테고리 이름 가져오기
select chboard.*, chboardcategory.chcate_name
from chboard   join chboardcategory
on   chboard.chnum  =  chboardcategory.chnum
where boardnum = 21  
and chboard.chcate_id = chboardcategory.chcate_id;

-- 게시판 board의 카테고리 name 가져오기
select *
from chboardcategory
where chcate_name='테스트1';

select * from channellist;

select channellist.*, chboard.*, chboardcategory.*
from channellist c, chboard b, chboardcategory t
where c.chnum = b.chnum and b.chnum = t.chnum;

select chboard.*, channellist.chname, chboardcategory.chcate_name
from chboard b
	join channelist c
	on	b.chnum = c.chnum 
	join chboardcategory t
	on	c.chnum = t.chnum;
	where boardnum = 21;

-- 게시물 리스트 출력하기
select *
from channellist c
	join chboard b
	on	c.chnum = b.chnum 
	join chboardcategory t
	on	b.chnum = t.chnum
	where t.chcate_name = '테스트1'
	and b.chcate_id = t.chcate_id;
	
select chcate_name
from chboardcategory
where 


select * from itda_user;

select * from seller;

select * from channellist;

select * from boardreply;
select count(*) from boardreply
where boardnum=16;

select distinct*
from chboard a
left outer join boardreply b
on a.boardnum = b.boardnum
where a.chnum = 21;

뭐가 잘못됐어?

select chnum 
from seller a join channellist b
on b.ownerid = a.userid
where ownerid = 'itda11';

select chnum
from channellist
where ownerid = 'itda13';

select *
from comment

select *
from chboard a
	join boardreply b
	on	a.boardnum = b.boardnum 
	join tag c
	on	b.boardnum = c.boardnum
	where a.chnum = 21;

	select * from tag;
	
select nvl(*,0)
from (select a.boardnum, 
						(select count(*)
						from boardreply
						where boardnum = a.boardnum)
	from chboard as a)
where chnum = 16;

select * from boardreply;

select a.boardnum count(b.boardnum)
from chboard a
left outer join boardreply b
on a.boardnum = b.boardnum
where a.chnum = 21;

select a.boardnum, count(b.boardnum)
from chboard a
left outer join boardreply b
on a.boardnum = b.boardnum
where a.chnum = 21
group by a.boardnum;

select a.boardnum, a.chnum, a.writer, a., count(b.boardnum)
from chboard a
left outer join boardreply b
on a.boardnum = b.boardnum
where a.chnum = 21
group by *;

select * from chboardcategory;

select *
from chboardcategory a
left join chboard b
		on a.chnum = b.chnum
left join  c boardreply
		on b.boardnum = c.boardnum
where chnum=21


select *
from chboardcategory a
left join chboard b
		on a.chnum = b.chnum
left join  c boardreply
		on b.boardnum = c.boardnum
where chnum=21
GROUP BY chboard.boardNum, chboard.chnum, chboardcategory.chcate_id;
		
	boardreply b
	chboardcategory c
where a.boardnum = b.boardnum(+)
and 
join 


select *
from chboard natural join chboardcategory
where chNum = 21 AND chcate_id = 1
order by boardDate asc;

SELECT chboard.*, chboardcategory.*, COUNT(boardreply.replynum) AS replyCount
FROM chboard
JOIN chboardcategory ON chboard.chNum = chboardcategory.chNum
LEFT JOIN boardreply ON chboard.boardNum = boardreply.boardNum
where is chnum = 21
GROUP BY chboard.boardNum, chboard.chnum, chboardcategory.chcate_id;

SELECT chboard.*, chboardcategory.*, COUNT(boardreply.replyNum) AS replyCount
FROM chboard
JOIN chboardcategory ON chboard.chNum = chboardcategory.chNum
LEFT JOIN boardreply ON chboard.boardNum = boardreply.boardNum
GROUP BY chboard.*, chboardcategory.*;


select chboard.*, chboardcategory.chcate_name, count(boardreply.boardnum)
from chboard   join chboardcategory
on   chboard.chnum  =  chboardcategory.chnum
join boardreply
on	 chboard.boardnum = boardreply.boardnum
where chboard.chnum = 21
group by chboard.*, chboardcategory.chcate_name;  


select *
from chboard natural join boardreply
where chnum = 21;

select boardnum, count(replynum) as cnt 
from boardreply
group by boardnum; 

select a.boardnum, COALESCE(b.cnt, 0) as comment_cnt
from chboard a 
left outer join (select count(replynum) as cnt 
				 from boardreply
				 group by boardnum) b
on a.boardnum = b.boardnum
where a.chnum = 21
order by a.boardnum;

select boardnum, count(a.replynum) as comment_cnt
from boardreply as a 
left outer join (select *
				 from chboard
				 where is chnum = 21 ) as b
on a.boardnum = b.boardnum
group by boardnum;

select *
from(select a.*
	from (
			select boardnum, nvl(cnt,0) as cnt
			from chboard left outer join (select count(boardnum) cnt
										from boardreply
										)
			on boardnum = boardnum
			where chnum = 21
			order by boardnum;
	where chnum
	))


select *
from(select count(*) as cnt
	from boardreply
	group by boardnum)
	chboard
	where chnum = 21;
	
SELECT
    cb.boardNum,
    COUNT(br.*) AS replyCount
FROM
    chboard cb
LEFT JOIN
    boardreply br ON cb.boardNum = br.boardNum
where
	cb.chnum = 21
    GROUP BY
    cb.boardNum;
    
    
select *
from chboardcategory


select chboard.*, chboardcategory.chcate_name
from chboard   join chboardcategory
on   chboard.chnum  =  chboardcategory.chnum
where boardnum = 21  
and chboard.chcate_id = chboardcategory.chcate_id;

select tag.tagname 
from chboard join tag
on chboard.boardnum = tag.boardnum
where chboard.chnum = 31 and chboard.boardnum = 52;

select *
from tag;