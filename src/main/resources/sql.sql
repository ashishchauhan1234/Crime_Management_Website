-- ===========================================
-- Crime Management System - Production SQL
-- ===========================================

-- 1. ROLES & USERS (Auth & RBAC)
-- -----------------------------------

-- Enable CITEXT for case-insensitive emails
CREATE EXTENSION IF NOT EXISTS citext;

-- ROLES TABLE
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL -- ADMIN, OFFICER, ANALYST, PUBLIC
);

-- USERS
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);

-- USER ↔ ROLE (MANY-TO-MANY)
CREATE TABLE user_roles (
    user_id INT REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    role_id INT REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- 2. CRIME MODULE TABLES
-- -----------------------------------

-- CRIME TYPES
CREATE TABLE crime_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- CRIME LOCATION
CREATE TABLE crime_locations (
    id SERIAL PRIMARY KEY,
    address TEXT NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);

-- CRIME
CREATE TABLE crimes (
    id SERIAL PRIMARY KEY,
    crime_type_id INT REFERENCES crime_types(id),
    location_id INT REFERENCES crime_locations(id),
    reported_by INT REFERENCES users(id),
    description TEXT,
    occurred_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Trigger to auto-update `updated_at` column
CREATE OR REPLACE FUNCTION update_crimes_updated_at()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_crimes_updated_at
BEFORE UPDATE ON crimes
FOR EACH ROW
EXECUTE FUNCTION update_crimes_updated_at();

-- Indexes
CREATE INDEX idx_crime_type ON crimes(crime_type_id);
CREATE INDEX idx_crime_location ON crimes(location_id);
CREATE INDEX idx_crimes_occurred_at ON crimes(occurred_at);
CREATE INDEX idx_crime_description_ft ON crimes USING GIN (to_tsvector('english', description));

-- 3. CASE MANAGEMENT MODULE
-- -----------------------------------

-- CASE STATUS
CREATE TABLE case_status (
    id SERIAL PRIMARY KEY,
    status VARCHAR(50) UNIQUE NOT NULL -- OPEN, UNDER_INVESTIGATION, CLOSED
);

-- CASES
CREATE TABLE cases (
    id SERIAL PRIMARY KEY,
    crime_id INT REFERENCES crimes(id),
    assigned_officer INT REFERENCES users(id),
    status_id INT REFERENCES case_status(id),
    summary TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Trigger to auto-update `updated_at` column
CREATE OR REPLACE FUNCTION update_cases_updated_at()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_cases_updated_at
BEFORE UPDATE ON cases
FOR EACH ROW
EXECUTE FUNCTION update_cases_updated_at();

-- Indexes
CREATE INDEX idx_case_status ON cases(status_id);
CREATE INDEX idx_case_crime ON cases(crime_id);
CREATE INDEX idx_case_officer ON cases(assigned_officer);
CREATE INDEX idx_case_summary_ft ON cases USING GIN (to_tsvector('english', summary));

-- OFFICER ASSIGNMENT HISTORY
CREATE TABLE case_assignments (
    id SERIAL PRIMARY KEY,
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    officer_id INT REFERENCES users(id),
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_case_assignments_officer ON case_assignments(officer_id);

-- CASE TIMELINE UPDATES
CREATE TABLE case_updates (
    id SERIAL PRIMARY KEY,
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    updated_by INT REFERENCES users(id),
    update_note TEXT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. PERSON TABLES (Suspect, Victim, Witness)
-- -----------------------------------

CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    address TEXT,
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE suspects (
    id INT PRIMARY KEY REFERENCES persons(id) ON DELETE CASCADE,
    criminal_history TEXT
);

CREATE TABLE victims (
    id INT PRIMARY KEY REFERENCES persons(id) ON DELETE CASCADE,
    injury_description TEXT
);

CREATE TABLE witnesses (
    id INT PRIMARY KEY REFERENCES persons(id) ON DELETE CASCADE,
    statement TEXT
);

CREATE TABLE case_suspects (
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    suspect_id INT REFERENCES suspects(id) ON DELETE CASCADE,
    PRIMARY KEY (case_id, suspect_id)
);

CREATE TABLE case_victims (
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    victim_id INT REFERENCES victims(id) ON DELETE CASCADE,
    PRIMARY KEY (case_id, victim_id)
);

CREATE TABLE case_witnesses (
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    witness_id INT REFERENCES witnesses(id) ON DELETE CASCADE,
    PRIMARY KEY (case_id, witness_id)
);

-- Index for person name search
CREATE INDEX idx_person_name ON persons(name);

-- 5. EVIDENCE MANAGEMENT
-- -----------------------------------

CREATE TABLE evidence (
    id SERIAL PRIMARY KEY,
    case_id INT REFERENCES cases(id) ON DELETE CASCADE,
    evidence_type VARCHAR(100),
    file_path TEXT NOT NULL,
    file_url TEXT,
    file_size BIGINT,
    description TEXT,
    uploaded_by INT REFERENCES users(id),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_evidence_case ON evidence(case_id);

-- 6. AUDIT LOGS
-- -----------------------------------

CREATE TABLE audit_logs (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    action_type VARCHAR(50),
    action VARCHAR(255) NOT NULL,
    ip_address VARCHAR(50),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_audit_logs_user ON audit_logs(user_id);

-- ===========================================
-- End of SQL
-- ===========================================
