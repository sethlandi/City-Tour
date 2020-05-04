BEGIN TRANSACTION;

DROP TABLE IF EXISTS userinfo           cascade;
DROP TABLE IF EXISTS useritinerary      cascade;
DROP TABLE IF EXISTS itinerarydetails   cascade;
DROP TABLE IF EXISTS users              cascade;
DROP TABLE IF EXISTS itinerary          cascade;

DROP SEQUENCE IF EXISTS users_userid_seq;     
DROP SEQUENCE IF EXISTS itinerary_itineraryid_seq;
DROP SEQUENCE IF EXISTS itinerarydetails_itinerarydetailsid_seq;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE TABLE users (
         userid                 SERIAL                          PRIMARY KEY
        ,username               VARCHAR(255)    NOT NULL        UNIQUE    
        ,password               VARCHAR(32)     NOT NULL      
        ,salt                   VARCHAR(256)    NOT NULL       
        ,role                   VARCHAR(255)    NOT NULL        DEFAULT('user')
);

CREATE TABLE itinerary (
         itineraryid            SERIAL                          PRIMARY KEY
        ,itineraryname          VARCHAR(256)    NOT NULL       
);

CREATE TABLE userinfo (
         userid                 INT             NOT NULL        REFERENCES users(userid)
        ,username               VARCHAR(255)    NOT NULL        UNIQUE
        ,email                  VARCHAR(32)     NOT NULL        UNIQUE
        ,firstname              VARCHAR(32)     NOT NULL
        ,lastname               VARCHAR(32)     NOT NULL
        ,phonenumber            BIGINT          NOT Null        UNIQUE      
);

CREATE TABLE useritinerary (
         itineraryid            INT             NOT NULL        REFERENCES itinerary(itineraryid)
        ,userid                 INT             NOT NULL        REFERENCES users(userid) 
);

CREATE TABLE itinerarydetails (
         itineraryid            INT             NOT NULL        REFERENCES itinerary(itineraryid)
        ,pickedlocation         VARCHAR (255)   NOT NULL
        ,dateselected           VARCHAR(255)    NOT NULL
        ,apiplaceid             VARCHAR(255)    NOT NULL
        ,liked                  INT             NOT NULL
);

INSERT INTO users (userid, username, password, salt, role) VALUES (1, 'TheKing', '1234567890', '9812384760976234', 'user');
INSERT INTO users (userid, username, password, salt, role) VALUES (2, 'WanderingSloth', '0987654321', '1234567890-=', 'user');
INSERT INTO users (userid, username, password, salt, role) VALUES (3, 'BurntToast', 'ILoatheMyself', '=-0987654321', 'user');

INSERT INTO itinerary (itineraryid, itineraryname) VALUES (1, 'Party Time');
INSERT INTO itinerary (itineraryid, itineraryname) VALUES (2, 'First Time');
INSERT INTO itinerary (itineraryid, itineraryname) VALUES (3, 'School Trip');

INSERT INTO userinfo (userid, username, email, firstName, lastName, phoneNumber) VALUES (1, 'TheKing', 'RichAF@gmail.com', 'Richard', 'Big', 1234567890);
INSERT INTO userinfo (userid, username, email, firstName, lastName, phoneNumber) VALUES (2, 'WanderingSloth', 'Slow@yahoo.com', 'Hun', 'Lo', 0987654321);
INSERT INTO userinfo (userid, username, email, firstName, lastName, phoneNumber) VALUES (3, 'BurntToast', 'Bread@gmail.com', 'Poop', 'Smith', 9813453872);

INSERT INTO useritinerary (userid, itineraryid) VALUES (1,1);
INSERT INTO useritinerary (userid, itineraryid) VALUES (2,2);
INSERT INTO useritinerary (userid, itineraryid) VALUES (1,3);

INSERT INTO itinerarydetails (itineraryid, pickedlocation, dateselected, apiplaceid, liked) VALUES (1, 'Airport', '01-12-2021', '1', 0);
INSERT INTO itinerarydetails (itineraryid, pickedlocation, dateselected, apiplaceid, liked) VALUES (2, 'Hotel Vizza', '01-12-2022', '2', 1);
INSERT INTO itinerarydetails (itineraryid, pickedlocation, dateselected, apiplaceid, liked) VALUES (3, 'House of Blues', '05-05-2020', '3', 0);


COMMIT;