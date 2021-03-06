-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

insert into users (username, password, first_name, last_name, phone_number, email, enabled) values ('bojan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojan', 'Skokic', '0601231234', 'bojan99skokic@gmail.com', TRUE);
insert into users (username, password, first_name, last_name, phone_number, email, enabled) value ('zelimir', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zelimir', 'Zulov', '0601231235', 'zelimirzulov@gmail.com', TRUE);

insert into role (name) values ('ROLE_CLIENT');
insert into role (name) values ('ROLE_TOURGUIDE');
insert into role (name) values ('ROLE_ADMIN');

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (2, 2);

insert into address(user_id, country, city, street, number)	values (1, 'Srbija', 'Uzice', 'Svetozara Markovica', '59');
insert into address(user_id, country, city, street, number)	values (2, 'Srbija', 'Novi Sad', 'Papa Pavla', '41');

insert into client(user_id) values (1);

insert into tour_guide(user_id) values (2);

insert into location(name, description)	values ('Đavolja Varoš', 'Đavolja Varoš je nesvakidašnji prizor zemljanih kupa, koje su po narodnom predanju okamenjeni svatovi koji su slušajući đavola pošli da venčaju brata i sestru. Pored reljefa, Đavolju Varoš čine i dva izvora kisele vode.');
insert into location(name, description)	values ('Babin Zub', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik');
insert into location(name, description)	values ('Zlatar', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik');
insert into location(name, description)	values ('Visoka Planina', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik');

insert into vehicle(name, max_number_of_persons) values ('Golf 2 GTI', 25);

insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-06-21', 20, 100, 1, 1, 1);
insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-05-19', 25, 120, 1, 1, 1);
insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-05-15', 25, 110, 1, 1, 1);
insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-05-16', 25, 90, 2, 1, 1);
insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-05-17', 25, 90, 3, 1, 1);
insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-07-25', 30, 90, 4, 1, 1);

insert into reservation(client_id, excursion_id, number_of_persons, cancelled) values (1, 1, 5, FALSE);

insert into past_excursion(client_id, excursion_id, number_of_persons) values (1, 2, 4);
insert into past_excursion(client_id, excursion_id, number_of_persons) values (1, 3, 5);
insert into past_excursion(client_id, excursion_id, number_of_persons) values (1, 4, 4);
insert into past_excursion(client_id, excursion_id, number_of_persons) values (1, 5, 2);

insert into comment(rate, text, past_excursion_client_id, past_excursion_excursion_id) values (4, 'Bilo je lepo. Drusvo i ja smo bili zadovoljni provodom. Zaleli bi nekad ovako nesto ponovo da uradimo.', 1, 2);

insert into liked_commnets(client_id, comment_id) values (1, 1);