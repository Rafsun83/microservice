-- Convert the ENUM column to VARCHAR so Hibernate manages it as a string enum
ALTER TABLE wallet MODIFY COLUMN type VARCHAR(50);

-- Now update the old values to the new ones
UPDATE wallet
-- SET type = 'PERSONAL'
-- WHERE type IN ('E_CURRENCY', 'E_WALLET');
SET type = 'SAVINGS'
WHERE type = 'BUSINESS'