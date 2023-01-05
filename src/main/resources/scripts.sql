alter table student
    add constraint age_constraint check (age > 16);
alter table student
    add constraint name_constraint unique (name);
alter table student
    alter column name set not null;
alter table student
    alter column age set default age = 20;

alter table faculty
    add constraint name_constraint unique (name);
alter table faculty
    add constraint color_constraint unique (color);

select student.name, age, faculty_id
from student
         inner join faculty f on student.faculty_id = f.id;

