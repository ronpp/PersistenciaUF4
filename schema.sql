# Set Encoding to UFT8
ALTER DATABASE uf4_db CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;


# CREATE DATABASE SCHEMA
CREATE TABLE IF NOT EXISTS categories (
    category_id INT  AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(category_id)
);

CREATE TABLE IF NOT EXISTS authors (
    author_id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(1000),
    PRIMARY KEY(author_id)
);


CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    author_id INT ,
    category_id INT ,
    publication_date VARCHAR(30),
    edition VARCHAR(15) DEFAULT '1ra Edition',
    pages VARCHAR(20),
    quantity SMALLINT  DEFAULT 0, 
    price DECIMAL(5,2) ,
    PRIMARY KEY(book_id),
    FOREIGN KEY(author_id) REFERENCES authors(author_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(category_id) REFERENCES categories(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS customers (
    customer_id INT  AUTO_INCREMENT,
    name  VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    address VARCHAR(80)  NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY(customer_id)
);


CREATE TABLE IF NOT EXISTS librarians (
    librarian_id INT  AUTO_INCREMENT,
    name  VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    address VARCHAR(80),
    phone VARCHAR(15),
    email VARCHAR(30) UNIQUE,
    username VARCHAR(20) UNIQUE NOT NULL,
    passwd longblob NOT NULL, 
    PRIMARY KEY(librarian_id)
);



CREATE TABLE IF NOT EXISTS loans(
    loan_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    loan_end_date DATETIME NOT NULL,
    customer_id INT  NOT NULL,
    book_id INT  NOT NULL,
    librarian_id INT ,
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(book_id) REFERENCES books(book_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (customer_id, book_id)
);

# POPUPALE THE DATABASE

# Categories
INSERT INTO categories(name)VALUES ('Sistemas y Redes');
INSERT INTO categories(name)VALUES ('Desarrollo');
INSERT INTO categories(name)VALUES ('Ofimatica'); 
INSERT INTO categories(name)VALUES ('Base de Datos');

# Librarians(Users)
INSERT INTO librarians(name, lastname, email, username, passwd) VALUES('Jose', 'Pe??a', 'jose@libraryit.com','jose', aes_encrypt('1235678', 'key'));
INSERT INTO librarians(name, lastname, email, username, passwd) VALUES('Tania', 'Villa Lobos', 'tvillalobos@libraryit.com','tania', aes_encrypt('1235678', 'key'));
INSERT INTO librarians(name, lastname, email, username, passwd) VALUES('Josua', 'Aquiles', 'aquiles@libraryit.com','josua', aes_encrypt('1235678', 'key'));
INSERT INTO librarians(name, lastname, email, username, passwd) VALUES('Mariam', 'Lorenzo', 'mariam@libraryit.com','mariam', aes_encrypt('1235678', 'key'));

# Customers
INSERT INTO customers(name, lastname, address, phone, email) VALUES('Ronny A.', 'Pe??a Perez', 'Calle 1, 12, 70827', '111-111-111', 'ron@gmail.com');
INSERT INTO customers(name, lastname, address, phone, email) VALUES('Andreu', 'Pasalamar', 'Calle 3, 25, 80927', '333-333-333', 'andreu.pasalamar@gmail.com');
INSERT INTO customers(name, lastname, address, phone, email) VALUES('Montserrat', 'Garcia', 'Calle 74, 17, 90531', '555-555-555', 'mgarcia@gmail.com');

# Authors
INSERT INTO authors(name, description) VALUES ('Jerome Gabillaud', 'Ingeniero en TI para la industria y consultor, Jerome Gabillaud tambi??n es director educativo en un gran
		centro de formaci??n de TI. Especialista en sistemas de acceso a datos de Microsoft, ya es autor de numerosos
		libros sobre este tema, reconocidos por sus cualidades t??cnicas y educativas.');

INSERT INTO authors(name, description) VALUES ('Olivier Heurtel', 'Despu??s de m??s de ocho a??os en una empresa de servicios, donde sucesivamente desempe???? labores de desarrollador,
		jefe de proyectos y director de proyectos, Olivier Heurtel inici?? una actividad como consultor/formador independiente,
		especializado en bases de datos (Oracle), desarrollo web (PHP) y los sistemas de apoyo a las decisiones. Tambi??n es poseedor
		del certificado Oracle Certified Professional.');


INSERT INTO authors(name, description) VALUES ('Sylvain Hebuterne', 'Es Arquitecto Android. Especializado en la programaci??n orientada a objetos desde hace 15 a??os, dise??a aplicaciones Android
		a t??tulo personal y para diversas agencias de comunicaci??n, compa????as de ingenier??a inform??tica y start-up. Estos proyectos,
		muy diversos, le permiten explotar todo el potencial de la plataforma Android, e incluso las funcionalidades m??s avanzadas 
		propuestas por las ??ltimas versiones.');
INSERT INTO authors(name, description) VALUES ('Thierry Groussard', 'Analista y desarrollador durante m??s de 10 a??os, Thierry Groussard se orient?? m??s adelante a la formaci??n, en particular en el campo
		del desarrollo de software. Sus conocimientos avanzados de las necesidades de la empresa y sus cualidades pedag??gicas hacen que este
		libro est?? especialmente adecuado para el aprendizaje y puesta en pr??ctica del desarrollo de aplicaciones en Java.');
INSERT INTO authors(name, description) VALUES ('Philippe Banquet', 'Es ingeniero inform??tico independiente desde hace casi 20 a??os. Est?? especializado en programaci??n, en la administraci??n de sistemas y redes as?? como en la formaci??n; ense??a Linux y Unix a un p??blico de inform??ticos. Ha concebido y desarrollado aplicaciones en lenguaje C, C++ y Perl,
		y tambi??n en scripts shell. Combinando su experiencia concreta sobre el terreno con su pr??ctica de formador, usa un m??todo muy pedag??gico apoy??ndose
		en ejemplos funcionales para transmitir eficazmente sus competencias. Es autor de numerosas obras consagradas a Linux y a la programaci??n.');
INSERT INTO authors(name, description) VALUES ('Jose Dordoigne', 'Ingeniero Inform??tico, Jos?? DORDOIGNE es un t??cnico experto en numerosos temas (equipos de trabajo y servidores Windows, redes y servicios de red, Unix, HP-UX, AIX, Linux...)que actualmente trabaja en una gran compa????a de seguros francesa. Tiene numerosas certificaciones (varias veces Microsoft Certified Systems Engineer, pero tambi??n Red Hat Certified Engineer Linux). Su destreza pedag??gica y t??cnica se reconoce a trav??s de una experiencia de casi 10 a??os como formador, de consultor durante 12 a??os en una gran empresa de TI as?? como con la escritura de una decena de libros sobre los sistemas operativos Microsoft y las redes TCP/IP.');
INSERT INTO authors(name, description) VALUES ('Nicolas Bonnet', 'Nicolas BONNET es Consultor y Formador en sistemas operativos de Microsoft desde hace varios a??os y posee una experiencia de m??s de 10 a??os en la administraci??n de sistemas inform??ticos. Pose la certificaci??n MCT (Microsoft Certified Trainer), MCSA (Windows 7, 8, 10, 2008, 2012 y Office 365) y es un reconocido Microsoft MVP (Most Valuable Professional) Enterprise Mobility. Es miembro de la comunidad aos (http://aos.community).');


# Books
INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'SQL Server 2019',
    '978-2-409-02980-6',
    (SELECT author_id FROM authors WHERE name = 'Jerome Gabillaud'), 
    (SELECT category_id FROM categories WHERE name = 'Base de Datos'),
    'Abril 2021'
    '498',
    4,
    22,53);

INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'Oracle 12c',
    '978-2-7460-9667-7',
    (SELECT author_id FROM authors WHERE name = 'Jerome Gabillaud'), 
    (SELECT category_id FROM categories WHERE name = 'Base de Datos'),
    'Julio 2015'
    '551',
    7,
    19,76);

INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'SQL Server 2014 - SQL, Transact SQL',
    '978-2-7460-9552-6',
    (SELECT author_id FROM authors WHERE name = 'Jerome Gabillaud'), 
    (SELECT category_id FROM categories WHERE name = 'Base de Datos'),
    'Abril 2015'
    '490',
    10,
    19,76);

INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'PHP 8',
    '978-2-409-03309-4',
    (SELECT author_id FROM authors WHERE name = 'Olivier Heurtel'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Octubre 2021'
    '658',
     5,
     22,53);


INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price) VALUES(
    'PHP y MySQL',
    '978-2-409-02434-4',
    (SELECT author_id FROM authors WHERE name = 'Olivier Heurtel'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Febrero 2020'
    '4ta Edition',
    '794',
    4,
    19,76);


INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'Desarrolle una aplicacion Android',
    '978-2-409-01447-5',
    (SELECT author_id FROM authors WHERE name = 'Sylvain Hebuterne'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Junio 2018'
    '508',
    15,
    25,25);


 INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'JAVA 8',
    '978-2-7460-0934-8',
    (SELECT author_id FROM authors WHERE name = 'Thierry Groussard'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Diciembre 2014'
    '500',
     25,
     19,76);


 INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'Visual Basic 2012',
    '978-2-7460-0821-3',
    (SELECT author_id FROM authors WHERE name = 'Thierry Groussard'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Junio 2013'
    '580',
     3,
    17,92);

 INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'C# 5',
    '978-2-7460-0804-9',
    (SELECT author_id FROM authors WHERE name = 'Thierry Groussard'), 
    (SELECT category_id FROM categories WHERE name = 'Desarrollo'),
    'Marzo 2013'
    '639',
     3,
    17,92);

 INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price) VALUES(
    'Preparaci??n para la certificaci??n LPIC-2 (ex??menes LPI 201 y LPI 202)',
    '978-2-409-03388-9',
    (SELECT author_id FROM authors WHERE name = 'Philippe Banquet'), 
    (SELECT category_id FROM categories WHERE name = 'Sistemas y Redes'),
    'Febrero 2022'
    '4ta Edicion',
    '899',
     20,
     36,75);


 INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price) VALUES(
    'Linux: Administraci??n del sistema y explotaci??n de los servicios de red',
    '978-2-409-03536-4',
    (SELECT author_id FROM authors WHERE name = 'Philippe Banquet'), 
    (SELECT category_id FROM categories WHERE name = 'Sistemas y Redes'),
    'Marzo 2022'
    '4ta Edicion',
    '796',
     20,
     25,25);


 INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price) VALUES(
    'Las redes: Administre una red en Windows o Linux: Ejercicios y soluciones',
    '978-2-409-02921-9',
    (SELECT author_id FROM authors WHERE name = 'Jose Dordoigne'), 
    (SELECT category_id FROM categories WHERE name = 'Sistemas y Redes'),
    'Enero 2021'
    '3ra Edicion',
    '638',
     10,
    17,15);




 INSERT INTO books(title, isbn, author_id, category_id, publication_date, edition, pages, quantity, price) VALUES(
    'Windows Server 2016',
    '978-2-409-01847-3',
    (SELECT author_id FROM authors WHERE name = 'Nicolas Bonnet'), 
    (SELECT category_id FROM categories WHERE name = 'Sistemas y Redes'),
    'Febrero 2019'
    '2da Edicion',
    '796 ',
    15,
    25,31);


 INSERT INTO books(title, isbn, author_id, category_id, publication_date, pages, quantity, price) VALUES(
    'Windows Server 2016 - Instalaci??n, gesti??n del almacenamiento y computaci??n(Examen 70-740)',
    '978-2-409-01217-4',
    (SELECT author_id FROM authors WHERE name = 'Nicolas Bonnet'), 
    (SELECT category_id FROM categories WHERE name = 'Sistemas y Redes'),
    'Diciembre 2017'
    '500',
     4,
    36,75);


    #Loans

    INSERT INTO loans(loan_end_date, customer_id, book_id) VALUES(
        (now() + interval 14 DAY),
        (SELECT customer_id FROM customers WHERE name = 'Andreu'),
        (SELECT book_id FROM books WHERE title = 'Preparaci??n para la certificaci??n LPIC-2 (ex??menes LPI 201 y LPI 202)')
    );


    INSERT INTO loans(loan_end_date, customer_id, book_id) VALUES(
        (now() + interval 14 DAY),
        (SELECT customer_id FROM customers WHERE name = 'Ronny A.'),
        (SELECT book_id FROM books WHERE title = 'JAVA 8')
    );
    
    INSERT INTO loans(loan_end_date, customer_id, book_id) VALUES(
        (now() + interval 14 DAY),
        (SELECT customer_id FROM customers WHERE name = 'Montserrat'),
        (SELECT book_id FROM books WHERE title = 'C# 5')
    );

