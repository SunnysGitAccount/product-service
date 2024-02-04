CREATE TABLE category
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN,
    name       VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    is_deleted  BOOLEAN,
    title       VARCHAR(255),
    price       DOUBLE PRECISION,
    category_id BIGINT,
    description VARCHAR(255),
    image       VARCHAR(255),
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);