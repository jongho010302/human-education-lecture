-- 샘플 데이터 처리
INSERT INTO commerce.purchase_orders(amount, name, address, status, timestamp)
VALUES(20000, '홍길동', '중부대로 104', '00', 1569210509000);

SET @id = LAST_INSERT_ID();
SELECT @id;

INSERT INTO commerce.purchase_order_items(purchase_order_id, product_id, unit_price, quantity)
VALUES(@id, 1, 10000, 1);
INSERT INTO commerce.purchase_order_items(purchase_order_id, product_id, unit_price, quantity)
VALUES(@id, 2, 5000, 2);


-- commerce database
SELECT * FROM commerce.purchase_orders;
SELECT * FROM commerce.purchase_order_items;

DELETE FROM commerce.purchase_order_items WHERE id > 0;
DELETE FROM commerce.purchase_orders WHERE id > 0;

-- sales database
SELECT * FROM sales.sales_orders;
SELECT * FROM sales.sales_order_items;

DELETE FROM sales.sales_order_items WHERE id > 0;
DELETE FROM sales.sales_orders WHERE id > 0;
