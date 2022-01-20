drop database if exists partnershop;
create database partnershop;
use partnershop;


create table utente_registrato(
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

lock tables utente_registrato write;
insert into utente_registrato values ('sw@d.d', 'peppe', 'dicazzo', '1212-12-12', 'napoli, contrada minghia n° 1', '3333333333', 'giuse', sha1('password1'), false),
                                     ('ven@d.d', 'peppfe', 'dicfazzo', '1212-12-12', 'napoli, contrada minghia n° 1', '2222222222', 'gisuse', sha1('password1'), true);
unlock tables;

create table cliente(
                        email varchar(50) not null primary key,
                        creditcard varchar(50),
                        foreign key(email) references utente_registrato(email) on delete cascade on update cascade
);

lock tables cliente write;
insert into cliente values ('sw@d.d', null);
unlock tables;

create table venditore(
                          email varchar(50) not null primary key,
                          nome_negozio varchar(50) not null,
                          partita_iva varchar(50) not null,
                          foreign key(email) references utente_registrato(email) on delete cascade on update cascade
);

lock tables venditore write;
insert into venditore values ('ven@d.d', 'porco', 'cane');
unlock tables;

create table amministratore(
                               id integer not null primary key auto_increment,
                               username varchar(50) unique not null,
                               passwordhash varchar(50) not null
);

lock tables amministratore write;
insert into amministratore values (1, 'porco', sha1('cane'));
unlock tables;

create table prodotto(
                         id integer not null auto_increment primary key,
                         email_venditore varchar(50) not null,
                         nome varchar(50) not null,
                         descrizione varchar(500) not null,
                         categoria varchar(50) not null,
                         prezzo_cent integer not null,
                         quantita_disponibile integer not null,
                         foreign key(email_venditore) references venditore(email) on delete cascade on update cascade
);

lock tables prodotto write;
insert into prodotto values (1, 'ven@d.d', 'penna', 'dddddddddd', 'tipografia', 1, 300);
unlock tables;

create table ordine(
                       id integer not null auto_increment primary key,
                       email_cliente varchar(50) unique not null,
                       data_ordine date not null,
                       indirizzo varchar(500) not null,
                       prezzo_tot float not null,
                       foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

lock tables ordine write;
insert into ordine values (1, 'sw@d.d', '1212-12-12', 'dddddddddd', 300);
unlock tables;

create table segnalazione(
                             id integer not null auto_increment primary key,
                             email_cliente varchar(50) not null,
                             stato boolean default false not null,
                             motivazione varchar(500) not null,
                             commento varchar(500) not null,
                             foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

lock tables segnalazione write;
insert into segnalazione values (1, 'sw@d.d', false, 'ciao hola come va', 'dddddddggggddd');
unlock tables;

create table lista_desideri(
                               id integer not null auto_increment primary key,
                               email_cliente varchar(50) unique not null,
                               foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

lock tables lista_desideri write;
insert into lista_desideri values (1, 'sw@d.d');
unlock tables;

create table desideri_prodotto(
                                  id_desideri int not null,
                                  id_prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_desideri) references lista_desideri(id) on delete cascade on update cascade,
                                  foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                  primary key(id_desideri, id_prodotto)
);

lock tables desideri_prodotto write;
insert into desideri_prodotto values (1, 1, 20);
unlock tables;

create table carrello(
                         id integer not null auto_increment primary key,
                         email_cliente varchar(50) unique not null,
                         foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

lock tables carrello write;
insert into carrello values (1, 'sw@d.d');
unlock tables;

create table carrello_prodotto(
                                  id_carrello int not null,
                                  id_prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_carrello) references carrello(id) on delete cascade on update cascade,
                                  foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                  primary key(id_carrello, id_prodotto)
);

lock tables carrello_prodotto write;
insert into carrello_prodotto values (1, 1, 20);
unlock tables;

create table segnalazione_amministratore(
                                            id_segnalazione integer not null primary key,
                                            id_amministratore integer not null,
                                            foreign key(id_segnalazione) references segnalazione(id) on delete cascade on update cascade,
                                            foreign key(id_amministratore) references amministratore(id) on delete cascade on update cascade
);

lock tables segnalazione_amministratore write;
insert into segnalazione_amministratore values (1, 1);
unlock tables;

create table prodotto_amministratore(
                                        id_prodotto integer not null primary key,
                                        id_amministratore integer not null,
                                        foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                        foreign key(id_amministratore) references amministratore(id) on delete cascade on update cascade
);

lock tables prodotto_amministratore write;
insert into prodotto_amministratore values (1, 1);
unlock tables;

create table utente_amministratore(
                                      email_cliente varchar(50) not null primary key,
                                      id_amministratore integer not null,
                                      foreign key(email_cliente) references cliente(email) on delete cascade on update cascade,
                                      foreign key(id_amministratore) references amministratore(id) on delete cascade on update cascade
);

lock tables utente_amministratore write;
insert into utente_amministratore values ('sw@d.d', 1);
unlock tables;

create table ordine_prodotto(
                                id_ordine integer not null,
                                id_prodotto integer not null,
                                quantita int not null,
                                foreign key(id_ordine) references ordine(id) on delete cascade on update cascade,
                                foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                primary key(id_ordine, id_prodotto)
);

lock tables ordine_prodotto write;
insert into ordine_prodotto values (1, 1, 30);
unlock tables;


