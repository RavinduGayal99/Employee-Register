CREATE TABLE 'employee' (
    'id' INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    'name' VARCHAR(30),
    'email' VARCHAR(50) NOT NULL UNIQUE,
    'birthday' DATE,
    'profile_picture' VARCHAR(255),
    'current_age_in_days' INT(10)
);

CREATE TABLE 'users' (
    'id' INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    'username' VARCHAR(30) NOT NULL UNIQUE,
    'password' VARCHAR(20) NOT NULL,
    'role_id' INT,
    FOREIGN KEY ('role_id') REFERENCES 'roles' ('id')
);

CREATE TABLE 'roles' (
    'id' INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    'name' VARCHAR(30) NOT NULL UNIQUE
);

INSERT INTO 'roles' ('name') VALUES ('user'), ('admin');