insert into stockoneq.company (id, created_date, modified_date, code, name, sector, status)
values  (1, '2023-08-21 18:56:06.000000', '2023-08-21 18:56:06.000000', 'ZYX100', '스톡원큐', '카페', '정상');

insert into stockoneq.store (id, created_date, modified_date, address, code, name, sector, status, manager_id)
values  (1, '2023-08-21 10:00:11.766328', '2023-08-21 10:00:12.220514', '서울시 강남구 역삼동', 'LKIAK8', '스톡원큐 강남점', '카페', '정상', 1),
        (2, '2023-08-21 10:00:44.677021', '2023-08-21 10:00:44.937888', '서울시 서초구 서초동', 'Y84TKS', '스톡원큐 신논현점', '카페', '정상', 2),
        (3, '2023-08-21 10:01:04.064191', '2023-08-21 10:01:04.317637', '서울시 강남구 논현동', 'WO2Y5N', '스톡원큐 논현점', '카페', '정상', 3),
        (4, '2023-08-21 10:01:28.419684', '2023-08-21 10:01:28.678265', '서울시 용산구 한남동', 'AA7NOB', '스톡원큐 한남점', '카페', '정상', 4),
        (5, '2023-08-21 10:01:54.584342', '2023-08-21 10:01:54.857790', '서울시 강남구 역삼동', 'X4OYRH', '스톡원큐 역삼점', '카페', '정상', 5),
        (6, '2023-08-21 10:02:15.091521', '2023-08-21 10:02:15.347757', '서울시 양천구 목동', 'FTUA83', '스톡원큐 목동점', '카페', '정상', 6),
        (7, '2023-08-21 10:02:36.521197', '2023-08-21 10:02:36.783257', '서울시 종로구 혜화동', 'R899C7', '스톡원큐 혜화점', '카페', '정상', 7),
        (8, '2023-08-21 10:02:51.748974', '2023-08-21 10:02:52.018538', '서울시 서초구 반포동', '38225I', '스톡원큐 반포점', '카페', '정상', 8),
        (9, '2023-08-21 10:03:14.050311', '2023-08-21 10:03:14.331387', '서울시 강남구 신사동', 'AT781K', '스톡원큐 압구정점', '카페', '정상', 9),
        (10, '2023-08-21 10:03:33.104387', '2023-08-21 10:03:33.364272', '서울시 송파구 신천동', '3OXI00', '스톡원큐 잠실점', '카페', '정상', 10),
        (11, '2023-08-21 10:03:50.579937', '2023-08-21 10:03:50.847646', '서울시 광진구 화양동', '29M7X0', '스톡원큐 건대입구점', '카페', '정상', 11),
        (12, '2023-08-21 10:04:11.192061', '2023-08-21 10:04:11.464194', '서울시 성수구 성수동', '7OR5PT', '스톡원큐 성수점', '카페', '정상', 12),
        (13, '2023-08-21 10:04:27.126799', '2023-08-21 10:04:27.381266', '서울시 용산구 한남동', '9ZD6EN', '스톡원큐 한남2호점', '카페', '정상', 13),
        (14, '2023-08-21 10:04:42.574241', '2023-08-21 10:04:42.829093', '서울시 강남구 역삼동', 'WPJSNT', '스톡원큐 역삼2호점', '카페', '정상', 14),
        (15, '2023-08-21 10:05:02.418233', '2023-08-21 10:05:02.645650', '서울시 양천구 목동', '1UGOKJ', '스톡원큐 목동2호점', '카페', '정상', 15),
        (16, '2023-08-21 10:05:18.267483', '2023-08-21 10:05:18.535389', '서울시 종로구 혜화동', 'DGM1MX', '스톡원큐 혜화2호점', '카페', '정상', 16),
        (17, '2023-08-21 10:05:32.354287', '2023-08-21 10:05:32.618730', '서울시 서초구 반포동', '1HT99Q', '스톡원큐 반포2호점', '카페', '정상', 17),
        (18, '2023-08-21 10:05:54.171511', '2023-08-21 10:05:54.505406', '서울시 강남구 신사동', '4KAPIP', '스톡원큐 압구정2호점', '카페', '정상', 18),
        (19, '2023-08-21 10:06:08.646861', '2023-08-21 10:06:08.911838', '서울시 송파구 신천동', '2D9HXF', '스톡원큐 잠실2호점', '카페', '정상', 19),
        (20, '2023-08-21 10:06:25.665472', '2023-08-21 10:06:25.921585', '서울시 광진구 화양동', '4J2NNS', '스톡원큐 건대입구2호점', '카페', '정상', 20),
        (21, '2023-08-21 10:06:42.734650', '2023-08-21 10:06:42.987604', '서울시 성수구 성수동', '2NHYUI', '스톡원큐 성수2호점', '카페', '정상', 21);

insert into stockoneq.part_timer (id, created_date, modified_date, part_timer_id, store_id)
values  (1, '2023-08-27 15:30:12.197036', '2023-08-27 15:30:12.197036', 43, 1);

insert into stockoneq.friend (id, created_date, modified_date, relation_status, receiver_id, sender_id)
values  (1, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '수락', 1, 2),
        (4, '2023-08-21 19:36:29.000000', '2023-08-24 08:56:44.769829', '수락', 1, 5),
        (8, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 17, 9),
        (9, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 18, 10),
        (10, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 19, 11),
        (11, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 20, 12),
        (12, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 13, 1),
        (16, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 17, 1),
        (18, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 19, 1),
        (20, '2023-08-21 19:36:29.000000', '2023-08-21 19:36:29.000000', '요청', 21, 1);


insert into stockoneq.business (id, created_date, modified_date, relation_status, manager_id, supervisor_id)
values  (1, '2023-08-21 19:37:00.000000', '2023-08-21 19:37:00.000000', '수락', 1, 22),
        (2, '2023-08-24 03:23:01.734291', '2023-08-24 03:23:01.734291', '수락', 24, 22),
        (3, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 34, 32),
        (4, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 35, 32),
        (5, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 36, 32),
        (6, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 37, 32),
        (7, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 34, 33),
        (8, '2023-08-27 21:50:42.000000', '2023-08-27 21:50:42.000000', '수락', 35, 33);

insert into stockoneq.share (id, created_date, modified_date, category, content, file, status, title, business_id)
values  (1, '2023-08-27 21:58:19.926610', '2023-08-27 21:58:19.926610', '공지사항', '공지사항 숙지 바랍니다.', null, '정상', '첫번째 공지사항입니다.', 3),
        (2, '2023-08-27 21:58:52.316976', '2023-08-27 22:09:41.511910', '공지사항', '첨부 파일 확인 요망', 'share/ff1a85a0-c802-439c-a6b2-810ecb434108_cafe.png', '정상', '수정된 공지사항입니다.', 3),
        (3, '2023-08-27 22:00:54.548002', '2023-08-27 22:00:54.548002', '행사내용', '확인 바랍니다.', 'share/14d66dff-647a-4ee7-9446-475efba06b3d_png.png', '정상', '이번 달 행사내용입니다.', 3),
        (7, '2023-08-27 22:26:25.000000', '2023-08-27 22:26:25.000000', '공지사항', '공지사항입니다.', null, '정상', '회사 규정 다시 알립니다.', 3),
        (8, '2023-08-27 22:26:25.000000', '2023-08-27 22:26:25.000000', '공지사항', '공지사항입니다.', null, '정상', '전체 미팅데이 공지입니다.', 3),
        (9, '2023-08-27 14:06:00.879831', '2023-08-27 14:06:00.879831', '레시피', '레시피 숙지 부탁', 'share/1b6daec1-dab6-405e-a3ca-b57cc1c27a05_recipe.txt', '정상', '레시피 전달합니다.', 3),
        (10, '2023-08-27 14:12:09.819886', '2023-08-27 14:12:09.819886', '행사내용', '필참입니다.', null, '정상', '카페 전체 행사입니다.', 4);

insert into stockoneq.product (id, created_date, modified_date, expiration_date, image_url, location, name, order_freq, price, receiving_date, require_quant, site_to_order, status, stock_quant, store_condition, vendor, store_id)
values  (1, '2023-08-22 00:49:19.000000', '2023-08-24 16:13:02.326740', '2023-07-27', 'product/24041a00-764d-4eb3-b485-e35e2228c05a_croquemo.png', '작은 냉동고', '크로크무슈', 40, 2000, '2023-07-20', 5, 'https://www.kurly.com', '소멸', 13, '냉동', '크로크무슈사랑해', 1),
        (2, '2023-08-22 00:49:19.000000', '2023-08-27 11:23:06.660416', '2023-08-01', 'product/f2c2351d-18da-49e5-b08e-c396719c0605_mango.png', '작은 냉동고', '냉동망고짱짱짱', 80, 9800, '2023-07-20', 1, 'https://www.coupang.com', '정상', 3, '냉동', '망고는망고', 1),
        (3, '2023-08-22 00:49:19.000000', '2023-08-27 14:50:57.662305', '2023-07-31', 'product/ae77a8cd-297a-491d-8459-8bc531abfd9e_strawberry_ice.png', null, '티코', 20, 5000, '2023-07-20', 2, 'https://www.kurly.com', '정상', 3, '냉동', '딸기가제철이여', 1),
        (4, '2023-08-22 00:49:19.000000', '2023-08-24 16:36:19.247158', '2023-08-25', 'product/a76eb497-2540-415b-bf23-9e751653f61b_maskaponae.png', '작은 냉동고', '마스카포네', 60, 1500, '2023-07-20', 5, 'https://www.kurly.com', '정상', 10, '냉동', '치이이즈', 1),
        (5, '2023-08-22 00:49:19.000000', '2023-08-27 10:40:37.607773', '2023-08-25', 'product/1e6327aa-e6c3-4fc8-88a8-52df8b7be5ca_kiwi.png', '작은 냉동고', '냉동키위짱', 40, 9900, '2023-07-20', 1, 'https://www.kurly.com', '정상', 3, '냉동', '키위는키위', 1),
        (6, '2023-08-22 00:49:19.000000', '2023-08-27 10:44:26.310025', '2023-08-25', 'product/73ed82aa-72a8-4d57-b857-02d5059bba51_vanilla_ice.png', '작은 냉동고', '바닐라 아이스크림', 80, 7800, '2023-07-20', 1, 'https://www.kurly.com', '소멸', 3, '냉동', '아이스크림팔아요', 1),
        (7, '2023-08-22 00:49:19.000000', '2023-08-24 16:14:51.658100', '2023-10-31', 'product/4bd7e61c-8059-450c-87c5-90ab8b7ee3a1_patbingsu.png', '큰 냉동고', '팥빙수', 40, 2500, '2023-07-20', 10, 'https://www.coupang.com', '정상', 11, '냉동', '차가워너무나', 1),
        (8, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-10-25', 'product/aed8aef6-99c7-4af6-8c16-65fc9e70d85f_icepack.png', '큰 냉동고', '아이스팩', 100, 500, '2023-07-20', 10, 'https://www.coupang.com', '정상', 2, '냉동', '차가워너무나', 1),
        (9, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-11-27', 'product/7de5a008-3baa-417a-a8af-29b5e8dd6a65_chocolate_ice.png', '큰 냉동고', '초코 아이스크림', 40, 5500, '2023-07-20', 1, null, '정상', 5, '냉동', '아이스크림팔아요', 1),
        (10, '2023-08-22 00:49:19.000000', '2023-08-27 09:26:52.048367', '2025-11-01', 'product/5e5d0dd3-645d-4a54-ab27-a6efe07b4935_ice.png', '작은 냉동고', '돌얼음', 100, 20000, '2023-07-20', 10, null, '정상', 30, '냉동', '차가워너무나', 1),
        (11, '2023-08-22 00:49:19.000000', '2023-08-24 16:15:23.735567', '2023-12-31', 'product/6d95d3b9-648e-45ac-b010-e0f1e577d49d_cookiescream.png', '큰 냉동고', '쿠키슈', 20, 1400, '2023-07-20', 214738, null, '정상', 3, '냉동', '쿠키가조항', 1),
        (12, '2023-08-22 00:49:19.000000', '2023-08-27 11:21:01.680472', '2024-08-25', 'product/4eb681b1-cdbe-40ed-88c3-7d4d99de0213_strawberry.png', '큰 냉동고', '냉동딸기', 100, 16800, '2023-07-20', 4, null, '정상', 12, '냉동', '딸기가제철이여', 1),
        (13, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-08-23', 'product/e0362080-ea7a-4eed-bba3-adb9d2dc44b0_coldbrew.png', '작은 냉장고', '콜드브루원액', 20, 24800, '2022-07-10', 2, 'https://www.coupang.com', '정상', 10, '냉장', '춥다추운원액', 1),
        (14, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-08-23', 'product/0e0cf58f-3936-4e15-909f-1409d0c5a496_pat.png', '작은 냉장고', '팥', 40, 4830, '2023-04-20', 2, null, '정상', 5, '냉장', '차가워너무나', 1),
        (15, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-08-25', 'product/25616562-2e7c-4207-b882-b1ab03a53986_doyou.png', '큰 냉장고', '두유', 20, 2200, '2023-07-20', 5, 'https://www.coupang.com', '정상', 11, '냉장', '메일우유', 1),
        (16, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-09-08', 'product/71c3633d-f423-41c4-8a04-e31765af43b0_mlik.png', '작은 냉장고', '우유', 100, 1900, '2023-08-18', 30, 'https://www.coupang.com', '정상', 10, '냉장', '서울우유', 1),
        (17, '2023-08-22 00:49:19.000000', '2023-08-22 09:40:11.157131', '2025-09-11', 'product/a1edf2a8-866a-49cf-ba7c-5f8608269c1e_honey.png', '작은 냉장고', '초코시럽', 80, 6700, '2023-07-20', 1, null, '정상', 3, '냉장', '초코는초코', 1),
        (18, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-11-25', 'product/aaa50e02-2535-4e91-b787-4c8d4c1254bf_injeolmi.png', '작은 냉장고', '인절미', 60, 7800, '2023-07-20', 3, null, '정상', 10, '냉장', '절미야반가워', 1),
        (19, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-31', 'product/70a66848-9efd-4f99-8d81-98c540d40dd6_caramel_syrup.png', '큰 냉장고', '카라멜시럽', 80, 8800, '2023-07-20', 3, null, '정상', 8, '냉장', '시럽입니다랑', 1),
        (20, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-25', 'product/ec309160-cf5a-4820-aed1-e293bbab912e_strawberry_syrup.png', '큰 냉장고', '딸기시럽', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉장', '시럽입니다랑', 1),
        (21, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-07-23', 'product/595fbc29-6c46-4cd6-8156-1259436781cd_croquemo.png', '작은 냉동고', '크로크무슈', 40, 1800, '2022-07-10', 6, null, '정상', 20, '냉동', '크로크무슈사랑해', 2),
        (22, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-08-25', 'product/7329ae0f-0f71-473e-92a4-afe15dce3800_vanilla_ice.png', '작은 냉동고', '바닐라 아이스크림', 80, 4830, '2023-04-20', 2, null, '정상', 5, '냉동', '차가워너무나', 2),
        (23, '2023-08-22 00:49:19.000000', '2023-08-24 05:40:08.257185', '2024-08-25', 'product/8bbb1cd2-fe43-4959-a74c-83082fbce823_mango.png', '큰 냉동고', '냉동망고젤리', 100, 12000, '2023-07-20', 5, null, '정상', 11, '냉동', '망고는망고', 2),
        (24, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-09-08', 'product/60696d59-c1d7-4276-9882-a958c948efd1_strawberry.png', '작은 냉동고', '냉동딸기', 100, 19000, '2023-08-18', 5, null, '정상', 20, '냉동', '딸기가제철이여', 2),
        (25, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2025-09-11', 'product/db370107-749f-4b91-894e-a3fc64b2d3a0_makarong.png', '작은 냉동고', '마카롱', 60, 1200, '2023-07-20', 10, null, '정상', 30, '냉동', '카롱카롱마카롱', 2),
        (26, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-11-25', 'product/c8ae5885-d752-41fb-9226-fb39b6ccc0ac_cheesestick.png', '작은 냉동고', '치즈스틱', 60, 7800, '2023-07-20', 3, null, '정상', 10, '냉동', '치이즈', 2),
        (27, '2023-08-22 00:49:19.000000', '2023-08-23 03:36:30.264262', '2024-10-31', 'product/429fce9e-43e3-415b-bde4-e9500af211d2_chicken.jpg', '큰 냉동고', '치킨너겟', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉동', '너겟은사랑', 2),
        (28, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-25', 'product/362a13af-750b-47df-a0fb-bac458a4af7e_mando.png', '큰 냉동고', '비비고만두', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉동', '비비고', 2),
        (29, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-25', 'product/2495bdc1-594e-49c9-9044-45df7ebf4ad1_kimchirie.png', '큰 냉동고', '김치주먹밥', 60, 8800, '2023-07-20', 3, null, '정상', 10, '냉동', '비비고', 2),
        (30, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-07-23', 'product/95a82c14-c590-4488-be84-b60933ee28cb_chocolate_syrup.png', '작은 냉장고', '초코시럽', 40, 1800, '2022-07-10', 6, null, '정상', 20, '냉장', '초코의나라', 2),
        (31, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-08-25', 'product/ad0c8b1e-acab-4cdb-b4dd-eae14c8362ac_strawberry_syrup.png', '작은 냉장고', '딸기시럽', 80, 4830, '2023-04-20', 2, null, '정상', 5, '냉장', '딸기가제철이여', 2),
        (32, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-09-07', 'product/c8542c56-683a-4d3c-b6f5-d1e7e3e5c736_mlik.png', '큰 냉장고', '우유', 100, 12000, '2023-08-20', 15, null, '정상', 30, '냉장', '서울우유', 2),
        (33, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-09-08', 'product/a3c03a85-af7a-4db1-939c-92d4681c1146_sugarstick.png', '카운터 뒤 서랍', '일회용설탕', 20, 19000, '2023-08-18', 1, 'https://www.coupang.com', '정상', 3, '상온', 'cj제일제당', 2),
        (34, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2025-09-11', 'product/9e1fc486-6f84-4d58-b9d0-c3ffff38e199_wondo.png', '카운터 뒤 테이블', '카페원두', 100, 60000, '2023-07-20', 10, null, '정상', 25, '상온', '원두는싸다', 2),
        (35, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-11-25', 'product/9513e7fa-24b1-43cb-accf-04c5ce3a1970_chocochips.png', '카운터 뒤 서랍', '초코칩', 60, 7800, '2023-07-20', 3, null, '정상', 10, '상온', '초코의나라', 2),
        (36, '2023-08-22 00:49:19.000000', '2023-08-23 03:37:05.070009', '2024-10-31', 'product/6eb2fcfc-a765-49f4-9806-323c848f396d_chicken.jpg', '큰 냉동고', '치킨너겟', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉동', '너겟은사랑', 3),
        (37, '2023-08-22 00:49:19.000000', '2023-08-23 03:37:57.000213', '2024-10-25', 'product/40d8f22c-18d8-4e49-b181-dd4c7c8adfa4_mando.jpg', '큰 냉동고', '비비고만두', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉동', '비비고', 3),
        (38, '2023-08-22 00:49:19.000000', '2023-08-23 03:38:25.894583', '2024-10-25', 'product/58b5cdbd-76fb-4bc4-a6d1-2fec76320977_kimvhi.jpg', '큰 냉동고', '김치주먹밥', 60, 8800, '2023-07-20', 3, null, '정상', 10, '냉동', '비비고', 3),
        (39, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-07-23', 'product/93aa0453-ed27-4027-b928-9caa6a8d8f83_croquemo.png', '작은 냉동고', '크로크무슈', 40, 1800, '2022-07-10', 6, null, '정상', 20, '냉동', '크로크무슈사랑해', 3),
        (40, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-08-25', 'product/78fbfe58-419c-4667-b6ab-9b0988eab866_icepack.png', '작은 냉동고', '아이스팩', 80, 1330, '2023-04-20', 2, null, '정상', 5, '냉동', '차가워너무나', 3),
        (41, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-08-25', 'product/a4b026e4-fc49-48e9-b52a-cb4fcecd4093_maskaponae.png', '큰 냉동고', '마스카포네', 100, 2000, '2023-07-20', 5, null, '정상', 11, '냉동', '치이즈', 3),
        (42, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-09-08', 'product/d60d441d-8af2-4aa2-a5b9-3d5cae2608b1_spamrice.png', '작은 냉동고', '스팸주먹밥', 100, 19000, '2023-08-18', 5, null, '정상', 20, '냉동', '비비고', 3),
        (43, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2025-09-11', 'product/7219f794-6e0f-4b3c-b838-20f1da0cec4e_bagel.png', '작은 냉동고', '베이글', 60, 1200, '2023-07-20', 10, null, '정상', 30, '냉동', '이글이글베이글', 3),
        (44, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-11-25', 'product/d08f4155-e160-4d18-aa27-218612820ba5_caramel_syrup.png', '작은 냉장고', '카라멜시럽', 60, 7800, '2023-07-20', 3, null, '정상', 10, '냉장', '라멜이', 3),
        (45, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-31', 'product/82d68fa9-68ca-43ad-8587-f809e0c9634b_yoguret.png', '큰 냉장고', '요구르트', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉장', '한국요구르트', 3),
        (46, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-10-25', null, '큰 냉장고', '오렌지주스', 60, 8800, '2023-07-20', 3, null, '정상', 8, '냉장', '델몬트', 3),
        (47, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2023-08-25', 'product/aed28b9d-c4ce-4f14-91c6-d445573a9ecc_snack.png', '카운터 앞', '꼬북칩', 60, 1800, '2023-07-20', 3, null, '정상', 10, '상온', '오리온', 3),
        (48, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2026-07-23', 'product/d52d576c-fadf-4c31-9481-66fbc46916e7_spoon.png', '카운터 뒤 서랍', '일회용 수저', 40, 18000, '2022-07-10', 6, null, '정상', 20, '상온', '다이소', 3),
        (49, '2023-08-22 00:49:19.000000', '2023-08-22 00:49:19.000000', '2024-08-25', 'product/86d3467b-7f97-4a65-9d62-77f2690f2928_bowl.png', '카운터 뒤 서랍', '일회용 용기', 80, 4830, '2023-04-20', 2, null, '정상', 5, '상온', '다이소', 3);

insert into stockoneq.board (id, created_date, modified_date, content, hit, status, title, writer_id)
values  (1, '2023-08-24 00:09:32.000000', '2023-09-04 18:51:53.944901', '배달업체 할인 받을 수 있는 카드 추천해주세요! 배달비가 너무 비싸요 ㅠㅠ 자영업자에게 할인되는 카드 좀 알려주세요. 저는 지금 현대카드쓰고 있는데 아무런 혜택이 없어서 아쉽네요!!!! ㅠㅠㅠㅠㅠㅠㅠ', 133, '정상', '자영업자에게 할인 혜택이 제공되는 카드 추천해주세요!', 1),
        (2, '2023-08-24 00:09:32.000000', '2023-08-24 09:48:41.017241', '요즘 알바생 어떻게 뽑나요... 뽑을 때마다 힘드네요 꿀팁 좀 알려주세요', 28, '정상', '알바생 고르는 꿀팁 공유해주세요', 2),
        (3, '2023-08-24 00:09:32.000000', '2023-08-21 15:24:26.821423', '오늘 영업 매출이 별로 안 나왔어요... 장사를 계속해도 되는 걸까요... 다들 어떻게 버티십니까 ㅠㅠ', 59, '정상', '요즘 넘 힘들어요ㅠㅠ', 3),
        (4, '2023-08-24 00:09:32.000000', '2023-08-24 08:43:24.074787', '오래된 알바생에게 시급을 얼마나 올려주나요? 올려주는 시기를 얼마나 가져가야 할까요?!', 41, '정상', '알바생 시급 말이에요', 2),
        (5, '2023-08-24 00:09:32.000000', '2023-08-24 09:01:25.266914', '다들 오늘 하루는 어땠나요? 처음 가게 운영해보는 건데 너무 힘드네요~', 41, '정상', '오늘의 하루는?', 1),
        (6, '2023-08-24 00:09:32.000000', '2023-08-21 15:11:40.493588', '재료를 좀 더 싸게 구매할 수 있는 방법이 있나요?', 15, '정상', '재료비 질문해요 ㅠㅠ', 2),
        (7, '2023-08-24 00:09:32.000000', '2023-08-23 20:39:51.234117', '자영업 시작한지 22일이 지났습니다. 다들 세금 신고 어떻게 하시나요? 이게 운영하면서 의외로 힘든 구석이네요 ㅠㅠ', 30, '정상', '22일차 자린이가 질문합니다...', 1);

insert into stockoneq.board_like (id, board_id, user_id)
values  (1, 1, 2),
        (2, 1, 3),
        (3, 1, 4),
        (4, 1, 6),
        (5, 1, 7),
        (6, 1, 8),
        (7, 1, 9),
        (8, 1, 10),
        (9, 1, 11),
        (10, 2, 3),
        (11, 2, 4),
        (12, 2, 5),
        (13, 2, 7),
        (14, 2, 9),
        (15, 2, 10),
        (16, 2, 11),
        (17, 3, 1),
        (18, 3, 3),
        (19, 3, 4),
        (20, 3, 6),
        (21, 3, 7),
        (22, 3, 8),
        (23, 3, 9),
        (24, 3, 10),
        (25, 3, 11),
        (26, 3, 12),
        (27, 4, 4),
        (28, 4, 6),
        (29, 4, 7),
        (30, 4, 8),
        (31, 4, 9),
        (32, 4, 10),
        (33, 4, 11),
        (34, 4, 12),
        (35, 4, 13),
        (36, 4, 14),
        (37, 4, 15),
        (38, 4, 16),
        (39, 4, 17),
        (40, 4, 18),
        (41, 4, 19),
        (42, 4, 20),
        (43, 6, 4),
        (44, 6, 6),
        (45, 6, 7),
        (46, 6, 8),
        (47, 6, 9),
        (48, 6, 10),
        (49, 6, 11),
        (50, 6, 12),
        (51, 7, 4),
        (52, 7, 6),
        (53, 7, 7),
        (54, 7, 8),
        (55, 7, 9),
        (56, 7, 11),
        (57, 7, 12),
        (58, 8, 4),
        (59, 8, 6),
        (60, 8, 7),
        (61, 8, 8),
        (62, 8, 9),
        (63, 8, 11),
        (64, 8, 12),
        (65, 4, 1),
        (68, 2, 1),
        (69, 42, 1),
        (70, 13, 1),
        (71, 14, 1),
        (72, 53, 1),
        (74, 12, 1),
        (78, 64, 2),
        (79, 63, 2),
        (80, 16, 1),
        (81, 11, 1),
        (82, 15, 1),
        (84, 83, 1);

insert into stockoneq.comment (id, created_date, modified_date, content, image, status, board_id, writer_id)
values  (1, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', 'kb카드 할인돼요~', null, '정상', 1, 2),
        (2, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '신한카드도 할인됩니다!!! 전 많이 혜택보고 있어요!.', null, '정상', 1, 3),
        (3, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '공유 감사합니다!.', null, '정상', 1, 4),
        (4, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 자영업자인데 이런 혜택 받는 카드가 있는 줄도 몰랐네요 감사합니다.', null, '정상', 1, 5),
        (5, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '우리카드도 혜택 있는 걸로 알아요!', null, '정상', 1, 6),
        (6, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 그냥 뽑습니다... 딱히 다른 거 없어요', null, '정상', 2, 7),
        (7, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '열정 가득한 아이들로 추려서 면접보고 인상 좋은 친구 뽑아요~', null, '정상', 2, 8),
        (8, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 지인들 주로 부탁하거나 그런식으로 하는 거 같아요ㅠㅠ', null, '정상', 2, 9),
        (9, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '어차피 관두니까 그냥 빨리 구하는 게 나을지도 몰라요', null, '정상', 2, 10),
        (10, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '저도 그냥 뽑는 편이네요ㅠ', null, '정상', 2, 11),
        (11, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 주로 경력자를 뽑는 거 같아요ㅠㅠ 일하는 속도가 다르더라고요', null, '정상', 2, 12),
        (12, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '매출은 항상 저를 힘들게 합니다', null, '정상', 3, 7),
        (13, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '매출이란 무엇일까요...', null, '정상', 3, 5),
        (14, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', 'ㅠㅠㅠㅠㅠㅠ', null, '정상', 3, 6),
        (15, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '극히 공감하는 바입니다..', null, '정상', 3, 11),
        (16, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '매출 힘들죠 진짜ㅠㅠ', null, '정상', 3, 4),
        (17, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '다같이 파이팅입니다!', null, '정상', 3, 10),
        (18, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 잘 주려고 해요. 일 잘하는 사람들은 지속적으로 올려줍니다! 6개월에 한번?', null, '정상', 4, 7),
        (19, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 일년마다 올려주는 거 같아요!', null, '정상', 4, 11),
        (20, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '그러게요 저도 고민이 많습니다ㅠㅠ', null, '정상', 4, 4),
        (21, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '많이 겪어보시면 다 알아서 깨닳을 것입니다... 저도 그랬어요 ㅠㅠ', null, '정상', 5, 7),
        (22, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '아무래도 불필요한 소비를 막는게 제일 중요해요ㅠㅠ', null, '정상', 5, 5),
        (23, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '화이팅입니다!', null, '정상', 5, 6),
        (24, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '사장님 힘내세요ㅠㅠ', null, '정상', 5, 11),
        (25, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '저도 운영이 젤 힘드네요ㅠㅠ 모든 사람이 그러겠지만...', null, '정상', 5, 4),
        (26, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '고생하다 보면 나중에 다 좋아지더라구요!!!!', null, '정상', 5, 10),
        (27, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '도매로 삽니다 그게 제일 싸요', null, '정상', 6, 7),
        (28, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 제가 직접하는데 나름 익숙해졌어요.. 따로 공부했습니다 ㅠ', null, '정상', 7, 7),
        (29, '2023-08-21 21:54:21.000000', '2023-08-21 21:54:21.000000', '전 세무사 끼고 합니다 ㅠㅠ 혼자하는 거 넘 어렵쓰~', null, '정상', 7, 5);

insert into stockoneq.reply (id, created_date, modified_date, content, image, status, comment_id, writer_id)
values  (1, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '응원합니다.', null, '정상', 12, 6),
        (2, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '저도요', null, '정상', 12, 7),
        (3, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '응원!!', null, '정상', 12, 8),
        (4, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '힘내라 힘!', null, '정상', 12, 9),
        (5, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '언제나 파이팅', null, '정상', 12, 10),
        (6, '2023-08-21 21:54:46.000000', '2023-08-21 21:54:46.000000', '사장님 응원할게요', null, '정상', 12, 11),
        (7, '2023-08-21 15:17:31.607551', '2023-08-21 15:17:31.607551', '공감합니다', null, '정상', 12, 24);