drop table registration;
drop table insertion;
--标准建表
create table registration (sensorID varchar(255) primary key,description varchar(255), keyword varchar(20), beginTime TimeStamp, endTime TimeStamp,
samplingTime varchar(50),easting Double,northing Double, altitude Double, observableProperty varchar(255), uom varchar(10));

create table insertion(id int primary key auto_increment, time_stamp timestamp ,sensorID varchar(255), observableProperty varchar(255), uom varchar(10), 
data varchar(100),foreign key(sensorID) references registration(sensorID) on delete cascade);

insert into registration (sensorID,description,keyword,beginTime,endTime,easting,northing,altitude,observableProperty,uom) 
values ('AAA','Temperature Sensor','SensorBus',now(),'2013-10-16',121.234455,21.1232134,0,'Temp','Degree');

insert into insertion(time_stamp,SensorID,observableProperty,uom,data)
values (now(),'AAA','Temp','Degree','200');

insert into insertion(time_stamp,SensorID,data)
values (now(),'AAA','200');

--懒人建表
create table registration (sensorID varchar(255) primary key,description varchar(255), keyword varchar(20), beginTime varchar(20), endTime varchar(20),
samplingTime varchar(50),easting varchar(20),northing varchar(20), altitude varchar(20), observableProperty varchar(255), uom varchar(10));

create table insertion(id int primary key auto_increment, timeStamp varchar(20) ,sensorID varchar(255), observableProperty varchar(255), uom varchar(10), 
foreign key(sensorID) references registration(sensorID) on delete cascade);
