CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS app_users
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

CREATE TABLE IF NOT EXISTS developers (
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
CREATE TABLE IF NOT EXISTS recruiters (
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
CREATE TABLE IF NOT EXISTS job_types (
   job_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   type_name VARCHAR(50) not null unique
);

-- jobs
CREATE TABLE IF NOT EXISTS jobs (
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
CREATE TABLE IF NOT EXISTS job_skills (
    job_id UUID NOT NULL,
    skill_id UUID NOT NULL,
    CONSTRAINT fk_job_id FOREIGN KEY (job_id) REFERENCES jobs(job_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skills(skill_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_job_skill PRIMARY KEY (job_id, skill_id)
);

-- skill
CREATE TABLE IF NOT EXISTS skills (
   skill_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   skill_name VARCHAR(50) not null unique
);

-- developer_skill
CREATE TABLE IF NOT EXISTS developer_skills (
     developer_id UUID NOT NULL,
    skill_id UUID NOT NULL,
    CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skills(skill_id) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT pk_developer_skill PRIMARY KEY (developer_id, skill_id)
);

-- badge
CREATE TABLE IF NOT EXISTS badges (
    badge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(50) not null unique ,
    icon VARCHAR(255) not null ,
    description VARCHAR(100) not null
);

-- developer_badge
CREATE TABLE IF NOT EXISTS developer_badges (
    developer_id UUID NOT NULL,
    badge_id UUID NOT NULL,
    CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_badge_id FOREIGN KEY (badge_id) REFERENCES badges(badge_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_developer_badge PRIMARY KEY (developer_id, badge_id)
);

-- comments
CREATE TABLE IF NOT EXISTS comments (
      comment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      text TEXT not null ,
      total_upvotes INTEGER DEFAULT 0,
      created_at TIMESTAMP DEFAULT current_timestamp,
      edited_at TIMESTAMP,
      topic_id UUID REFERENCES topics(topic_id) on delete cascade on update cascade,
      parent_id UUID REFERENCES comments(comment_id) on delete cascade on update cascade,
      user_id UUID REFERENCES app_users(user_id) on delete cascade on update cascade
);

-- topics
CREATE TABLE IF NOT EXISTS topics (
    topic_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    content VARCHAR not null ,
    created_at TIMESTAMP DEFAULT current_timestamp,
    creator_id UUID REFERENCES app_users(user_id) on delete cascade on update cascade
);

-- upvote
CREATE TABLE IF NOT EXISTS upvote (
    comment_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_comment_id FOREIGN KEY (comment_id) REFERENCES comments(comment_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_upvote PRIMARY KEY (comment_id, user_id)
);

-- resume
CREATE TABLE IF NOT EXISTS resumes (
    resume_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fullname VARCHAR(100) not null ,
    phone_number VARCHAR(20) not null ,
    address VARCHAR(200) not null ,
    email VARCHAR(100) not null ,
    dob DATE not null ,
    position VARCHAR(100) not null ,
    description TEXT not null ,
    information JSONB not null ,
    developer_id UUID REFERENCES developers(developer_id) on delete cascade on update cascade
);

-- join_job
CREATE TABLE IF NOT EXISTS join_jobs (
    title VARCHAR(100) not null ,
    description TEXT not null ,
    is_approve BOOLEAN DEFAULT FALSE,
    job_id UUID NOT NULL,
    developer_id UUID NOT NULL,
    CONSTRAINT fk_job_id FOREIGN KEY (job_id) REFERENCES jobs(job_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_join_job PRIMARY KEY (developer_id, job_id)
);

-- code_challenge
CREATE TABLE IF NOT EXISTS code_challenges (
    challenge_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) not null ,
    description TEXT not null ,
    test_case JSONB not null ,
    problem_detail VARCHAR not null ,
    created_at TIMESTAMP DEFAULT current_timestamp,
    score INTEGER DEFAULT 0,
    creator_id UUID REFERENCES app_users(user_id) on delete cascade on update cascade
);

-- submission
CREATE TABLE IF NOT EXISTS submissions (
    score INTEGER DEFAULT 0,
    submit_time VARCHAR(50) not null ,
    challenge_id UUID NOT NULL,
    developer_id UUID NOT NULL,
    CONSTRAINT fk_challenge_id FOREIGN KEY (challenge_id) REFERENCES code_challenges(challenge_id) ON DELETE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_submission PRIMARY KEY (challenge_id, developer_id)
);

-- hackathons
CREATE TABLE IF NOT EXISTS hackathons (
    hackathon_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100) not null ,
    description VARCHAR(1000) not null ,
    start_at TIMESTAMP,
    finished_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT current_timestamp,
    is_available BOOLEAN DEFAULT false,
    creator_id UUID REFERENCES app_users(user_id) on delete cascade on update cascade
);

-- join_hackathon
CREATE TABLE IF NOT EXISTS join_hackathons (
    score INTEGER DEFAULT 0,
    joined_at TIMESTAMP DEFAULT current_timestamp,
    developer_id UUID NOT NULL,
    hackathon_id UUID NOT NULL,
    CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_hackathon_id FOREIGN KEY (hackathon_id) REFERENCES hackathons(hackathon_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_join_hackathon PRIMARY KEY (developer_id, hackathon_id)
);

-- hackathon_certificate
CREATE TABLE IF NOT EXISTS hackathon_certificate (
   description TEXT not null ,
   issued_date TIMESTAMP not null ,
   developer_id UUID NOT NULL,
   hackathon_id UUID NOT NULL,
   CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_hackathon_id FOREIGN KEY (hackathon_id) REFERENCES hackathons(hackathon_id) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT pk_join_hackathon PRIMARY KEY (developer_id, hackathon_id)
);

-- projects
CREATE TABLE IF NOT EXISTS projects (
      project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
      title VARCHAR(100) not null ,
      description VARCHAR not null ,
      is_open BOOLEAN DEFAULT true,
      created_at TIMESTAMP DEFAULT current_timestamp,
      owner_id UUID REFERENCES app_users(user_id) on delete cascade on update cascade
);

-- positions
CREATE TABLE IF NOT EXISTS positions (
   position_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   position_name VARCHAR(50) not null unique
);

-- position_limits
CREATE TABLE IF NOT EXISTS project_positions (
     position_limit_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     max_members INTEGER DEFAULT 0,
     project_id UUID NOT NULL,
     positions_id UUID NOT NULL,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_positions_id FOREIGN KEY (positions_id) REFERENCES positions(position_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT pk_project_positions PRIMARY KEY (project_id, positions_id)
);

-- join_project
CREATE TABLE IF NOT EXISTS join_projects (
      title VARCHAR(100) not null ,
      description TEXT not null ,
      is_approved BOOLEAN DEFAULT false,
      project_id UUID NOT NULL ,
      developer_id UUID NOT NULL,
      CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT fk_developer_id FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT pk_join_project PRIMARY KEY (project_id, developer_id)
);

CREATE TABLE IF NOT EXISTS project_skills (
      project_id UUID NOT NULL,
      skill_id UUID NOT NULL,
      CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skills(skill_id) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT pk_project_skill UNIQUE (project_id, skill_id)
);

-- bookmarks
CREATE TABLE IF NOT EXISTS bookmarks (
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     target_id UUID NOT NULL,
     target_type VARCHAR(50) NOT NULL,
     bookmark_by UUID NOT NULL,
     CONSTRAINT fk_bookmark_by FOREIGN KEY (bookmark_by) REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT pk_bookmarks PRIMARY KEY (target_id, bookmark_by, target_type),
     CONSTRAINT chk_target_type CHECK (target_type IN ('project', 'comment', 'hackathon', 'recruiter', 'developer', 'job', ''))
);
