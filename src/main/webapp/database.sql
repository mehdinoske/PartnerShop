drop database if exists partnershop;
create database partnershop;
use partnershop;

create table Utente_Registrato(
                                  email varchar(50) not null primary key,
                                  nome varchar(50) not null,
                                  cognome varchar(50) not null,
                                  ddn date not null,
                                  indirizzo varchar(500) not null,
                                  cellulare varchar(15) not null,
                                  username varchar(50) unique not null,
                                  passwordhash varchar(50) not null,
                                  tipo boolean not null
);

LOCK TABLES Utente_Registrato WRITE;
INSERT INTO Utente_Registrato VALUES ('sw@d.d', 'peppe', 'dicazzo', '1212-12-12', 'Napoli, contrada minghia n° 1', '3333333333', 'giuse', SHA1('password1'), false),
                                     ('ven@d.d', 'peppfe', 'dicfazzo', '1212-12-12', 'Napoli, contrada minghia n° 1', '2222222222', 'gisuse', SHA1('password1'), true);
UNLOCK TABLES;

create table Cliente(
                        email varchar(50) not null primary key,
                        creditcard varchar(50),
                        foreign key(email) references Utente_Registrato(email) on delete cascade on update cascade
);

LOCK TABLES Cliente WRITE;
INSERT INTO Cliente VALUES ('sw@d.d', null);
UNLOCK TABLES;

create table Venditore(
                          email varchar(50) not null primary key,
                          nome_Negozio varchar(50) not null,
                          partita_IVA varchar(50) not null,
                          foreign key(email) references Utente_Registrato(email) on delete cascade on update cascade
);

LOCK TABLES Venditore WRITE;
INSERT INTO Venditore VALUES ('ven@d.d', 'Porco', 'Cane');
UNLOCK TABLES;

create table Amministratore(
                               id integer not null primary key AUTO_INCREMENT,
                               username varchar(50) unique not null,
                               passwordhash varchar(50) not null
);

LOCK TABLES Amministratore WRITE;
INSERT INTO Amministratore VALUES (1, 'Porco', SHA1('Cane'));
UNLOCK TABLES;

create table Prodotto(
                         id integer not null auto_increment primary key,
                         email_Venditore varchar(50) not null,
                         nome varchar(50) not null,
                         descrizione varchar(500) not null,
                         categoria varchar(50) not null,
                         prezzo integer not null,
                         quantita_disponibile integer not null,
                         foreign key(email_Venditore) references Venditore(email) on delete cascade on update cascade
);

LOCK TABLES Prodotto WRITE;
INSERT INTO Prodotto VALUES (1, 'ven@d.d', 'Penna', 'dddddddddd', 'tipografia', 1, 300);
UNLOCK TABLES;

create table Ordine(
                       id integer not null auto_increment primary key,
                       email_Cliente varchar(50) unique not null,
                       data_Ordine date not null,
                       indirizzo varchar(500) not null,
                       prezzo_Tot float not null,
                       foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

LOCK TABLES Ordine WRITE;
INSERT INTO Ordine VALUES (1, 'sw@d.d', '1212-12-12', 'dddddddddd', 300);
UNLOCK TABLES;

create table Segnalazione(
                             id integer not null auto_increment primary key,
                             email_Cliente varchar(50) not null,
                             stato boolean default false not null,
                             motivazione varchar(500) not null,
                             commento varchar(500) not null,
                             foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

LOCK TABLES Segnalazione WRITE;
INSERT INTO Segnalazione VALUES (1, 'sw@d.d', false, 'ciao hola come va', 'dddddddggggddd');
UNLOCK TABLES;

create table Lista_Desideri(
                               id integer not null auto_increment primary key,
                               email_Cliente varchar(50) unique not null,
                               foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

LOCK TABLES Lista_Desideri WRITE;
INSERT INTO Lista_Desideri VALUES (1, 'sw@d.d');
UNLOCK TABLES;

create table Desideri_Prodotto(
                                  id_Desideri int not null,
                                  id_Prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_Desideri) references Lista_Desideri(id) on delete cascade on update cascade,
                                  foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                  primary key(id_Desideri, id_Prodotto)
);

LOCK TABLES Desideri_Prodotto WRITE;
INSERT INTO Desideri_Prodotto VALUES (1, 1, 20);
UNLOCK TABLES;

create table Carrello(
                         id integer not null auto_increment primary key,
                         email_Cliente varchar(50) unique not null,
                         foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

LOCK TABLES Carrello WRITE;
INSERT INTO Carrello VALUES (1, 'sw@d.d');
UNLOCK TABLES;

create table Carrello_Prodotto(
                                  id_Carrello int not null,
                                  id_Prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_Carrello) references Carrello(id) on delete cascade on update cascade,
                                  foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                  primary key(id_Carrello, id_Prodotto)
);

LOCK TABLES Carrello_Prodotto WRITE;
INSERT INTO Carrello_Prodotto VALUES (1, 1, 20);
UNLOCK TABLES;

create table Segnalazione_Amministratore(
                                            id_Desideri integer not null primary key,
                                            id_Amministratore integer not null,
                                            foreign key(id_Desideri) references Lista_Desideri(id) on delete cascade on update cascade,
                                            foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

LOCK TABLES Segnalazione_Amministratore WRITE;
INSERT INTO Segnalazione_Amministratore VALUES (1, 1);
UNLOCK TABLES;

create table Prodotto_Amministratore(
                                        id_Prodotto integer not null primary key,
                                        id_Amministratore integer not null,
                                        foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                        foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

LOCK TABLES Prodotto_Amministratore WRITE;
INSERT INTO Prodotto_Amministratore VALUES (1, 1);
UNLOCK TABLES;

create table Utente_Amministratore(
                                      email_Cliente varchar(50) not null primary key,
                                      id_Amministratore integer not null,
                                      foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade,
                                      foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

LOCK TABLES Utente_Amministratore WRITE;
INSERT INTO Utente_Amministratore VALUES ('sw@d.d', 1);
UNLOCK TABLES;

create table Ordine_Prodotto(
                                id_Ordine integer not null,
                                id_Prodotto integer not null,
                                quantita int not null,
                                foreign key(id_Ordine) references Ordine(id) on delete cascade on update cascade,
                                foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                primary key(id_Ordine, id_Prodotto)
);

LOCK TABLES Ordine_Prodotto WRITE;
INSERT INTO Ordine_Prodotto VALUES (1, 1, 30);
UNLOCK TABLES;