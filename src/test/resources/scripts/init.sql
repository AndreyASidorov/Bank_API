CREATE TABLE IF NOT EXISTS user
(
    id IDENTITY PRIMARY KEY,
    user_name VARCHAR(20) not null,
    password VARCHAR(30) not null,
    version BIGINT
);

CREATE TABLE IF NOT EXISTS account (
                                       id IDENTITY PRIMARY KEY,
                                       number VARCHAR(10) not null,
                                       balance DECIMAL(15,2),
                                       user BIGINT not null REFERENCES user (id),
                                       version BIGINT
);

CREATE UNIQUE INDEX ON account(number);

CREATE TABLE IF NOT EXISTS card (
                                    id IDENTITY PRIMARY KEY,
                                    number VARCHAR(20),
                                    account BIGINT NOT NULL REFERENCES account (id),
                                    version BIGINT
);

--CREATE UNIQUE INDEX ON card(number);


MERGE INTO USER (id, password, user_name, version) VALUES(1, 'Andrey','1111', 0),
                                                (2, 'Test','test', 0);
MERGE INTO ACCOUNT (id, user, number, balance, version) VALUES ( 1, 1, '0000000001', 0, 0),
                                                      ( 2, 2, '0000000002', 5000, 0);
MERGE INTO CARD (id, account, number, version) VALUES (1, 1, '4276000011112222', 0),
                                              (2, 1, '4276999988887777', 0),
                                              (3, 2, '4276888877776666', 0),
                                              (4, 2, '4276777766665555', 0);