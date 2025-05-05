CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE app_users
(
    user_id uuid PRIMARY KEY DEFAULT uuid_generate_v4() ,
    first_name  VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL UNIQUE ,
    password  VARCHAR(100) NOT NULL,
    is_recruiter BOOLEAN DEFAULT false,
    is_verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE developers (
    developer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bio VARCHAR(500),
    address VARCHAR(200),
    profile_picture VARCHAR(255),
    cover_picture VARCHAR(255),
    cv VARCHAR(255),
    github_username VARCHAR(100),
    top_comment INTEGER DEFAULT 0,
    mvp_count INTEGER DEFAULT 0,
    top_one_count INTEGER DEFAULT 0,
    employee_status BOOLEAN DEFAULT FALSE,
    job_type_id UUID not null ,
    user_id UUID not null ,
    FOREIGN KEY (job_type_id) REFERENCES job_types(job_type_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- recruiters
CREATE TABLE recruiters (
    recruiter_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    company_name VARCHAR(100),
    industry VARCHAR(100),
    company_location VARCHAR(200),
    bio TEXT,
    establish_date TIMESTAMP,
    profile_picture VARCHAR(255),
    cover_picture VARCHAR(255),
    user_id UUID REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- job_types
CREATE TABLE job_types (
   job_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   type_name VARCHAR(50) not null unique
);

-- jobs
CREATE TABLE jobs (
      job_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100) not null ,
      salary VARCHAR(50) not null ,
      location VARCHAR(200) not null ,
      status BOOLEAN DEFAULT FALSE,
      description VARCHAR(100),
      posted_date TIMESTAMP default current_timestamp,
      creator_id UUID REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- job_skill
CREATE TABLE job_skill (
   job_skill_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   job_id UUID REFERENCES jobs(job_id) ON DELETE CASCADE ON UPDATE CASCADE,
   skill_id UUID REFERENCES skill(skill_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- skill
CREATE TABLE skill (
   skill_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   skill_name VARCHAR(50) not null unique
);

-- developer_skill
CREATE TABLE developer_skill (
     dev_skill_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     developer_id UUID REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     skill_id UUID REFERENCES skill(skill_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- badge
CREATE TABLE badges (
    badge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) not null unique ,
    icon VARCHAR(255) not null ,
    description VARCHAR(100) not null
);

-- developer_badge
CREATE TABLE developer_badge (
     developer_id UUID REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     badge_id UUID REFERENCES badges(badge_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- comments
CREATE TABLE comments (
      comment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      text TEXT not null ,
      total_upvotes INTEGER DEFAULT 0,
      created_at TIMESTAMP DEFAULT current_timestamp,
      edited_at TIMESTAMP,
      topic_id UUID REFERENCES topics(topic_id),
      parent_id UUID,
      user_id UUID REFERENCES app_users(user_id)
);

-- topics
CREATE TABLE topics (
    topic_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    content VARCHAR not null ,
    created_at TIMESTAMP DEFAULT current_timestamp,
    creator_id UUID REFERENCES app_users(user_id)
);

-- upvote
CREATE TABLE upvote (
    comment_id UUID REFERENCES comments(comment_id),
    user_id UUID REFERENCES app_users(user_id),
    PRIMARY KEY (comment_id, user_id)
);

-- resume
CREATE TABLE resume (
    resume_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fullname VARCHAR(100) not null ,
    phone_number VARCHAR(20) not null ,
    address VARCHAR(200) not null ,
    email VARCHAR(100) not null ,
    dob DATE not null ,
    position VARCHAR(100) not null ,
    description TEXT not null ,
    information JSONB not null ,
    developer_id UUID REFERENCES developers(developer_id)
);


-- join_job
CREATE TABLE join_job (
      join_job_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100) not null ,
      description TEXT not null ,
      is_approve BOOLEAN DEFAULT FALSE,
      job_id UUID REFERENCES jobs(job_id),
      developer_id UUID REFERENCES developers(developer_id)
);

-- code_challenge
CREATE TABLE code_challenge (
    challenge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) not null ,
    description TEXT not null ,
    test_case JSONB not null ,
    problem_detail VARCHAR not null ,
    created_at TIMESTAMP DEFAULT current_timestamp,
    score INTEGER DEFAULT 0,
    creator_id UUID REFERENCES app_users(user_id)
);

-- submission
CREATE TABLE submission (
    submission_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER DEFAULT 0,
    submit_time VARCHAR(50) not null ,
    challenge_id UUID REFERENCES code_challenge(challenge_id),
    developer_id UUID REFERENCES developers(developer_id)
);

-- hackathons
CREATE TABLE hackathons (
    hackathon_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) not null ,
    description VARCHAR(1000) not null ,
    start_at TIMESTAMP,
    finished_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT current_timestamp,
    is_available BOOLEAN DEFAULT false,
    creator_id UUID REFERENCES app_users(user_id)
);

-- join_hackathon
CREATE TABLE join_hackathon (
    join_hackathon_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER DEFAULT 0,
    developer_id UUID REFERENCES developers(developer_id),
    hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- hackathon_certificate
CREATE TABLE hackathon_certificate (
   certificate_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   description TEXT not null ,
   issued_date TIMESTAMP not null ,
   developer_id UUID REFERENCES developers(developer_id),
   hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- volunteers
CREATE TABLE volunteers (
    volunteers_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER DEFAULT 0,
    joined_at TIMESTAMP DEFAULT current_timestamp,
    developer_id UUID REFERENCES developers(developer_id),
    hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- projects
CREATE TABLE projects (
      project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100) not null ,
      description VARCHAR not null ,
      max_member INTEGER DEFAULT 0,
      created_at TIMESTAMP DEFAULT current_timestamp,
      owner_id UUID REFERENCES app_users(user_id)
);

-- positions
CREATE TABLE positions (
   position_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   position_name VARCHAR(50) not null unique
);

-- position_limits
CREATE TABLE position_limits (
     position_limit_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     max_members INTEGER DEFAULT 0,
     project_id UUID REFERENCES projects(project_id),
     positions_id UUID REFERENCES positions(position_id)
);

-- project_teams
CREATE TABLE project_teams (
   project_team_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   joined_at TIMESTAMP DEFAULT current_timestamp,
   developer_id UUID REFERENCES developers(developer_id),
   project_id UUID REFERENCES projects(project_id),
   positions_id UUID REFERENCES positions(position_id)
);

-- join_project
CREATE TABLE join_project (
  join_project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title VARCHAR(100) not null ,
  description TEXT not null ,
  is_approved BOOLEAN DEFAULT false,
  project_id UUID REFERENCES projects(project_id),
  developer_id UUID REFERENCES developers(developer_id)
);

-- bookmarks
CREATE TABLE bookmarks (
   created_at TIMESTAMP DEFAULT current_timestamp,
   target_id UUID not null ,
   target_type VARCHAR(50) not null ,
   bookmark_by UUID REFERENCES app_users(user_id),
   PRIMARY KEY (target_id, bookmark_by)
);