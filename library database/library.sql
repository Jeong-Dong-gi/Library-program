-- �������� �����ͺ��̽��Դϴ�.

-- ������ �̿��ڿ� ���� ���̺� ����
create table library_user(
    user_id number primary key,
    user_name varchar2(100),
    user_address varchar2(300),
    phone varchar2(100),
    birthday date
);

-- ������ �̿��� ���� �Է�
insert into library_user values (1,'ȫ�浿','�λ�1','010-1','2001-01-01');
insert into library_user values (2,'���ڹ�','�λ�2','010-2','2001-01-02');
insert into library_user values (3,'ȫ�ڹ�','�λ�1','010-3','2001-01-03');
insert into library_user values (4,'�̿���1','�λ�2','010-4','2001-01-04');
insert into library_user values (5,'�̿���2','�λ�3','010-5','2001-01-05');
insert into library_user values (6,'�̿���3','�λ�4','010-6','2001-01-06');
insert into library_user values (7,'�̿���4','�λ�4','010-7','2001-01-01');
insert into library_user values (8,'�̿���5','�λ�5','010-8','2001-01-08');
insert into library_user values (9,'�̿���6','�λ�6','010-9','2001-01-09');

-- å ������ ���� ���̺� ����
create table book(
    book_id number primary key,
    book_name varchar2(300),
    author varchar2(100),
    publisher varchar2(250),
    publish_date date
);

-- å ���� �Է�
insert into book values (1,'�Һ��� ��Ģ','��� �Ͽ���','���ﵶ','2024-02-28');
insert into book values (2,'���� �ɸ���','��� �Ͽ���','���÷翣��','2023-11-06');
insert into book values (3,'�Ϸ��� ����','������ ��ī��','�ʸ�','2024-03-20');
insert into book values (4,'���� �Ҹ����� �ʴ� ������ �µ��� ���Ͽ�',
                            '��Ƽ�ƽ� ����','�۽�Ʈ���','2024-03-10');
insert into book values (5,'���� õ ���� �λ����� ��� �͵�','�赵��','�Ϸθ���','2024-04-17');
insert into book values (6,'������ ���� �ູ�ϰ� ����°�',
                            '��ó�� J. ���̴�','���÷���','2024-03-04');
insert into book values (7,'���','�����','����','2013-04-01');
insert into book values (8,'���̳��� ����ħ','���̳�','���̿�','2023-03-02');
insert into book values (9,'���� �ʶ� ��� �� ���� ����','���¿�','�Ϸθ���','2023-07-17');
insert into book values (10,'�������� ����� ����� ��','�̺���','���а�������','2024-04-24');
insert into book values (11,'���� ��Űȣ��','��ȣ��','����������','2024-04-25');

-- �������� å ���̺� ����
create table getbook(
    book_id number primary key,
    book_location varchar2(200),
    book_count number,
    loan_count number,
    foreign key (book_id) references book(book_id)
);

-- �������� å ���� �Է�
insert into getbook values (1,'��ġ1',5,2);
insert into getbook values (2,'��ġ2',4,0);
insert into getbook values (3,'��ġ3',2,0);
insert into getbook values (4,'��ġ4',7,0);
insert into getbook values (5,'��ġ3',5,0);
insert into getbook values (6,'��ġ2',6,0);
insert into getbook values (7,'��ġ1',4,0);
insert into getbook values (8,'��ġ5',3,0);
insert into getbook values (9,'��ġ4',2,2);
insert into getbook values (10,'��ġ5',5,0);
insert into getbook values (11,'��ġ5',7,0);

-- �������� å ���̺� ����
create table loan(
    loan_id number,
    user_id number,
    book_id number,
    start_date date,
    end_date date,
    foreign key (user_id) references library_user(user_id),
    foreign key (book_id) references book(book_id)
);

-- loan ���̺� primary key(loan_id) �߰�
alter table loan add primary key(loan_id);

-- �������� å �Է�
insert into loan values (1,5,1,'2023-04-26','2023-05-09');
insert into loan values (2,2,1,'2023-04-30','2023-05-13');
insert into loan values (3,5,9,'2023-04-26','2023-05-09');
insert into loan values (4,7,9,'2023-05-04','2023-05-17');

-- ��û�� å ���̺� ����
create table request_book(
    user_id number,
    request_name varchar2(300),
    request_author varchar2(100),
    request_publisher varchar2(250),
    foreign key (user_id) references library_user(user_id)
);

-- ��û�� å �Է�
insert into request_book values (4,'���� �������� ���� �������� �ʾҾ�',
                                    '�����','���ѷα�');
                                    
-----------------------------------------------------------------------


-- ������ �̿����� �̸��� �������� ���̽ÿ�
select user_name, user_address from library_user;

-- ��� �Ͽ����� ������ å�� ������ ���̽ÿ�
select book_name from book where author='��� �Ͽ���';

-- å�� ��ġ�� ���� å�� ������ ���̽ÿ�.
select book_location "å�� ��ġ", count(*) "����" from getbook
group by book_location order by book_location;

-- å�� ������ �̿����� �̸��� å ������ ���̽ÿ�
select u.user_name, b.book_name
from loan l, library_user u, book b
where l.book_id=b.book_id and u.user_id=l.user_id;

-- �������� å�� ����, ��ġ, ���� ������ ���̽ÿ�
select b.book_name, gb.book_location, gb.book_count
from book b, getbook gb
where b.book_id=gb.book_id;

-- �������� å �� ��� ����� å�� �̸�, ����, ���ǻ縦 ���̽ÿ�.
select b.book_name, b.author, b.publisher
from book b, getbook gb
where b.book_id=gb.book_id and book_count-loan_count=0;

-- å ��û�ϱ�
insert into request_book 
values (3,'���� ���� ���� ���� ���� �����Ѵ�','���Ͽ�','����̵�');

-- 4�� ����ڰ� ��û�� å�� book ���̺� �߰�
insert into book values (12,'���� �������� ���� �������� �ʾҾ�',
                            '�����','���ѷα�','2024-03-04');

-- book ���̺� ���Ӱ� �߰��� å�� getbook ���̺� �߰�
insert into getbook values (12, '��ġ6',2,0);

-- ��û�� å �߿��� �߰��� å ����
delete from request_book 
where request_name in (select book_name from book)
and request_author in (select author from book)
and request_publisher in (select publisher from book);

-- getbook ���̺��� 9�� å�� �߰��� �����Ͽ� update �ϱ�
update getbook set book_count=4 where book_id=9;

-- �̿���2�� ������ å�� �ݳ��Ǿ� getbook���̺� ���� �ϱ�
update getbook set loan_count=(loan_count-1)
where book_id in(select book_id from loan
where user_id=(select user_id from library_user where user_name='�̿���2')); 
    
-- �̿���2�� å�� �ݳ��Ͽ� �ݳ��� å�� loan ���̺��� ���� �ϱ�
delete from loan where user_id=(select user_id from library_user
                                where user_name='�̿���2');
                                
-- ���� ��¥�� 4��å �뿩�ϱ�(loan ���̺� �߰�)
insert into loan values(5,8,4,sysdate,sysdate+13);

-- getbook ���̺� �����ϱ�(4��å �뿩�� �� 1 ����)
update getbook set loan_count=loan_count+1 where book_id=4;

-- �̿���1�� �Ϸ��� ���� �뿩�ϱ�(loan ���̺� �߰�)
insert into loan values (6,
(select user_id from library_user where user_name='�̿���1'),
(select book_id from book where book_name='�Ϸ��� ����'),
'2024-04-26','2024-05-09');

-- getbook ���̺� �����ϱ�(�Ϸ��� ���� �뿩�� �� 1 ����)
update getbook set loan_count=loan_count+1
where book_id=(select book_id from book where book_name='�Ϸ��� ����');

-- ����Ǿ� �ִ� å�� ����, ����, ���ǻ�, ���� �뿩 �ϼ��� ���̽ÿ�(view ����)
create view loan_date(�����,����, ����, ���ǻ�, "���� �뿩 �ϼ�")
as select u.user_name, b.book_name, b.author, b.publisher, ceil(l.end_date-sysdate)
from book b, loan l, library_user u
where b.book_id=l.book_id and u.user_id=l.user_id;

-- �Ű�(2024��)������ ����, ����, ���ǻ縦 ���̽ÿ�
select book_name, author, publisher from book
where to_char(publish_date,'yyyy')=to_char(sysdate,'yyyy');

-- ��ġ���� ���� ���� ������ å�� �̸�, ����, ���ǻ縦 ���̽ÿ�
select book_location, book_name, author, publisher
from getbook gb, book b
where gb.book_id=b.book_id and
(book_location, book_count) in
(select book_location, max(book_count)
from book b, getbook gb
where b.book_id=gb.book_id
group by book_location)
order by book_location;
