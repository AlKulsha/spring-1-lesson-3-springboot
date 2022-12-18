create table if not exists products (id bigserial primary key, title varchar(255), price int, secret_key varchar(255));
insert into products (title, price, secret_key) values ('book', 5600, 'kjhsuio'), ('pencil', 150, 'kjhsuio'), ('cup', 800, 'kjhsuio'), ('bag', 1000, 'kjhsuio'), ('magazine', 250, 'kjhsuio'), ('mirror', 1300, 'kjhsuio'), ('pillow', 1990, 'kjhsuio'), ('toy', 300, 'kjhsuio'), ('lamp', 295, 'kjhsuio'), ('cream', 1560, 'kjhsuio');

create table users (
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles (
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);