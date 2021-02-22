CREATE TABLE authors(
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  about TEXT,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);