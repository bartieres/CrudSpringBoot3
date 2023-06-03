
alter table pacientes add column nome varchar(100);

alter table pacientes add ativo tinyint;
update pacientes set ativo = 1;