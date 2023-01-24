INSERT INTO centro_vaccinale (id,nome_centro_vaccinale,indirizzo,comune,sigla_provincia,cap,tipologia) VALUES (1,'Ospedale San Raffaele','Via Olgettina, 60','Milano','MI',20132,'Ospedaliero');
INSERT INTO centro_vaccinale (id,nome_centro_vaccinale,indirizzo,comune,sigla_provincia,cap,tipologia) VALUES (2,'Centro Commerciale Move In','Via F.Turati, 52','Cerro Maggiore','MI',20023,'Hub');
INSERT INTO centro_vaccinale (id,nome_centro_vaccinale,indirizzo,comune,sigla_provincia,cap,tipologia) VALUES (3,'UNIPOL','via de Castillia, 23','Milano','MI',20124,'Aziendale');
INSERT INTO cittadino (codice_fiscale,nome_cittadino,cognome_cittadino) VALUES ('PNZRRT44D01M007D','Roberto Lidio','Penazzo');
INSERT INTO cittadino (codice_fiscale,nome_cittadino,cognome_cittadino) VALUES ('CMPDLI77T41L279R','Idalia','Campione');
INSERT INTO cittadino (codice_fiscale,nome_cittadino,cognome_cittadino) VALUES ('CRNSDR53P41A454A','Sandrina','Cernautan');
INSERT INTO vaccinazione (id,vaccino_somministrato,data_vaccinazione,id_centro_vaccinale,codice_fiscale) VALUES (1,'Pfizer','2022-01-08','1','PNZRRT44D01M007D');
INSERT INTO vaccinazione (id,vaccino_somministrato,data_vaccinazione,id_centro_vaccinale,codice_fiscale) VALUES (2,'AstraZeneca','2021-09-12','2','CMPDLI77T41L279R');
INSERT INTO vaccinazione (id,vaccino_somministrato,data_vaccinazione,id_centro_vaccinale,codice_fiscale) VALUES (3,'Moderna','2020-12-5','1','CRNSDR53P41A454A');
INSERT INTO cittadino_registrato (userid,email,password,codice_fiscale) VALUES ('Robertone77','roberto@gmail.com','Robertone77.','PNZRRT44D01M007D');
INSERT INTO cittadino_registrato (userid,email,password,codice_fiscale) VALUES ('Sandrina1','sandrina@gmail.com','Sandrina99.','CRNSDR53P41A454A');
INSERT INTO evento_avverso (id,evento,severità,note,id_cittadino) VALUES (1,'Mal di testa',3,'emicrania persistente da 5 giorni','Robertone77');
INSERT INTO evento_avverso (id,evento,severità,note,id_cittadino) VALUES (2,'Dolori muscolari e articolari',5,'gambe indolezite e spossatezza generale','Sandrina1');
INSERT INTO evento_avverso (id,evento,severità,note,id_cittadino) VALUES (3,'linfoadenopatia',2,'linfonodi molto gonfi','Robertone77');