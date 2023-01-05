-- liquibase formatted sql

-- changeset tradzhabov:1
create index student_name_index on student (name);
create index faculty_color_index on faculty (color);