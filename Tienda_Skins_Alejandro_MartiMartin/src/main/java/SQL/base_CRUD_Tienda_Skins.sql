create schema tienda;
use tienda;
CREATE DATABASE  tienda_skins DEFAULT CHARACTER SET utf8 ;
USE tienda_skins;

CREATE TABLE  usuarios (
  correo_electronico VARCHAR(100) NOT NULL ,
  pass VARCHAR(100) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  saldo double NOT NULL,
  PRIMARY KEY (correo_electronico))ENGINE = InnoDB;
  
 CREATE TABLE  skins (
  id int(5) not null auto_increment,
  nombre VARCHAR(100) NOT NULL ,
  codigo VARCHAR(100) NOT NULL ,
  precio double NOT NULL ,
  juego VARCHAR(50) NOT NULL,
  vendedor VARCHAR(100),
  PRIMARY KEY (id),
  foreign key(vendedor) references usuarios(correo_electronico))ENGINE = InnoDB; 
  
  
  
  
  
insert into usuarios values('ana@gmail.com', sha1('123'), 'Ana','Garcia Lopez', 80.75),('juan@gmail.com', sha1('456'), 'Juan','Lopez Lopez', 50.25);

insert into skins (nombre,codigo,precio,juego,vendedor) values('Sage galactica', 'fg451df64bdgb6d54df65', 15.25,'Valorant','ana@gmail.com'),('Raze pegaFuerte', 'drtb1fxtg75jh65g84hs98fg', 10.25,'Valorant','ana@gmail.com'),('Orisa heaven', 'b45dfgb65hzd74b68z74dfg', 20.25,'Overwatch','juan@gmail.com'),('Lucio Triton', '645sg684sd+g854su6d', 16.25,'Overwatch','juan@gmail.com');