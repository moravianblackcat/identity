@person
Feature: Person information

  Scenario: Person information is obtained
	Given Persons with the following properties exist:
	  | id | nationality | first_name | last_name | name          | display_name | date_of_birth |
	  | 1  | USA         | John       | Smith     | John Smith    | Johnny       | 1990-01-01    |
	  | 2  | USA         | Jon        | Smyth     | Jon Smyth     | Jonny        | 1991-02-02    |
	  | 3  | USA         | Johan      | Schmidt   | Johan Schmidt | Jo           | 1992-03-03    |
	When Search for person with name Smith is requested
	Then /api/v1/person returns array of following objects:
	  | id | nationality | firstName | lastName | dateOfBirth |
	  | 1  | USA         | John      | Smith    | 1990-01-01  |