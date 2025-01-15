Feature: Validation Place API

  @AddPlace
  Scenario Outline: Validate if place is successfully added using AddPlaceApi
    Given  Add Place Payload "<address>" "<name>" "<language>"
    When  User calls "AddplaceApi" with "Post" Http Request
    Then  the Api call got success with Status code 200
    And  "status" in Response Body is "OK"
    And  "scope" in Response Body is "APP"

    And Verify placeId created that maps to "<name>" using "GetPlaceApi"

    Examples:
      | address | language  | name            |
      | cohen   | French-IN | Frontline house |
      #| Charlotte | German    | Welson          |

  @DeletePlace
  Scenario: Verify Delete Place is working
    Given DeletePlace Payload
    When  User calls "DeletePlaceApi" with "Post" Http Request
    Then  the Api call got success with Status code 200
    And  "status" in Response Body is "OK"



