CREATE TABLE posts(
  id UUID PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  subtitle VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  written_at TIMESTAMP NOT NULL,
  author_id UUID REFERENCES authors(id) NOT NULL,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);
