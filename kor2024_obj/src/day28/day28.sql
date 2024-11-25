# [주석]
/*여러 줄 주석*/
# 한 줄 주석
-- 한 줄 주석

# [명령어 실행]alter
# [1] ctrl + enter : 실행할 명령어에 커서를 두고 단위 실행 ( 번개 모양 I )
# [2] ctrl + shift : .sql 파일에 존재하는 모든 명령어가 실행  ( 번개 모양 )

# [명령어]
/*
	[ DDL ] : 테이터베이스 정의 언어
	1. show databases;					: db server 모든 데이터베이스를 확인
    2. show variables like 'datadir'	: db server local path 확인
    3. create database 데이터베이스명 		: db server 내 db 생성
    4. drop database 데이터베이스 명 		: db server 내 db 삭제
		-drop database if exists 데이터 명: 만일 존재하면 삭제
	5. use 사용할 데이터베이스 명 			: db server 내 여러 db 중에서 사용 활성화
    6. create table 테이블 명( 속성명 타입, 속성명 타입, 속성명 타입 ) : 활성화된 db에서 테이블/표 생성한다.
    7. drop table 테이블명 				: 활성화된 db에서 테이블 삭제
		- drop table if exists 테이블명 	: 만일 테이블이 존재하면 삭제
	8. show tables 						: 활성화된 db 내 모든 테이블 확인

	[ 데이터 타입 ] : 테이블 생성시 필드/속성 의 데이터 타입
		[ 정수 ]	TINYINT, SMALLINT, MEDIUMINT, INT, BIGINT
		[ 실수 ]	FLOAT, DOUBLE, DECIMAL( 문자 )
        [ 문자 ] CHAR(), VARCHAR()
		[ 날짜/ 시간 ] DATE, TIME, DATETIME
        [ 논리 ] BOOL
*/
/* [ 11/20 SQL 과제] 키오스크 개발 - 필요한 데이터베이스 구축하기
		- 요구사항
			1. 여러개 카테고리 중 하나의 카테고리를 선택하여 카테고리별 제품들을 출력
            2. 해당 제품을 선택하여 수량 입력받아 주문 처리 - 단) *한 번 주문의 여러개 제품을 주문 할 수 있다.
            3. 주문번호를 받아서 대기한다.
		- 메모리 설계 필수 필드 속성
			1. 카테고리명 2. 제품명 3. 가격 4. 주문수량 5. 주문날짜
		- 핵심 : 여러 테이블 구성 할 때는 테이블 간의 데이터 중복을 최소화 한다.
*/
# 생각순서
# 1. 우리가 만들고자 하는 프로그램의 저장할 데이터를 생각,
	# 카테고리( 커피, 음료, 주스, 스무디 ).
    # 제품( 아메리카노1000, 사과주스2000, 메론스무디3000, 콜라2500, 모카치노3500 )
	# 주문( 102 아메리카노 2개 11/20, 103 메론스무디 1개 콜라 1개 11/21 )
# 2. 데이터들을 어떻게 관리/구분/처리
	# 카페 알바생 - 종이장부
    # 손님 1번째이 아메리카노 1개를 주문했을 때 어떻게 작성할지?
    # 손님 2번째이 아메리카노 1개, 메론 스무디 2개 주문 했을 때, 어떻게 작성할지?
# 3. (정규화) 중복을 최소화, 메모리를 효율성있게 아껴쓸려고
create database mydb1121;
use mydb1121;
    # 카테고리 테이블(카테고리 명 필드)
create table 카테고리( 카테고리명 char(10));
    # 제품 테이블 (제품명필드, 가격필드)
create table 제품(제품명 varchar(50), 가격 int);
    # 주문 테이블 (주문날짜)
create table 주문(주문날짜 date);
    # 주문 상세 테이블(주문 수량)
create table 주문상세(주문수량 int);

# 4. (관계), 관계형[서로 다른 테이블 간의 종속] 데이터베이스, 왜?
	# 카테고리번호(카테고리의 식별) 가 필요한 이유? 카테고리명으로 식별 역할로 사용하지 않고 번호로 식별한다.
create table 카테고리 (카테고리번호PK int, 카테고리명 char(10)); 						# 상위 테이블
# create table 제품(제품명 varchar(50), 가격 int, 카테고리명 char(10)); 				# 하위 테이블
create table 제품(제품번호PK int, 제품명 varchar(50), 가격 int, 카테고리번호FK int); 	# 하위 테이블 # 효율적이다

create table 주문(주문번호PK int, 주문날짜 date);		# 상위 테이블
create table 주문상세(주문상세번호PK int, 주문수량 int, 주문번호FK int, 제품번호FK int);	# 하위 테이블
## 관례적으로 모든 테이블에는 1개 이상의 식별데이터/번호가 존재한다. (기본키 - pk)
## pk가 다른 테이블에서 사용/참조한다. (참조키-fk)

# [제약 조건]
	# 1. not null : 해당 필드에 null 값을 포함하지 않는다.
    # 2. default 초기값 : 해당 필드에 레코드(값) 삽입시 비어있는 경우 기본값을 지정한다.
    # 3. unique : 해당 필드에 중복된 갑을 허용하지 않는다. [중복제거] 레코드 삽입시 데이터가 중복이면 오류 발생
    # 4. auto_increment : 해당 필드에 레코드 삽입시 순서대로 번호가 삽입된다. (oracle)
    # 5. primary key : 테이블의 식별 필드, (기본키-pk), 식별 가능한 고유 값을 가진 키, 참조를 당하는 키
		# primary key( pk 필드명 )
    # 6. foreign key : 테이블의 참조 필드, (참조키-fk), 다른 테이블 내 기본 키를 참조하는 키, 참조 하는 키
		# foreign key( fk 필드명 ) references 참조테이블( pk 필드명 )
    # 관례적으로 모든 테이블은 1개 이상의 pk필드를 갖는다.

# 회원제 게시판(회원 테이블, 게시판 테이블 = 종속관계 파악, [o]회원(상위pk)이 게시물(하위fk) 작성 vs [x]게시물이 회원 작성)
create table 회원테이블(
	회원번호 int unsigned not null auto_increment,				-- 회원번호는 일반적으로 가입 순서대로 1~ 저장하기 위해서 int 사용
		-- unsigned : [부호가 없다 뜻] int(+-21억) int unsigned(42억)
    아이디 varchar(30) unique,		-- 아이디는 최대 30글자만 입력받을 예정이라서 varchar(30) 사용
    비밀번호 varchar(30),		-- 비밀번호는 최대 30글자만 입력받을 예정이라서 varchar(30) 사용
    연락처 char(13),			-- 010-1234-5678 형식으로 받을 예정이라서 char(13) 사용
    광고전송여부 boolean default 0,		-- true, false 형식으로 저장할 예정이라서 boolean 사용
    가입일 datetime			-- 2024-11-21 20:25 형식으로 저장할 예정이라서 datetime 사용
    primary key(회원번호)		-- 지정한 필드를 pk(기본키)로 사용하겠다.
		-- 아이디 대신에 회원번호를 사용한 이유? 1.용량작다 2.중복배제(유재석이 'QWE' 아이디로 회원탈퇴 후 5년 후 강호동이 'QWE' 아이디로 가입)
        -- 유재석과 강호동이 같은 회원인가? 아니요.

); # 임의 테이블 생성, create table 테이블명(필드명 타입, 필드명 타입);
create table 게시물테이블(
	게시물번호 int unsigned not null auto_increment, -- 게시물번호는 일반적으로 작성 순서대로 1~ 지정하기 때문에 int unsigned
    게시물제목 varchar(100), 			-- 게시물제목은 최대 100글자로 하기 위해서 varchar(100) 사용.
    게시물내용 longtext, 				-- 게시물 내용은 많은 데이터를 저장하기 위해서 큰 용량인(4gb) longtext 사용.
    게시물작성자 int 					-- 게시물작성자가 아이디가 아니고 회원번호인 이유는 : 회원번호를 통해 아이디를 참조할 수 있다.
		-- 예] 동사무소 가면 주민등록번호(식별키)만 말해도 내 신상정보를 공무원(직원)이 볼 수(참조) 있다.
	foreign key( 게시글작성자회원번호FK ) references 회원테이블( 회원번호PK )
    primary key( 게시물번호PK )
);

/*
	[ SQL과제2 ] 회원들만 구매할 수 있는 '라면' 쇼핑몰, 데이터베이스 구축하기
		- 요구사항
			1. 회원은 회원가입을 진행한다. (임의)
			2. 로그인된 상황에서 카테고리를 선택한다.
            3. 지정된 카테고리에서 제품을 선택한다.
            4. 지정한 제품을 장바구니에 담는다.
            --> 여러번 장바구니에 담았을 때
            5. 만약에 장바구니에 담긴 '불닭볶음면' 2개, '짜파게티' 1개를 주문했다.
            6. 하나의 주문번호를 발급 받았고, 주문 내역에서 지정한 주문 상세를 클릭시 주문 상세 내역(정보) 나왔다.
		- 조건
			1. 서로 다른 테이블 간의 pk(기본키) fk(외래키)를 적용하여 관계형 테이블 만드시오.
            2. 적절한 필드명과 타입을 사용하시오.
            3. 적절한 제약조건을 사용하시오.
*/

create database RamenShop;
use RamenShop;
#회원가입
create table accountRegist(
	accountNum int unsigned not null auto_increment, -- 회원번호, 부호를 갖지 않고 순차적으로 번호 부여
    id varchar(12) unique, -- id, pwd 12자리 내 문자열
    pwd varchar(12),
    adsAccept boolean, -- 광고 허용 t/f
    registDate datetime, -- 가입 날짜/시간
	primary key(accountNum) -- 회원번호(필드)를 기본 키로 사용
);
#브랜드 카테고리
create table categories( -- 브랜드별 카테고리
	brandNum int unsigned not null auto_increment, -- 카테고리 번호부여
    brandName varchar(30),
    primary key(brandNum)
);

#제품
create table product(
	productNum int unsigned not null auto_increment, -- 제품고유번호 부호x 순차적으로
    productName varchar(30),
    productPrice int,
    productCate varchar(10),
    foreign key( cateNum ) references categories( brandNum ),
    primary key(productNum)
);

#주문
create table ramenOrder(
	orderNum int unsigned not null auto_increment, -- 주문번호 부호x 순차부여
    orderDate datetime, -- 주문 시간/날짜
    foreign key(orderAccount) references accountRegist(accountNum),
    primary key(orderNum)
);

#주문 상세 내역
create table orderContent(
	orderContentNum int unsigned not null auto_increment, -- 주문상세내역PK
    foreign key(orderProduct) references product(productNum), -- 제품번호
    foreign key(orderProductNum) references ramenOrder(orderNum), -- 주문번호
    orderAmount int,
    primary key(orderContentNum)
);