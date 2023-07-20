CREATE TABLE employer(
    id BIGSERIAL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    company_name TEXT NOT NULL,
    company_site TEXT NOT NULL
);

CREATE TABLE vacancy(
    id BIGSERIAL PRIMARY KEY,
    employer_id BIGINT REFERENCES employer(id) ON DELETE CASCADE,
    vacancy_name TEXT NOT NULL,
    description TEXT NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE candidate(
    id BIGSERIAL PRIMARY KEY,
    email TEXT NOT NULL,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    summary TEXT
);

CREATE TABLE response(
    id BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT REFERENCES candidate(id) ON DELETE CASCADE,
    vacancy_id BIGINT REFERENCES vacancy(id) ON DELETE CASCADE
);