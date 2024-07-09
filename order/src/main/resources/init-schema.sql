DROP SCHEMA IF EXISTS "order" CASCADE;

CREATE SCHEMA "order";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('CREATED', 'PAID', 'PAYMENT_DECLINED', 'CANCELLED', 'DISPATCHED', 'DELIVERED');


DROP TABLE IF EXISTS "order".orders CASCADE;

CREATE TABLE "order".orders
(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    order_amount numeric(10,2) NOT NULL,
    order_status order_status NOT NULL,
    created_at timestamp,
    created_by character varying COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp,
    updated_by character varying COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "order".order_items CASCADE;

CREATE TABLE "order".order_items
(
    id bigint NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_items
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

CREATE TABLE "order".order_address
(
    id uuid NOT NULL,
    order_id uuid UNIQUE NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_address_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_address
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;



CREATE TABLE "order".outbox
(
    id uuid NOT NULL,
    payload jsonb NOT NULL,
    aggregate_type character varying COLLATE pg_catalog."default" NOT NULL,
    aggregate_id uuid NOT NULL,
    tracingspancontext character varying COLLATE pg_catalog."default",
    CONSTRAINT product_outbox_pkey PRIMARY KEY (id)
);