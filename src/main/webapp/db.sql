drop database if exists partnershop;
create database partnershop;
use partnershop;

create table UtenteRegistrato(
                                 nome varchar(50) not null,
                                 cognome varchar(50) not null,
                                 ddn date not null,
                                 email varchar(50) unique not null,
                                 indirizzo varchar(500) not null,
                                 username varchar(50) unique not null,
                                 passwordhash varchar(50) not null,
                                 tipo boolean not null,
                                 primary key(email)
);

create table Cliente(
                        email varchar(50) unique not null,
                        creditcard varchar(50),
                        foreign key(email) references UtenteRegistrato(email) on delete cascade on update cascade,
                        primary key(email)
);

create table Venditore(
                          email varchar(50) unique not null,
                          nomeNegozio varchar(50) not null,
                          partitaIVA varchar(50) not null,
                          foreign key(email) references UtenteRegistrato(email) on delete cascade on update cascade,
                          primary key(email)
);

create table Amministratore(
                               nome varchar(50) not null,
                               cognome varchar(50) not null,
                               email varchar(50) unique not null,
                               username varchar(50) unique not null,
                               passwordhash varchar(50) not null,
                               primary key(email)
);

create table Prodotto(
                         idProdotto int unique not null auto_increment,
                         nome varchar(50) not null,
                         descrizione varchar(500) not null,
                         categoria varchar(50) not null,
                         quantita int not null,
                         emailVenditore varchar(50) not null,
                         foreign key(emailVenditore) references Venditore(email) on delete cascade on update cascade,
                         primary key(idProdotto)
);

create table Ordine(
                       numOrdine int unique not null auto_increment,
                       emailCliente varchar(50) unique not null,
                       dataOrdine date not null,
                       indirizzo varchar(500) not null,
                       prezzoTot float not null,
                       foreign key(emailCliente) references Cliente(email) on delete cascade on update cascade,
                       primary key(numOrdine)
);

create table Segnalazione(
                             idSegnalazione int unique not null auto_increment,
                             emailCliente varchar(50) not null,
                             stato boolean default false not null,
                             motivazione varchar(500) not null,
                             commento varchar(500) not null,
                             foreign key(emailCliente) references Cliente(email) on delete cascade on update cascade,
                             primary key(idSegnalazione)
);

create table ListaDesideri(
                              emailCliente varchar(50) not null,
                              idProdotto int not null,
                              foreign key(idProdotto) references Prodotto(idProdotto) on delete cascade on update cascade,
                              foreign key(emailCliente) references Cliente(email) on delete cascade on update cascade,
                              primary key(emailCliente)
);

create table Carrello(
                         emailCliente varchar(50) not null,
                         idProdotto int not null,
                         quantita int not null,
                         foreign key(idProdotto) references Prodotto(idProdotto) on delete cascade on update cascade,
                         foreign key(emailCliente) references Cliente(email) on delete cascade on update cascade,
                         primary key(emailCliente)
);
