CREATE SCHEMA IF NOT EXISTS ai_notes;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- TODO: write user, notebook schema

-- Notebook
-- User

CREATE TABLE IF NOT EXISTS ai_notes.users (
    user_id SERIAL PRIMARY KEY DEFAULT uuid_generate_v4(),
    notebook_name VARCHAR(255),
    notebook_id UUID REFERENCES ai_notes.notebook(notebook_id),
    );

CREATE TABLE IF NOT EXISTS ai_notes.notebook (
    notebook_id SERIAL PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID REFERENCES ai_notes.users(user_id),
    title VARCHAR(255),
    category VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );



