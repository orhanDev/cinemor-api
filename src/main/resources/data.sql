-- Seed data for movies table
-- This file will be executed on startup if spring.sql.init.mode=always

INSERT INTO movies (id, title, original_title, genre, duration, director, movie_cast, description, year, country, release_date, fsk, poster_path, slider_path, ticket_path, is_coming_soon, created_at, updated_at)
VALUES 
(1, 'Dune', 'Dune', 'Sci-Fi, Action', '155 min', 'Denis Villeneuve', 'Timothée Chalamet, Rebecca Ferguson, Oscar Isaac', 'A noble family becomes embroiled in a war for control over the galaxy''s most valuable asset.', '2021', 'USA', '22.10.2021', 'FSK 12', '/images/movies/nowshowing/dune5.png', '/images/movies/nowshowing/dune5.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Civil War', 'Civil War', 'Action, Drama', '109 min', 'Alex Garland', 'Kirsten Dunst, Wagner Moura, Cailee Spaeny', 'A journey across a dystopian future America.', '2024', 'USA', '12.04.2024', 'FSK 16', '/images/movies/nowshowing/civilwar 3.png', '/images/movies/nowshowing/civilwar 3.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Furiosa', 'Furiosa: A Mad Max Saga', 'Action, Adventure', '148 min', 'George Miller', 'Anya Taylor-Joy, Chris Hemsworth, Tom Burke', 'The origin story of renegade warrior Furiosa.', '2024', 'Australia', '24.05.2024', 'FSK 16', '/images/movies/nowshowing/furiosa1.png', '/images/movies/nowshowing/furiosa1.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'IF', 'IF', 'Comedy, Family', '104 min', 'John Krasinski', 'Ryan Reynolds, Cailey Fleming, Steve Carell', 'A young girl who goes through a difficult experience begins to see everyone''s imaginary friends.', '2024', 'USA', '17.05.2024', 'FSK 6', '/images/movies/nowshowing/if2.png', '/images/movies/nowshowing/if2.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Kingdom of the Planet of the Apes', 'Kingdom of the Planet of the Apes', 'Sci-Fi, Action', '145 min', 'Wes Ball', 'Owen Teague, Freya Allan, Kevin Durand', 'Many years after the reign of Caesar, a young ape goes on a journey that will lead him to question everything.', '2024', 'USA', '10.05.2024', 'FSK 12', '/images/movies/nowshowing/kingdomplanetape4.png', '/images/movies/nowshowing/kingdomplanetape4.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'The Sheriff', 'The Sheriff', 'Action, Western', '120 min', 'Unknown', 'Unknown', 'A classic western tale.', '2024', 'USA', '01.06.2024', 'FSK 12', '/images/movies/nowshowing/sheriff6.png', '/images/movies/nowshowing/sheriff6.png', NULL, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Update sequence to continue from the highest ID
SELECT setval('movies_id_seq', (SELECT MAX(id) FROM movies));
