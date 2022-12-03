select * from teachers;
select * from teachers where id = ?;
select * from teachers t join courses c on t.id = c.teacher_id where t.id = ?;
insert into teachers(fio) values (?);
delete from teachers where id = ?