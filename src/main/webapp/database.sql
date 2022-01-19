drop database if exists partnershop;
create database partnershop;
use partnershop;

create table Utente_Registrato(
                                  email varchar(50) not null primary key,
                                  nome varchar(50) not null,
                                  cognome varchar(50) not null,
                                  ddn date not null,
                                  indirizzo varchar(500) not null,
                                  cellulare long not null,
                                  username varchar(50) unique not null,
                                  passwordhash varchar(50) not null,
                                  tipo boolean not null
);

LOCK TABLES Utente_Registrato WRITE;
INSERT INTO Utente_Registrato VALUES ('sw@d.d', 'peppe', 'dicazzo', '1212-12-12', 'Napoli, contrada minghia n° 1', 3333333333, 'giuse', SHA1('password1'), false),
                                     ('ven@d.d', 'peppfe', 'dicfazzo', '1212-12-12', 'Napoli, contrada minghia n° 1', 2222222222, 'gisuse', SHA1('password1'), true);
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

create table Ordine(
                       id integer not null auto_increment primary key,
                       email_Cliente varchar(50) unique not null,
                       data_Ordine date not null,
                       indirizzo varchar(500) not null,
                       prezzo_Tot float not null,
                       foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

create table Segnalazione(
                             id integer not null auto_increment primary key,
                             email_Cliente varchar(50) not null,
                             stato boolean default false not null,
                             motivazione varchar(500) not null,
                             commento varchar(500) not null,
                             foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

create table Lista_Desideri(
                               id integer not null auto_increment primary key,
                               email_Cliente varchar(50) unique not null,
                               foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

create table Desideri_Prodotto(
                                  id_Desideri int not null,
                                  id_Prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_Desideri) references Lista_Desideri(id) on delete cascade on update cascade,
                                  foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                  primary key(id_Desideri, id_Prodotto)
);

create table Carrello(
                         id integer not null auto_increment primary key,
                         email_Cliente varchar(50) unique not null,
                         foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade
);

create table Carrello_Prodotto(
                                  id_Carrello int not null,
                                  id_Prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_Carrello) references Carrello(id) on delete cascade on update cascade,
                                  foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                  primary key(id_Carrello, id_Prodotto)
);

create table Segnalazione_Amministratore(
                                            id_Desideri integer not null primary key,
                                            id_Amministratore integer not null,
                                            foreign key(id_Desideri) references Lista_Desideri(id) on delete cascade on update cascade,
                                            foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

create table Prodotto_Amministratore(
                                        id_Prodotto integer not null primary key,
                                        id_Amministratore integer not null,
                                        foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                        foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

create table Utente_Amministratore(
                                      email_Cliente varchar(50) not null primary key,
                                      id_Amministratore integer not null,
                                      foreign key(email_Cliente) references Cliente(email) on delete cascade on update cascade,
                                      foreign key(id_Amministratore) references Amministratore(id) on delete cascade on update cascade
);

create table Ordine_Prodotto(
                                id_Ordine integer not null,
                                id_Prodotto integer not null,
                                quantita int not null,
                                foreign key(id_Ordine) references Ordine(id) on delete cascade on update cascade,
                                foreign key(id_Prodotto) references Prodotto(id) on delete cascade on update cascade,
                                primary key(id_Ordine, id_Prodotto)
);