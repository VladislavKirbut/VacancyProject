ALTER TABLE vacancy ALTER COLUMN employer_id SET NOT NULL;

ALTER TABLE response ALTER COLUMN candidate_id SET NOT NULL;

ALTER TABLE response ALTER COLUMN vacancy_id SET NOT NULL;

ALTER TABLE response ADD COLUMN response_time TIMESTAMP;

ALTER TABLE response ALTER COLUMN response_time SET NOT NULL;