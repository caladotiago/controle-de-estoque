CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(255),
    descricao VARCHAR(255),
    grupo VARCHAR(255),
    unidade VARCHAR(255),
    quantidade NUMERIC(8, 4),
    valor_unitario NUMERIC(15, 6)
);