insert into remittance(id,conduit,status,source_Amount) values (1,'ML','O',5000);
insert into remittance(id,conduit,status,source_Amount) values (2,'ML','P',5000);
insert into remittance(id,conduit,status,source_Amount) values (3,'SB','O',8000);
insert into remittance(id,conduit,status,source_Amount) values (4,'ML','P',3000);
insert into remittance(id,conduit,status,source_Amount) values (5,'ML','P',9000);
insert into remittance(id,conduit,status,source_Amount) values (6,'ML','P',8000);
insert into remittance(id,conduit,status,source_Amount) values (7,'SB','O',6000);
insert into remittance(id,conduit,status,source_Amount) values (8,'ML','P',7000);
insert into remittance(id,conduit,status,source_Amount) values (9,'ML','O',2000);
insert into remittance(id,conduit,status,source_Amount) values (10,'ML','O',3000);

insert into conduit(conduit_name, balance, credit_limit, total_remit) values ('ML', 2000, 10000, 0);
insert into conduit(conduit_name, balance, credit_limit, total_remit) values ('SB', 1000, 10000, 0);