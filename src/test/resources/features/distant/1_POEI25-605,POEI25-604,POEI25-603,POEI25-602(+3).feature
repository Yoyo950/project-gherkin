Feature: 

	@POEI25-605 @YD_FullAuto
	Scenario: exo1B feature
		Given I am on the login page of the site
		When I enter the username "standard_user" and the password "notthis" and click on the validation button
		  Then I should have an error displaying and stay on the login page.
		
	@POEI25-604 @YD_FullAuto
	Scenario: Exo6 feature
		Given I enter the username "standard_user" and the password "secret_sauce" and click on the validation button
		    When I click on the burger menu
		    And I click on logout
		    Then I am on the login page of the site
		
	@POEI25-603 @YD_FullAuto
	Scenario: Exo5 feature
		Given I enter the username "standard_user" and the password "secret_sauce" and click on the validation button
		    When I select sort price from low to high
		    Then The product are sorted from their prices lowest to highest
		
	@POEI25-602 @YD_FullAuto
	Scenario Outline: Exo4 feature
		Given I enter the username "standard_user" and the password "secret_sauce" and click on the validation button
		    And I click on the Add to cart button on 'Sauce Labs Backpack'
		    And I go to the cart page
		    When I click on checkout
		    Then I am on checkout page one
		    When I enter my "<first_name>", "<last_name>", "<postal_code>", and click on continue
		    Then I am on checkout page two
		    When I click on finish
		    Then I am on checkout complete page
		    When I click on back home
		    Then I am on the homepage of the site
		    Examples:
		      | first_name | last_name | postal_code |
		      | Yoann      | Dagand    | 94240       |
		      | Louis      | Aragon    | 95240       |
		      | Patrick    | Bruel     | 75016       |
		
	@POEI25-601 @YD_FullAuto
	Scenario: Exo3 feature
		Given I enter the username "standard_user" and the password "secret_sauce" and click on the validation button
		    When I click on the Add to cart button on 'Sauce Labs Backpack'
		    And I go to the cart page
		    Then The product 'Sauce Labs Backpack' is added to the cart
		
	@POEI25-600 @YD_FullAuto
	Scenario Outline: Exo 2 Feature
		When I enter the username "<username>" and the password "<password>" and click on the validation button
		    Then I am on the homepage of the site
		    Examples:
		      | username                | password     |
		      | standard_user           | secret_sauce |
		      | performance_glitch_user | secret_sauce |
		      | visual_user             | secret_sauce |
		
	@POEI25-599 @YD_FullAuto
	Scenario: Exo1 Feature
		Given I am on the login page of the site
		    When I enter the username "standard_user" and the password "secret_sauce" and click on the validation button
		    Then I am on the homepage of the site
		
