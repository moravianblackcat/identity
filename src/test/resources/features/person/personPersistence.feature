@person
Feature: Person persistence
  
  Scenario: Person is delivered
	When Person is delivered with the following properties:
	  | id          | 22             |
	  | nationality | CZE            |
	  | firstName   | Josef          |
	  | lastName    | Masopust       |
	  | name        | Josef Masopust |
	  | displayName | Josef Masopust |
	  | dateOfBirth | 1931-02-09     |
	Then 2s Person with the following properties is saved:
	  | id            | 22             |
	  | nationality   | CZE            |
	  | first_name    | Josef          |
	  | last_name     | Masopust       |
	  | name          | Josef Masopust |
	  | display_name  | Josef Masopust |
	  | date_of_birth | 1931-02-09     |