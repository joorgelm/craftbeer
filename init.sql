CREATE DATABASE craftbeer;

USE craftbeer;

CREATE TABLE tb_beer (
    beer_id SERIAL PRIMARY KEY,
    beer_name VARCHAR(100) NOT NULL,
    beer_ingredients VARCHAR(500) NOT NULL,
    beer_alcoholContent VARCHAR(255) NOT NULL,
    beer_price NUMERIC(18, 8) NOT NULL
);

COMMENT ON TABLE "craftbeer"."tb_beer" IS 'Tabela contendo as cervejas.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_id" IS 'Chave primária da tabela.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_name" IS 'Nome da cerveja.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_ingredients" IS 'Ingredientes utilizados na fabricação.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_alcoholContent" IS 'Teor alcoolico.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_price" IS 'Valor de venda.';

INSERT INTO tb_beer(beer_id, beer_name, beer_ingredients, beer_alcoholContent, beer_price)
VALUES  (1, 'Red Ale', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id varius est.', '14%', 14.5)
        (2, 'Americam Ale', 'Lorem consectetur adipiscing elit.', '10%', 10.5)
        (3, 'IPA das Braba', 'Lorem consectetur adipiscing elit.', '20%', 17.5);
