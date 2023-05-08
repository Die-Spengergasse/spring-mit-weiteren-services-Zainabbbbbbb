
INSERT INTO Communication (priority, received)
VALUES ('routine', '2022-01-01T10:00:00'),
       ('urgent', '2022-02-02T14:30:00'),
       ('asap', '2022-03-03T16:45:00'),
       ('stat', '2022-04-04T09:15:00');

-
INSERT INTO IdentifierUse (communication_id, system, value)
VALUES (1, 'http://example.com/communication', '12345'),
       (2, 'http://example.com/communication', '67890'),
       (3, 'http://example.com/communication', 'ABCDE');


INSERT INTO CodeableConcept (communication_id, coding_system, coding_code, coding_display)
VALUES (1, 'http://example.com/communication/medium', 'email', 'Email'),
       (2, 'http://example.com/communication/medium', 'sms', 'SMS'),
       (2, 'http://example.com/communication/medium', 'phone', 'Phone'),
       (3, 'http://example.com/communication/medium', 'sms', 'SMS'),
       (3, 'http://example.com/communication/medium', 'email', 'Email');


INSERT INTO message_payload (communication_id, content_string)
VALUES (1, 'This is a routine communication.'),
       (2, 'This is an urgent communication.'),
       (3, 'This is an ASAP communication.'),
       (3, 'This is a STAT communication.');