create table if not exists account  (
  iban_account_number varchar(255) not null,
  account_name varchar(255),
  balance double not null,
  primary key (iban_account_number)
);

insert into account
values('GR9608100010000001234567890','Greece Lightning', 1000);

insert into account
values('FR7630006000011234567890189','Francois', 2000);

insert into account
values('DE91100000000123456789','Mrs. Suleen Smith', 3000);

insert into account
values('NL91ABNA0417164300','Mr. Christoff Smith', 3000);