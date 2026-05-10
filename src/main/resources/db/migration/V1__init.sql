CREATE TABLE patch_records (

    id BIGSERIAL PRIMARY KEY,

    system_name VARCHAR(255) NOT NULL,

    patch_name VARCHAR(255) NOT NULL,

    status VARCHAR(50) NOT NULL,

    compliance_score INTEGER,

    created_at TIMESTAMP,

    updated_at TIMESTAMP
);