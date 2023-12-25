create table movie (
    id bigserial primary key,
    name text not null,
    release_date date not null,
    unique (name)
);