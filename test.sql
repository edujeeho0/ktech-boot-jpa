DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS order_item;
create table item(
    id integer PRIMARY KEY AUTOINCREMENT,
    name text,
    stock int
);
create table customer_order(
    id integer PRIMARY KEY AUTOINCREMENT,
    total_price integer,
    address text
);
create table order_item(
    order_id integer,
    item_id integer,
    count integer
);
INSERT INTO item(name, stock)
VALUES ('mouse', 10);


BEGIN TRANSACTION;
-- 사용자가 아이템을 장바구니에 넣고 구매를 한다.
-- 0. 주문정보를 먼저 생성한다.
INSERT INTO customer_order(total_price, address)
VALUES (0, 'seoul');

-- 1. 일단 구매하려는 물품 정보(item)를 조회한다.
SELECT * FROM item WHERE id = 1;

-- 2. 해당 물품의 재고(stock)가 구매 수량(count)보다 많음을 확인한다.

-- 3. order_item 테이블에 한 열을 추가한다.
INSERT INTO order_item(order_id, item_id, count)
VALUES (1, 1, 2);

-- 4. 추가한 갯수만큼 stock을 차감한다.
UPDATE item SET stock = stock - 2
WHERE id = 1;

-- TRANSACTION의 변경사항을 데이터베이스에 반영한다.
COMMIT;
-- TRANSACTION에서 있었던 작업을 없덜일로 한다.
ROLLBACK;


-- 학생 정보만 필요할수도 있고
SELECT * FROM student;

-- 연관된 정보도 같이 필요할 수 있다.
SELECT s.name as student, i.name as advisor
FROM student s LEFT OUTER JOIN instructor i
    ON s.advisor_id = i.id;
