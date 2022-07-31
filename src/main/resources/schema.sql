;             
CREATE USER IF NOT EXISTS "" SALT '' HASH '' ADMIN;           
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_85A148F3_3917_4042_AF09_429B3AF08E40" START WITH 5 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_EF98C3C7_4EE9_4B68_8D8E_4054D14C4D46" START WITH 4 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D83FF26F_EAA8_42D7_A2AB_9B42E880CB75" START WITH 3 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_0C9FCAE5_024B_4F9B_B075_334AA5EC2577" START WITH 3 BELONGS_TO_TABLE;
CREATE CACHED TABLE "PUBLIC"."QUESTIONS" COMMENT 'Contains the list of job questions'(
    "TITLE" VARCHAR(200) NOT NULL,
    "CONTENT" VARCHAR(300) NOT NULL,
    "IMAGE" BLOB,
    "ID" INTEGER DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_0C9FCAE5_024B_4F9B_B075_334AA5EC2577") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_0C9FCAE5_024B_4F9B_B075_334AA5EC2577"
);          
ALTER TABLE "PUBLIC"."QUESTIONS" ADD CONSTRAINT "PUBLIC"."QUESTIONS_PK" PRIMARY KEY("ID");    
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.QUESTIONS;               
INSERT INTO "PUBLIC"."QUESTIONS" VALUES
('Can you disable JIT?', '', X'', 1),
('What''s JDK?', '', X'', 2); 
CREATE CACHED TABLE "PUBLIC"."ANSWERS" COMMENT 'Contains the answers to questions'(
    "CONTENT" VARCHAR(5000) NOT NULL,
    "IMAGE" BLOB,
    "ID" INTEGER DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D83FF26F_EAA8_42D7_A2AB_9B42E880CB75") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D83FF26F_EAA8_42D7_A2AB_9B42E880CB75"
);
ALTER TABLE "PUBLIC"."ANSWERS" ADD CONSTRAINT "PUBLIC"."ANSWERS_PK" PRIMARY KEY("ID");        
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.ANSWERS; 
INSERT INTO "PUBLIC"."ANSWERS" VALUES
(STRINGDECODE('Yes, we can disable the JIT compiler by providing the options \u2013Xint or \u2013DJava.compiler=NONE to the Java command.'), NULL, 1),
(STRINGDECODE('JDK is an environment using which we can develop and compile Java applications or even applets. So, I would say this is a developer\u2019s bundle. JRE is essentially part of JDK nowadays.'), NULL, 2);        
CREATE CACHED TABLE "PUBLIC"."STUDENTS" COMMENT 'Contains the list of students'(
    "ID" INTEGER DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_85A148F3_3917_4042_AF09_429B3AF08E40") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_85A148F3_3917_4042_AF09_429B3AF08E40",
    "NAME" VARCHAR(200) NOT NULL
);          
ALTER TABLE "PUBLIC"."STUDENTS" ADD CONSTRAINT "PUBLIC"."STUDENTS_PK" PRIMARY KEY("ID");      
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.STUDENTS;
INSERT INTO "PUBLIC"."STUDENTS" VALUES
(1, 'David'),
(2, 'Mike'),
(4, 'Helen');            
CREATE CACHED TABLE "PUBLIC"."SESSIONS"(
    "ID" INTEGER DEFAULT (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_EF98C3C7_4EE9_4B68_8D8E_4054D14C4D46") NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_EF98C3C7_4EE9_4B68_8D8E_4054D14C4D46",
    "QUESTION" INTEGER NOT NULL,
    "STUDENT" INTEGER NOT NULL
);  
ALTER TABLE "PUBLIC"."SESSIONS" ADD CONSTRAINT "PUBLIC"."SESSIONS_PK" PRIMARY KEY("ID");      
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.SESSIONS;
INSERT INTO "PUBLIC"."SESSIONS" VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1);    
ALTER TABLE "PUBLIC"."SESSIONS" ADD CONSTRAINT "PUBLIC"."SESSIONS_FK_2" FOREIGN KEY("QUESTION") REFERENCES "PUBLIC"."QUESTIONS"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."SESSIONS" ADD CONSTRAINT "PUBLIC"."SESSIONS_FK_3" FOREIGN KEY("STUDENT") REFERENCES "PUBLIC"."STUDENTS"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."ANSWERS" ADD CONSTRAINT "PUBLIC"."QUESTION" FOREIGN KEY("ID") REFERENCES "PUBLIC"."QUESTIONS"("ID") NOCHECK;            
