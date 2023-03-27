CREATE TABLE IF NOT EXISTS user_permission (
  id SERIAL PRIMARY KEY,
  id_user INT8 NOT NULL REFERENCES users(id),
  id_permission INT8 NOT NULL REFERENCES permission(id),
  CONSTRAINT unique_user_permission UNIQUE (id_user, id_permission)
);
