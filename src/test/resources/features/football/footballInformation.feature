@football
Feature: Football information
  
  Scenario: Football team information is obtained
	Given Teams with the following properties exist:
	  | id  | name          | founded | country | stadium        | city        |
	  | 14  | Dundee United | 1909    | SCO     | Tannadice Park | Dundee      |
	  | 41  | Dundee        | 1893    | SCO     | Dens Park      | Dundee      |
	  | 421 | Dunfermline   | 1885    | SCO     | East End Park  | Dunfermline |
	When Search for team with name Dund is requested
	Then /api/v1/team returns array of following objects:
	  | id  | name          | country |
	  | 14  | Dundee United | SCO     |
	  | 41  | Dundee        | SCO     |