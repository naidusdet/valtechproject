Feature: Valtech Section Headings And Contact Locations Assertion Scenarios.

  @1Test
  Scenario Outline: Display Latest News Section
    Given I am on valtech home page
    Then  I should see section named "<section>"
    Examples:
      | section        |
      | LATEST NEWS    |

  @2Test
  Scenario Outline: Display About, Work, Services Section Headings
    Given I am on valtech home page
    When I click on Navigation "<Tab>"
    Then  I should see header named "<header>"
    Examples:
      |Tab               | header         |
      | ABOUT            | About          |
      | SERVICES         | Services       |
      | WORK             | Work           |

  @3Test
  Scenario: Count All Valtech Locations From Contact Page
    Given I am on valtech home page
    When I click on contact page icon
    Then I should see count contact locations