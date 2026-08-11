-- Backend local/test sample seed (curated, not a full backup dump)
-- Prerequisite: hnn_schema + V001(state) + V002(dashboard)
-- Admin login username: 한수민
-- Password: use the plaintext you used when generating the BCrypt hash below (local test only)

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE orders;
TRUNCATE TABLE message;
TRUNCATE TABLE material;
TRUNCATE TABLE event_info;
TRUNCATE TABLE admin_user;
TRUNCATE TABLE team;
TRUNCATE TABLE state;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO state (state_num, state) VALUES
  (1, 'submitted'),
  (2, 'accepted'),
  (3, 'design_complete'),
  (4, 'print_complete'),
  (5, 'failed'),
  (6, 'picked_up');

INSERT INTO admin_user (admin_id, username, password_hash, role, created_at) VALUES
  (1, '한수민', '$2a$10$fuThpJpiJiizmOFSXFECFeS8wnzbXdDlOrLvOQixCYAHSgc9uQ4wa', 'admin', NOW());

INSERT INTO team (team_num, phone) VALUES
  ('H07', 0),
  ('H09', 0),
  ('H38', 0),
  ('M07', 0),
  ('M17', 0),
  ('M30', 0),
  ('M31', 0),
  ('M36', 0);

INSERT INTO material (material_num, material, is_active) VALUES
  (1, 'mdf_3mm', 1),
  (3, '아크릴_3mm', 1),
  (7, '3d 프린팅', 1);

INSERT INTO event_info (
  event_id, name, start_time, end_time, is_open, description, created_at,
  completed_limit, waiting_limit
) VALUES (
  1,
  '경상북도교육청 주관 제 7회 SW-AI 창의융합 해커톤',
  '2025-10-18 09:00:00',
  '2025-10-19 09:00:00',
  1,
  '레이저컷팅 / 3D 프린팅 접수·출력 운영',
  NOW(),
  9,
  12
);

INSERT INTO message (message_id, content, is_emergency, is_display, created_at) VALUES
  (1, '아크릴 소진되었습니다.', 1, 1, NOW()),
  (2, '허용 규격: MDF 3mm, 아크릴 3mm', 0, 1, NOW()),
  (3, '완료 명단에 팀명 확인 시, 가지러 오시기 바랍니다.', 0, 1, NOW());

INSERT INTO orders (
  order_id, file_name, ordered_at, team_num, material, state_num, admin_id, updated_at, hidden_from_dashboard
) VALUES
  (1, 'M30_1M', '2025-10-18 11:21:01', 'M30', 1, 1, NULL, NULL, 0),
  (2, 'H38_2A', '2025-10-18 11:37:03', 'H38', 3, 2, 1, NULL, 0),
  (3, 'M17_3',  '2025-10-18 12:48:22', 'M17', 7, 3, 1, NULL, 0),
  (4, 'H09_4M', '2025-10-19 03:47:20', 'H09', 1, 4, 1, NULL, 0),
  (5, 'M07_5M', '2025-10-19 04:27:38', 'M07', 1, 5, NULL, NULL, 0),
  (6, 'M31_6M', '2025-10-18 13:22:06', 'M31', 1, 6, 1, NULL, 0),
  (7, 'H07_7A', '2025-10-18 15:35:51', 'H07', 3, 2, 1, NULL, 0),
  (8, 'M36_8M', '2025-10-19 04:00:01', 'M36', 1, 4, 1, NULL, 1);

ALTER TABLE admin_user AUTO_INCREMENT = 2;
ALTER TABLE material AUTO_INCREMENT = 8;
ALTER TABLE message AUTO_INCREMENT = 4;
ALTER TABLE event_info AUTO_INCREMENT = 2;
ALTER TABLE orders AUTO_INCREMENT = 9;
ALTER TABLE state AUTO_INCREMENT = 7;
