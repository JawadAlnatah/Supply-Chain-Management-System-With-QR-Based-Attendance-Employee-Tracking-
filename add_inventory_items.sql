-- Add missing dairy inventory items to database
-- These match the items from requisitions

INSERT INTO inventory_items (item_name, description, category, quantity, unit_price, reorder_level, reorder_quantity, supplier_id, location)
VALUES
-- Dairy Products
('Butter (25kg blocks)', 'High-quality butter in 25kg blocks', 'Raw Materials', 0, 320.00, 20, 50, 1, 'Warehouse A'),
('Whole Milk - Bulk (1000L)', 'Fresh whole milk in bulk 1000L containers', 'Raw Materials', 0, 2500.00, 50, 100, 1, 'Warehouse A'),
('Fresh Cream (200L)', 'Premium fresh cream in 200L containers', 'Raw Materials', 0, 800.00, 30, 60, 1, 'Warehouse A'),
('Skimmed Milk Powder (50kg)', 'Low-fat milk powder in 50kg bags', 'Raw Materials', 0, 450.00, 40, 80, 1, 'Warehouse A'),

-- Packaging Materials
('Milk Bottles 1L (1000 units)', '1-liter plastic milk bottles, pack of 1000', 'Packaging', 0, 850.00, 100, 200, 2, 'Warehouse B'),
('Plastic Bottle Caps (5000 units)', 'Bottle caps for milk bottles, pack of 5000', 'Packaging', 0, 120.00, 50, 100, 2, 'Warehouse B'),
('Product Labels - Roll (2000)', 'Product labels on roll, 2000 labels per roll', 'Packaging', 0, 220.00, 80, 150, 2, 'Warehouse B');
