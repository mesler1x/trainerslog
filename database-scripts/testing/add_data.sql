INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(1, 'roma', '$2a$10$t4gytZdXG/yppKRHouVPEOT/gEpFIY3pP8yp.IyI3RcK9Q80T/Y9a', '["COACH"]'::jsonb, true, true, true, true);
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(37, 'student1', '$2a$10$y7eYB8a830StPp4CbjeNi.Mf5iMm1pDQrCMhtv9IRHQ6dKvKrqdHm', '["ROLE_STUDENT"]'::jsonb, true, true, true, true);
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(38, 'student2', '$2a$10$bmCWwJGMtqhQZka09g8bbu0KEBSCWlEfBhQs9yVIYcyrmQXXHaDd6', '["ROLE_STUDENT"]'::jsonb, true, true, true, true);
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(39, 'student3', '$2a$10$YtgTjuAQH1QCVe0p0snW6.8j4rpGUkceA1qk8DjkfyrjaCmZpybIm', '["ROLE_STUDENT"]'::jsonb, true, true, true, true);
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(40, 'student4', '$2a$10$b6JprSsvdsvr2eMDVFFUs.Dz/nt8kAluHDf.pJjQ6LgmKTJSXZwme', '["ROLE_STUDENT"]'::jsonb, true, true, true, true);
INSERT INTO public.user_info
(id, username, "password", authorities, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(41, 'student5', '$2a$10$1rh42iM3o5CqaZQ24Sv.PeaSU1HZNifiWkw2Z.40igV8FE8DmtjAi', '["ROLE_DEFAULT"]'::jsonb, true, true, true, true);


INSERT INTO public.filial
(id, filial_name, address)
VALUES(1, 'Юго Западный филиал', 'Фурманова 125');
INSERT INTO public.filial
(id, filial_name, address)
VALUES(3, 'Академический филиал', 'Павла Шаманова 6');


INSERT INTO public.coach
(id, "name", filial_id, user_id)
VALUES(2, 'Ivanov Ivan Ivanovich', 1, 1);


INSERT INTO public.sgroup
(id, sgroup_name, coach_id)
VALUES(10, 'AT-14', 2);


INSERT INTO public.ticket
(id, start_date, end_date, is_expired, paid_amount)
VALUES(6, '2024-05-07', '2024-06-07', true, 1000);
INSERT INTO public.ticket
(id, start_date, end_date, is_expired, paid_amount)
VALUES(8, '2024-05-07', '2024-06-07', true, 1000);
INSERT INTO public.ticket
(id, start_date, end_date, is_expired, paid_amount)
VALUES(11, '2024-05-07', '2024-06-07', true, 1000);
INSERT INTO public.ticket
(id, start_date, end_date, is_expired, paid_amount)
VALUES(26, '2024-05-08', '2024-06-08', true, 1000);


INSERT INTO public.student
(id, ticket_id, full_name, sex, birth_date, sgroup_id, age, q, phone_number, parent_phone_number, parent_full_name, user_id, link_to_cheques)
VALUES(5, 26, 'PPP PPP PPP', 'м', '2004-01-01', 10, 0, 2, '+79221953030', '+79222218001', 'Roman Ruzik Mesler', 37, '["https://drive.google.com/"]'::jsonb);
INSERT INTO public.student
(id, ticket_id, full_name, sex, birth_date, sgroup_id, age, q, phone_number, parent_phone_number, parent_full_name, user_id, link_to_cheques)
VALUES(12, 11, 'GGG GGG GGG', 'м', '2004-01-01', NULL, 0, 2, '+79221953030', '+79222218001', 'Roman Ruzik Mesler', 40, NULL);
INSERT INTO public.student
(id, ticket_id, full_name, sex, birth_date, sgroup_id, age, q, phone_number, parent_phone_number, parent_full_name, user_id, link_to_cheques)
VALUES(7, 6, 'SDFG DSFGSD SFSDFA', 'м', '2004-01-01', 10, 0, 2, '+79221953030', '+79222218001', 'Roman Ruzik Mesler', 38, NULL);
INSERT INTO public.student
(id, ticket_id, full_name, sex, birth_date, sgroup_id, age, q, phone_number, parent_phone_number, parent_full_name, user_id, link_to_cheques)
VALUES(9, 8, 'ASDFAS SSDAFASDF SAFASDF', 'м', '2004-01-01', 10, 0, 2, '+79221953030', '+79222218001', 'Roman Ruzik Mesler', 39, NULL);


