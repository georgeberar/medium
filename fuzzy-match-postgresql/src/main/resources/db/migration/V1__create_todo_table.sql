CREATE SEQUENCE todo_id_seq
    START WITH 1
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE CACHE 1;

CREATE TABLE t_todo
(
    id         BIGINT                 NOT NULL DEFAULT nextval('todo_id_seq'),
    ext_ref    CHARACTER VARYING(255) NOT NULL,
    title      CHARACTER VARYING(255) NOT NULL,
    active     BOOLEAN                NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (ext_ref),
    UNIQUE (title)
);