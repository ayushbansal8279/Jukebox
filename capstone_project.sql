create database jukebox;
use jukebox;

create table songs(
songId int primary key auto_increment,
songName varchar(25) not null unique,
artist varchar(30) not null,
genre varchar(20) not null,
album varchar(20),
duration float not null);

insert into songs(songName,artist,genre,album,duration) values('Dancing Queen','ABBA','Jazz','Abb',5);
insert into songs(songName,artist,genre,album,duration) values('Rose Garden','Lyenn','Rock','LNR',4);
insert into songs(songName,artist,genre,album,duration) values('Cattle call','Eddy','soul','cc',4);
insert into songs(songName,artist,genre,album,duration) values('Heart of moment','Asia','dance','mhoi','8');
insert into songs(songName,artist,genre,album,duration) values('Loser','Back','hip hop','salr',3);
insert into songs(songName,artist,genre,album,duration) values('Fantastic baby','Big bang','jazz','baby',6);

select* from songs;
