CREATE TABLE customer (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  annual_spend DECIMAL(10, 2),
  last_purchase_date TIMESTAMP
);
