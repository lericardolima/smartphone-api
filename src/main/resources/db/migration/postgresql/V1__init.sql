create sequence if not exists code_sequence
increment by 1
minvalue 10000
maxvalue 99999;

create or replace function generate_code(seq bigint)
returns text as $$
declare
  alphabet text[] := '{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z}';
  alphabet_array_length integer := 25;
  prefix_code text := '';
begin
  for i in 1..3 loop
	prefix_code := prefix_code || alphabet[random() * alphabet_array_length + 1];
  end loop;
  return prefix_code || seq;
end;
$$ language plpgsql;

create table smartphone (
  id serial primary key,
  code varchar(8) default generate_code(nextval('code_sequence')) unique not null,
  model varchar(255) not null,
  price decimal(19,2) not null,
  brand varchar(255) not null,
  photo_url text not null,
  start_date timestamp not null check (start_date >= '2018-12-25'),
  end_date timestamp not null check (end_date > start_date),
  color varchar(5) not null check (color in ('BLACK', 'WHITE', 'GOLD', 'PINK'))
) ;