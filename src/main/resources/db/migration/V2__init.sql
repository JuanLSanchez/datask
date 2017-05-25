-- Users
-- password in BCrypt. '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u' = 'password'
insert into user_api (activated, login, surname, creation_moment, password_hash) values 
	(1, 'admin', 'surname admin', null, '$2a$10$xvWAQrz1kyZ2YpCasVZZ1OjXp/SwaTZOytf7cjy7AEm8qQsKI6e.i'),
	(1, 'manage', 'surname manage', null, '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u'),
	(1, 'user001', 'surname user001', null, '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u'),
	(1, 'user002', 'surname user002', null, '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u'),
	(0, 'user003', 'surname user003', null, '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u'),
	(1, 'user004', 'surname user004', null, '$2a$10$WfbrKlSCG3RCCBI09D3/JuMeLkrfnntIkv8LiMEXG6qhZpUrZvI9u');
	
---- Authority
insert into authority (name) values 
	('ADMIN'), ('USER'), ('MANAGE');

---- User - Authority
insert into user_authority (user_id, authority_name) values 
	(1, 'ADMIN'), (2, 'MANAGE'), (3, 'USER'), (4, 'USER'), (5, 'USER'), (6, 'USER');