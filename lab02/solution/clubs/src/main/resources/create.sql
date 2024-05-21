create table stadium(id SERIAL PRIMARY KEY, name TEXT, city TEXT, country TEXT);
create table club(id SERIAL PRIMARY KEY, name TEXT,  stadium_id INT, FOREIGN KEY (stadium_id) REFERENCES stadium(id));
