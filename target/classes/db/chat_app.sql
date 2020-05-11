CREATE TABLE user(
    username text PRIMARY KEY,
    password text NOT NULL,
    age integer NOT NULL,
    interest text NOT NULL,
    admin integer NOT NULL DEFAULT 0,
    status integer NOT NULL DEFAULT 0)
;

CREATE TABLE room(
    roomID integer PRIMARY KEY AUTOINCREMENT,
    roomName text NOT NULL,
    rules text NOT NULL,
    category text NOT NULL)
;

CREATE TABLE messeges(
    id integer PRIMARY KEY AUTOINCREMENT,
    roomID integer NOT NULL,
    sender text NOT NULL,
    message text NOT NULL,
    FOREIGN KEY (roomID) REFERENCES room(roomID)
);