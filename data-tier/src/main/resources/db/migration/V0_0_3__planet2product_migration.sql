CREATE TABLE IF NOT EXISTS PLANET2PRODUCT (
    ID SERIAL PRIMARY KEY,
    planet_id INT REFERENCES planet(id),
    product_type VARCHAR(64),
    amount INT,
    price DECIMAL
)