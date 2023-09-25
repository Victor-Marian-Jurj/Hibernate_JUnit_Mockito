create table persons(
        cnp varchar(13) primary key,
        last_name varchar(30) not null,
        first_name varchar(40) not null,
        adress varchar(100) not null,
        phone varchar(15) NOT NULL UNIQUE,
        email varchar(50) UNIQUE
);
 
INSERT INTO persons VALUES ('1111', 'Stefan', 'Marian', 'Apostoli', '0741189215', 'marian.stefan@gmail.com');
INSERT INTO persons VALUES ('2222', 'Cosmin', 'Marian', 'Piata Mare', '0747539215', 'mar.cos@gmail.com');
INSERT INTO persons VALUES ('3333', 'Vlad', 'Cosmin', 'Romania Mare', '0734587215', 'cosmin.cosmin@gmail.com');
INSERT INTO persons VALUES ('4444', 'Eusebiu', 'Vasile', 'Roma', '0700689215', 'eusebiu.vasile@gmail.com');


create table Products(
        bar_code varchar(10) primary key,
        cnp_buyer(13) not null,
        product_name varchar(50) not null,
        product_type varchar(40) not null,
        quatity varchar(15) not null,
        price varchar(100) not null,
        CONSTRAINT fk_product_buyer
        FOREIGN(cnp_buyer)
        REFERENCES persons(cnp)
);

INSERT INTO products VALUES ('0101', '1111', 'Beko 8kg 8000rpm', 'Electrocasnice', '1', '980');
INSERT INTO products VALUES ('0202', '2222', 'Masina de spalat vase Beko', 'Electrocasnice', '1', '1400');
INSERT INTO products VALUES ('0303', '3333', 'Samsung S23+', 'Electronice', '1', '4300');
INSERT INTO products VALUES ('0404', '4444', 'TV Samsung 4k 110cm', 'Electronice', '2', '2000');