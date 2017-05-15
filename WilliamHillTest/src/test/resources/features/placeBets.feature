Feature: Place a bet on williamhill.com

  Scenario Outline: Place a bet 
     When I open williamhill website to place a bet "0.05" for <Sport>
     Then Check retun value on bet slip
      And Check Total stake on bet slip
      And User balance amount is reduced by bet amount
      
      Examples:
      		|Sport|
      		|Football|
      		|Tennis  |
      		
      				
  
