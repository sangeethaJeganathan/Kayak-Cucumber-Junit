Feature: Check search Details and result matches
Scenario Outline: Enter search Details
Given launch URL
When Enter the origin and destination "<row index>"
And Enter From and to date "<row index>"
Then click on the search button

  Examples:
    |row index|
    | 1      |


