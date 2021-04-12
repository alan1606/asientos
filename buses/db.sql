drop database if exists asientos;

create database if not exists asientos;

use asientos;

create table destino(
	id int unsigned not null auto_increment,
    ciudad varchar(150) not null,
    constraint pk_id primary key (id),
    constraint uq_ciudad unique(ciudad)
);

create table viaje(
	id int unsigned not null auto_increment,
    id_destino int unsigned not null,
    fecha date not null,
	no_asientos tinyint unsigned not null,
    constraint pk_id primary key (id),
    constraint fk_destino_viaje foreign key (id_destino) references destino(id)
);

create table cliente (
	id int unsigned not null auto_increment,
    nombre varchar (120) not null,
    telefono varchar(12) not null,
    constraint pk_id primary key (id)
);

insert into cliente values(1, "", "");

create table asiento(
	numero tinyint unsigned not null,
    id_viaje int unsigned not null,
	id_cliente int unsigned not null default 1,
    disponible boolean not null default true,
    constraint pk_numero_id_viaje primary key (numero,id_viaje),
    constraint fk_id_viaje_asiento foreign key (id_viaje) references viaje(id),
    constraint fk_id_cliente_asiento foreign key (id_cliente) references cliente (id)
);

create table usuario (
	id int unsigned not null auto_increment,
	usuario varchar(100) not null, 
	pass varchar(100) not null, 
    nombre varchar(100) not null,
    tipo enum("Administrador", "Coordinador") not null,
    constraint pk_id primary key (id),
    constraint uq_usuario unique(usuario)
);

create table detalle(
	id_viaje int unsigned not null,
    id_cliente int unsigned not null,
    id_usuario int unsigned not null,
    personas tinyint unsigned not null,
    sube varchar(150) not null,
    habitacion varchar(100) not null,
    costo double not null,
    constraint fk_id_viaje_detalle foreign key (id_viaje) references viaje (id),
	constraint fk_id_cliente_detalle  foreign key (id_cliente) references cliente (id),
    constraint fk_usuario_vendedor foreign key(id_usuario) references usuario(id)
);

