create table plan_coverage(
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar NOT NULL ,
   PRIMARY KEY (`ID`));


insert into plan_coverage (name) values
('Preventive Care'),
('Outpatient Services'),
('Inpatient Hospital Care'),
('Maternity'),
('Emergency And Urgent Care'),
('Prescription Drugs');


create table plan_coverage_mapping(
  `id` int NOT NULL AUTO_INCREMENT,
  `plan_coverage_id` int NOT NULL ,
  `sub_category` varchar NOT NULL,
  `first_plan_id` varchar NOT NULL,
  `second_plan_id` varchar NOT NULL,
  `third_plan_id` varchar NOT NULL,
   PRIMARY KEY (`ID`),
   FOREIGN KEY (plan_coverage_id) REFERENCES plan_coverage(id));
   
insert into plan_coverage_mapping (plan_coverage_id,sub_category,first_plan_id,second_plan_id,third_plan_id) values
(1,'ROUTINE PHYSICAL EXAM, MAMMOGRAMS, ETC.','No Charge','No Charge','No Charge'),
(2,'PRIMARY CARE OFFICE VISIT','40','50','60'),
(2,'SPECIALTY CARE OFFICE VISIT','70','80','90'),
(2,'X-RAYS, ETC.','40%','50%','60%'),
(2,'LAB TESTS','80','90','100'),
(2,'MRI, CT, PET','40','50','60'),
(2,'OUTPATIENT SURGERY','40','50','60'),
(2,'MENTAL HEALTH VISIT','60','70','80'),
(3,'ROOM AND BOARD','40','50','60'),
(3,'SURGERY','40','50','60'),
(3,'ANESTHESIA','40','50','60'),
(3,'X-RAYS','40','50','60'),
(3,'LAB TESTS','40','50','60'),
(3,'MEDICATIONS','40','50','60'),
(3,'MENTAL HEALTH CARE','40','50','60'),
(4,'ROUTINE PRENATAL CARE VISIT','40','40','30'),
(4,'FIRST POSTPARTUM VISIT','40','40','30'),
(4,'DELIVERY AND INPATIENT WELL-BABY CARE','40','40','30'),
(5,'EMERGENCY DEPARTMENT VISIT','40','50','60'),
(5,'URGENT CARE VISIT','40','US$ 100','US$ 120'),
(5,'AMBULANCE SERVICES','40','40','30'),
(6,'GENERIC','60','70','80'),
(6,'PREFERRED BRAND','50','60','70'),
(6,'NON-PREFERRED BRAND','40','50','60'),
(6,'SPECIALTY','40','50','60');


create table plan_description(
  `id` int NOT NULL AUTO_INCREMENT,
 `plan_id` varchar NOT NULL,
  `plan_name` varchar NOT NULL,
  `coverage_type` varchar NOT NULL,
  `estimated_premium` varchar NOT NULL,
  `annual_deductiable_individual` varchar NOT NULL,
  `annual_deductiable_family` varchar NOT NULL,
   PRIMARY KEY (`id`));

   
insert into plan_description (plan_id,plan_name,coverage_type,estimated_premium,annual_deductiable_individual,annual_deductiable_family) values
('P001','40','Family','US$ 962.00','6000','12000'),
('P002','50','Family','US$ 1017.00','5000','10000'),
('P003','20','Family','US$ 1,045.67','4000','8000');


create table policy_data(
 `id` int NOT NULL AUTO_INCREMENT,
 `policy_id` int NOT NULL,
  `policy_holder_id`int NOT NULL,
  `first_name` varchar NOT NULL,
  `last_name` varchar NOT NULL,
  `plan_id` varchar NOT NULL,
  `coverage_start_date` varchar NOT NULL,
  `coverage_end_date` varchar NOT NULL,
  `ACCUMULATED_DEDUCTIABLE` double NOT NULL,
  `deductiable` double NOT NULL,
   PRIMARY KEY (`ID`));

   
insert into policy_data (policy_id,policy_holder_id,first_name,last_name,plan_id,coverage_start_date,coverage_end_date,ACCUMULATED_DEDUCTIABLE,deductiable) values
(100001,1000011,'Sam','Collins','P001','1/1/2004 12:00:00 AM','',0.00,0),
(100001,1000012,'Jina','Collins','P001','5/12/2008 12:00:00 AM','',0.00,0),
(100001,1000013,'Nancy','Collins','P001','6/5/2010 12:00:00 AM','',0.00,0),
(100001,1000014,'Jack','Collins','P001','8/7/2013 12:00:00 AM','',0.00,0),
(100002,1000021,'Daniel','Hayer','P002','1/1/2012 12:00:00 AM','',4000.00,4000.00),
(100003,1000031,'Sally','Adams','P001','1/1/2016 12:00:00 AM','',2500.00,2500.00),
(100004,1000041,'Jacke','Seegers','P003','1/1/2009 12:00:00 AM','31/5/2016 12:00:00 AM',5560.42,5560.42),
(100005,1000051,'Tom','Haskel','P001','1/1/2011 12:00:00 AM','',6712.34,6712.34),
(100006,1000061,'San','Mildred','P001','5/1/2016 12:00:00 AM','',3250.61,3250.61),
(100007,1000071,'Mack','Lee','P003','6/12/2011 12:00:00 AM','1/9/2016 12:00:00 AM',4460.82,4460.82);




