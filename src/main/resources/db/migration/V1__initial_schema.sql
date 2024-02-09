CREATE SCHEMA IF NOT EXISTS ai_notes;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE ai_notes.notebook(
    notebook_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID,
    title VARCHAR(255),
    category VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX user_id_title_index ON ai_notes.notebook(user_id,title);
CREATE INDEX user_id_category_index ON ai_notes.notebook(user_id,category);


