postgres=# \c train_ticket_booking_system
You are now connected to database "train_ticket_booking_system" as user "postgres".
train_ticket_booking_system=# \dt
              List of relations
 Schema |       Name       | Type  |  Owner
--------+------------------+-------+----------
 public | berth            | table | postgres
 public | booking_status   | table | postgres
 public | classtype        | table | postgres
 public | coach            | table | postgres
 public | passenger        | table | postgres
 public | route            | table | postgres
 public | seat             | table | postgres
 public | seat_status      | table | postgres
 public | station          | table | postgres
 public | train            | table | postgres
 public | train_seatstatus | table | postgres
 public | users            | table | postgres
 public | wishlists        | table | postgres
(13 rows)


train_ticket_booking_system=# select * from station;
 id |     name      | code
----+---------------+------
  1 | sengottai     | SCT
  2 | tenkasi       | TSI
  3 | sankarankovil | SNKL
  4 | sivakasi      | SVKS
  5 | madurai       | MDU
  6 | chennai       | MS
  7 | dindigul      | DG
  8 | tirunelveli   | TEN
  9 | virudunagar   | VPT
 10 | kanyakumari   | CAPE
(10 rows)


train_ticket_booking_system=# select * from route left join station on stationid=station.id;
 trainid | stationid | distance | id |     name      | code
---------+-----------+----------+----+---------------+------
       2 |         1 |        0 |  1 | sengottai     | SCT
       1 |         1 |        0 |  1 | sengottai     | SCT
       2 |         2 |       10 |  2 | tenkasi       | TSI
       1 |         2 |       10 |  2 | tenkasi       | TSI
       2 |         3 |       45 |  3 | sankarankovil | SNKL
       1 |         3 |       45 |  3 | sankarankovil | SNKL
       2 |         4 |      105 |  4 | sivakasi      | SVKS
       1 |         4 |      105 |  4 | sivakasi      | SVKS
       4 |         5 |      240 |  5 | madurai       | MDU
       3 |         5 |      157 |  5 | madurai       | MDU
       2 |         5 |      174 |  5 | madurai       | MDU
       1 |         5 |      174 |  5 | madurai       | MDU
       4 |         6 |      320 |  6 | chennai       | MS
       3 |         6 |      245 |  6 | chennai       | MS
       2 |         6 |      270 |  6 | chennai       | MS
       1 |         6 |      250 |  6 | chennai       | MS
       2 |         7 |      230 |  7 | dindigul      | DG
       4 |         8 |       90 |  8 | tirunelveli   | TEN
       3 |         8 |        0 |  8 | tirunelveli   | TEN
       3 |         9 |      114 |  9 | virudunagar   | VPT
       4 |        10 |        0 | 10 | kanyakumari   | CAPE
(21 rows)


train_ticket_booking_system=# select * from route left join station left join trainid=train.id on stationid=station.id;
ERROR:  syntax error at or near "="
LINE 1: ...t * from route left join station left join trainid=train.id ...
                                                             ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id;
 trainid | stationid | distance | id |     name      | code
---------+-----------+----------+----+---------------+------
       2 |         1 |        0 |  1 | sengottai     | SCT
       1 |         1 |        0 |  1 | sengottai     | SCT
       2 |         2 |       10 |  2 | tenkasi       | TSI
       1 |         2 |       10 |  2 | tenkasi       | TSI
       2 |         3 |       45 |  3 | sankarankovil | SNKL
       1 |         3 |       45 |  3 | sankarankovil | SNKL
       2 |         4 |      105 |  4 | sivakasi      | SVKS
       1 |         4 |      105 |  4 | sivakasi      | SVKS
       4 |         5 |      240 |  5 | madurai       | MDU
       3 |         5 |      157 |  5 | madurai       | MDU
       2 |         5 |      174 |  5 | madurai       | MDU
       1 |         5 |      174 |  5 | madurai       | MDU
       4 |         6 |      320 |  6 | chennai       | MS
       3 |         6 |      245 |  6 | chennai       | MS
       2 |         6 |      270 |  6 | chennai       | MS
       1 |         6 |      250 |  6 | chennai       | MS
       2 |         7 |      230 |  7 | dindigul      | DG
       4 |         8 |       90 |  8 | tirunelveli   | TEN
       3 |         8 |        0 |  8 | tirunelveli   | TEN
       3 |         9 |      114 |  9 | virudunagar   | VPT
       4 |        10 |        0 | 10 | kanyakumari   | CAPE
(21 rows)


train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id;
 trainid | stationid | distance | id |     name      | code | id |        name         | number | rate_per_km
---------+-----------+----------+----+---------------+------+----+---------------------+--------+-------------
       2 |         1 |        0 |  1 | sengottai     | SCT  |  2 | QLN EXPRESS         |  16102 |           2
       1 |         1 |        0 |  1 | sengottai     | SCT  |  1 | POTHIGAI EXPRESS    |  12662 |           2
       2 |         2 |       10 |  2 | tenkasi       | TSI  |  2 | QLN EXPRESS         |  16102 |           2
       1 |         2 |       10 |  2 | tenkasi       | TSI  |  1 | POTHIGAI EXPRESS    |  12662 |           2
       2 |         3 |       45 |  3 | sankarankovil | SNKL |  2 | QLN EXPRESS         |  16102 |           2
       1 |         3 |       45 |  3 | sankarankovil | SNKL |  1 | POTHIGAI EXPRESS    |  12662 |           2
       2 |         4 |      105 |  4 | sivakasi      | SVKS |  2 | QLN EXPRESS         |  16102 |           2
       1 |         4 |      105 |  4 | sivakasi      | SVKS |  1 | POTHIGAI EXPRESS    |  12662 |           2
       4 |         5 |      240 |  5 | madurai       | MDU  |  4 | KANYAKUMARI EXPRESS |  12634 |           2
       3 |         5 |      157 |  5 | madurai       | MDU  |  3 | NELLAI EXPRESS      |  12632 |           2
       2 |         5 |      174 |  5 | madurai       | MDU  |  2 | QLN EXPRESS         |  16102 |           2
       1 |         5 |      174 |  5 | madurai       | MDU  |  1 | POTHIGAI EXPRESS    |  12662 |           2
       4 |         6 |      320 |  6 | chennai       | MS   |  4 | KANYAKUMARI EXPRESS |  12634 |           2
       3 |         6 |      245 |  6 | chennai       | MS   |  3 | NELLAI EXPRESS      |  12632 |           2
       2 |         6 |      270 |  6 | chennai       | MS   |  2 | QLN EXPRESS         |  16102 |           2
       1 |         6 |      250 |  6 | chennai       | MS   |  1 | POTHIGAI EXPRESS    |  12662 |           2
       2 |         7 |      230 |  7 | dindigul      | DG   |  2 | QLN EXPRESS         |  16102 |           2
       4 |         8 |       90 |  8 | tirunelveli   | TEN  |  4 | KANYAKUMARI EXPRESS |  12634 |           2
       3 |         8 |        0 |  8 | tirunelveli   | TEN  |  3 | NELLAI EXPRESS      |  12632 |           2
       3 |         9 |      114 |  9 | virudunagar   | VPT  |  3 | NELLAI EXPRESS      |  12632 |           2
       4 |        10 |        0 | 10 | kanyakumari   | CAPE |  4 | KANYAKUMARI EXPRESS |  12634 |           2
(21 rows)


train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id group by trainid;
ERROR:  column "route.stationid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select * from route left join station on stationid=station.i...
               ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id group by trainid;
ERROR:  column "route.stationid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select * from route left join station on stationid=station.i...
               ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id group by trainid,stationid;
ERROR:  column "route.distance" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select * from route left join station on stationid=station.i...
               ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id where name='chennai' group by trainid;
ERROR:  column reference "name" is ambiguous
LINE 1: ...tion.id left join train on trainid=train.id where name='chen...
                                                             ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid;
ERROR:  column "route.stationid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select * from route left join station on stationid=station.i...
               ^
train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai';
 trainid | stationid | distance | id |  name   | code | id |        name         | number | rate_per_km
---------+-----------+----------+----+---------+------+----+---------------------+--------+-------------
       1 |         6 |      250 |  6 | chennai | MS   |  1 | POTHIGAI EXPRESS    |  12662 |           2
       2 |         6 |      270 |  6 | chennai | MS   |  2 | QLN EXPRESS         |  16102 |           2
       3 |         6 |      245 |  6 | chennai | MS   |  3 | NELLAI EXPRESS      |  12632 |           2
       4 |         6 |      320 |  6 | chennai | MS   |  4 | KANYAKUMARI EXPRESS |  12634 |           2
(4 rows)


train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai';
 trainid | stationid | distance | id |  name   | code | id |        name         | number | rate_per_km
---------+-----------+----------+----+---------+------+----+---------------------+--------+-------------
       4 |         5 |      240 |  5 | madurai | MDU  |  4 | KANYAKUMARI EXPRESS |  12634 |           2
       3 |         5 |      157 |  5 | madurai | MDU  |  3 | NELLAI EXPRESS      |  12632 |           2
       2 |         5 |      174 |  5 | madurai | MDU  |  2 | QLN EXPRESS         |  16102 |           2
       1 |         5 |      174 |  5 | madurai | MDU  |  1 | POTHIGAI EXPRESS    |  12662 |           2
       4 |         6 |      320 |  6 | chennai | MS   |  4 | KANYAKUMARI EXPRESS |  12634 |           2
       3 |         6 |      245 |  6 | chennai | MS   |  3 | NELLAI EXPRESS      |  12632 |           2
       2 |         6 |      270 |  6 | chennai | MS   |  2 | QLN EXPRESS         |  16102 |           2
       1 |         6 |      250 |  6 | chennai | MS   |  1 | POTHIGAI EXPRESS    |  12662 |           2
(8 rows)


train_ticket_booking_system=# select * from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid;
ERROR:  column "route.stationid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select * from route left join station on stationid=station.i...
               ^
train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid;
ERROR:  column "route.stationid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select trainid,stationid,distance,station.name,code,train.na...
                       ^
train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km;
 trainid | stationid | distance |  name   | code |        name         | number | rate_per_km
---------+-----------+----------+---------+------+---------------------+--------+-------------
       1 |         5 |      174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2
       1 |         6 |      250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2
       2 |         5 |      174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2
       2 |         6 |      270 | chennai | MS   | QLN EXPRESS         |  16102 |           2
       3 |         5 |      157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2
       3 |         6 |      245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2
       4 |         5 |      240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2
       4 |         6 |      320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2
(8 rows)


train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | stationid | distance |  name   | code |        name         | number | rate_per_km | sum
---------+-----------+----------+---------+------+---------------------+--------+-------------+-----
       1 |         5 |      174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |         6 |      250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |         5 |      174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |         6 |      270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |         5 |      157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |         6 |      245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |         5 |      240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |         6 |      320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km,sum(distance where distinct trainid) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near "where"
LINE 1: ...e,code,train.name,number,rate_per_km,sum(distance where dist...
                                                             ^
train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | stationid | distance |  name   | code |        name         | number | rate_per_km | sum
---------+-----------+----------+---------+------+---------------------+--------+-------------+-----
       1 |         5 |      174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |         6 |      250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |         5 |      174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |         6 |      270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |         5 |      157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |         6 |      245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |         5 |      240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |         6 |      320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid,distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | stationid | distance |  name   | code |        name         | number | rate_per_km | sum
---------+-----------+----------+---------+------+---------------------+--------+-------------+-----
       1 |         5 |      174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |         6 |      250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |         5 |      174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |         6 |      270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |         5 |      157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |         6 |      245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |         5 |      240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |         6 |      320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | fromid | toid | distance |  name   | code |        name         | number | rate_per_km | sum
---------+--------+------+----------+---------+------+---------------------+--------+-------------+-----
       1 |      5 |    5 |      174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |      6 |    6 |      250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |      5 |    5 |      174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |      6 |    6 |      270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |      5 |    5 |      157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |      6 |    6 |      245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |      5 |    5 |      240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |      6 |    6 |      320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance fromdistance,station.name from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near ","
LINE 1: ...ionid toid,distance fromdistance,station.name from,code,trai...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance fromdistance,station.name from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near ","
LINE 1: ...ionid toid,distance fromdistance,station.name from,code,trai...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | fromid | toid | fromdistance |  from   | code |        name         | number | rate_per_km | sum
---------+--------+------+--------------+---------+------+---------------------+--------+-------------+-----
       1 |      5 |    5 |          174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |      6 |    6 |          250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |      5 |    5 |          174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |      6 |    6 |          270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |      5 |    5 |          157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |      6 |    6 |          245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |      5 |    5 |          240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |      6 |    6 |          320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='madurai' inner join trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near "inner"
LINE 1: ... on trainid=train.id where station.name='madurai' inner join...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='madurai' iner join trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near "iner"
LINE 1: ... on trainid=train.id where station.name='madurai' iner join ...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='madurai' inner join select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near "inner"
LINE 1: ... on trainid=train.id where station.name='madurai' inner join...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id inner join select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near "select"
LINE 1: ...id left join train on trainid=train.id inner join select tra...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id inner join trainid,stationid fromid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
ERROR:  syntax error at or near ","
LINE 1: ... join train on trainid=train.id inner join trainid,stationid...
                                                             ^
train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | fromid | toid | fromdistance |  from   | code |        name         | number | rate_per_km | sum
---------+--------+------+--------------+---------+------+---------------------+--------+-------------+-----
       1 |      5 |    5 |          174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |      6 |    6 |          250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |      5 |    5 |          174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |      6 |    6 |          270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |      5 |    5 |          157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |      6 |    6 |          245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |      5 |    5 |          240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |      6 |    6 |          320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select trainid,stationid fromid,stationid toid,distance fromdistance,station.name as from,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' or station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km ;
 trainid | fromid | toid | fromdistance |  from   | code |        name         | number | rate_per_km | sum
---------+--------+------+--------------+---------+------+---------------------+--------+-------------+-----
       1 |      5 |    5 |          174 | madurai | MDU  | POTHIGAI EXPRESS    |  12662 |           2 | 174
       1 |      6 |    6 |          250 | chennai | MS   | POTHIGAI EXPRESS    |  12662 |           2 | 250
       2 |      5 |    5 |          174 | madurai | MDU  | QLN EXPRESS         |  16102 |           2 | 174
       2 |      6 |    6 |          270 | chennai | MS   | QLN EXPRESS         |  16102 |           2 | 270
       3 |      5 |    5 |          157 | madurai | MDU  | NELLAI EXPRESS      |  12632 |           2 | 157
       3 |      6 |    6 |          245 | chennai | MS   | NELLAI EXPRESS      |  12632 |           2 | 245
       4 |      5 |    5 |          240 | madurai | MDU  | KANYAKUMARI EXPRESS |  12634 |           2 | 240
       4 |      6 |    6 |          320 | chennai | MS   | KANYAKUMARI EXPRESS |  12634 |           2 | 320
(8 rows)


train_ticket_booking_system=# select t1.trainid,t1.staionid fromid,t2.stationid toid,t1.distance from_dt,t2.distance to_dt,t1.station.name from,t2.station.name to,t1.train.name from (select trainid,stationid, distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km) t1 inner join (select trainid,stationid, distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km) t2 ;
ERROR:  syntax error at or near ","
LINE 1: ...nce from_dt,t2.distance to_dt,t1.station.name from,t2.statio...
                                                             ^
train_ticket_booking_system=# select t1.trainid,t1.staionid fromid,t2.stationid toid,t1.distance from_dt,t2.distance to_dt,t1.station.name as from,t2.station.name as to,t1.train.name (select trainid,stationid, distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='madurai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km) t1 inner join (select trainid,stationid, distance,station.name,code,train.name,number,rate_per_km,sum(distance) from route left join station on stationid=station.id left join train on trainid=train.id where station.name='chennai' group by trainid,stationid,distance,station.name,code,train.name,number,rate_per_km) t2 ;
ERROR:  syntax error at or near "select"
LINE 1: ...name as from,t2.station.name as to,t1.train.name (select tra...
                                                             ^
train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name
---------+--------+------+---------+-------+---------+---------+---------------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name
---------+--------+------+---------+-------+---------+---------+---------------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             distance - LAG(distance) OVER (PARTITION BY trainid ORDER BY stationid) AS distance_diff
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             distance - LAG(distance) OVER (PARTITION BY trainid ORDER BY stationid) AS distance_diff
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name
---------+--------+------+---------+-------+---------+---------+---------------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name
---------+--------+------+---------+-------+---------+---------+---------------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-#  ,totaldistance(to_dt-from_dt)FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
ERROR:  column "to_dt" does not exist
LINE 10:  ,totaldistance(to_dt-from_dt)FROM
                         ^
train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name
train_ticket_booking_system-# ,t2.distance-t1.distance as totaldistance
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance
---------+--------+------+---------+-------+---------+---------+---------------------+---------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name,
train_ticket_booking_system-# t2.distance - t1.distance as totaldistance,
train_ticket_booking_system-# t1.rate_per_km,
train_ticket_booking_system-# totaldistance*t1.rate_per_km ticketcharge
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
ERROR:  column "totaldistance" does not exist
LINE 12: totaldistance*t1.rate_per_km ticketcharge
         ^
HINT:  Perhaps you meant to reference the column "t1.total_distance" or the column "t2.total_distance".
train_ticket_booking_system=# SELECT
train_ticket_booking_system-#     t1.trainid,
train_ticket_booking_system-#     t1.stationid AS fromid,
train_ticket_booking_system-#     t2.stationid AS toid,
train_ticket_booking_system-#     t1.distance AS from_dt,
train_ticket_booking_system-#     t2.distance AS to_dt,
train_ticket_booking_system-#     t1.station_name AS from,
train_ticket_booking_system-#     t2.station_name AS to,
train_ticket_booking_system-#     t1.train_name,
train_ticket_booking_system-# t2.distance-t1.distance totaldistance,
train_ticket_booking_system-# t1.rate_per_km,
train_ticket_booking_system-# (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-# FROM
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'madurai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t1
train_ticket_booking_system-# INNER JOIN
train_ticket_booking_system-#     (
train_ticket_booking_system(#         SELECT
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name AS station_name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name AS train_name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km,
train_ticket_booking_system(#             SUM(distance) AS total_distance
train_ticket_booking_system(#         FROM
train_ticket_booking_system(#             route
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             station ON stationid = station.id
train_ticket_booking_system(#         LEFT JOIN
train_ticket_booking_system(#             train ON trainid = train.id
train_ticket_booking_system(#         WHERE
train_ticket_booking_system(#             station.name = 'chennai'
train_ticket_booking_system(#         GROUP BY
train_ticket_booking_system(#             trainid,
train_ticket_booking_system(#             stationid,
train_ticket_booking_system(#             distance,
train_ticket_booking_system(#             station.name,
train_ticket_booking_system(#             code,
train_ticket_booking_system(#             train.name,
train_ticket_booking_system(#             number,
train_ticket_booking_system(#             rate_per_km
train_ticket_booking_system(#     ) t2
train_ticket_booking_system-# ON
train_ticket_booking_system-#     t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance | rate_per_km | ticketcharge
---------+--------+------+---------+-------+---------+---------+---------------------+---------------+-------------+--------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76 |           2 |          152
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96 |           2 |          192
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88 |           2 |          176
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80 |           2 |          160
(4 rows)


train_ticket_booking_system=# select * from station;
 id |     name      | code
----+---------------+------
  1 | sengottai     | SCT
  2 | tenkasi       | TSI
  3 | sankarankovil | SNKL
  4 | sivakasi      | SVKS
  5 | madurai       | MDU
  6 | chennai       | MS
  7 | dindigul      | DG
  8 | tirunelveli   | TEN
  9 | virudunagar   | VPT
 10 | kanyakumari   | CAPE
(10 rows)


train_ticket_booking_system=# select * from route;
 trainid | stationid | distance
---------+-----------+----------
       1 |         1 |        0
       1 |         2 |       10
       1 |         3 |       45
       1 |         4 |      105
       1 |         5 |      174
       1 |         6 |      250
       2 |         1 |        0
       2 |         2 |       10
       2 |         3 |       45
       2 |         4 |      105
       2 |         5 |      174
       2 |         7 |      230
       2 |         6 |      270
       3 |         8 |        0
       3 |         9 |      114
       3 |         5 |      157
       3 |         6 |      245
       4 |        10 |        0
       4 |         8 |       90
       4 |         5 |      240
       4 |         6 |      320
(21 rows)


train_ticket_booking_system=# alter table route add column id serial primary key;
ALTER TABLE
train_ticket_booking_system=# select * from route;
 trainid | stationid | distance | id
---------+-----------+----------+----
       1 |         1 |        0 |  1
       1 |         2 |       10 |  2
       1 |         3 |       45 |  3
       1 |         4 |      105 |  4
       1 |         5 |      174 |  5
       1 |         6 |      250 |  6
       2 |         1 |        0 |  7
       2 |         2 |       10 |  8
       2 |         3 |       45 |  9
       2 |         4 |      105 | 10
       2 |         5 |      174 | 11
       2 |         7 |      230 | 12
       2 |         6 |      270 | 13
       3 |         8 |        0 | 14
       3 |         9 |      114 | 15
       3 |         5 |      157 | 16
       3 |         6 |      245 | 17
       4 |        10 |        0 | 18
       4 |         8 |       90 | 19
       4 |         5 |      240 | 20
       4 |         6 |      320 | 21
(21 rows)


train_ticket_booking_system=# select id,trainid,stationid,distance from route;
 id | trainid | stationid | distance
----+---------+-----------+----------
  1 |       1 |         1 |        0
  2 |       1 |         2 |       10
  3 |       1 |         3 |       45
  4 |       1 |         4 |      105
  5 |       1 |         5 |      174
  6 |       1 |         6 |      250
  7 |       2 |         1 |        0
  8 |       2 |         2 |       10
  9 |       2 |         3 |       45
 10 |       2 |         4 |      105
 11 |       2 |         5 |      174
 12 |       2 |         7 |      230
 13 |       2 |         6 |      270
 14 |       3 |         8 |        0
 15 |       3 |         9 |      114
 16 |       3 |         5 |      157
 17 |       3 |         6 |      245
 18 |       4 |        10 |        0
 19 |       4 |         8 |       90
 20 |       4 |         5 |      240
 21 |       4 |         6 |      320
(21 rows)


train_ticket_booking_system=# select id,distinct on trainid,stationid,distance from route;
ERROR:  syntax error at or near "distinct"
LINE 1: select id,distinct on trainid,stationid,distance from route;
                  ^
train_ticket_booking_system=# select distinct on trainid,stationid,distance from route;
ERROR:  syntax error at or near "trainid"
LINE 1: select distinct on trainid,stationid,distance from route;
                           ^
train_ticket_booking_system=# select id,trainid,stationid,distance from route distinct on trainid;
ERROR:  syntax error at or near "distinct"
LINE 1: select id,trainid,stationid,distance from route distinct on ...
                                                        ^
train_ticket_booking_system=# select distinct on(trainid) trainid,id,trainid,stationid,distance from route;
 trainid | id | trainid | stationid | distance
---------+----+---------+-----------+----------
       1 |  1 |       1 |         1 |        0
       2 |  7 |       2 |         1 |        0
       3 | 14 |       3 |         8 |        0
       4 | 18 |       4 |        10 |        0
(4 rows)


train_ticket_booking_system=# select distinct on(trainid) trainid,id,stationid,distance from route;
 trainid | id | stationid | distance
---------+----+-----------+----------
       1 |  1 |         1 |        0
       2 |  7 |         1 |        0
       3 | 14 |         8 |        0
       4 | 18 |        10 |        0
(4 rows)


train_ticket_booking_system=# select trainid,id,stationid,distance from route where stationid=5;
 trainid | id | stationid | distance
---------+----+-----------+----------
       1 |  5 |         5 |      174
       2 | 11 |         5 |      174
       3 | 16 |         5 |      157
       4 | 20 |         5 |      240
(4 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route) t1 inner join (select trainid,id,stationid,distance) t2 on t1.trainid=t2.trainid;
ERROR:  column "trainid" does not exist
LINE 1: ...tionid,distance from route) t1 inner join (select trainid,id...
                                                             ^
HINT:  There is a column named "trainid" in table "t1", but it cannot be referenced from this part of the query.
train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route) t1 inner join (select trainid,id,stationid,distance from route) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          1 |     0 |        0
       1 |          1 |          2 |     1 |       10
       1 |          1 |          3 |     2 |       45
       1 |          1 |          4 |     3 |      105
       1 |          1 |          5 |     4 |      174
       1 |          1 |          6 |     5 |      250
       2 |          7 |          7 |     0 |        0
       2 |          7 |          8 |     1 |       10
       2 |          7 |          9 |     2 |       45
       2 |          7 |         10 |     3 |      105
       2 |          7 |         11 |     4 |      174
       2 |          7 |         12 |     5 |      230
       2 |          7 |         13 |     6 |      270
       3 |         14 |         14 |     0 |        0
       3 |         14 |         15 |     1 |      114
       3 |         14 |         16 |     2 |      157
       3 |         14 |         17 |     3 |      245
       4 |         18 |         18 |     0 |        0
       4 |         18 |         19 |     1 |       90
       4 |         18 |         20 |     2 |      240
       4 |         18 |         21 |     3 |      320
(21 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          5 |     4 |      174
       2 |          7 |         11 |     4 |      174
       3 |         14 |         16 |     2 |      157
       4 |         18 |         20 |     2 |      240
(4 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by id) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
ERROR:  SELECT DISTINCT ON expressions must match initial ORDER BY expressions
LINE 1: ....id as index,t2.distance from (select distinct on(trainid) t...
                                                             ^
train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          5 |     4 |      174
       2 |          7 |         11 |     4 |      174
       3 |         14 |         16 |     2 |      157
       4 |         18 |         20 |     2 |      240
(4 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          5 |     4 |      174
       2 |          7 |         11 |     4 |      174
       3 |         14 |         16 |     2 |      157
       4 |         18 |         20 |     2 |      240
(4 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.station.name as station_name,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
ERROR:  missing FROM-clause entry for table "station"
LINE 1: ...1.trainid,t1.id as startingid,t2.id as positionid,t2.station...
                                                             ^
train_ticket_booking_system=# \dt
              List of relations
 Schema |       Name       | Type  |  Owner
--------+------------------+-------+----------
 public | berth            | table | postgres
 public | booking_status   | table | postgres
 public | classtype        | table | postgres
 public | coach            | table | postgres
 public | passenger        | table | postgres
 public | route            | table | postgres
 public | seat             | table | postgres
 public | seat_status      | table | postgres
 public | station          | table | postgres
 public | train            | table | postgres
 public | train_seatstatus | table | postgres
 public | users            | table | postgres
 public | wishlists        | table | postgres
(13 rows)


train_ticket_booking_system=# select * from berth;
 id |    type
----+-------------
  1 | LOWER
  2 | MIDDLE
  3 | UPPER
  4 | RAC
  5 | WAITINGLIST
(5 rows)


train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.station.name as station_name,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
ERROR:  missing FROM-clause entry for table "station"
LINE 1: ...1.trainid,t1.id as startingid,t2.id as positionid,t2.station...
                                                             ^
train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          5 |     4 |      174
       2 |          7 |         11 |     4 |      174
       3 |         14 |         16 |     2 |      157
       4 |         18 |         20 |     2 |      240
(4 rows)


train_ticket_booking_system=# SELECT
train_ticket_booking_system-# //        t1.trainid,
train_ticket_booking_system-# //        t1.stationid AS fromid,
train_ticket_booking_system-# //        t2.stationid AS toid,
train_ticket_booking_system-# //        t1.distance AS from_dt,
train_ticket_booking_system-# //        t2.distance AS to_dt,
train_ticket_booking_system-# //        t1.station_name AS from,
train_ticket_booking_system-# //        t2.station_name AS to,
train_ticket_booking_system-# //        t1.train_name,
train_ticket_booking_system-# //    t2.distance-t1.distance totaldistance,
train_ticket_booking_system-# //    t1.rate_per_km,
train_ticket_booking_system-# //    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-# //    FROM
train_ticket_booking_system-# //        (
train_ticket_booking_system(# //            SELECT
train_ticket_booking_system(# //                trainid,
train_ticket_booking_system(# //                stationid,
train_ticket_booking_system(# //                distance,
train_ticket_booking_system(# //                station.name AS station_name,
train_ticket_booking_system(# //                code,
train_ticket_booking_system(# //                train.name AS train_name,
train_ticket_booking_system(# //                number,
train_ticket_booking_system(# //                rate_per_km,
train_ticket_booking_system(# //                SUM(distance) AS total_distance
train_ticket_booking_system(# //            FROM
train_ticket_booking_system(# //                route
train_ticket_booking_system(# //            LEFT JOIN
train_ticket_booking_system(# //                station ON stationid = station.id
train_ticket_booking_system(# //            LEFT JOIN
train_ticket_booking_system(# //                train ON trainid = train.id
train_ticket_booking_system(# //            WHERE
train_ticket_booking_system(# //                station.name = 'madurai'
train_ticket_booking_system(# //            GROUP BY
train_ticket_booking_system(# //                trainid,
train_ticket_booking_system(# //                stationid,
train_ticket_booking_system(# //                distance,
train_ticket_booking_system(# //                station.name,
train_ticket_booking_system(# //                code,
train_ticket_booking_system(# //                train.name,
train_ticket_booking_system(# //                number,
train_ticket_booking_system(# //                rate_per_km
train_ticket_booking_system(# //        ) t1
train_ticket_booking_system-# //    INNER JOIN
train_ticket_booking_system-# //        (
train_ticket_booking_system(# //            SELECT
train_ticket_booking_system(# //                trainid,
train_ticket_booking_system(# //                stationid,
train_ticket_booking_system(# //                distance,
train_ticket_booking_system(# //                station.name AS station_name,
train_ticket_booking_system(# //                code,
train_ticket_booking_system(# //                train.name AS train_name,
train_ticket_booking_system(# //                number,
train_ticket_booking_system(# //                rate_per_km,
train_ticket_booking_system(# //                SUM(distance) AS total_distance
train_ticket_booking_system(# //            FROM
train_ticket_booking_system(# //                route
train_ticket_booking_system(# //            LEFT JOIN
train_ticket_booking_system(# //                station ON stationid = station.id
train_ticket_booking_system(# //            LEFT JOIN
train_ticket_booking_system(# //                train ON trainid = train.id
train_ticket_booking_system(# //            WHERE
train_ticket_booking_system(# //                station.name = 'chennai'
train_ticket_booking_system(# //            GROUP BY
train_ticket_booking_system(# //                trainid,
train_ticket_booking_system(# //                stationid,
train_ticket_booking_system(# //                distance,
train_ticket_booking_system(# //                station.name,
train_ticket_booking_system(# //                code,
train_ticket_booking_system(# //                train.name,
train_ticket_booking_system(# //                number,
train_ticket_booking_system(# //                rate_per_km
train_ticket_booking_system(# //        ) t2
train_ticket_booking_system-# //    ON
train_ticket_booking_system-# //        t1.trainid = t2.trainid;
ERROR:  syntax error at or near "//"
LINE 13: //    FROM
         ^
train_ticket_booking_system=# //        t1.trainid = t2.trainid;
ERROR:  syntax error at or near "//"
LINE 1: //        t1.trainid = t2.trainid;
        ^
train_ticket_booking_system=# SELECT
train_ticket_booking_system-#        t1.trainid,
train_ticket_booking_system-#        t1.stationid AS fromid,
train_ticket_booking_system-#        t2.stationid AS toid,
train_ticket_booking_system-#        t1.distance AS from_dt,
train_ticket_booking_system-#        t2.distance AS to_dt,
train_ticket_booking_system-#        t1.station_name AS from,
train_ticket_booking_system-#        t2.station_name AS to,
train_ticket_booking_system-#        t1.train_name,
train_ticket_booking_system-#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system-#    t1.rate_per_km,
train_ticket_booking_system-#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-#    FROM
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system-#    INNER JOIN
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system-#    ON
train_ticket_booking_system-#        t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance | rate_per_km | ticketcharge
---------+--------+------+---------+-------+---------+---------+---------------------+---------------+-------------+--------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76 |           2 |          152
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96 |           2 |          192
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88 |           2 |          176
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80 |           2 |          160
(4 rows)


train_ticket_booking_system=# \dt
              List of relations
 Schema |       Name       | Type  |  Owner
--------+------------------+-------+----------
 public | berth            | table | postgres
 public | booking_status   | table | postgres
 public | classtype        | table | postgres
 public | coach            | table | postgres
 public | passenger        | table | postgres
 public | route            | table | postgres
 public | seat             | table | postgres
 public | seat_status      | table | postgres
 public | station          | table | postgres
 public | train            | table | postgres
 public | train_seatstatus | table | postgres
 public | users            | table | postgres
 public | wishlists        | table | postgres
(13 rows)


train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-21  | {f,f,f,f}
(17 rows)


train_ticket_booking_system=# select * from seat_status where status[0]=t;
ERROR:  column "t" does not exist
LINE 1: select * from seat_status where status[0]=t;
                                                  ^
train_ticket_booking_system=# select * from seat_status where status[0]='t';
 seatid | travel_date | status
--------+-------------+--------
(0 rows)


train_ticket_booking_system=# select * from seat_status where status[0]='f';
 seatid | travel_date | status
--------+-------------+--------
(0 rows)


train_ticket_booking_system=# select * from seat_status where status[1]='f';
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-21  | {f,f,f,f}
(17 rows)


train_ticket_booking_system=# select * from seat_status where status[1]='t';
 seatid | travel_date | status
--------+-------------+--------
(0 rows)


train_ticket_booking_system=# select * from seat_status where status[1]='j';
ERROR:  invalid input syntax for type boolean: "j"
LINE 1: select * from seat_status where status[1]='j';
                                                  ^
train_ticket_booking_system=# select * from seat_status where status[1select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;]='j';
ERROR:  trailing junk after numeric literal at or near "1s"
LINE 1: select * from seat_status where status[1select t1.trainid,t1...
                                               ^
ERROR:  syntax error at or near "]"
LINE 1: ]='j';
        ^
train_ticket_booking_system=# select t1.trainid,t1.id as startingid,t2.id as positionid,t2.id-t1.id as index,t2.distance from (select distinct on(trainid) trainid,id,stationid,distance from route order by trainid) t1 inner join (select trainid,id,stationid,distance from route where stationid=5) t2 on t1.trainid=t2.trainid;
 trainid | startingid | positionid | index | distance
---------+------------+------------+-------+----------
       1 |          1 |          5 |     4 |      174
       2 |          7 |         11 |     4 |      174
       3 |         14 |         16 |     2 |      157
       4 |         18 |         20 |     2 |      240
(4 rows)


train_ticket_booking_system=# select count(*) from seat_status where status[1:3]=array[false,false,false]::boolean[];
 count
-------
    17
(1 row)


train_ticket_booking_system=# select count(*),status from seat_status where status[1:3]=array[false,false,false]::boolean[];
ERROR:  column "seat_status.status" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select count(*),status from seat_status where status[1:3]=ar...
                        ^
train_ticket_booking_system=# select status from seat_status where status[1:3]=array[false,false,false]::boolean[];
     status
-----------------
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
 {f,f,f,f}
(17 rows)


train_ticket_booking_system=# select status from seat_status where status[1:]=array[false,false,false]::boolean[];
 status
--------
(0 rows)


train_ticket_booking_system=# select status from seat_status where status[1:5]=array[false,false,false]::boolean[];
 status
--------
(0 rows)


train_ticket_booking_system=# select status from seat_status where status[1:5]=array[f,f,f,f,f]::boolean[];
ERROR:  column "f" does not exist
LINE 1: ...t status from seat_status where status[1:5]=array[f,f,f,f,f]...
                                                             ^
train_ticket_booking_system=# select status from seat_status where status[1:5]=array[false,false,false,false,false]::boolean[];
     status
-----------------
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
 {f,f,f,f,f,f,f}
(8 rows)


train_ticket_booking_system=# select count(status) from seat_status where status[1:5]=array[false,false,false,false,false]::boolean[];
 count
-------
     8
(1 row)


train_ticket_booking_system=#      SELECT
train_ticket_booking_system-#        t1.trainid,
train_ticket_booking_system-#        t1.stationid AS fromid,
train_ticket_booking_system-#        t2.stationid AS toid,
train_ticket_booking_system-#        t1.distance AS from_dt,
train_ticket_booking_system-#        t2.distance AS to_dt,
train_ticket_booking_system-#        t1.station_name AS from,
train_ticket_booking_system-#        t2.station_name AS to,
train_ticket_booking_system-#        t1.train_name,
train_ticket_booking_system-#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system-#    t1.rate_per_km,
train_ticket_booking_system-#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-#    FROM
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system-#    INNER JOIN
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system-#    ON
train_ticket_booking_system-#        t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance | rate_per_km | ticketcharge
---------+--------+------+---------+-------+---------+---------+---------------------+---------------+-------------+--------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76 |           2 |          152
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96 |           2 |          192
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88 |           2 |          176
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80 |           2 |          160
(4 rows)


train_ticket_booking_system=# select t1.trainid,seat_status.status(
train_ticket_booking_system(#      SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid;) inner join seat_status on t1.trainid=seatstatus.trainid;
ERROR:  syntax error at or near "SELECT"
LINE 2:      SELECT
             ^
train_ticket_booking_system=#  select t1.trainid,seat_status.status from(
train_ticket_booking_system(#      SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid;) inner join seat_status on t1.trainid=seat_status.trainid;
ERROR:  syntax error at or near ";"
LINE 75:        t1.trainid = t2.trainid;) inner join seat_status on t...
                                       ^
train_ticket_booking_system=#     select t1.trainid,seat_status.status from(
train_ticket_booking_system(#      SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid)
train_ticket_booking_system-# inner join seat_status on t1.trainid=seat_status.trainid;
ERROR:  subquery in FROM must have an alias
LINE 1: select t1.trainid,seat_status.status from(
                                                 ^
HINT:  For example, FROM (SELECT ...) [AS] foo.
train_ticket_booking_system=#  select t1.trainid,seat_status.status from(
train_ticket_booking_system(#      SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid) ;
ERROR:  subquery in FROM must have an alias
LINE 1: select t1.trainid,seat_status.status from(
                                                 ^
HINT:  For example, FROM (SELECT ...) [AS] foo.
train_ticket_booking_system=#    select table.trainid,seat_status.status from(
train_ticket_booking_system(#      SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid) table inner join seat_status on table.trainid=seat_status.trainid;
ERROR:  syntax error at or near "table"
LINE 1: select table.trainid,seat_status.status from(
               ^
train_ticket_booking_system=#   SELECT t.trainid, ss.status
train_ticket_booking_system-# FROM (
train_ticket_booking_system(#     SELECT
train_ticket_booking_system(#         t1.trainid,
train_ticket_booking_system(#         t1.stationid AS fromid,
train_ticket_booking_system(#         t2.stationid AS toid,
train_ticket_booking_system(#         t1.distance AS from_dt,
train_ticket_booking_system(#         t2.distance AS to_dt,
train_ticket_booking_system(#         t1.station_name AS from,
train_ticket_booking_system(#         t2.station_name AS to,
train_ticket_booking_system(#         t1.train_name,
train_ticket_booking_system(#         t2.distance - t1.distance AS totaldistance,
train_ticket_booking_system(#         t1.rate_per_km,
train_ticket_booking_system(#         (t2.distance - t1.distance) * t1.rate_per_km AS ticketcharge
train_ticket_booking_system(#     FROM
train_ticket_booking_system(#         (
train_ticket_booking_system(#             SELECT
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name AS station_name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name AS train_name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km,
train_ticket_booking_system(#                 SUM(distance) AS total_distance
train_ticket_booking_system(#             FROM
train_ticket_booking_system(#                 route
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 station ON stationid = station.id
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 train ON trainid = train.id
train_ticket_booking_system(#             WHERE
train_ticket_booking_system(#                 station.name = 'madurai'
train_ticket_booking_system(#             GROUP BY
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km
train_ticket_booking_system(#         ) t1
train_ticket_booking_system(#     INNER JOIN
train_ticket_booking_system(#         (
train_ticket_booking_system(#             SELECT
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name AS station_name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name AS train_name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km,
train_ticket_booking_system(#                 SUM(distance) AS total_distance
train_ticket_booking_system(#             FROM
train_ticket_booking_system(#                 route
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 station ON stationid = station.id
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 train ON trainid = train.id
train_ticket_booking_system(#             WHERE
train_ticket_booking_system(#                 station.name = 'chennai'
train_ticket_booking_system(#             GROUP BY
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km
train_ticket_booking_system(#         ) t2 ON t1.trainid = t2.trainid
train_ticket_booking_system(# ) AS t
train_ticket_booking_system-# INNER JOIN seat_status ON t.trainid = seat_status.trainid;
ERROR:  column seat_status.trainid does not exist
LINE 76: INNER JOIN seat_status ON t.trainid = seat_status.trainid;
                                               ^
train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-21  | {f,f,f,f}
(17 rows)


train_ticket_booking_system=# \dt
              List of relations
 Schema |       Name       | Type  |  Owner
--------+------------------+-------+----------
 public | berth            | table | postgres
 public | booking_status   | table | postgres
 public | classtype        | table | postgres
 public | coach            | table | postgres
 public | passenger        | table | postgres
 public | route            | table | postgres
 public | seat             | table | postgres
 public | seat_status      | table | postgres
 public | station          | table | postgres
 public | train            | table | postgres
 public | train_seatstatus | table | postgres
 public | users            | table | postgres
 public | wishlists        | table | postgres
(13 rows)


train_ticket_booking_system=#      SELECT
train_ticket_booking_system-#        t1.trainid,
train_ticket_booking_system-#        t1.stationid AS fromid,
train_ticket_booking_system-#        t2.stationid AS toid,
train_ticket_booking_system-#        t1.distance AS from_dt,
train_ticket_booking_system-#        t2.distance AS to_dt,
train_ticket_booking_system-#        t1.station_name AS from,
train_ticket_booking_system-#        t2.station_name AS to,
train_ticket_booking_system-#        t1.train_name,
train_ticket_booking_system-#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system-#    t1.rate_per_km,
train_ticket_booking_system-#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-#    FROM
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system-#    INNER JOIN
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system-#    ON
train_ticket_booking_system-#        t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance | rate_per_km | ticketcharge
---------+--------+------+---------+-------+---------+---------+---------------------+---------------+-------------+--------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76 |           2 |          152
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96 |           2 |          192
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88 |           2 |          176
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80 |           2 |          160
(4 rows)


train_ticket_booking_system=# SELECT COUNT(*)
train_ticket_booking_system-# FROM your_table
train_ticket_booking_system-# WHERE your_column[1:3] = ARRAY[false, false, false]::boolean[];
ERROR:  relation "your_table" does not exist
LINE 2: FROM your_table
             ^
train_ticket_booking_system=# select count(status) from seat_status where status[1:5]=array[false,false,false,false,false]::boolean[];
 count
-------
     8
(1 row)


train_ticket_booking_system=# select count(status) from seat_status where travel_date='21-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[];
 count
-------
     0
(1 row)


train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-21  | {f,f,f,f}
(17 rows)


train_ticket_booking_system=# select count(status) from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[];
 count
-------
     8
(1 row)


train_ticket_booking_system=# select *,count(status) from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[];
ERROR:  column "seat_status.seatid" must appear in the GROUP BY clause or be used in an aggregate function
LINE 1: select *,count(status) from seat_status where travel_date='2...
               ^
train_ticket_booking_system=# select count(status) count from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[];
 count
-------
     8
(1 row)


train_ticket_booking_system=# select * from(select count(status) count from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1;
 count
-------
     8
(1 row)


train_ticket_booking_system=# select * from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
(8 rows)


train_ticket_booking_system=# select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[];
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
(8 rows)


train_ticket_booking_system=# select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[] inner join seat on seatid=seat.id;
ERROR:  syntax error at or near "inner"
LINE 1: ...]=array[false,false,false,false,false]::boolean[] inner join...
                                                             ^
train_ticket_booking_system=# (select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id;
ERROR:  syntax error at or near "t1"
LINE 1: ...=array[false,false,false,false,false]::boolean[]) t1 inner j...
                                                             ^
train_ticket_booking_system=# select * from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id;
 seatid | travel_date |     status      | id | number | trainid | coachid | berthid
--------+-------------+-----------------+----+--------+---------+---------+---------
      1 | 2000-06-25  | {f,f,f,f,f,f}   |  1 |      1 |       1 |       1 |       1
      2 | 2000-06-25  | {f,f,f,f,f,f}   |  2 |      2 |       1 |       1 |       2
      3 | 2000-06-25  | {f,f,f,f,f,f}   |  3 |      3 |       1 |       1 |       3
      4 | 2000-06-25  | {f,f,f,f,f,f}   |  4 |      4 |       1 |       1 |       4
      5 | 2000-06-25  | {f,f,f,f,f,f,f} |  5 |      1 |       2 |       2 |       1
      6 | 2000-06-25  | {f,f,f,f,f,f,f} |  6 |      2 |       2 |       2 |       2
      7 | 2000-06-25  | {f,f,f,f,f,f,f} |  7 |      3 |       2 |       2 |       3
      8 | 2000-06-25  | {f,f,f,f,f,f,f} |  8 |      4 |       2 |       2 |       4
(8 rows)


train_ticket_booking_system=# select seatid,status,trainid from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id;
 seatid |     status      | trainid
--------+-----------------+---------
      1 | {f,f,f,f,f,f}   |       1
      2 | {f,f,f,f,f,f}   |       1
      3 | {f,f,f,f,f,f}   |       1
      4 | {f,f,f,f,f,f}   |       1
      5 | {f,f,f,f,f,f,f} |       2
      6 | {f,f,f,f,f,f,f} |       2
      7 | {f,f,f,f,f,f,f} |       2
      8 | {f,f,f,f,f,f,f} |       2
(8 rows)


train_ticket_booking_system=# select distinct on trainid,seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id;
ERROR:  syntax error at or near "trainid"
LINE 1: select distinct on trainid,seatid,status from(select * from ...
                           ^
train_ticket_booking_system=# select distinct on trainid,seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id order by trainid;
ERROR:  syntax error at or near "trainid"
LINE 1: select distinct on trainid,seatid,status from(select * from ...
                           ^
train_ticket_booking_system=# select distinct on trainid,seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id order by trainid,seatid,status;
ERROR:  syntax error at or near "trainid"
LINE 1: select distinct on trainid,seatid,status from(select * from ...
                           ^
train_ticket_booking_system=# select distinct(trainid),seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id order by trainid;
 trainid | seatid |     status
---------+--------+-----------------
       1 |      1 | {f,f,f,f,f,f}
       1 |      2 | {f,f,f,f,f,f}
       1 |      3 | {f,f,f,f,f,f}
       1 |      4 | {f,f,f,f,f,f}
       2 |      5 | {f,f,f,f,f,f,f}
       2 |      6 | {f,f,f,f,f,f,f}
       2 |      7 | {f,f,f,f,f,f,f}
       2 |      8 | {f,f,f,f,f,f,f}
(8 rows)


train_ticket_booking_system=# select distinct on (trainid),seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id order by trainid;
ERROR:  syntax error at or near ","
LINE 1: select distinct on (trainid),seatid,status from(select * fro...
                                    ^
train_ticket_booking_system=# select distinct(trainid),seatid,status from(select * from seat_status where travel_date='25-06-2000' and status[1:5]=array[false,false,false,false,false]::boolean[]) t1 inner join seat on seatid=seat.id order by trainid;
 trainid | seatid |     status
---------+--------+-----------------
       1 |      1 | {f,f,f,f,f,f}
       1 |      2 | {f,f,f,f,f,f}
       1 |      3 | {f,f,f,f,f,f}
       1 |      4 | {f,f,f,f,f,f}
       2 |      5 | {f,f,f,f,f,f,f}
       2 |      6 | {f,f,f,f,f,f,f}
       2 |      7 | {f,f,f,f,f,f,f}
       2 |      8 | {f,f,f,f,f,f,f}
(8 rows)


train_ticket_booking_system=# SELECT DISTINCT ON (trainid) trainid, seatid, status
train_ticket_booking_system-# FROM (
train_ticket_booking_system(#     SELECT *
train_ticket_booking_system(#     FROM seat_status
train_ticket_booking_system(#     WHERE travel_date = '2000-06-25' AND status[1:5] = ARRAY[false, false, false, false, false]::boolean[]
train_ticket_booking_system(# ) t1
train_ticket_booking_system-# INNER JOIN seat ON seatid = seat.id
train_ticket_booking_system-# ORDER BY trainid;
 trainid | seatid |     status
---------+--------+-----------------
       1 |      1 | {f,f,f,f,f,f}
       2 |      5 | {f,f,f,f,f,f,f}
(2 rows)


train_ticket_booking_system=# SELECT trainid, COUNT(*) AS count
train_ticket_booking_system-# FROM (
train_ticket_booking_system(#     SELECT DISTINCT ON (trainid) trainid, seatid, status
train_ticket_booking_system(#     FROM (
train_ticket_booking_system(#         SELECT *
train_ticket_booking_system(#         FROM seat_status
train_ticket_booking_system(#         WHERE travel_date = '2000-06-25' AND status[1:5] = ARRAY[false, false, false, false, false]::boolean[]
train_ticket_booking_system(#     ) t1
train_ticket_booking_system(#     INNER JOIN seat ON seatid = seat.id
train_ticket_booking_system(#     ORDER BY trainid
train_ticket_booking_system(# ) subquery
train_ticket_booking_system-# GROUP BY trainid;
 trainid | count
---------+-------
       1 |     1
       2 |     1
(2 rows)


train_ticket_booking_system=# SELECT trainid, COUNT(*) AS count
train_ticket_booking_system-# FROM (
train_ticket_booking_system(#     SELECT seat_status.trainid, seatid, status
train_ticket_booking_system(#     FROM seat_status
train_ticket_booking_system(#     INNER JOIN seat ON seatid = seat.id
train_ticket_booking_system(#     WHERE seat_status.travel_date = '2000-06-25' AND seat_status.status[1:5] = ARRAY[false, false, false, false, false]::boolean[]
train_ticket_booking_system(# ) subquery
train_ticket_booking_system-# GROUP BY trainid;
ERROR:  column seat_status.trainid does not exist
LINE 3:     SELECT seat_status.trainid, seatid, status
                   ^
train_ticket_booking_system=# SELECT trainid, COUNT(*) AS count
train_ticket_booking_system-# from(
train_ticket_booking_system(# select trainid,seatid,status
train_ticket_booking_system(# from( select *
train_ticket_booking_system(# from seat_status
train_ticket_booking_system(# where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
train_ticket_booking_system(# ) t1
train_ticket_booking_system(# inner join seat on seatid = seat.id
train_ticket_booking_system(# order by trainid
train_ticket_booking_system(# ) subquery
train_ticket_booking_system-# group by trainid;
 trainid | count
---------+-------
       1 |     4
       2 |     4
(2 rows)


train_ticket_booking_system=# SELECT trainid, COUNT(*) AS count
train_ticket_booking_system-#   from(
train_ticket_booking_system(#   select trainid,seatid,status
train_ticket_booking_system(#   from( select *
train_ticket_booking_system(#   from seat_status
train_ticket_booking_system(#   where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
train_ticket_booking_system(#   ) t1
train_ticket_booking_system(#   inner join seat on seatid = seat.id
train_ticket_booking_system(#   order by trainid
train_ticket_booking_system(#   ) subquery
train_ticket_booking_system-#   group by trainid;
 trainid | count
---------+-------
       1 |     4
       2 |     4
(2 rows)


train_ticket_booking_system=# kjhg
train_ticket_booking_system-# kjhg
train_ticket_booking_system-# ckjv
train_ticket_booking_system-# jhg;
ERROR:  syntax error at or near "kjhg"
LINE 1: kjhg
        ^
train_ticket_booking_system=# SELECT
train_ticket_booking_system-#        t1.trainid,
train_ticket_booking_system-#        t1.stationid AS fromid,
train_ticket_booking_system-#        t2.stationid AS toid,
train_ticket_booking_system-#        t1.distance AS from_dt,
train_ticket_booking_system-#        t2.distance AS to_dt,
train_ticket_booking_system-#        t1.station_name AS from,
train_ticket_booking_system-#        t2.station_name AS to,
train_ticket_booking_system-#        t1.train_name,
train_ticket_booking_system-#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system-#    t1.rate_per_km,
train_ticket_booking_system-#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system-#    FROM
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system-#    INNER JOIN
train_ticket_booking_system-#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system-#    ON
train_ticket_booking_system-#        t1.trainid = t2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |     train_name      | totaldistance | rate_per_km | ticketcharge
---------+--------+------+---------+-------+---------+---------+---------------------+---------------+-------------+--------------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS    |            76 |           2 |          152
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS         |            96 |           2 |          192
       3 |      5 |    6 |     157 |   245 | madurai | chennai | NELLAI EXPRESS      |            88 |           2 |          176
       4 |      5 |    6 |     240 |   320 | madurai | chennai | KANYAKUMARI EXPRESS |            80 |           2 |          160
(4 rows)


train_ticket_booking_system=# select * from (
train_ticket_booking_system(# SELECT
train_ticket_booking_system(#        t1.trainid,
train_ticket_booking_system(#        t1.stationid AS fromid,
train_ticket_booking_system(#        t2.stationid AS toid,
train_ticket_booking_system(#        t1.distance AS from_dt,
train_ticket_booking_system(#        t2.distance AS to_dt,
train_ticket_booking_system(#        t1.station_name AS from,
train_ticket_booking_system(#        t2.station_name AS to,
train_ticket_booking_system(#        t1.train_name,
train_ticket_booking_system(#    t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#    t1.rate_per_km,
train_ticket_booking_system(#    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#    FROM
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'madurai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#    INNER JOIN
train_ticket_booking_system(#        (
train_ticket_booking_system(#            SELECT
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name AS station_name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name AS train_name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km,
train_ticket_booking_system(#                SUM(distance) AS total_distance
train_ticket_booking_system(#            FROM
train_ticket_booking_system(#                route
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                station ON stationid = station.id
train_ticket_booking_system(#            LEFT JOIN
train_ticket_booking_system(#                train ON trainid = train.id
train_ticket_booking_system(#            WHERE
train_ticket_booking_system(#                station.name = 'chennai'
train_ticket_booking_system(#            GROUP BY
train_ticket_booking_system(#                trainid,
train_ticket_booking_system(#                stationid,
train_ticket_booking_system(#                distance,
train_ticket_booking_system(#                station.name,
train_ticket_booking_system(#                code,
train_ticket_booking_system(#                train.name,
train_ticket_booking_system(#                number,
train_ticket_booking_system(#                rate_per_km
train_ticket_booking_system(#        ) t2
train_ticket_booking_system(#    ON
train_ticket_booking_system(#        t1.trainid = t2.trainid) sq1 inner join (
train_ticket_booking_system(#     SELECT trainid, COUNT(*) AS count
train_ticket_booking_system(#       from(
train_ticket_booking_system(#       select trainid,seatid,status
train_ticket_booking_system(#       from( select *
train_ticket_booking_system(#       from seat_status
train_ticket_booking_system(#       where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
train_ticket_booking_system(#       ) t1
train_ticket_booking_system(#       inner join seat on seatid = seat.id
train_ticket_booking_system(#       order by trainid
train_ticket_booking_system(#       ) subquery
train_ticket_booking_system(#       group by trainid) sq2 on sq1.trainid=sq2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |    train_name    | totaldistance | rate_per_km | ticketcharge | trainid | count
---------+--------+------+---------+-------+---------+---------+------------------+---------------+-------------+--------------+---------+-------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS |            76 |           2 |          152 |       1 |     4
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS      |            96 |           2 |          192 |       2 |     4
(2 rows)


train_ticket_booking_system=# select * from (
train_ticket_booking_system(#  SELECT
train_ticket_booking_system(#         t1.trainid,
train_ticket_booking_system(#         t1.stationid AS fromid,
train_ticket_booking_system(#         t2.stationid AS toid,
train_ticket_booking_system(#         t1.distance AS from_dt,
train_ticket_booking_system(#         t2.distance AS to_dt,
train_ticket_booking_system(#         t1.station_name AS from,
train_ticket_booking_system(#         t2.station_name AS to,
train_ticket_booking_system(#         t1.train_name,
train_ticket_booking_system(#     t2.distance-t1.distance totaldistance,
train_ticket_booking_system(#     t1.rate_per_km,
train_ticket_booking_system(#     (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
train_ticket_booking_system(#     FROM
train_ticket_booking_system(#         (
train_ticket_booking_system(#             SELECT
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name AS station_name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name AS train_name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km,
train_ticket_booking_system(#                 SUM(distance) AS total_distance
train_ticket_booking_system(#             FROM
train_ticket_booking_system(#                 route
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 station ON stationid = station.id
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 train ON trainid = train.id
train_ticket_booking_system(#             WHERE
train_ticket_booking_system(#                 station.name = 'madurai'
train_ticket_booking_system(#             GROUP BY
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km
train_ticket_booking_system(#         ) t1
train_ticket_booking_system(#     INNER JOIN
train_ticket_booking_system(#         (
train_ticket_booking_system(#             SELECT
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name AS station_name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name AS train_name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km,
train_ticket_booking_system(#                 SUM(distance) AS total_distance
train_ticket_booking_system(#             FROM
train_ticket_booking_system(#                 route
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 station ON stationid = station.id
train_ticket_booking_system(#             LEFT JOIN
train_ticket_booking_system(#                 train ON trainid = train.id
train_ticket_booking_system(#             WHERE
train_ticket_booking_system(#                 station.name = 'chennai'
train_ticket_booking_system(#             GROUP BY
train_ticket_booking_system(#                 trainid,
train_ticket_booking_system(#                 stationid,
train_ticket_booking_system(#                 distance,
train_ticket_booking_system(#                 station.name,
train_ticket_booking_system(#                 code,
train_ticket_booking_system(#                 train.name,
train_ticket_booking_system(#                 number,
train_ticket_booking_system(#                 rate_per_km
train_ticket_booking_system(#         ) t2
train_ticket_booking_system(#     ON
train_ticket_booking_system(#         t1.trainid = t2.trainid) sq1 inner join (
train_ticket_booking_system(#      SELECT trainid, COUNT(*) AS count
train_ticket_booking_system(#        from(
train_ticket_booking_system(#        select trainid,seatid,status
train_ticket_booking_system(#        from( select *
train_ticket_booking_system(#        from seat_status
train_ticket_booking_system(#        where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
train_ticket_booking_system(#        ) t1
train_ticket_booking_system(#        inner join seat on seatid = seat.id
train_ticket_booking_system(#        order by trainid
train_ticket_booking_system(#        ) subquery
train_ticket_booking_system(#        group by trainid) sq2 on sq1.trainid=sq2.trainid;
 trainid | fromid | toid | from_dt | to_dt |  from   |   to    |    train_name    | totaldistance | rate_per_km | ticketcharge | trainid | count
---------+--------+------+---------+-------+---------+---------+------------------+---------------+-------------+--------------+---------+-------
       1 |      5 |    6 |     174 |   250 | madurai | chennai | POTHIGAI EXPRESS |            76 |           2 |          152 |       1 |     4
       2 |      5 |    6 |     174 |   270 | madurai | chennai | QLN EXPRESS      |            96 |           2 |          192 |       2 |     4
(2 rows)


train_ticket_booking_system=# SELECT seat.trainid,train.name,count(travel_date) from seat left join seat_status on seat.id=seatid left join train on train.id=seat.trainid where travel_date='25-06-2000' group by seat.trainid,train.name
train_ticket_booking_system-# ;
 trainid |        name         | count
---------+---------------------+-------
       1 | POTHIGAI EXPRESS    |     4
       2 | QLN EXPRESS         |     4
       3 | NELLAI EXPRESS      |     4
       4 | KANYAKUMARI EXPRESS |     4
(4 rows)


train_ticket_booking_system=# INSERT INTO seat_status(seatid,status,travel_date) SELECT seat.id,train_status.seatstatus,? FROM seat RIGHT JOIN train_seatstatus ON seat.trainid=train_seatstatus.trainid;
ERROR:  syntax error at or near "FROM"
LINE 1: ...el_date) SELECT seat.id,train_status.seatstatus,? FROM seat ...
                                                             ^
train_ticket_booking_system=# INSERT INTO seat_status(seatid,status,travel_date) SELECT seat.id,train_status.seatstatus FROM seat RIGHT JOIN train_seatstatus ON seat.trainid=train_seatstatus.trainid;
ERROR:  missing FROM-clause entry for table "train_status"
LINE 1: ..._status(seatid,status,travel_date) SELECT seat.id,train_stat...
                                                             ^
train_ticket_booking_system=# INSERT INTO seat_status (seatid, status, travel_date)
train_ticket_booking_system-#                     SELECT seat.id, train_seatstatus.seatstatus, '2000-06-25'
train_ticket_booking_system-#                     FROM seat
train_ticket_booking_system-#                     RIGHT JOIN train_seatstatus ON seat.trainid = train_seatstatus.trainid;
INSERT 0 16
train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-21  | {f,f,f,f}
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
(33 rows)


train_ticket_booking_system=# alter table seat_status truncate table restart identity;
ERROR:  syntax error at or near "truncate"
LINE 1: alter table seat_status truncate table restart identity;
                                ^
train_ticket_booking_system=# truncate table seat_status restart identity;
TRUNCATE TABLE
train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date | status
--------+-------------+--------
(0 rows)


train_ticket_booking_system=# INSERT INTO seat_status (seatid, status, travel_date)
train_ticket_booking_system-#                     SELECT seat.id, train_seatstatus.seatstatus, '2000-06-25'
train_ticket_booking_system-#                     FROM seat
train_ticket_booking_system-#                     RIGHT JOIN train_seatstatus ON seat.trainid = train_seatstatus.trainid;
INSERT 0 16
train_ticket_booking_system=# select * from seat_status;
 seatid | travel_date |     status
--------+-------------+-----------------
      1 | 2000-06-25  | {f,f,f,f,f,f}
      2 | 2000-06-25  | {f,f,f,f,f,f}
      3 | 2000-06-25  | {f,f,f,f,f,f}
      4 | 2000-06-25  | {f,f,f,f,f,f}
      5 | 2000-06-25  | {f,f,f,f,f,f,f}
      6 | 2000-06-25  | {f,f,f,f,f,f,f}
      7 | 2000-06-25  | {f,f,f,f,f,f,f}
      8 | 2000-06-25  | {f,f,f,f,f,f,f}
      9 | 2000-06-25  | {f,f,f,f}
     10 | 2000-06-25  | {f,f,f,f}
     11 | 2000-06-25  | {f,f,f,f}
     12 | 2000-06-25  | {f,f,f,f}
     13 | 2000-06-25  | {f,f,f,f}
     14 | 2000-06-25  | {f,f,f,f}
     15 | 2000-06-25  | {f,f,f,f}
     16 | 2000-06-25  | {f,f,f,f}
(16 rows)


train_ticket_booking_system=#