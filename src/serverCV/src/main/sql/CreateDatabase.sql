
CREATE TABLE centro_vaccinale (
	id Integer primary key,
	nome_centro_vaccinale varchar(50),
	indirizzo varchar(255),
	comune varchar(50),
	sigla_provincia varchar(2),
	cap Integer,
	tipologia varchar(255)
);

ALTER TABLE centro_vaccinale ADD CONSTRAINT nome_centro_vaccinale_not_null
CHECK(nome_centro_vaccinale is not null);

ALTER TABLE centro_vaccinale ADD CONSTRAINT comune_not_null
CHECK(comune is not null);

ALTER TABLE centro_vaccinale ADD CONSTRAINT indirizzo_not_null
CHECK(indirizzo is not null);

ALTER TABLE centro_vaccinale ADD CONSTRAINT sigla_provincia_not_null
CHECK(sigla_provincia is not null);

ALTER TABLE centro_vaccinale ADD CONSTRAINT tipologia_not_null
CHECK(tipologia is not null);

ALTER TABLE centro_vaccinale ADD CONSTRAINT cap_not_null
CHECK(cap is not null);


CREATE TABLE cittadino (
	codice_fiscale varchar(16) primary key,
	nome_cittadino varchar(30),
	cognome_cittadino varchar(30)
);

ALTER TABLE cittadino ADD CONSTRAINT nome_cittadino_not_null
CHECK(nome_cittadino is not null);

ALTER TABLE cittadino ADD CONSTRAINT cognome_cittadino_not_null
CHECK(cognome_cittadino is not null);


CREATE TABLE vaccinazione (
	id Integer primary key,
	vaccino_somministrato varchar(255),
	data_vaccinazione date,
	id_centro_vaccinale Integer references centro_vaccinale,
	codice_fiscale varchar(16) references cittadino
);

ALTER TABLE vaccinazione ADD CONSTRAINT vaccino_somministrato_not_null
CHECK(vaccino_somministrato is not null);

ALTER TABLE vaccinazione ADD CONSTRAINT data_vaccinazione_not_null
CHECK(data_vaccinazione is not null);

ALTER TABLE vaccinazione ADD CONSTRAINT id_centro_vaccinale_not_null
CHECK(id_centro_vaccinale is not null);

ALTER TABLE vaccinazione  ADD CONSTRAINT codice_fiscale_not_null
CHECK(codice_fiscale is not null);

CREATE TABLE cittadino_registrato (
	userid varchar(12) primary key,
	email varchar(255),
	password varchar(255),
	codice_fiscale varchar(16) references cittadino
);

ALTER TABLE cittadino_registrato ADD CONSTRAINT email_not_null
CHECK(email is not null);

ALTER TABLE cittadino_registrato ADD CONSTRAINT userid_not_null
CHECK(userid is not null);

ALTER TABLE cittadino_registrato ADD CONSTRAINT password_not_null
CHECK(password is not null);

ALTER TABLE cittadino_registrato ADD CONSTRAINT codice_fiscale_not_null
CHECK(codice_fiscale is not null);

CREATE TABLE evento_avverso (
	id Integer primary key,
	evento varchar(30),
	severità Integer,
	note varchar(255),
	id_cittadino varchar(12) references cittadino_registrato
);

ALTER TABLE evento_avverso ADD CONSTRAINT evento_not_null
CHECK(evento is not null);

ALTER TABLE evento_avverso ADD CONSTRAINT severità_not_null
CHECK(severità is not null);

ALTER TABLE evento_avverso ADD CONSTRAINT id_cittadino_not_null
CHECK(id_cittadino is not null);

CREATE TABLE enum (
	id Integer primary key,
	name varchar(10),
	list varchar(255)
);

INSERT INTO enum (id,name,list) VALUES (1,'tipologie','[Hub, Aziendale, Ospedaliero]');