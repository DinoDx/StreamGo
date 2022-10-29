/* begin database creation */
drop database if exists esameTsw;
create database esameTsw; /* It is a synonym for CREATE SCHEMA*/
use esameTsw;

/* create user and password */
SET GLOBAL time_zone = '+1:00';
DROP USER IF EXISTS 'esameTsw'@'localhost';
CREATE USER 'esameTsw'@'localhost' IDENTIFIED BY 'esameTsw';
GRANT ALL ON esameTsw.* TO 'esameTsw'@'localhost';

/* begin table creation */

CREATE TABLE Prodotti(
Nome VARCHAR(70) NOT NULL,
Durata INT NOT NULL,
PRIMARY KEY(NOME));

CREATE TABLE Film(
Genere VARCHAR(20) NOT NULL,
Nome VARCHAR(70) NOT NULL,
Link VARCHAR(50) NOT NULL,
Img VARCHAR(100) NOT NULL,
FOREIGN KEY(Nome) REFERENCES Prodotti(Nome) ON DELETE CASCADE) ;

CREATE TABLE SerieTV(
NumEpisodi INT NOT NULL,
Nome VARCHAR(70) NOT NULL,
Link VARCHAR(70) NOT NULL,
Img VARCHAR(100) NOT NULL,
FOREIGN KEY(Nome) REFERENCES Prodotti(Nome)ON DELETE CASCADE);

CREATE TABLE Utenti(
Username VARCHAR(15) NOT NULL,
Password VARCHAR(15) NOT NULL,
Ruolo ENUM ('Amministratore', 'Utente') NOT NULL,
Nome VARCHAR(15) NOT NULL,
Cognome VARCHAR(15) NOT NULL,
PRIMARY KEY(Username)
);

CREATE TABLE Telefoni(
Numero CHAR(10) NOT NULL,
Username VARCHAR(15) NOT NULL,
FOREIGN KEY(Username) REFERENCES Utenti(Username)ON DELETE CASCADE,
PRIMARY KEY(Numero)
);

CREATE TABLE Recensioni(
NumProgressivo INT NOT NULL AUTO_INCREMENT,
Voto SMALLINT UNSIGNED 	NOT NULL,
Descrizione VARCHAR(100),
Username VARCHAR(15) NOT NULL,
NomeProdotto VARCHAR(70) NOT NULL,
PRIMARY KEY(NumProgressivo),
FOREIGN KEY(Username) REFERENCES Utenti(Username)ON DELETE CASCADE,
FOREIGN KEY(NomeProdotto) REFERENCES Prodotti(Nome)ON DELETE CASCADE
);

/* end table creation */

/* begin data population */

/* prodotti */
INSERT into Prodotti(Nome, Durata)
values('Il padrino', 110);

INSERT into Prodotti(Nome, Durata)
values('Il padrino - Parte II', 110);

INSERT into Prodotti(Nome, Durata)
values('Il cavaliere oscuro', 120);

INSERT into Prodotti(Nome, Durata)
values('Le ali della liberta', 120);

INSERT into Prodotti(Nome, Durata)
values('La parola ai giurati', 100);

INSERT into Prodotti(Nome, Durata)
values('Schindler s List', 100);

INSERT into Prodotti(Nome, Durata)
values('Il Signore degli Anelli - Il ritorno del re', 180);

INSERT into Prodotti(Nome, Durata)
values('Pulp Fiction', 130);

INSERT into Prodotti(Nome, Durata)
values('Il buono, il brutto, il cattivo', 100);

INSERT into Prodotti(Nome, Durata)
values('Il Signore degli Anelli La compagnia dell Anello', 180);

INSERT into Prodotti(Nome, Durata)
values('L Impero colpisce ancora', 125);

INSERT into Prodotti(Nome, Durata)
values('Il Signore degli Anelli - Le due torri', 180);

INSERT into Prodotti(Nome, Durata)
values('Guerre stellari', 123);

INSERT into Prodotti(Nome, Durata)
values('Joker', 134);

INSERT into Prodotti(Nome, Durata)
values('Ritorno al futuro', 112);

INSERT into Prodotti(Nome, Durata)
values('Breaking Bad', 50);

INSERT into Prodotti(Nome, Durata)
values('Il trono di spade', 60);

INSERT into Prodotti(Nome, Durata)
values('Rick and Morty', 20);

INSERT into Prodotti(Nome, Durata)
values('Sherlock', 90);

INSERT into Prodotti(Nome, Durata)
values('Fullmetal Alchemist : Brotherhood', 25);

INSERT into Prodotti(Nome, Durata)
values('Friends', 22);

INSERT into Prodotti(Nome, Durata)
values('Black Mirror', 50);

INSERT into Prodotti(Nome, Durata)
values('Over the Garden Wall', 30);

INSERT into Prodotti(Nome, Durata)
values('The Mandalorian', 40);

INSERT into Prodotti(Nome, Durata)
values('Stranger Things', 35);

/* film */
INSERT into Film(Genere, Nome, Link, Img)
values('Azione', (SELECT Nome FROM Prodotti WHERE Nome='Il padrino'), 'sY1S34973zA', 'img/ilpadrino.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Azione', (SELECT Nome FROM Prodotti WHERE Nome='Il padrino - Parte II'), '9O1Iy9od7-A', 'img/ilpadrino2.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Cinecomic', (SELECT Nome FROM Prodotti WHERE Nome='Il cavaliere oscuro'), 'yqcDBdk8wpo', 'img/ilcavosc.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Drammatico', (SELECT Nome FROM Prodotti WHERE Nome='Le ali della liberta'), '0ly4vMdPBo0', 'img/leali.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Drammatico', (SELECT Nome FROM Prodotti WHERE Nome='La parola ai giurati'), 'I-Jw_yZSwIw', 'img/laparola.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Storico', (SELECT Nome FROM Prodotti WHERE Nome='Schindler s List'), 'gG22XNhtnoY', 'img/list.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Fantasy', (SELECT Nome FROM Prodotti WHERE Nome='Il Signore degli Anelli - Il ritorno del re'), 'dezthH3eGw0', 'img/ilritorno.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Commedia', (SELECT Nome FROM Prodotti WHERE Nome='Pulp Fiction'), 's7EdQ4FqbhY', 'img/pulp.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Western', (SELECT Nome FROM Prodotti WHERE Nome='Il buono, il brutto, il cattivo'), 'zzJ7Mi_TvuY', 'img/ilbuono.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Fantasy', (SELECT Nome FROM Prodotti WHERE Nome='Il Signore degli Anelli La compagnia dell Anello'), 'Oiz8siG8OXA', 'img/lacomp.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Fantascienza', (SELECT Nome FROM Prodotti WHERE Nome='L Impero colpisce ancora'), '9Oeb1Y7sUcg', 'img/limp.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Fantasy', (SELECT Nome FROM Prodotti WHERE Nome='Il Signore degli Anelli - Le due torri'), 'O8im5qfCepI', 'img/ledue.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Fantascienza', (SELECT Nome FROM Prodotti WHERE Nome='Guerre stellari'), 'fzikKDE30Q4', 'img/guerre.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Cinecomic', (SELECT Nome FROM Prodotti WHERE Nome='Joker'), 'o7nkJDjuSp4', 'img/joker.jpg');

INSERT into Film(Genere, Nome, Link, Img)
values('Avventura', (SELECT Nome FROM Prodotti WHERE Nome='Ritorno al futuro'), 'qcYGOPylffk', 'img/ritorno.jpg');

/* serie tv */
INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(61, (SELECT Nome FROM Prodotti WHERE Nome='Breaking Bad'), 'videoseries?list=PLoCL7ZWIWKM6YQoPiRDSjir2AAO1Xi9wJ', 'img/bb.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(46, (SELECT Nome FROM Prodotti WHERE Nome='Il trono di spade'), 'videoseries?list=PLDisKgcnAC4RXiVgVkJ_4eXmNP5QEaqYj', 'img/got.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(13, (SELECT Nome FROM Prodotti WHERE Nome='Rick and Morty'), 'videoseries?list=PLwgRxxvjwZFLYJ8PXvHj5QYMV5HvleeKA', 'img/rem.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(62, (SELECT Nome FROM Prodotti WHERE Nome='Sherlock'), 'videoseries?list=PL5g4Fki_QYDazvjpTCCNrScQej4s7Lzo4', 'img/sherlock.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(53, (SELECT Nome FROM Prodotti WHERE Nome='Fullmetal Alchemist : Brotherhood'), 'videoseries?list=PLD0FC564EE21715DE', 'img/fmab.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(28, (SELECT Nome FROM Prodotti WHERE Nome='Friends'), 'videoseries?list=PLGX6-c-guOrrgQ2ymiON7_jrGuDjIOwVu', 'img/friends.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(34, (SELECT Nome FROM Prodotti WHERE Nome='Black Mirror'), 'videoseries?list=PLvahqwMqN4M3d_PTQywnw74cLqK82BLEO', 'img/bm.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(40, (SELECT Nome FROM Prodotti WHERE Nome='Over the Garden Wall'), 'videoseries?list=PLwiF1HrSFVi3n1Y84rwv3yEMLRUKkRS-p', 'img/otgw.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(34, (SELECT Nome FROM Prodotti WHERE Nome='The Mandalorian'), 'videoseries?list=PLWZ31lk1bxFDYPvRlRcKI16UAsGOiXQXQ', 'img/mando.jpg');

INSERT into SerieTv(NumEpisodi, Nome, Link, Img)
values(36, (SELECT Nome FROM Prodotti WHERE Nome='Stranger Things'), 'videoseries?list=PLgROM6oFx_rpu9Cv5kuZ2yp-AVhR7-_i3', 'img/st.jpg');

/*Popolamento Utenti*/
insert into Utenti values('admin', 'admin', 'Amministratore', 'Alessandro', 'Romano');
insert into Utenti values('PippoSowlo', 'password', 'Utente', 'Leonardo', 'Rossi');
insert into Utenti values('GiulyWhite', 'password', 'Utente', 'Giulia', 'Bianchi');
insert into Utenti values('GalloAuro', 'password', 'Utente', 'Aurora', 'Gallo');
insert into Utenti values('MollyJackson', 'password', 'Utente', 'Mattia', 'Conti');
insert into Utenti values('FerrSofy', 'password', 'Utente', 'Sofia', 'Ferraro');
insert into Utenti values('Robot56', 'password', 'Utente', 'Alice', 'Costa');

/*TELEFONI*/
insert into telefoni values('2259784521', 'admin');
insert into telefoni values('1343154442', 'GiulyWhite');
insert into telefoni values('2259416521', 'MollyJackson');
insert into telefoni values('7558784521', 'Robot56');
insert into telefoni values('7455451521', 'FerrSofy');

/*Popolamento Recensioni*/
insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('1', 'Orribile. Film con audio non ben calibrato tra musica e audio.',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'La parola ai giurati'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Bello',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Guerre stellari'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', NULL,
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Breaking Bad'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', NULL,
(select Username from Utenti where Username= 'GiulyWhite'),
(select Nome from Prodotti where Nome = 'Rick and Morty'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Un classico senza fine',
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Regia stratosferica',
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', null,
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Schindler s List'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'MollyJackson'),
(select Nome from Prodotti where Nome = 'La parola ai giurati'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Mi aspettavo meglio',
(select Username from Utenti where Username= 'MollyJackson'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'MollyJackson'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Non al liello della prima parte',
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il padrino - Parte II'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Sopravvalutato',
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', null,
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Spettacolare',
(select Username from Utenti where Username= 'Robot56'),
(select Nome from Prodotti where Nome = 'Il Signore degli Anelli - Il ritorno del re'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Noioso',
(select Username from Utenti where Username= 'Robot56'),
(select Nome from Prodotti where Nome = 'La parola ai giurati'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Capolavoro',
(select Username from Utenti where Username= 'Robot56'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Commovente',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Le ali della liberta'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', null,
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', null,
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Il padrino - Parte II'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Troppo lungo',
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Il Signore degli Anelli - Il ritorno del re'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Le ali della liberta'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Bello, ma non come il primo',
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Il padrino - Parte II'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', null,
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Masterpiece',
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Noioso',
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Pulp Fiction'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', null,
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Il Signore degli Anelli - Il ritorno del re'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Non mi e piaciuto',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Il padrino - Parte II'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('1', 'Male',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Pulp Fiction'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Nulla di speciale',
(select Username from Utenti where Username= 'GiulyWhite'),
(select Nome from Prodotti where Nome = 'Le ali della liberta'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'GiulyWhite'),
(select Nome from Prodotti where Nome = 'Il padrino'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', 'Il miglior prodotto targato DC',
(select Username from Utenti where Username= 'GiulyWhite'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', null,
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Le ali della liberta'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', 'Diretto benissimo',
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Il cavaliere oscuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Capolavoro al livello del primo capitolo',
(select Username from Utenti where Username= 'GalloAuro'),
(select Nome from Prodotti where Nome = 'Il padrino - Parte II'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', 'Film che rimarra nella storia',
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Guerre stellari'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', 'Gran film',
(select Username from Utenti where Username= 'MollyJackson'),
(select Nome from Prodotti where Nome = 'L Impero colpisce ancora'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Il buono, il brutto, il cattivo'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', 'Miglior film del 2019',
(select Username from Utenti where Username= 'Robot56'),
(select Nome from Prodotti where Nome = 'Joker'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('4', null,
(select Username from Utenti where Username= 'FerrSofy'),
(select Nome from Prodotti where Nome = 'Ritorno al futuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', null,
(select Username from Utenti where Username= 'MollyJackson'),
(select Nome from Prodotti where Nome = 'L Impero colpisce ancora'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', 'Sopravvalutato',
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Joker'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('3', 'Invecchiato male',
(select Username from Utenti where Username= 'Robot56'),
(select Nome from Prodotti where Nome = 'Ritorno al futuro'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('5', 'Attesa pienamente ripagata',
(select Username from Utenti where Username= 'admin'),
(select Nome from Prodotti where Nome = 'Joker'));

insert into Recensioni (Voto, Descrizione, Username, NomeProdotto)
values ('2', null,
(select Username from Utenti where Username= 'PippoSowlo'),
(select Nome from Prodotti where Nome = 'Guerre stellari'));