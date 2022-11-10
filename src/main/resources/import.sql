-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

insert into users (username, password, first_name, last_name, phone_number, email, enabled) values ('bojan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bojan', 'Skokic', '0601231234', 'bskokic@outlook.com', TRUE);
insert into users (username, password, first_name, last_name, phone_number, email, enabled) values ('petar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrović', '0601231235', 'bskokic@outlook.com', TRUE);
insert into users (username, password, first_name, last_name, phone_number, email, enabled) values ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zdravko', 'Zdravkovic', '0601231235', 'bskokic@outlook.com', TRUE)
insert into users (username, password, first_name, last_name, phone_number, email, enabled) values ('uros', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Uroš', 'Urošević', '0603214998', 'bskokic@outlook.com', TRUE)

insert into role (name) values ('ROLE_CLIENT');
insert into role (name) values ('ROLE_TOURGUIDE');
insert into role (name) values ('ROLE_ADMIN');

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (2, 2);
insert into user_role(user_id, role_id) values (3, 3);
insert into user_role(user_id, role_id) values (4, 2);


insert into address(user_id, country, city, street, number)	values (1, 'Srbija', 'Uzice', 'Svetozara Markovica', '59');
insert into address(user_id, country, city, street, number)	values (2, 'Srbija', 'Novi Sad', 'Papa Pavla', '41');

--insert into client(user_id) values (1);

insert into tour_guide(user_id, deleted) values (2, FALSE);
insert into tour_guide(user_id, deleted) values (4, FALSE);

insert into location(name, description, deleted, email, ticket_price, img_link)	values ('Đavolja Varoš', 'Đavolja Varoš je nesvakidašnji prizor zemljanih kupa, koje su po narodnom predanju okamenjeni svatovi koji su slušajući đavola pošli da venčaju brata i sestru. Pored reljefa, Đavolju Varoš čine i dva izvora kisele vode.', FALSE, 'bskokic@outlook.com', 1000, "https://fajlovi.bos4.tours/uploads/images/albums/uploads/2664.jpg");
insert into location(name, description, deleted, email, ticket_price, img_link)	values ('Zlatibor', 'Zlatibor je planina izuzetne lepote čija su posebna geografska svojstva učinila da ova planina bude pravi dragulj zapadne Srbije.', FALSE, 'bskokic@outlook.com', 0, "https://www.montesol.rs/images/galerija/zlatibor/Zlatibor8.jpg");
insert into location(name, description, deleted, email, ticket_price, img_link)	values ('Babin Zub', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik.', FALSE, 'bskokic@outlook.com', 500, "https://www.photohound.co/images/1024041l.jpg");
insert into location(name, description, deleted, email, ticket_price, img_link)	values ('Zlatar', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik.', FALSE, 'bskokic@outlook.com', 0, "https://fajlovi.bos4.tours/uploads/2021/08/images/tour_869/img-2cde6a6bd740e2976bc9c18bcf978603-v-1.jpg");
insert into location(name, description, deleted, email, ticket_price, img_link)	values ('Visoka Planina', 'Napredni snowboarder i skijaš početnik – naš tim za zimske sportove je prilično raznolik.', FALSE, 'bskokic@outlook.com', 350, "https://planinskibozur.com/wp-content/uploads/2022/03/1-1.jpg");


insert into vehicle(name, max_number_of_persons, deleted, price_per_day) values ('Sprinter minibus', 20, false, 15000);
insert into vehicle(name, max_number_of_persons, deleted, price_per_day) values ('Fiat ducato kombi', 10, false, 8000);
insert into vehicle(name, max_number_of_persons, deleted, price_per_day) values ('Vanhool Alicron bus', 55, false, 30000);
insert into vehicle(name, max_number_of_persons, deleted, price_per_day) values ('Neoplan 122 bus', 55, false, 34000);

--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-21', 20, 100, 1, 1, 1);
--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-19', 25, 120, 1, 1, 1);
--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-15', 25, 110, 1, 1, 1);
--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-16', 25, 90, 2, 1, 1);
--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-17', 25, 90, 3, 1, 1);
--insert into excursion(cancelled, date, max_number_of_persons, price, location_id, tour_guide_id, vehicle_id) values (FALSE, '2022-11-25', 30, 90, 4, 1, 1);

insert into restaurant(address, email, name, phone_number) values ("Novosadsko sajma 15", "bskokic@outlook.com", "Restoran Ognjište", "021-123123");
insert into restaurant(address, email, name, phone_number) values ("Bulevar Evrope 157", "bskokic@outlook.com", "Restoran Evropa", "021-000123");
insert into restaurant(address, email, name, phone_number) values ("Papa Pavla", "bskokic@outlook.com", "Restoran Ciao", "021-000999");

insert into meal(description, name, price, restaurant_id) values ("sastojci jela1", "Gulaš i paprikaš", 700, 1);
insert into meal(description, name, price, restaurant_id) values ("sastojci jela2", "Petrovački ručak", 1500, 1);
insert into meal(description, name, price, restaurant_id) values ("sastojci jela3", "Zlatiborski obrok", 2000, 1);
insert into meal(description, name, price, restaurant_id) values ("sastojci jela4", "Gulaš i paprikaš", 1000, 2);
insert into meal(description, name, price, restaurant_id) values ("sastojci jela5", "Ciao ručak", 1500, 3);
insert into meal(description, name, price, restaurant_id) values ("sastojci jela6", "Zlatiborski obrok", 2200, 3);


insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-11-21', 80, 4000, 1, TRUE, 1, 40);
insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-11-19', 25, 5000, 1, TRUE, 2, 20);
insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-11-15', 25, 3600, 1, TRUE, 3, 20);
insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-11-16', 25, 4000, 1, TRUE, 4, 20);
insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-11-17', 25, 2500, 1, TRUE, 5, 20);
insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id, reservated_tickets_num) values (FALSE, '2022-9-21', 30, 6000, 1, TRUE, 6, 10);
--insert into excursion(cancelled, date, max_number_of_persons, price, tour_guide_id, approved, meal_id) values (FALSE, '2022-9-21', 80, 3000, 1, TRUE, 1);


insert into excursion_locations(excursion_id, location_id) values (1, 1);
insert into excursion_locations(excursion_id, location_id) values (1, 2);
insert into excursion_locations(excursion_id, location_id) values (1, 3);
insert into excursion_locations(excursion_id, location_id) values (2, 2);
insert into excursion_locations(excursion_id, location_id) values (2, 3);
insert into excursion_locations(excursion_id, location_id) values (2, 4);
insert into excursion_locations(excursion_id, location_id) values (3, 4);
insert into excursion_locations(excursion_id, location_id) values (3, 5);
insert into excursion_locations(excursion_id, location_id) values (4, 1);
insert into excursion_locations(excursion_id, location_id) values (4, 5);
insert into excursion_locations(excursion_id, location_id) values (5, 1);
insert into excursion_locations(excursion_id, location_id) values (6, 4);
--insert into excursion_locations(excursion_id, location_id) values (7, 1);
--insert into excursion_locations(excursion_id, location_id) values (7, 2);

insert into excursion_vehicles(excursion_id, vehicle_id) values (1, 1);
insert into excursion_vehicles(excursion_id, vehicle_id) values (1, 2);
insert into excursion_vehicles(excursion_id, vehicle_id) values (1, 3);
insert into excursion_vehicles(excursion_id, vehicle_id) values (2, 1);
insert into excursion_vehicles(excursion_id, vehicle_id) values (2, 2);
insert into excursion_vehicles(excursion_id, vehicle_id) values (3, 1);
insert into excursion_vehicles(excursion_id, vehicle_id) values (3, 2);
insert into excursion_vehicles(excursion_id, vehicle_id) values (4, 1);
insert into excursion_vehicles(excursion_id, vehicle_id) values (4, 2);
insert into excursion_vehicles(excursion_id, vehicle_id) values (5, 3);
insert into excursion_vehicles(excursion_id, vehicle_id) values (6, 4);


insert into reservation(cancelled, number_of_persons, client_id, excursion_id) values (FALSE, 4, 1, 1);
--insert into reservation(cancelled, number_of_persons, client_id, excursion_id) values (FALSE, 5, 1, 2);
--insert into reservation(cancelled, number_of_persons, client_id, excursion_id) values (FALSE, 1, 1, 3);
insert into reservation(cancelled, number_of_persons, client_id, excursion_id) values (FALSE, 4, 1, 6);


--insert into comment(rate, text, past_excursion_client_id, past_excursion_excursion_id) values (4, 'Bilo je lepo. Drusvo i ja smo bili zadovoljni provodom. Zaleli bi nekad ovako nesto ponovo da uradimo.', 1, 2);
--
--insert into liked_commnets(client_id, comment_id) values (1, 1);