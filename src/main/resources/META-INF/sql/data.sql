

-- =============== USERS
INSERT INTO musicsite.users (id, admin, confirmed, email, first_name, password, username) VALUES (1, true, true, 'pss@gmail.com', 'Mr. Hide', '$2a$10$1gb3IF3813JSbZ2X7XERGOVPTYAmIvdOP1MdCs5xVG8DLKEB41uby', 'Dr. Jekyll');
INSERT INTO musicsite.users (id, admin, confirmed, email, first_name, password, username) VALUES (2, false, true, 'blabla@gmail.com', 'Roman', '$2a$10$crECq/A.Cdlvb5CTsJAIJ.Pjmxi2xsYBtSFaakYIm2EJtwVzy0kCm', 'Angel');
INSERT INTO musicsite.users (id, admin, confirmed, email, first_name, password, username) VALUES (3, false, true, 'barbara@rabarbar', 'Barbara', '$2a$10$xp2XCf2OIQjcuf02KjbnrupLCrfzAyfE4OAEZN1P0xagIsy4uUIwm', 'Rabarbar');
INSERT INTO musicsite.users (id, admin, confirmed, email, first_name, password, username) VALUES (5, false, true, 'aniolowski93@gmail.com', 'Tomasz', '$2a$10$tFPVOJz9Ev/fDcuhbr7xBuBWxKMd2xJ/ESKjoMAldCRRGbebY6f2e', 'Filozof');


-- =============== PERFORMERS
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (1, 3.6666666666666665, 'Ryszard', 'Andrzejewski', false, 'Peja');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (2, 0, 'Bob', 'Marley', false, 'Bob Marley & The Wailers');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (3, 0, 'Earl', 'Simmons', false, 'DMX');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (4, 0, null, null, false, 'A Tribe Called Quest');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (5, 0, 'Piotr', 'Kowalczyk', false, 'Tau');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (6, 0, 'Lauryn', 'Hill', false, 'Lauryn Hill');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (7, 0, 'Adam', 'Zieliński', false, 'Łona');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (8, 0, 'Riley Ben', 'King', false, 'B.B. King');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (9, 0, 'Eric', 'Clapton', false, 'Eric Clapton');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (10, 0, 'Erykah', 'Badu', false, 'Erykah Badu');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (11, 0, null, null, false, 'Esbjorn Svensson Trio');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (12, 0, 'Isaac', 'Hayes', false, 'Isaac Hayes');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (13, 0, 'Jacek', 'Kaczmarski', false, 'Jacek Kaczmarski');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (14, 0, 'Przemysław', 'Gintrowski', false, 'Przemysław Gintrowski');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (15, 0, 'Zbigniew', 'Łapiński', false, 'Zbigniew Łapiński');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (16, 0, null, null, false, 'Led Zeppelin');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (17, 0, 'Miles', 'Davis', false, 'Miles Davis');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (18, 0, 'Peter', 'Tosh', false, 'Peter Tosh');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (19, 0, null, null, false, 'Pink Floyd');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (20, 0, null, null, false, 'The Doors');
INSERT INTO musicsite.performers (id, average, first_name, last_name, proposition, pseudonym) VALUES (21, 0, null, null, false, 'The Rolling Stones');


-- =============== ALBUMS
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (1, 4, 'Szacunek ludzi ulicy', false, '2006', 1);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (2, 0, 'Najlepszą obroną jest atak', false, '2005', 1);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (3, 0, 'Kaya', false, '1978', 2);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (4, 0, 'Flesh of my flesh blood of my blood', false, '1998', 3);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (5, 0, 'Reedukacja', false, '2011', 1);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (6, 0, 'Midnight marauders', false, '1993', 4);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (7, 0, 'The Miseducation of Lauryn Hill', false, '1998', 6);
INSERT INTO musicsite.albums (id, average, name, proposition, year_of_publication, performer_id) VALUES (8, 0, 'Insert EP', false, '2008', 7);



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
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (7, 1);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (7, 6);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (7, 7);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (8, 1);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (5, 1);
INSERT INTO musicsite.albums_categories (albums_id, categories_id) VALUES (6, 1);




-- =============== RATINGS
INSERT INTO musicsite.ratings (id, rating, album_id, performer_id, track_id, user_id) VALUES (1, 4, null, 1, null, 2);
INSERT INTO musicsite.ratings (id, rating, album_id, performer_id, track_id, user_id) VALUES (2, 3, null, 1, null, 3);
INSERT INTO musicsite.ratings (id, rating, album_id, performer_id, track_id, user_id) VALUES (5, 4, null, 1, null, 1);
INSERT INTO musicsite.ratings (id, rating, album_id, performer_id, track_id, user_id) VALUES (6, 4, 1, null, null, 1);



-- =============== TRACKS
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (1, 0, 'We can get down', false, '1993', 6, 1, 4, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (3, 0, 'Insert', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (4, 0, 'Bumbox', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (5, 0, 'Świat jest pełen filozofów', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (6, 0, 'Co to będzie?', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (7, 0, 'Nic tu po nas', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (8, 0, 'Nie zostało nic', false, '2008', 8, 1, 7, null);
INSERT INTO musicsite.tracks (id, average, name, proposition, year_of_publication, album_id, category_id, performer_id, ordering_num) VALUES (9, 0, 'Traper', false, '2013', null, 1, 5, null);


-- =============== COMMENTS
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (8, 'asdsad', null, null, 1, 1);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (10, '1', null, 1, null, 1);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (12, '3', null, 1, null, 1);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (13, '4', null, 1, null, 1);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (14, '11', null, 1, null, 3);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (15, 'zxcsac', null, 1, null, 3);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (16, 'asdsad', 1, null, null, 3);
INSERT INTO musicsite.comments (id, content, album_id, performer_id, track_id, user_id) VALUES (17, '1', 1, null, null, 3);
