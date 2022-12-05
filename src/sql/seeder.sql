USE vueblog_db;

# password: groovy
INSERT INTO users (id, email, password, role, username)
VALUES(1, 'jay@email.com', '$2a$10$yOkrbY0g4bNupMXjP8qSG.aygWa8n4hyLJ680yufr7Is4sjMyWnFi', 'USER', 'jayman123');

INSERT INTO posts (id, body, title, author_id)
VALUES(1, 'This is my first blog!', 'My First Blog', 1),
      (2, 'Wow, today is such a beautiful day! I took a stroll down to the market and it was amazing!', 'Sunny Day', 1),
      (3, 'Thanksgiving Dinner was awesome! My favorite dish this year was the cheesy mashed potatoes!', 'Thanksgiving Dinner', 1),
      (4, 'My New Year\'s Resolution is code and learn something new every day!', 'Happy New Year!', 1);