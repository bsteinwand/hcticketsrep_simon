ALTER TABLE hctickets.UserApplicationRole
ADD COLUMN ActiveDate timestamptz;

UPDATE hctickets.UserApplicationRole
SET ActiveDate = NOW()
WHERE ActiveDate IS NULL;

ALTER TABLE hctickets.UserApplicationRole
ALTER COLUMN ActiveDate SET NOT NULL;