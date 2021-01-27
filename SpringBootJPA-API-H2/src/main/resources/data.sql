INSERT INTO STUDENT(first_name, last_name) values ('Tam', 'Nguyen');
 INSERT INTO STUDENT(first_name, last_name) values ('Tam', 'Chi');

INSERT INTO course( name) values ('Java');
INSERT INTO course(name) values ('C#');
INSERT INTO course(name ) values ('C++');

INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(1,1);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(1,2);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(2,1);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(2,2);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(2,3);

INSERT INTO ADDRESS(province,district,ward,student_id) values ('Phu Yen' , 'Dong - Hoa' , 'HHTrung',1);
INSERT INTO ADDRESS(province,district,ward,student_id) values ('T.Phu Yen' , 'P.Dong - Hoa' , 'P.HHTrung',1);
 