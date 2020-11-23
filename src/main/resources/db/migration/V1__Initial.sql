CREATE TABLE currencies
(
  id serial not null,
  from_currency varchar(3) not null,
  to_currency varchar(3) not null,
  value decimal not null,
  created_at TIMESTAMP not null,
  PRIMARY KEY(id)
)