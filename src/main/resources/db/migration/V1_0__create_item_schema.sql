CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(255),
    descricao VARCHAR(255),
    quantidade BIGINT,
    custo_unitario NUMERIC(12, 5)
);