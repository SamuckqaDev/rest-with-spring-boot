-- public.tb_book definition

-- Drop table

-- DROP TABLE public.tb_book;

CREATE TABLE public.tb_book (
	id int8 NOT NULL,
	author varchar(255) NOT NULL,
	launch_date TIMESTAMP(6),
	price DECIMAL(65,2) NOT NULL,
	title varchar(255) NOT NULL
);