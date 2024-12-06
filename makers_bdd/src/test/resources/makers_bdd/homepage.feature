Feature: Homepage

  Scenario Outline: Testing links on Makers homepage navigation
    Given I am on the Makers homepage
    When I click the "<page_title>" link
    Then I should be on the "<title>" page
    Examples:
      | page_title              | title                                |
      | Student T&C             | Student Terms and Conditions         |
      | Apprenticeships         | Software Engineer Apprenticeships    |
      | Code of Conduct         | Code of conduct                      |
      | Bootcamp                | Software Engineering Bootcamp        |
      | Hire an Apprentice      | Apprentices                          |
      | Reviews                 | Makers Academy Reviews               |
      | Community               | Once a Maker, Always a Maker - Learn About Our Community |
      | Blog                    | Medium                               |
      | About Us                | About Us                             |
      | Curriculum              | Curriculum                           |
      | Our Partners            | Our Partners                         |
      | Our Impact              | About Us                             |
      | Privacy Policy          | Privacy Policy                       |
      | FAQs                    | Help Center                          |
      | Apprenticeship Policies | Apprenticeship Policies              |
      | Financing               | Financing options                    |
      | Job Hunting Support     | Job Hunting Support                  |
