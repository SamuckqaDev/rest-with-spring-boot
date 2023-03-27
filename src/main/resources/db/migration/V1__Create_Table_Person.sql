-- public.tb_person definition

-- Drop table

-- DROP TABLE public.tb_person;

CREATE TABLE  public.tb_person (
	id int8 NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	address varchar(255) NULL,
	gender varchar(255) NULL,
	CONSTRAINT tb_person_pkey PRIMARY KEY (id)
);