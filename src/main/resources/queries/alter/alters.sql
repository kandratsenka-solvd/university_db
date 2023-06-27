-- alter table statements:

ALTER TABLE title
  ADD COLUMN category INT;

ALTER TABLE title
 DROP COLUMN category;

ALTER TABLE person
  ADD COLUMN country_residence VARCHAR(45) DEFAULT 'UNDEFINED';

ALTER TABLE person
 DROP COLUMN country_residence;

ALTER TABLE department
  ADD COLUMN employee_number INT;

ALTER TABLE department
 DROP COLUMN employee_number;

