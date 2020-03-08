CREATE TABLE proposal
(
  id                UUID PRIMARY KEY NOT NULL,
  person_id         UUID  NOT NULL,
  amount_of_loan    NUMERIC(19, 6) NOT NULL,
  terms_installment TEXT NOT NULL,
  income            NUMERIC(19, 6) NOT NULL,
  refused_policy    TEXT,
  status            TEXT,
  result            TEXT,
  created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  updated_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  deleted_at        TIMESTAMP WITH TIME ZONE,
  enabled           BOOLEAN not null default true
);

CREATE UNIQUE INDEX proposal_id_idx
  ON proposal
  USING btree
  (id);

CREATE INDEX proposal_person_id_idx
   ON proposal
   USING btree
   (person_id);
