CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(255),
    descricao VARCHAR(255),
    grupo VARCHAR(255),
    unidade VARCHAR(255),
    quantidade NUMERIC(8, 3),
    valor_unitario NUMERIC(15, 2),
    CONSTRAINT uq_codigo UNIQUE(codigo)
);