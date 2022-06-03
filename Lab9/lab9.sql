CREATE SEQUENCE id
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
 CREATE SEQUENCE city_id
 START WITH 500
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
 CREATE SEQUENCE country_id
 START WITH 100
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
 DROP SEQUENCE id;
 DROP SEQUENCE city_id;
 DROP SEQUENCE country_id;
 
 delete  from continents;
 delete from cities;
 delete from countries;
 
 alter table cities add (population number(10,0));
 