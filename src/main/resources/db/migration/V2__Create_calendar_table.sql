CREATE TABLE calendar
(
    id              serial PRIMARY KEY,
    original_json   text,
    compressed_ical bytea
);

CREATE UNIQUE INDEX calendar_json_idx ON calendar (digest(original_json, 'sha512'));