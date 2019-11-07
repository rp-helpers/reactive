CREATE TABLE applications(
id BIGSERIAL NOT NULL,
public_application_id UUID NOT NULL,
name VARCHAR NOT NULL,
status VARCHAR NOT NULL,
create_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
PRIMARY KEY (id)
);
CREATE UNIQUE INDEX ON applications (public_application_id);