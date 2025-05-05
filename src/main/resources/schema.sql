CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE app_users
(
    user_id uuid PRIMARY KEY DEFAULT uuid_generate_v4() ,
    first_name  VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email     VARCHAR(50) NOT NULL UNIQUE ,
    password  VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR,
    is_recruiter BOOLEAN DEFAULT false,
    is_verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

drop table app_users