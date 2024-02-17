-- liquibase formatted sql

-- changeset denik:1
CREATE TABLE notifications(
    id           SERIAL PRIMARY KEY,
    chat_id      BIGINT,
    message      TEXT,
    date         TIMESTAMP
)