INSERT INTO customer (id, name, email, annual_spend, last_purchase_date)
VALUES
  (RANDOM_UUID(), 'Alice Wonderland', 'alice@example.com', 1500.00, '2024-08-01T10:00:00'),
  (RANDOM_UUID(), 'Boba Fett', 'boba@example.com', 950.00, '2023-07-01T10:00:00'),
  (RANDOM_UUID(), 'Baby Doll', 'baby@example.com', 12000.00, '2025-03-15T10:00:00');
