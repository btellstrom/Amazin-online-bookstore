INSERT into role_table (role_name) VALUES
  ('ROLE_ADMIN'),
  ('ROLE_USER'),
  ('ROLE_ANONYMOUS')
  ON CONFLICT DO NOTHING;