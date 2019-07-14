INSERT INTO musicsite.users (id, email, first_name, password, username) VALUES (1, 'blabla@gmail.com', 'Tomasz', '$2a$10$crECq/A.Cdlvb5CTsJAIJ.Pjmxi2xsYBtSFaakYIm2EJtwVzy0kCm', 'Angel');
INSERT INTO musicsite.users (id, email, first_name, password, username) VALUES (2, 'pss@gmail.com', 'Mr. Hide', '$2a$10$1gb3IF3813JSbZ2X7XERGOVPTYAmIvdOP1MdCs5xVG8DLKEB41uby', 'Dr. Jekyll');
INSERT INTO musicsite.performers (id, first_name, image_link, last_name, pseudonym) VALUES (1, 'Ryszard', null, 'Andrzejewski', 'Peja');
INSERT INTO musicsite.albums (id, average, image_link, name, year_of_publication, performers_id) VALUES (1, 0, null, 'Szacunek ludzi ulicy', '2006', null);
INSERT INTO musicsite.albums (id, average, image_link, name, year_of_publication, performers_id) VALUES (2, 0, null, 'Najlepszą Obroną Jest Atak', '2005', null);
INSERT INTO musicsite.albums_performers (Album_id, performers_id) VALUES (1, 1);
INSERT INTO musicsite.albums_performers (Album_id, performers_id) VALUES (2, 1);