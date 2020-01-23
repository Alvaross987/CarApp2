CREATE TABLE IF NOT EXISTS public.brand (
	brand_id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT brand_pkey PRIMARY KEY (brand_id)
);
ALTER TABLE public.brand OWNER TO postgres;
GRANT ALL ON TABLE public.brand TO postgres;


CREATE TABLE IF NOT EXISTS public.country (
	country_id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT country_pkey PRIMARY KEY (country_id)
);
ALTER TABLE public.country OWNER TO postgres;
GRANT ALL ON TABLE public.country TO postgres;



CREATE TABLE IF NOT EXISTS public.car (
	id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	checked int4 NOT NULL,
	color varchar(255) NULL,
	model varchar(255) NULL,
	registration timestamp NOT NULL,
	country_id int4 NOT NULL,
	brand_id int4 NOT NULL,
	CONSTRAINT car_pkey PRIMARY KEY (id),
	CONSTRAINT fk7coo9i18j692gp7loi29t13x9 FOREIGN KEY (country_id) REFERENCES public.country(country_id),
	CONSTRAINT fkj1mws2ruu9q6k2sa4pwlxthxn FOREIGN KEY (brand_id) REFERENCES public.brand(brand_id)
);
ALTER TABLE public.car OWNER TO postgres;
GRANT ALL ON TABLE public.car TO postgres;