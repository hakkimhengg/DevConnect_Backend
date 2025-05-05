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

CREATE TABLE developers (
    developer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bio VARCHAR(500),
    address VARCHAR(200),
    profile_picture VARCHAR(255),
    cover_picture VARCHAR(255),
    vv VARCHAR(255),
    github_username VARCHAR(100),
    top_comment INTEGER,
    mvp_count INTEGER,
    top_one_count INTEGER,
    top_five_count INTEGER,
    employee_status BOOLEAN,
    job_type_id UUID,
    user_id UUID,
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
    profile_picture VARCHAR(255),
    cover_picture VARCHAR(255),
    user_id UUID REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- job_types
CREATE TABLE job_types (
   job_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   type_name VARCHAR(50)
);

-- jobs
CREATE TABLE jobs (
  job_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title VARCHAR(100),
  salary VARCHAR(50),
  location VARCHAR(200),
  status BOOLEAN,
  description VARCHAR(1000),
  post_date TIMESTAMP,
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
   skill_name VARCHAR(50)
);

-- developer_skill
CREATE TABLE developer_skill (
     dev_skill_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     developer_id UUID REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     skill_id UUID REFERENCES skill(skill_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- badge
CREATE TABLE badge (
   badge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   name VARCHAR(50),
   icon VARCHAR(255),
   description VARCHAR(500)
);

-- developer_badge
CREATE TABLE developer_badge (
     developer_id UUID REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     badge_id UUID REFERENCES badge(badge_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- comments
CREATE TABLE comments (
      comment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      text TEXT,
      total_upvotes INTEGER,
      created_at TIMESTAMP,
      edited_at TIMESTAMP,
      topic_id UUID REFERENCES topics(topic_id),
      parent_id UUID,
      user_id UUID REFERENCES app_users(user_id)
);

-- topics
CREATE TABLE topics (
    topic_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    content VARCHAR(1000),
    post_at TIMESTAMP,
    creator_id UUID REFERENCES app_users(user_id)
);

-- upvote
CREATE TABLE upvote (
    upvote_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    comment_id UUID REFERENCES comments(comment_id),
    user_id UUID REFERENCES app_users(user_id),
    UNIQUE (comment_id, user_id)
);

-- resume
CREATE TABLE resume (
    resume_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fullname VARCHAR(100),
    phone_number VARCHAR(20),
    address VARCHAR(200),
    email VARCHAR(100),
    dob DATE,
    position VARCHAR(100),
    description TEXT,
    information JSONB,
    developer_id UUID REFERENCES developers(developer_id)
);


-- join_job
CREATE TABLE join_job (
      join_job_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100),
      description TEXT,
      cv VARCHAR(255),
      is_approve BOOLEAN,
      job_id UUID REFERENCES jobs(job_id),
      developer_id UUID REFERENCES developers(developer_id)
);

-- code_challenge
CREATE TABLE code_challenge (
    challenge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100),
    description TEXT,
    test_case JSONB,
    problem_detail VARCHAR(1000),
    create_at TIMESTAMP,
    score INTEGER,
    creator_id UUID REFERENCES app_users(user_id)
);

-- submission
CREATE TABLE submission (
    submission_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER,
    submit_time VARCHAR(50),
    challenge_id UUID REFERENCES code_challenge(challenge_id),
    developer_id UUID REFERENCES developers(developer_id)
);

-- hackathons
CREATE TABLE hackathons (
    hackathon_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100),
    description VARCHAR(1000),
    start_at TIMESTAMP,
    finished_at TIMESTAMP,
    created_at TIMESTAMP,
    is_available BOOLEAN,
    creator_id UUID REFERENCES app_users(user_id)
);

-- join_hackathon
CREATE TABLE join_hackathon (
    join_hackathon_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER,
    developer_id UUID REFERENCES developers(developer_id),
    hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- hackathon_certificate
CREATE TABLE hackathon_certificate (
   certificate_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   description TEXT,
   issued_date TIMESTAMP,
   developer_id UUID REFERENCES developers(developer_id),
   hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- volunteers
CREATE TABLE volunteers (
    volunteers_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    score INTEGER,
    joined_at TIMESTAMP,
    developer_id UUID REFERENCES developers(developer_id),
    hackathon_id UUID REFERENCES hackathons(hackathon_id)
);

-- projects
CREATE TABLE projects (
      project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100),
      description VARCHAR(1000),
      max_member INTEGER,
      created_at TIMESTAMP,
      owner_id UUID REFERENCES app_users(user_id)
);

-- positions
CREATE TABLE positions (
   position_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   position_name VARCHAR(50)
);

-- position_limits
CREATE TABLE position_limits (
     position_limit_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     max_members INTEGER,
     project_id UUID REFERENCES projects(project_id),
     positions_id UUID REFERENCES positions(position_id)
);

-- project_teams
CREATE TABLE project_teams (
   project_team_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   joined_at TIMESTAMP,
   developer_id UUID REFERENCES developers(developer_id),
   project_id UUID REFERENCES projects(project_id),
   positions_id UUID REFERENCES positions(position_id)
);

-- join_project
CREATE TABLE join_project (
  join_project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title VARCHAR(100),
  description TEXT,
  is_approved BOOLEAN,
  project_id UUID REFERENCES projects(project_id),
  developer_id UUID REFERENCES developers(developer_id)
);

-- bookmarks
CREATE TABLE bookmarks (
   bookmark_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   created_at TIMESTAMP,
   target_id UUID,
   target_type VARCHAR(50),
   bookmark_by UUID REFERENCES app_users(user_id)
);