-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name update-user! :! :n
-- :doc update an existing user record
UPDATE users
SET first_name = :first_name, last_name = :last_name, email = :email
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name delete-user! :! :n
-- :doc delete a user given the id
DELETE FROM users
WHERE id = :id

-- :name create-team! :! :n
-- :doc creates a new team record
INSERT INTO teams
(team_name)
VALUES (:team_name)

-- :name get-team :? :1
-- :doc retrieve a team given the name
SELECT * FROM teams
WHERE team_name = :team_name

-- :name delete-team! :! :n
-- :doc delete a team given the id
DELETE FROM teams
WHERE team_name = :team_name
