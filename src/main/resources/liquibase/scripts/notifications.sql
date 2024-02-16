-- liquibase formatted sql

-- changeset denik:1
CREATE TABLE notifications(
    id           SERIAL PRIMARY KEY,
    chat_id      INTEGER,
    message      TEXT,
    date         TIMESTAMP
)