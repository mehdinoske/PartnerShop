unlock tables ;
drop database if exists partnershop;
create database partnershop;
use partnershop;

create table amministratore(
                               id integer not null primary key auto_increment,
                               username varchar(50) unique not null,
                               passwordhash varchar(50) not null
);

create table utente_registrato(
                                  email varchar(50) not null primary key,
                                  nome varchar(50) not null,
                                  cognome varchar(50) not null,
                                  ddn date not null,
                                  indirizzo varchar(500) not null,
                                  cellulare varchar(15) not null,
                                  username varchar(50) unique not null,
                                  passwordhash varchar(50) not null,
                                  tipo boolean not null,
                                  id_amministratore integer,
                                  foreign key(id_amministratore) references amministratore(id)
);

create table cliente(
                        email varchar(50) not null primary key,
                        creditcard varchar(50),
                        foreign key(email) references utente_registrato(email) on delete cascade on update cascade
);

create table venditore(
                          email varchar(50) not null primary key,
                          nome_negozio varchar(50) not null,
                          partita_iva varchar(50) not null,
                          foreign key(email) references utente_registrato(email) on delete cascade on update cascade
);

create table prodotto(
                         id integer not null auto_increment primary key,
                         email_venditore varchar(50) not null,
                         nome varchar(50) not null,
                         descrizione varchar(500) not null,
                         categoria varchar(50) not null,
                         prezzo_cent integer not null,
                         quantita_disponibile integer not null,
                         id_amministratore integer,
                         foreign key(id_amministratore) references amministratore(id),
                         foreign key(email_venditore) references venditore(email) on delete cascade on update cascade
);

create table ordine(
                       id integer not null auto_increment primary key,
                       email_cliente varchar(50) not null,
                       data_ordine date not null,
                       indirizzo varchar(500) not null,
                       prezzo_tot float not null,
                       foreign key(email_cliente) references cliente(email)
);

create table segnalazione(
                             id integer not null auto_increment primary key,
                             email_cliente varchar(50) not null,
                             stato boolean default false not null,
                             motivazione varchar(500) not null,
                             commento varchar(500) not null,
                             id_amministratore integer default 0,
                             foreign key(id_amministratore) references amministratore(id),
                             foreign key(email_cliente) references cliente(email)
);

create table lista_desideri(
                               id integer not null auto_increment primary key,
                               email_cliente varchar(50) unique not null,
                               foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

create table desideri_prodotto(
                                  id_desideri int not null,
                                  id_prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_desideri) references lista_desideri(id) on delete cascade on update cascade,
                                  foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                  primary key(id_desideri, id_prodotto)
);

create table carrello(
                         id integer not null auto_increment primary key,
                         email_cliente varchar(50) unique not null,
                         foreign key(email_cliente) references cliente(email) on delete cascade on update cascade
);

create table carrello_prodotto(
                                  id_carrello int not null,
                                  id_prodotto int not null,
                                  quantita int not null,
                                  foreign key(id_carrello) references carrello(id) on delete cascade on update cascade,
                                  foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                  primary key(id_carrello, id_prodotto)
);

create table ordine_prodotto(
                                id_ordine integer not null,
                                id_prodotto integer not null,
                                quantita int not null,
                                foreign key(id_ordine) references ordine(id) on delete cascade on update cascade,
                                foreign key(id_prodotto) references prodotto(id) on delete cascade on update cascade,
                                primary key(id_ordine, id_prodotto)
);



insert into utente_registrato values ('anconamarco@gmail.com', 'marco', 'ancona', '1212-12-12', 'napoli, contrada  n° 1', '3333333333', 'ancona1', sha1('asd'), 0, null),
                                     ('depalmamarco@gmail.com', 'marco', 'depalma', '1212-12-12', 'napoli, contrada  n° 1', '2222222222', 'depalma1', sha1('asd'), 1, null),
                                     ('boudad@gmail.com','mehdi','boudad','1212-12-12', 'Salerno, vicolo cioffi', '2222222222', 'noske', sha1('root'), 1, null);

insert into amministratore values (1, 'admin', sha1('admin'));
insert into cliente values ('anconamarco@gmail.com', null);
insert into venditore values ('depalmamarco@gmail.com', 'negozio1', 'piva1');
insert into venditore values ('boudad@gmail.com', 'negozio2', 'piva2');


insert into carrello values (1, 'anconamarco@gmail.com');
insert into lista_desideri values (1, 'anconamarco@gmail.com');
insert into prodotto values (1, 'depalmamarco@gmail.com', 'penna', 'penna blu molto costosa', 'tipografia', 1, 300, null);
insert into prodotto values (2, 'depalmamarco@gmail.com', 'laptop', 'macbook m1 ', 'elettronica', 1, 300, null);
insert into prodotto values (3, 'boudad@gmail.com', 'penna', 'dddddddddd', 'tipografia', 1, 300, null);
insert into prodotto values (4, 'boudad@gmail.com', 'penna', 'dddddddddd', 'tipografia', 1, 300, null);
insert into prodotto values (5, 'boudad@gmail.com', 'penna', 'dddddddddd', 'tipografia', 1, 300, null);

insert into segnalazione values (1, 'anconamarco@gmail.com', false, 'corriere scortese', 'commenti aggiuntivi', null);
insert into ordine values (1, 'anconamarco@gmail.com', '1212-12-12', 'indirizzo1', 300);
insert into carrello_prodotto values (1, 1, 20);
insert into ordine_prodotto values (1, 1, 30);
insert into desideri_prodotto values (1, 1, 20);

