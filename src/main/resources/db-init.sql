CREATE TABLE IF NOT EXISTS dates
(
    date_id serial PRIMARY KEY,
    localdate DATE NOT NULL
);
CREATE TABLE IF NOT EXISTS currencies
(
    currency_id serial PRIMARY KEY,
    currency_cbr_id varchar(10) NOT NULL,
    currency_num_code integer NOT NULL,
    currency_char_code varchar(5) NOT NULL,
    currency_nominal integer NOT NULL,
    currency_name varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS currency_values (
date_id bigint NOT NULL,
currency_id bigint NOT NULL,
rate_value decimal NOT NULL,
CONSTRAINT fk_date_id FOREIGN KEY (date_id) REFERENCES dates(date_id) ON DELETE CASCADE,
CONSTRAINT fk_currency_id FOREIGN KEY (currency_id) REFERENCES currencies(currency_id) ON DELETE CASCADE,
CONSTRAINT date_currency UNIQUE (date_id, currency_id));