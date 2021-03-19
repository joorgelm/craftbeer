CREATE SCHEMA craftbeer;

CREATE SEQUENCE "craftbeer"."seq_tb_beer"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE "craftbeer".tb_beer (
                         beer_id INTEGER PRIMARY KEY,
                         beer_name VARCHAR(100) NOT NULL,
                         beer_ingredients VARCHAR(500) NOT NULL,
                         beer_alcohol_content VARCHAR(255) NOT NULL,
                         beer_category VARCHAR(255) NOT NULL,
                         beer_price NUMERIC(18, 8) NOT NULL
);

COMMENT ON TABLE "craftbeer"."tb_beer" IS 'Tabela contendo as cervejas.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_id" IS 'Chave primária da tabela.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_name" IS 'Nome da cerveja.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_ingredients" IS 'Ingredientes utilizados na fabricação.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_alcohol_content" IS 'Teor alcoolico.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_category" IS 'Categoria da cerveja.';

COMMENT ON COLUMN "craftbeer"."tb_beer"."beer_price" IS 'Valor de venda.';

INSERT INTO "craftbeer".tb_beer(beer_id, beer_name, beer_ingredients, beer_alcohol_content, beer_category, beer_price)
VALUES  (nextval('seq_tb_beer'), 'Red Ale', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id varius est.', '14%', 'RED ALE', 14.5),
        (nextval('seq_tb_beer'), 'Americam Ale', 'Lorem consectetur adipiscing elit.', '10%', 'AMERICAN', 10.5),
        (nextval('seq_tb_beer'), 'IPA das Braba', 'Lorem consectetur adipiscing elit.', '20%', 'IPA', 17.5);
