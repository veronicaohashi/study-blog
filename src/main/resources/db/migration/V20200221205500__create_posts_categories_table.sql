CREATE TABLE posts_categories (
  post_id  UUID REFERENCES posts(id) NOT NULL,
  category_id  UUID REFERENCES categories(id) NOT NULL,
  PRIMARY KEY (post_id, category_id),
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);