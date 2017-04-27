insert into establishment (establishment_id, name, description) values (1, 'Lochside House', 'Lochside House Hotel description')

insert into room_category (room_category_id, room_category, price) values (1, 'SINGLE', 100)
insert into room_category (room_category_id, room_category, price) values (2, 'DOUBLE', 200)

insert into room (room_id, name, description, establishment_id, room_category_id) values (1, 'Single Room 1', 'This is single room 1', 1, 1)
insert into room (room_id, name, description, establishment_id, room_category_id) values (2, 'Single Room 2', 'This is single room 2', 1, 1)
insert into room (room_id, name, description, establishment_id, room_category_id) values (3, 'Double Room 1', 'This is double room 1', 1, 2)
insert into room (room_id, name, description, establishment_id, room_category_id) values (4, 'Double Room 2', 'This is double room 2', 1, 2)
insert into room (room_id, name, description, establishment_id, room_category_id) values (5, 'Double Room 3', 'This is double room 3', 1, 2)

insert into customer (customer_id, first_name, last_name) values (1, 'firstname1', 'surname1')
insert into customer (customer_id, first_name, last_name) values (2, 'firstname2', 'surname2')
insert into customer (customer_id, first_name, last_name) values (3, 'firstname3', 'surname3')
