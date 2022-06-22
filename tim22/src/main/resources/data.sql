
INSERT INTO public.role(id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role(id, name)
VALUES (2, 'ROLE_CLIENT');
INSERT INTO public.role(id, name)
VALUES (3, 'ROLE_VACATION_OWNER');
INSERT INTO public.role(id, name)
VALUES (4, 'ROLE_SHIP_OWNER');
INSERT INTO public.role(id, name)
VALUES (5, 'ROLE_INSTRUCTOR');

INSERT INTO public.address(city, country, street_name, street_number)
VALUES ('Novi Sad', 'Serbia ', 'Alekse Santica', 4);

INSERT INTO public.address(city, country, street_name, street_number)
VALUES ('Jagodina', 'Serbia', 'Kralja Aleksandra', 27);

INSERT INTO public.address(city, country, street_name, street_number)
VALUES ('Planina', 'Bosnia', 'Na vrh brda', 13);

INSERT INTO public.address(city, country, street_name, street_number)
VALUES ('Amsterdam', 'Netherlands', 'Robina van Persija', 18);


INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ( 'Petra Kocica 38, Jagodina', false, true, null, 94, 'Stefan','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '066240610', 'Milosevic', 'stefan.milosevic.e14@gmail.com');


INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ( 'Ulica retarda 1, Zrenjanin', false, true, null, 0, 'Vanja','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '066222333', 'Serfeze', 'serfezev@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ( 'Veliki grad, Novi Sad', false, true, null, 0, 'Aleksa','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '066222111', 'Stevanovic', 'stevaszumza@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ( 'Foca', false, true, null, 0, 'Milica','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '066230222', 'Skipina', 'skipina@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ( 'Marka Kraljevica 12, Sremska Mitroica', false, true, null, 0, 'Bubi','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '0661332324', 'Bubisa', 'bubisa@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ('Petra Kocica 8a, Kraljevo', false, true, null, 20, 'Srdjan','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '066240610', 'Milosevic', 'djidja.milosevic@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ('Vujice Vujanova 12, Melenci', false, true, null, 5, 'Nikoleta','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '0662454298', 'Milivojev', 'ketiketi@gmail.com');

INSERT INTO public.user_table(address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ('Vojvodjanskih Brigata 52, Pancevo', false, true, null, 0, 'Anastasia','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '0622234567', 'Indjic', 'indija4@gmail.com');

INSERT INTO public.user_table( address, is_deleted, is_enabled, last_password_reset_date, loyalty_points, name, password, phone_number, surname, username)
VALUES ('Cara Lazara 66, Paracin', false, true, null, 0, 'Milos','$2a$10$cc2TaFZx.pg3N8q3qNGhee252A/1YKth3KwywXrrGhRMLdD0baknC' , '0642280890', 'Petrovic', 'perapera@gmail.com');


INSERT INTO public.user_role(user_id, role_id)
VALUES (1, 2);
INSERT INTO public.user_role(user_id, role_id)
VALUES (2,3);
INSERT INTO public.user_role(user_id, role_id)
VALUES (3,4);
INSERT INTO public.user_role(user_id, role_id)
VALUES (4,5);
INSERT INTO public.user_role(user_id, role_id)
VALUES (5, 1);
INSERT INTO public.user_role(user_id, role_id)
VALUES (6, 3);
INSERT INTO public.user_role(user_id, role_id)
VALUES (7, 4);
INSERT INTO public.user_role(user_id, role_id)
VALUES (8, 5);
INSERT INTO public.user_role(user_id, role_id)
VALUES (9, 2);


INSERT INTO public.adventure(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (1, 3,20,2,'TOOOOOOP!!!! Adrenalinska avantura u kojoj ćete iskusiti skakanje sa padobranom, planinarenje i svašta nešto. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','ADVENTURE',false, 'Top avantura', 150,'Zabranjeno pušenje! Zabranjeni kućni ljubimci!', 1,4);

INSERT INTO public.vessel(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id, engine_number, engine_power, max_speed, vessel_type)
VALUES (2, 3,20,2,'TOOOOOOP!!!!','VESSEL',false, 'Jahta', 120,'None', 3,3, 1, 150, 50, 'YACHT');

INSERT INTO public.adventure(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (3, 4,20,2,'TOOOOOOP!!!!','ADVENTURE',false, 'Avantura', 120,'None', 3,4);

INSERT INTO public.vessel(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id, engine_number, engine_power, max_speed, vessel_type)
VALUES (4, 1,20,2,'TOOOOOOP!!!!','VESSEL',false, 'Brodic', 100,'None', 3,3, 1, 150, 50, 'YACHT');

INSERT INTO public.vessel(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id, engine_number, engine_power, max_speed, vessel_type)
VALUES (5, 5,20,2,'TOOOOOOP!!!!','VESSEL',false, 'Brod', 20,'None', 2,3, 1, 150, 50, 'YACHT');

INSERT INTO public.vacation(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (6, 4, 20 , 3,'Bad Place to stay','VACATION',false,'Fina kuca', 300, 'None', 2,2);

INSERT INTO public.vacation(id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (7, 3, 20, 4, 'Great Place', 'VACATION', false, 'Drvena kuca', 500, 'None', 1, 2);






INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (8, 3, 10, 4, 'Student excursion with lots of fun and beautiful scenery', 'ADVENTURE', false, 'Summer Fun', 120, 'None', 2, 4);
INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (9, 2, 20, 7,'for people who like risky and inaccessible landscapes', 'ADVENTURE', false, 'Unforgettable hiking in Bosnia', 200, 'None', 3, 4);
INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (10, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Tara River', 230, 'None', 3, 4);
INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (11, 5, 15, 10,'classes for beginners and children on the Danube' , 'ADVENTURE', false, 'Fishing lessons for beginners in Novi Sad', 135, 'beginners must listen to their instructor and be kind to other students', 1, 4);
INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (12, 4, 10, 8, 'Great adventure in nature very cheap and fun', 'ADVENTURE', false, 'Fun Trip', 80, 'None', 4, 4);


INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (13, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Sutjeska River', 230, 'None', 3, 4);

INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (14, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Belica River', 230, 'None', 3, 4);

INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (15, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Geralt of Rivia', 230, 'None', 3, 4);

INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (16, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Belica River', 230, 'None', 3, 4);

INSERT INTO public.adventure(
    id, average_score, cancellation_fee, capacity, description, entity_type, is_deleted, name, price, rules_of_conduct, addres_id, owner_id)
VALUES (17, 4, 20, 7, 'rafting on the Tara River full of adrenaline and fun', 'ADVENTURE', false, 'Rafting on the Geralt of Rivia', 230, 'None', 3, 4);








INSERT INTO public.photos(entity_id, photos)
VALUES (1, '1.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (1, '2.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (1, '3.jpg');

INSERT INTO public.photos(entity_id, photos)
VALUES (2, '4.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (3, '5.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (4, '6.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (5, '7.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (6, '8.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (7, '9.jpg');
INSERT INTO public.photos(entity_id, photos)
VALUES (8, '10.jpg');

INSERT INTO public.photos(entity_id, photos)
VALUES (9, '11.jpg');


INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-02-20T15:00', '2022-08-23T13:00', 1);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-03-20T15:00', '2023-04-12T13:00', 2);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-04-20T15:00', '2023-04-15T13:00', 3);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-05-20T15:00', '2023-05-11T13:00', 4);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-07-20T15:00', '2023-07-14T13:00', 5);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-05-20T15:00', '2023-11-27T13:00', 6);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-03-20T15:00', '2023-02-24T13:00', 7);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-04-20T15:00', '2023-04-22T13:00', 8);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-04-20T15:00', '2023-05-21T13:00', 9);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-04-20T15:00', '2023-09-21T13:00', 10);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-04-15T15:00', '2023-12-25T13:00', 11);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-03-16T15:00', '2023-05-26T13:00', 12);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-02-13T15:00', '2023-01-11T13:00', 13);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-01-21T15:00', '2023-08-16T13:00', 14);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-01-22T15:00', '2023-07-14T13:00', 15);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-06-22T15:00', '2023-06-20T13:00', 16);
INSERT INTO public.availability_period(
    date_from, date_to, system_entity)
VALUES ('2022-05-21T15:00', '2023-05-23T13:00', 17);


INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-07-20T16:00', '2022-07-22T15:30', true, 1, 6, false,100,75);
INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-07-25T15:00', '2022-07-28T15:00', true, 1, 7, false,100,75);
INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-04-25T15:00', '2022-04-28T15:30', true, 1, 7, false,100,75);
INSERT INTO public.reservation(date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-04-20T15:00', '2022-04-23T13:00', true, 1, 6, false,100,75);
INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-03-15T15:00', '2022-03-20T15:00', true, 1, 4, false,100,75);
INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-03-22T15:00', '2022-03-23T15:00', true, 1, 2, false,100,75);
INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2021-09-26T15:00', '2021-10-01T15:00', true, 1, 1, false,100,75);


INSERT INTO public.reservation( date_from, date_to, is_approved, client_id, system_entity_id, is_canceled, client_price, owner_price)
VALUES ( '2022-06-19T15:00', '2022-07-01T15:00', true, 1, 1, false,100,75);




INSERT INTO public.promo(
    capacity, date_from, date_to, description, price, system_entity_id,is_taken)
VALUES (2, '2022-07-15T13:00', '2022-07-25T12:00', 'Prelepa avantura po snizenoj ceni', 100, 1, false);

INSERT INTO public.promo(
    capacity, date_from, date_to, description, price, system_entity_id, is_taken)
VALUES (2, '2022-07-25T13:00', '2022-08-04T12:00', 'Prelepa avantura po snizenoj ceni', 100, 1, false);


INSERT INTO public.registration_request(
     description, is_answered, client_id)
VALUES ('Veoma sam posvecen i voleo bih da dobijem posao', false, 6);

INSERT INTO public.registration_request(

     description, is_answered, client_id)
VALUES ( 'Zelim da zaradim paree', false, 7);

INSERT INTO public.registration_request(
     description, is_answered, client_id)
VALUES ( 'Please give me a chance to prove i will pay', false, 8);

INSERT INTO public.loyalty_program(
    id, gold_discount, gold_limit, platinum_discount, platinum_limit, points_for_business, points_per_reservation, silver_discount, silver_limit)
VALUES (1, 10, 60, 15, 100, 3, 6, 5, 30);

INSERT INTO public.account_cancellation_request(
     is_answered, text, user_id)
VALUES ( false, 'Molim vas muka mi je od ove aplikacije hocu da je obrisem', 2);

INSERT INTO public.account_cancellation_request(
   is_answered, text, user_id)
VALUES ( false, 'Molim vas muka mi je od ove aplikacije hocu da je obrisem', 4);
INSERT INTO public.account_cancellation_request(
     is_answered, text, user_id)
VALUES ( false, 'Molim vas muka mi je od ove aplikacije hocu da je obrisem', 3);


INSERT INTO public.reservation_report(
     automatic_penalty, is_answered, text, client_id, owner_id, reservation)
VALUES ( false, false, 'Decko je haos glup i ucistio mi je celu vikendicu zasluzuje kaznu', 1, 2, 7);

INSERT INTO public.reservation_report(
    automatic_penalty, is_answered, text, client_id, owner_id, reservation)
VALUES ( false, false, 'Decko je haos glup i ucistio mi je celu vikendicu zasluzuje kaznu', 7, 2, 7);

INSERT INTO public.reservation_report(
     automatic_penalty, is_answered, text, client_id, owner_id, reservation)
VALUES ( false, false, 'Decko je haos glup i ucistio mi je celu vikendicu zasluzuje kaznu', 8, 2, 7);