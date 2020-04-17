CREATE TABLE user(
    username text PRIMARY KEY,
    password text NOT NULL,
    age integer NOT NULL,
    interest text NOT NULL)
;

CREATE TABLE room(
    roomID integer PRIMARY KEY AUTOINCREMENT,
    roomName text NOT NULL,
    rules text NOT NULL,
    category text NOT NULL)
;