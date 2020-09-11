# new feature
# Tags: optional

Feature: Test gmail account

  Scenario Outline: Log in to Gmail , mark n letter as important, and delete it
    When Log in to Gmail account with "<login>" and "<password>"
    And verify gmail main page is open
    Then mark <n> letter as important
    And Verify letter was moved to important list
    Then  Open important letters list
    And Mark <n> letters and delete it
    And Verify letter was deleted

    Examples:
      | login                      | password           | n |
      | besttesterever12@gmail.com | besttester20200828 | 3 |
      | basetestjava@gmail.com     | baseTestjava1      | 3 |
      | javatestsimple@gmail.com   | javaScript1290     | 3 |
      | cucumberjonny@gmail.com    | jonnyCucu123ber    | 3 |
      | lasttestt336@gmail.com     | lastTest54321      | 3 |

