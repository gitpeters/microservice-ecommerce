CREATE TABLE IF NOT EXISTS category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(150),
    description VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS product
(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(150),
    description         VARCHAR(255),
    available_quantity  INTEGER NOT NULL,
    price               NUMERIC(38,2),
    category_id         INTEGER,
    CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (category_id) REFERENCES category(id)
    );
