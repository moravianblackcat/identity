@football
Feature: Football persistence
  
  Scenario: Football player is delivered
	When Football player is delivered with the following properties:
	  | id          | 1            |
	  | nationality | GER          |
	  | position    | goalkeeper   |
	  | firstName   | Manuel       |
	  | lastName    | Neuer        |
	  | name        | Manuel Neuer |
	  | displayName | Manuel Neuer |
	  | dateOfBirth | 1986-03-27   |
	Then 4s Person with the following properties is saved:
	  | id            | 1            |
	  | nationality   | GER          |
	  | first_name    | Manuel       |
	  | last_name     | Neuer        |
	  | name          | Manuel Neuer |
	  | display_name  | Manuel Neuer |
	  | date_of_birth | 1986-03-27   |
	And 2s Football player profile with the following properties is saved:
	  | person_id | 1          |
	  | position  | goalkeeper |
  
  Scenario: Football team is delivered
	When Football team is delivered with the following properties:
	  | id      | 1              |
	  | name    | Dundee United  |
	  | founded | 1909           |
	  | country | SCO            |
	  | stadium | Tannadice Park |
	  | city    | Dundee         |
	Then 2s Football team with the following properties is saved:
	  | id      | 1              |
	  | name    | Dundee United  |
	  | founded | 1909           |
	  | country | SCO            |
	  | stadium | Tannadice Park |
	  | city    | Dundee         |