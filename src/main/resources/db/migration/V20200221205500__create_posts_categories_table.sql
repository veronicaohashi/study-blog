CREATE TABLE posts_categories (
  id UUID PRIMARY KEY,
  post_id  UUID REFERENCES posts(id) NOT NULL,
  category_id  UUID REFERENCES categories(id) NOT NULL,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);