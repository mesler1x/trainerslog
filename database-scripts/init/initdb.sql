CREATE DATABASE trainerslog;
GRANT ALL PRIVILEGES ON DATABASE trainerslog TO postgres;
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(1, 'admin', '$2a$10$P7RlsCSCD71TwbU9uApww.1wePivjtVic.1swI0fuClAjgCjjBB6q', '["ADMIN"]'::jsonb, true, true, true, true);