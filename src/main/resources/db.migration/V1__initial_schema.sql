CREATE SCHEMA IF NOT EXISTS ai_notes;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS ai_notes.users (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS ai_notes.notebook (
    notebook_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID,
    title VARCHAR(255),
    category VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
COMMIT;
CREATE INDEX "user_id_title_index" ON ai_notes.notebook("user_id","title");
CREATE INDEX "user_id_category_index" ON ai_notes.notebook("user_id","category");

INSERT INTO ai_notes.notebook (notebook_id, user_id, title, category)
VALUES ('123e4567-e89b-12d3-a456-426655440000', '123e4567-e89b-12d3-a456-426614174000', 'Introduction to AI', 'Education');

