/* AREAS */
INSERT INTO areas
VALUES (DEFAULT, 'PRIMARY LOCATION');
INSERT INTO areas
VALUES (DEFAULT, 'SECONDARY LOCATION');
INSERT INTO areas
VALUES (DEFAULT, 'TERTIARY LOCATION');


/* REGIONS */
INSERT INTO regions
VALUES (DEFAULT, 'REGION A');
INSERT INTO regions
VALUES (DEFAULT, 'REGION B');


/* REGIONAL AREAS */
INSERT INTO regional_areas
VALUES ((SELECT regions.id FROM regions WHERE regions.name = 'REGION A'),
        (SELECT areas.id FROM areas WHERE areas.name = 'PRIMARY LOCATION'));
INSERT INTO regional_areas
VALUES ((SELECT regions.id FROM regions WHERE regions.name = 'REGION A'),
        (SELECT areas.id FROM areas WHERE areas.name = 'SECONDARY LOCATION'));
INSERT INTO regional_areas
VALUES ((SELECT regions.id FROM regions WHERE regions.name = 'REGION B'),
        (SELECT areas.id FROM areas WHERE areas.name = 'TERTIARY LOCATION'));


/* ROLES */
INSERT INTO roles
VALUES (DEFAULT, 'MANAGER');
INSERT INTO roles
VALUES (DEFAULT, 'TEAM MEMBER');
INSERT INTO roles
VALUES (DEFAULT, 'FIRST AIDER');


/* QUALIFICATIONS */
INSERT INTO qualifications
VALUES (DEFAULT, 'Administration', 'Colleague qualified to undertake administrative tasks.');
INSERT INTO qualifications
VALUES (DEFAULT, 'Customer Service', 'Colleague qualified to server customers.');
INSERT INTO qualifications
VALUES (DEFAULT, 'First Aid', 'Colleague qualified to deliver first aid.');


/* ROLE QUALIFICATIONS */
INSERT INTO role_qualifications
VALUES ((SELECT roles.id FROM roles WHERE roles.name = 'MANAGER'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Administration'), FALSE);
INSERT INTO role_qualifications
VALUES ((SELECT roles.id FROM roles WHERE roles.name = 'MANAGER'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Customer Service'), FALSE);

INSERT INTO role_qualifications
VALUES ((SELECT roles.id FROM roles WHERE roles.name = 'TEAM MEMBER'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Customer Service'), FALSE);

INSERT INTO role_qualifications
VALUES ((SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'), TRUE);


/* PICTURE KEYS -> MOCK REPRESENTATION OF S3 KEYS */
INSERT INTO profile_pictures
VALUES (DEFAULT, 'key1');
INSERT INTO profile_pictures
VALUES (DEFAULT, 'key2');
INSERT INTO profile_pictures
VALUES (DEFAULT, 'key3');
INSERT INTO profile_pictures
VALUES (DEFAULT, 'anotherkey1');
INSERT INTO profile_pictures
VALUES (DEFAULT, 'anotherkey2');
INSERT INTO profile_pictures
VALUES (DEFAULT, 'anotherkey3');


/* MANAGER -> PRIMARY LOCATION */
INSERT INTO employees
VALUES (DEFAULT, 'John Smith',
        (SELECT areas.id FROM areas WHERE areas.name = 'PRIMARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'key1'),
        (SELECT roles.id FROM roles WHERE roles.name = 'MANAGER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'John Smith'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Administration'),
        'ACTIVE');
INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'John Smith'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Customer Service'),
        'ACTIVE');
INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'John Smith'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'),
        'INACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'John Smith'),
        (SELECT roles.id FROM roles WHERE roles.name = 'MANAGER'),
        'EXPERT');
INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'John Smith'),
        (SELECT roles.id FROM roles WHERE roles.name = 'TEAM MEMBER'),
        'COMPETENT');


/* FIRST AIDER -> EXPERT -> SECONDARY LOCATION */
INSERT INTO employees
VALUES (DEFAULT, 'Susan Pryce',
        (SELECT areas.id FROM areas WHERE areas.name = 'SECONDARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'key2'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Susan Pryce'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'),
        'ACTIVE');
INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Susan Pryce'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Customer Service'),
        'ACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Susan Pryce'),
        (SELECT roles.id FROM roles WHERE roles.name = 'TEAM MEMBER'),
        'ADVANCED');
INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Susan Pryce'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'),
        'EXPERT');


/* FIRST AIDER -> BASIC -> SECONDARY LOCATION */
INSERT INTO employees
VALUES (DEFAULT, 'Jonathan Pryce',
        (SELECT areas.id FROM areas WHERE areas.name = 'SECONDARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'anotherkey1'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Jonathan Pryce'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'),
        'ACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Jonathan Pryce'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'),
        'BASIC');


/* FIRST AIDER -> ADVANCED -> SECONDARY LOCATION */
INSERT INTO employees
VALUES (DEFAULT, 'Louise Wootton',
        (SELECT areas.id FROM areas WHERE areas.name = 'SECONDARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'anotherkey2'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Louise Wootton'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'),
        'ACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Louise Wootton'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'),
        'ADVANCED');


/*FIRST AIDER -> INACTIVE QUALIFICATION STATUS -> SECONDARY LOCATION*/
INSERT INTO employees
VALUES (DEFAULT, 'David Morrish',
        (SELECT areas.id FROM areas WHERE areas.name = 'SECONDARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'anotherkey3'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'David Morrish'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'First Aid'),
        'INACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'David Morrish'),
        (SELECT roles.id FROM roles WHERE roles.name = 'FIRST AIDER'),
        'COMPETENT');


/* TEAM MEMBER -> TERTIARY LOCATION */
INSERT INTO employees
VALUES (DEFAULT, 'Philip Frederickson',
        (SELECT areas.id FROM areas WHERE areas.name = 'TERTIARY LOCATION'),
        (SELECT profile_pictures.id FROM profile_pictures WHERE profile_pictures.s3_key = 'key3'),
        (SELECT roles.id FROM roles WHERE roles.name = 'TEAM MEMBER'));

INSERT INTO employee_qualifications
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Philip Frederickson'),
        (SELECT qualifications.id FROM qualifications WHERE qualifications.name = 'Customer Service'),
        'ACTIVE');

INSERT INTO employee_roles
VALUES ((SELECT employees.id FROM employees WHERE employees.name = 'Philip Frederickson'),
        (SELECT roles.id FROM roles WHERE roles.name = 'TEAM MEMBER'),
        'COMPETENT');


/* DEFAULT SHIFTS FOR VARIOUS DATES AND TIMES */
INSERT INTO shifts
VALUES (DEFAULT, 1, 1, 1, '2020-02-02T09:00:00', '2020-02-02T18:00:00', false, false, NULL);
INSERT INTO shifts
VALUES (DEFAULT, 1, 1, 1, '2020-02-06T09:00:00', '2020-02-06T16:00:00', false, false, NULL);
INSERT INTO shifts
VALUES (DEFAULT, 1, 1, 1, '2020-02-07T08:00:00', '2020-02-07T17:00:00', false, false, NULL);
INSERT INTO shifts
VALUES (DEFAULT, 1, 1, 1, '2020-02-10T08:00:00', '2020-02-10T19:00:00', false, false, NULL);

INSERT INTO shifts
VALUES (DEFAULT, 1, 2, 3, '2020-02-02T11:00:00', '2020-02-02T12:00:00', false, false, NULL);
INSERT INTO shifts
VALUES (DEFAULT, 1, 2, 3, '2020-02-02T15:00:00', '2020-02-02T22:00:00', false, false, NULL);
INSERT INTO shifts
VALUES (DEFAULT, 1, 2, 3, '2020-02-03T08:00:00', '2020-02-03T17:00:00', false, false, 'Two Hours');
INSERT INTO shifts
VALUES (DEFAULT, 1, 2, 3, '2020-02-05T08:00:00', '2020-02-05T17:00:00', false, false, 'One Hour');