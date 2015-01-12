--********************* SimpleEntity **************************************
insert into SimpleEntity(id, name) values(9999, 'simpleEntity1');
insert into SimpleEntity(name) values('simpleEntity2');
insert into SimpleEntity(name, someValue) values('test', 'someValue');

insert into SimpleEntity(name, someValue, numValue) values('group', 'A', 1);
insert into SimpleEntity(name, someValue, numValue) values('group', 'B', 2);
insert into SimpleEntity(name, someValue, numValue) values('group', 'C', 3);
insert into SimpleEntity(name, someValue, numValue) values('group', 'A', 4);
insert into SimpleEntity(name, someValue, numValue) values('group', 'D', 1);
insert into SimpleEntity(name, someValue, numValue) values('group', 'C', 2);

-- ******************* Office *********************************************
insert into office(id, name) values(1, 'Cracow');
insert into office(id, name) values(2, 'Luton');
insert into office(id, name) values(3, 'Mumbai');

-- ******************* Department *****************************************
insert into department(name, officeId) values('Developement', 1);
insert into department(name, officeId) values('QA', 1);
insert into department(name, officeId) values('Product', 1);
insert into department(name, officeId) values('Hosting', 1);

insert into department(name, officeId) values('Central', 2);
insert into department(name, officeId) values('Hindusi', 3);

-- ****************** Employee ********************************************
insert into employee(name, city, street, departmentId) values('Jaroslaw Marek', 'Somewhere', 'far far away', 1);
insert into employee(name, city, street, departmentId) values('Sebastian Pisarski', 'Dziki', 'Wschod', 1);
insert into employee(name, city, street, departmentId) values('Marek Kogut', 'Cracow', 'Str1', 1);
insert into employee(name, city, street, departmentId) values('Cezary Hotlos', 'Cracow', 'Str2', 1);
insert into employee(name, city, street, departmentId) values('Arkadiusz Toman', 'Cracow', 'Str3', 1);
insert into employee(name, city, street, departmentId) values('Tomasz Prokop', 'Cracow', 'Str4', 1);
insert into employee(name, city, street, departmentId) values('Mateusz Tutka', 'Cracow', 'Str5', 2);
insert into employee(name, city, street, departmentId) values('Kazimierz Chlopek', 'Cracow', 'Str6', 2);


-- ****************** Task ************************************************
insert into task(name) values('task1');
insert into task(name) values('task2');
insert into task(name) values('task3');
insert into task(name) values('task4');
insert into task(name) values('task5');

-- ***************** Employee - Tasks *************************************
insert into empl_tasks(emplId, taskId) values(2, 2);
insert into empl_tasks(emplId, taskId) values(2, 5);
insert into empl_tasks(emplId, taskId) values(3, 2);
insert into empl_tasks(emplId, taskId) values(4, 3);
insert into empl_tasks(emplId, taskId) values(5, 4);
insert into empl_tasks(emplId, taskId) values(6, 5);
insert into empl_tasks(emplId, taskId) values(7, 5);
