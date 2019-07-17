

-- =============== USERS
INSERT INTO musicsite.users (id, email, first_name, password, username, admin) VALUES (1, 'pss@gmail.com', 'Mr. Hide', '$2a$10$1gb3IF3813JSbZ2X7XERGOVPTYAmIvdOP1MdCs5xVG8DLKEB41uby', 'Dr. Jekyll', 1);
INSERT INTO musicsite.users (id, email, first_name, password, username, admin) VALUES (2, 'blabla@gmail.com', 'Tomasz', '$2a$10$crECq/A.Cdlvb5CTsJAIJ.Pjmxi2xsYBtSFaakYIm2EJtwVzy0kCm', 'Angel', 0);

-- =============== PERFORMERS
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (1, 0, 'Ryszard', 'Andrzejewski', false, 'Peja');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (2, 0, 'Bob', 'Marley', false, 'Bob Marley & The Wailers');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (3, 0, 'Earl', 'Simmons', true, 'DMX');

-- =============== ALBUMS
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (1, 0, 'Szacunek ludzi ulicy', false, '2006', 1);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (2, 0, 'Najlepszą obroną jest tak', false, '2005', 1);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (3, 0, 'Kaya', false, '1978', 2);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (4, 0, 'Flesh of my flesh blood of my blood', true, '1998', 3);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (5, 0, 'Reedukacja', true, '2011', 1);

-- =============== TRACKS
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, performer_id, category_id) VALUES (1, 0, 'my niggas (skit)', true, '1998', 4, 3, 1);


-- =============== CATEGORIES
INSERT INTO musicsite.categories (id, name) VALUES (9, 'Blues‎');
INSERT INTO musicsite.categories (id, name) VALUES (15, 'Classical music');
INSERT INTO musicsite.categories (id, name) VALUES (10, 'Country');
INSERT INTO musicsite.categories (id, name) VALUES (17, 'Dance music');
INSERT INTO musicsite.categories (id, name) VALUES (11, 'Film music');
INSERT INTO musicsite.categories (id, name) VALUES (8, 'Funk');
INSERT INTO musicsite.categories (id, name) VALUES (12, 'Jazz');
INSERT INTO musicsite.categories (id, name) VALUES (18, 'Metal');
INSERT INTO musicsite.categories (id, name) VALUES (3, 'Pop');
INSERT INTO musicsite.categories (id, name) VALUES (1, 'Rap');
INSERT INTO musicsite.categories (id, name) VALUES (4, 'Reggae');
INSERT INTO musicsite.categories (id, name) VALUES (6, 'Rhythm and blues');
INSERT INTO musicsite.categories (id, name) VALUES (2, 'Rock');
INSERT INTO musicsite.categories (id, name) VALUES (7, 'Soul');
INSERT INTO musicsite.categories (id, name) VALUES (16, 'Sung poetry');
INSERT INTO musicsite.categories (id, name) VALUES (5, 'Trip-hop');


-- =============== ALBUMS CATEGORIES
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (1, 1);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (2, 1);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (3, 4);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (4, 1);



