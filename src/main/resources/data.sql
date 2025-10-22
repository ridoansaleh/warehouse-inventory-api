-- Insert smartphone models
INSERT INTO items (name, brand, description) VALUES
('iPhone 16', 'Apple', 'Latest Apple flagship smartphone with A18 chip'),
('Samsung Galaxy S25', 'Samsung', 'Next-gen Android smartphone with One UI 8'),
('Google Pixel 10', 'Google', 'Flagship Pixel with advanced AI camera');

-- Insert variants (color & storage options)
INSERT INTO variants (item_id, name, sku, attributes, price) VALUES
(1, 'iPhone 16 - 128GB Black', 'IPH16-128-BLK', '{"color": "black", "storage": "128GB"}', 999.00),
(1, 'iPhone 16 - 256GB Silver', 'IPH16-256-SLV', '{"color": "silver", "storage": "256GB"}', 1099.00),
(2, 'Galaxy S25 - 256GB Gray', 'SGS25-256-GRY', '{"color": "gray", "storage": "256GB"}', 899.00),
(2, 'Galaxy S25 - 512GB Blue', 'SGS25-512-BLU', '{"color": "blue", "storage": "512GB"}', 999.00),
(3, 'Pixel 10 - 256GB Black', 'PIX10-256-BLK', '{"color": "black", "storage": "256GB"}', 949.00),
(3, 'Pixel 10 - 512GB White', 'PIX10-512-WHT', '{"color": "white", "storage": "512GB"}', 1049.00);

-- Insert stock levels
INSERT INTO stock_levels (variant_id, quantity) VALUES
(1, 25),
(2, 10),
(3, 15),
(4, 5),
(5, 20),
(6, 8);
