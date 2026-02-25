## Google Maps Add Place API Automation Framework
Overview

- This project is a Cucumber + RestAssured API automation framework for testing the Google Maps “Add Place” API. It demonstrates:
- Dynamic request payload generation for Google Maps API
- Shared scenario state using TestContext and PicoContainer
- Reusable API endpoint management with APIResources enum
- Automated validation of API responses using JUnit assertions
- This framework is modular, maintainable, and demonstrates best practices for API automation.

## Features

- Dynamic Google Maps Payloads: Build JSON payloads at runtime with customizable input.
- Reusable Request Specs: Centralized request configuration using SpecBuilderUtil.
- Shared Context: Use TestContext to store responses and place_id between steps.
- Cucumber Integration: Gherkin feature files drive test scenarios.
- Assertions: Validate HTTP status codes and response body content.
- Dynamic API Calls: Single method supports multiple endpoints and HTTP methods.

## Dependencies

- Java 11+
- Maven (for dependency management
- Cucumber Java – BDD framework
- RestAssured – API automation
- JUnit – Assertions and test execution

 ## Best Practices Demonstrated

- Dependency Injection via PicoContainer
- Centralized request/response specifications
- Dynamic and reusable step definitions
- Shared context for cross-step state management
- Clear separation of Given-When-Then logic

 ## Author

- Name: Kagisho
- Title: Junior QA / SDET | Automation Engineer
- LinkedIn: https://www.linkedin.com/in/kagisho
