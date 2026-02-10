Feature: Validating Place Api's

  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with post http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


    Examples:
      | name                           | language | address                                |
      | Sandton City Convention Centre | English  | 161 Maude St, Sandown, Sandton, 2196   |
      | Gallagher Convention Centre    | English  | 19 Richards Dr, Halfway House, Midrand |
