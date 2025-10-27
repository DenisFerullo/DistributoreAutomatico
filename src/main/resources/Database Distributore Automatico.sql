CREATE DATABASE distributore;
USE distributore;

-- Inserimento Locations (prima perché referenziata da Distributor)
INSERT INTO locations (address, house_number, cap, municipality, province, created_at, updated_at) 
VALUES 
('Via Roma', '123', '00100', 'Roma', 'RM', NOW(), NOW()),
('Corso Milano', '45', '20100', 'Milano', 'MI', NOW(), NOW()),
('Piazza Dante', '1', '80100', 'Napoli', 'NA', NOW(), NOW());

-- Inserimento Categories (prima perché referenziata da Product)
INSERT INTO categories (name, created_at, updated_at) 
VALUES 
('Beverages', NOW(), NOW()),
('Snacks', NOW(), NOW()),
('Sweets', NOW(), NOW()),
('Sandwiches', NOW(), NOW());

-- Inserimento Products
INSERT INTO products (sku, name, price, quantity, re_stock_value, category_id, created_at, updated_at) 
VALUES 
('WAT500', 'Natural Water 500ml', 1.00, 50, 10, 1, NOW(), NOW()),
('COC330', 'Coca Cola 330ml', 2.50, 30, 5, 1, NOW(), NOW()),
('CHI150', 'Classic Chips', 1.50, 40, 15, 2, NOW(), NOW()),
('CHO100', 'Milk Chocolate', 1.80, 20, 8, 3, NOW(), NOW()),
('SANTOM', 'Ham Sandwich', 3.50, 15, 5, 4, NOW(), NOW());

-- Inserimento Inventories
INSERT INTO inventories (name, maximum_capacity, created_at, updated_at) 
VALUES 
('Central Warehouse', 1000, NOW(), NOW()),
('North Italy Warehouse', 500, NOW(), NOW()),
('South Italy Warehouse', 500, NOW(), NOW());

-- Inserimento Distributors
INSERT INTO distributor (name, is_working, last_maintenance, created_at, updated_at, location_id) 
VALUES 
('Rome Center Distributor', true, NOW(), NOW(), NOW(), 1),
('Milan Station Distributor', true, NOW(), NOW(), NOW(), 2),
('Naples University Distributor', false, '2024-01-15 10:00:00', NOW(), NOW(), 3);

-- Inserimento CashRegister
INSERT INTO cash_register (name, total_cash, schedule, created_at, updated_at, distributor_id) 
VALUES 
('Main Rome Cash', 250.75, NOW(), NOW(), NOW(), 1),
('Milan Central Cash', 180.50, NOW(), NOW(), NOW(), 2),
('Naples Uni Cash', 95.25, NOW(), NOW(), NOW(), 3);

-- Inserimento SalesRegister (Vendite di esempio)
INSERT INTO sales (sale_date, sold_quantity, total_amount, product_id, cash_register_id, distributor_id, created_at, updated_at) 
VALUES 
(NOW(), 2, 2.00, 1, 1, 1, NOW(), NOW()),
(NOW(), 1, 2.50, 2, 1, 1, NOW(), NOW()),
('2024-01-20 15:30:00', 3, 4.50, 3, 2, 2, NOW(), NOW()),
('2024-01-19 10:15:00', 1, 1.80, 4, 3, 3, NOW(), NOW());

-- Inserimento Tabelle di Join (Many-to-Many)

-- Distributor_Products (quali prodotti sono in quali distributori)
INSERT INTO distributor_products (distributor_id, product_id) 
VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),  -- Distributor 1 ha tutti i prodotti
(2, 2), (2, 3), (2, 5),                   -- Distributor 2 ha solo alcuni
(3, 1), (3, 4);                           -- Distributor 3 ha pochi prodotti

-- Inventory_Products (quali prodotti sono in quali inventari)
INSERT INTO inventory_products (inventory_id, product_id) 
VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),  -- Magazzino centrale ha tutto
(2, 2), (2, 3), (2, 5),                   -- Magazzino Nord ha alcuni
(3, 1), (3, 4);                           -- Magazzino Sud ha pochi


