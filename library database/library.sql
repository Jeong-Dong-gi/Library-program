-- 도서관리 데이터베이스입니다.

-- 도서관 이용자에 관한 테이블 생성
create table library_user(
    user_id number primary key,
    user_name varchar2(100),
    user_address varchar2(300),
    phone varchar2(100),
    birthday date
);

-- 도서관 이용자 정보 입력
insert into library_user values (1,'홍길동','부산1','010-1','2001-01-01');
insert into library_user values (2,'김자바','부산2','010-2','2001-01-02');
insert into library_user values (3,'홍자바','부산1','010-3','2001-01-03');
insert into library_user values (4,'이용자1','부산2','010-4','2001-01-04');
insert into library_user values (5,'이용자2','부산3','010-5','2001-01-05');
insert into library_user values (6,'이용자3','부산4','010-6','2001-01-06');
insert into library_user values (7,'이용자4','부산4','010-7','2001-01-01');
insert into library_user values (8,'이용자5','부산5','010-8','2001-01-08');
insert into library_user values (9,'이용자6','부산6','010-9','2001-01-09');

-- 책 정보에 관한 테이블 생성
create table book(
    book_id number primary key,
    book_name varchar2(300),
    author varchar2(100),
    publisher varchar2(250),
    publish_date date
);

-- 책 정보 입력
insert into book values (1,'불변의 법칙','모건 하우절','서삼독','2024-02-28');
insert into book values (2,'돈의 심리학','모건 하우절','인플루엔셜','2023-11-06');
insert into book values (3,'일류의 조건','사이토 다카시','필름','2024-03-20');
insert into book values (4,'나를 소모하지 않는 현명한 태도에 관하여',
                            '마티아스 뇔케','퍼스트펭귄','2024-03-10');
insert into book values (5,'내가 천 개의 인생에서 배운 것들','김도윤','북로망스','2024-04-17');
insert into book values (6,'무엇이 나를 행복하게 만드는가',
                            '리처드 J. 라이더','북플레저','2024-03-04');
insert into book values (7,'모순','양귀자','쓰다','2013-04-01');
insert into book values (8,'세이노의 가르침','세이노','데이원','2023-03-02');
insert into book values (9,'나는 너랑 노는 게 제일 좋아','하태완','북로망스','2023-07-17');
insert into book values (10,'누군가를 이토록 사랑한 적','이병률','문학과지성사','2024-04-24');
insert into book values (11,'나의 돈키호테','김호연','나무옆의자','2024-04-25');

-- 보유중인 책 테이블 생성
create table getbook(
    book_id number primary key,
    book_location varchar2(200),
    book_count number,
    loan_count number,
    foreign key (book_id) references book(book_id)
);

-- 보유중인 책 정보 입력
insert into getbook values (1,'위치1',5,2);
insert into getbook values (2,'위치2',4,0);
insert into getbook values (3,'위치3',2,0);
insert into getbook values (4,'위치4',7,0);
insert into getbook values (5,'위치3',5,0);
insert into getbook values (6,'위치2',6,0);
insert into getbook values (7,'위치1',4,0);
insert into getbook values (8,'위치5',3,0);
insert into getbook values (9,'위치4',2,2);
insert into getbook values (10,'위치5',5,0);
insert into getbook values (11,'위치5',7,0);

-- 대출중인 책 테이블 생성
create table loan(
    loan_id number,
    user_id number,
    book_id number,
    start_date date,
    end_date date,
    foreign key (user_id) references library_user(user_id),
    foreign key (book_id) references book(book_id)
);

-- loan 테이블에 primary key(loan_id) 추가
alter table loan add primary key(loan_id);

-- 대출중인 책 입력
insert into loan values (1,5,1,'2023-04-26','2023-05-09');
insert into loan values (2,2,1,'2023-04-30','2023-05-13');
insert into loan values (3,5,9,'2023-04-26','2023-05-09');
insert into loan values (4,7,9,'2023-05-04','2023-05-17');

-- 요청된 책 테이블 생성
create table request_book(
    user_id number,
    request_name varchar2(300),
    request_author varchar2(100),
    request_publisher varchar2(250),
    foreign key (user_id) references library_user(user_id)
);

-- 요청된 책 입력
insert into request_book values (4,'벼랑 끝이지만 아직 떨어지진 않았어',
                                    '소재원','프롤로그');
                                    
-----------------------------------------------------------------------


-- 도서관 이용자의 이름과 거주지를 보이시오
select user_name, user_address from library_user;

-- 모건 하우절이 출판한 책의 제목을 보이시오
select book_name from book where author='모건 하우절';

-- 책의 위치에 따른 책의 개수를 보이시오.
select book_location "책의 위치", count(*) "개수" from getbook
group by book_location order by book_location;

-- 책을 대출한 이용자의 이름과 책 제목을 보이시오
select u.user_name, b.book_name
from loan l, library_user u, book b
where l.book_id=b.book_id and u.user_id=l.user_id;

-- 보유중인 책의 제목, 위치, 보유 수량을 보이시오
select b.book_name, gb.book_location, gb.book_count
from book b, getbook gb
where b.book_id=gb.book_id;

-- 보유중인 책 중 모두 대출된 책의 이름, 저자, 출판사를 보이시오.
select b.book_name, b.author, b.publisher
from book b, getbook gb
where b.book_id=gb.book_id and book_count-loan_count=0;

-- 책 요청하기
insert into request_book 
values (3,'나는 나의 스무 살을 가장 존중한다','이하영','토네이도');

-- 4번 사용자가 요청한 책을 book 테이블에 추가
insert into book values (12,'벼랑 끝이지만 아직 떨어지진 않았어',
                            '소재원','프롤로그','2024-03-04');

-- book 테이블에 새롭게 추가된 책을 getbook 테이블에 추가
insert into getbook values (12, '위치6',2,0);

-- 요청된 책 중에서 추가된 책 삭제
delete from request_book 
where request_name in (select book_name from book)
and request_author in (select author from book)
and request_publisher in (select publisher from book);

-- getbook 테이블에서 9번 책을 추가로 구매하여 update 하기
update getbook set book_count=4 where book_id=9;

-- 이용자2가 빌려간 책이 반납되어 getbook테이블 수정 하기
update getbook set loan_count=(loan_count-1)
where book_id in(select book_id from loan
where user_id=(select user_id from library_user where user_name='이용자2')); 
    
-- 이용자2가 책을 반납하여 반납된 책을 loan 테이블에서 삭제 하기
delete from loan where user_id=(select user_id from library_user
                                where user_name='이용자2');
                                
-- 오늘 날짜로 4번책 대여하기(loan 테이블에 추가)
insert into loan values(5,8,4,sysdate,sysdate+13);

-- getbook 테이블 수정하기(4번책 대여한 수 1 증가)
update getbook set loan_count=loan_count+1 where book_id=4;

-- 이용자1이 일류의 조건 대여하기(loan 테이블에 추가)
insert into loan values (6,
(select user_id from library_user where user_name='이용자1'),
(select book_id from book where book_name='일류의 조건'),
'2024-04-26','2024-05-09');

-- getbook 테이블 수정하기(일류의 조건 대여한 수 1 증가)
update getbook set loan_count=loan_count+1
where book_id=(select book_id from book where book_name='일류의 조건');

-- 대출되어 있는 책의 제목, 저자, 출판사, 남은 대여 일수를 보이시오(view 생성)
create view loan_date(사용자,제목, 저자, 출판사, "남은 대여 일수")
as select u.user_name, b.book_name, b.author, b.publisher, ceil(l.end_date-sysdate)
from book b, loan l, library_user u
where b.book_id=l.book_id and u.user_id=l.user_id;

-- 신간(2024년)도서의 제목, 저자, 출판사를 보이시오
select book_name, author, publisher from book
where to_char(publish_date,'yyyy')=to_char(sysdate,'yyyy');

-- 위치별로 가장 많이 보유한 책의 이름, 저자, 출판사를 보이시오
select book_location, book_name, author, publisher
from getbook gb, book b
where gb.book_id=b.book_id and
(book_location, book_count) in
(select book_location, max(book_count)
from book b, getbook gb
where b.book_id=gb.book_id
group by book_location)
order by book_location;
