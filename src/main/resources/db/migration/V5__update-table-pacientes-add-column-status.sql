alter table pacientes add status tinyint;
update pacientes set status = 1;