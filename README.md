# Precision Unified Automation Framework

> **Hybrid Test Automation Framework** for [AutomationExercise.com](https://automationexercise.com)  
> Submitted to **Veeva Systems** | Version `1.0.0` | April 2026

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Tech Stack](#2-tech-stack)
3. [Prerequisites](#3-prerequisites)
4. [Project Structure](#4-project-structure)
5. [Setup & Installation](#5-setup--installation)
6. [Configuration](#6-configuration)
7. [Running Tests](#7-running-tests)
8. [Parallel Execution](#8-parallel-execution)
9. [Reporting](#9-reporting)
10. [Module Breakdown](#10-module-breakdown)
11. [Design Patterns](#11-design-patterns)

---

## 1. Project Overview

This framework automates both the **UI** and **REST API** layers of the AutomationExercise web application. It follows industry-standard practices to deliver reliable, maintainable, and scalable test automation coverage across critical user journeys — user registration, login, and cart management.

The framework adopts a **Behaviour Driven Development (BDD)** approach using Cucumber, enabling test scenarios to be written in plain English (Gherkin syntax) that are understandable by both technical and non-technical stakeholders. It is structured as a **Maven multi-module project**, separating UI and API concerns into dedicated modules while sharing common utilities across both.

### What's Automated

| Layer | Test Cases |
|-------|-----------|
| **UI** | Register User, Valid Login, Invalid Login, Add to Cart, Remove from Cart |
| **API** | GET All Products, POST Search Product, POST Create User, DELETE User, PUT Update User, Negative Validation |
| **Cross-Layer (Hybrid)** | End-to-end flows combining UI actions with API-level verification |

---

## 2. Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java (JDK) | 21 | Primary programming language |
| Maven | 3.x | Build tool & multi-module management |
| Selenium WebDriver | 4.41.0 | UI browser automation |
| WebDriverManager | 6.3.3 | Automatic driver binary management |
| Cucumber (Java + TestNG) | 7.34.3 | BDD framework (Gherkin → Java) |
| TestNG | 7.12.0 | Test execution engine & parallel runner |
| RestAssured | 5.5.7 | REST API testing & validation |
| RestAssured JSON Schema Validator | 5.5.7 | JSON contract/schema validation |
| Log4j2 | 2.25.3 | Structured logging to console & file |
| Allure Cucumber7 JVM | 2.29.0 | Interactive HTML test reporting |
| JavaFaker | 1.0.2 | Synthetic test data generation |
| Jackson | 2.17.0 | JSON serialization / deserialization |

---

## 3. Prerequisites

Before running the project, ensure the following are installed and configured on your machine.

### Required Software

| Tool | Version | Download |
|------|---------|----------|
| JDK | 21+ | [Adoptium](https://adoptium.net) |
| Maven | 3.8+ | [maven.apache.org](https://maven.apache.org) |
| Google Chrome | Latest | [google.com/chrome](https://www.google.com/chrome) |
| Allure CLI | 2.x | [allure.qameta.io](https://allure.qameta.io) |
| Git | Any | [git-scm.com](https://git-scm.com) |

### Verify Installation

```bash
java -version        # Should show: openjdk 21.x.x
mvn -version         # Should show: Apache Maven 3.x.x
allure --version     # Should show: 2.x.x
git --version        # Should show: git version x.x.x
```

> **Note:** WebDriverManager automatically downloads the correct ChromeDriver binary at runtime — no manual driver setup required.

---

## 4. Project Structure

```
automationexercise-hybrid-framework/          ← Root (Parent POM)
├── pom.xml                                    ← Parent POM (dependency management)
├── README.md
│
├── common/                                    ← Shared utilities module
│   ├── pom.xml
│   └── src/main/
│       ├── java/
│       │   ├── config/ConfigReader.java       ← Loads config.properties across modules
│       │   └── logger/LogsFactory.java        ← Log4j2 logger factory (used by all modules)
│       └── resources/
│           └── config.properties
│
├── api-automation/                            ← API test module (RestAssured + Cucumber)
│   ├── pom.xml
│   ├── master-testng.xml
│   ├── parallel-testng.xml
│   └── src/
│       ├── main/java/
│       │   └── com.automationexercise.api/
│       │       ├── builder/RequestBuilder.java
│       │       ├── clients/
│       │       │   ├── ProductApiClient.java
│       │       │   └── UserApiClient.java
│       │       ├── constants/ApiEndpoints.java
│       │       ├── payloads/User.java
│       │       ├── util/
│       │           ├── UserDataBuilder.java
│       │           └── UserFactory.java
│       │       └── validators/
│       │           ├── CommonValidator.java
│       │           ├── ProductValidator.java
│       │           └── UserValidator.java
│       └── test/
│           ├── java/
│           │   └── com.automationexercise.api/
│           │       ├── hooks/Hooks.java
│           │       ├── runners/
│           │       │   ├── APITestRunner.java      ← Sequential execution
│           │       │   ├── ParallelTestRunner.java ← Parallel execution
│           │       │   └── FailedTestRunner.java   ← Re-run failed scenarios only
│           │       └── stepdef/
│           │           ├── DeleteUserAccountSteps.java
│           │           ├── ProductSteps.java
│           │           ├── UpdateAccountSteps.java
│           │           └── UserAccountSteps.java
│           └── resources/
│               ├── allure.properties
│               ├── features/
│               │   ├── f1_product.feature
│               │   ├── f2_user.feature
│               │   └── f3_negative_validation.feature
│               └── schemas/
│                   ├── products-schema.json
│                   └── user-schema.json
│
├── cross-layer-integration/                   ← Hybrid UI + API integration module
│   ├── pom.xml
│   └── src/test/
│       ├── java/
│       │   └── com.automationexercise.crosslayer/
│       │       ├── hooks/Hooks.java
│       │       ├── runners/
│       │       │   ├── CrossLayerTestRunner.java
│       │       │   └── FailedTestRunner.java
│       │       └── stepdefinitions/HybridSteps.java
│       └── resources/
│           ├── features/
│           │   └── hybrid.feature
│           └── schemas/
│               └── user-schema.json
│
└── ui-automation/                             ← UI test module (Selenium + Cucumber)
    ├── pom.xml
    ├── master-testng.xml
    ├── parallel-testng.xml
    └── src/
        ├── main/java/
        │   └── com.automationexercise.ui/
        │       ├── driver/
        │       │   ├── DriverFactory.java     ← ThreadLocal<WebDriver> management
        │       │   └── OptionsFactory.java    ← Browser capabilities builder
        │       ├── pages/                     ← Page Object Model (POM) classes
        │       │   ├── AccountCreatedPage.java
        │       │   ├── AccountDeletedPage.java
        │       │   ├── CartPage.java
        │       │   ├── HomePage.java
        │       │   ├── LoginPage.java
        │       │   ├── ProductPage.java
        │       │   └── SignupPage.java
        │       └── utils/
        │           ├── RandomUtil.java
        │           ├── RegisterDataUtil.java
        │           ├── ScreenshotUtil.java
        │           └── WaitUtil.java
        └── test/
            ├── java/
            │   └── com.automationexercise.ui/
            │       ├── hooks/Hooks.java        ← @Before / @After Cucumber hooks
            │       ├── runners/
            │       │   ├── FailedTestRunner.java
            │       │   ├── ParallelTestRunner.java
            │       │   └── UITestRunner.java
            │       └── stepdefinitions/
            │           ├── AddProductSteps.java
            │           ├── LoginSteps.java
            │           ├── RegisterSteps.java
            │           └── RemoveProductSteps.java
            └── resources/
                ├── allure.properties
                ├── log4j2.xml
                └── features/
                    ├── f1_register.feature
                    ├── f2_login.feature
                    └── f3_cart.feature
```

---

## 5. Setup & Installation

### Step 1 — Clone the Repository

```bash
git clone https://github.com/<your-org>/automationexercise-hybrid-framework.git
cd automationexercise-hybrid-framework
```

### Step 2 — Build the Project

```bash
mvn clean install -DskipTests
```

This compiles all modules, resolves dependencies, and installs the `common` module JAR into your local Maven repository so `ui-automation` and `api-automation` can reference it.

### Step 3 — Verify the Build

```bash
mvn validate
```

You should see `BUILD SUCCESS` with no errors. If you see dependency resolution failures, ensure your internet connection is active so Maven can download from Central.

---

## 6. Configuration

All environment-specific values are externalized — **no hardcoding exists in any Java class or feature file.**

### UI Module — `ui-automation/src/test/resources/config.properties`

```properties
# ── Application ──────────────────────────────────────
base.url=https://automationexercise.com/
browser=chrome                          # Options: chrome | edge

# ── Registration Data ────────────────────────────────
register.name=Test
register.email=test                     # Timestamp appended automatically at runtime
register.title=Mr                       # Options: Mr | Mrs
register.password=Test@123
register.dob.day=1
register.dob.month=January
register.dob.year=2000
register.firstname=Test
register.lastname=Max
register.company=Test Inc
register.address=9-23 Test Street
register.country=United States
register.state=Test State
register.city=Test City
register.zipcode=546732
register.mobile=9876543210

# ── Login Credentials ────────────────────────────────
login.email=test5656@gmail.com          # Valid email for positive login test (TC2)
login.password=Test@123
login.invalidemail=test@gmail.com       # Invalid email for negative login test (TC3)
```

### API Module — `api-automation/src/test/resources/config.properties`

```properties
# ── API Base URL ─────────────────────────────────────
base.url=https://automationexercise.com/api
```

> **Switching environments:** To point the framework at a different environment, update only the `base.url` in the relevant `config.properties` file. No Java code changes are needed.

### Supported Browsers

| Value in config | Browser launched |
|----------------|-----------------|
| `chrome` | Google Chrome (default) |
| `edge` | Microsoft Edge |

---

## 7. Running Tests

All commands are run from the **project root directory**.

### Run All Tests (UI + API, Sequential)

```bash
mvn clean test
```

### Run UI Tests Only

```bash
mvn clean test -pl ui-automation
```

### Run API Tests Only

```bash
mvn clean test -pl api-automation
```

### Run Cross-Layer (Hybrid) Tests Only

```bash
mvn clean test -pl cross-layer-integration
```

### Run a Specific Feature File

```bash
mvn clean test -pl ui-automation -Dcucumber.features=src/test/resources/features/f2_login.feature
```

### Run Tests by Tag

```bash
mvn clean test -pl ui-automation -Dcucumber.filter.tags="@smoke"
mvn clean test -pl ui-automation -Dcucumber.filter.tags="@regression and not @wip"
```

### Re-run Only Failed Scenarios

After a run that produced `target/failed_scenarios.txt`:

```bash
mvn test -pl ui-automation -Dsurefire.suiteXmlFiles=testng-rerun.xml
```

---

## 8. Parallel Execution

The framework supports **scenario-level parallel execution** in the UI module. Three runners are available:

| Runner | Class | Behaviour |
|--------|-------|-----------|
| `UITestRunner` | `runners.UITestRunner` | Sequential — single thread, for debugging and CI baseline |
| `ParallelTestRunner` | `runners.ParallelTestRunner` | Parallel — multiple browser sessions run concurrently |
| `FailedTestRunner` | `runners.FailedTestRunner` | Re-runs only scenarios that failed in the previous run |

### Run in Parallel Mode

```bash
mvn clean test -pl ui-automation -Dsurefire.suiteXmlFiles=testng-parallel.xml
```

### How Thread Safety Works

```
Each scenario  →  its own thread  →  its own WebDriver instance
                                         (stored in ThreadLocal<WebDriver>)

@Before  →  DriverFactory.initDriver()   Creates driver for this thread
  test runs...
@After   →  DriverFactory.quitDriver()   Quits driver, clears ThreadLocal
```

No WebDriver instance is ever shared between threads — parallel execution is fully isolated by design.

---

## 9. Reporting

### Allure Report (Recommended)

After any test run, generate and open the interactive Allure report:

```bash
allure serve target/allure-results
```

This opens a browser window with:
- Step-by-step scenario breakdown with pass/fail/skip status
- Execution timeline and duration per scenario
- Embedded **failure screenshots** directly in the report
- Tag-based filtering and suite grouping

To generate a static HTML export (for sharing):

```bash
allure generate target/allure-results --clean -o allure-report
```

### Cucumber HTML Report

Located at: `target/cucumber-reports/report.html`  
Open directly in any browser — no server required.

### All Report Artifacts

| Artifact | Path | Description |
|----------|------|-------------|
| Allure Results | `target/allure-results/` | Raw data for `allure serve` |
| Cucumber HTML | `target/cucumber-reports/report.html` | Standalone HTML report |
| Cucumber JSON | `target/cucumber-reports/report.json` | CI tool integration |
| TestNG Report | `test-output/index.html` | TestNG suite summary |
| Log File | `target/logs/automation.log` | Full Log4j2 execution log |
| Failed Scenarios | `target/failed_scenarios.txt` | Input for `FailedTestRunner` |

---

## 10. Module Breakdown

### `common` — Shared Utilities

| Class | Description |
|-------|-------------|
| `LogsFactory` | Wraps Log4j2's `LogManager`. Provides `getLogger(Class<?>)` used by all classes across all modules for consistent structured logging. |
| `ConfigReader` | Loads `config.properties` once at class init. Throws `RuntimeException` on missing keys — misconfiguration fails fast. Shared by both `ui-automation` and `api-automation`. |

### `ui-automation` — Key Classes

| Class | Description |
|-------|-------------|
| `DriverFactory` | Manages `ThreadLocal<WebDriver>`. Initializes Chrome/Edge, sets implicit waits, and provides `getDriver()` / `quitDriver()`. |
| `OptionsFactory` | Builds browser-specific options. Suppresses notifications, removes automation flags, and blocks ad-serving domains. |
| `Hooks` | `@Before` initializes driver and navigates to base URL. `@After` captures screenshot on failure and quits driver. |
| `UITestRunner` | Sequential Cucumber runner — single thread, for debugging and CI baseline. |
| `ParallelTestRunner` | Parallel Cucumber runner — multiple browser sessions run concurrently via TestNG. |
| `FailedTestRunner` | Re-runs only scenarios that failed in the previous run. |
| `WaitUtil` | Returns a `WebDriverWait` bound to the current thread's driver. Used for all explicit waits in Page Objects. |
| `ScreenshotUtil` | Captures a `byte[]` screenshot via `TakesScreenshot`. Attached to Cucumber scenario reports on failure. |
| `RandomUtil` | Generates a unique email by appending a timestamp to the base email from config. Prevents duplicate registrations. |
| `RegisterDataUtil` | Builds a `Map<String, String>` of all registration form fields from `config.properties`. |

### `api-automation` — Key Classes

| Class | Description |
|-------|-------------|
| `RequestBuilder` | Constructs RestAssured `RequestSpecification` objects. Provides `get()`, `post()`, and `postCreateAccount()` methods. |
| `ProductApiClient` | Wraps `RequestBuilder` to expose `getAllProducts()` and `getSingleProduct(param)`. |
| `UserApiClient` | Wraps `RequestBuilder` to expose user account operations (create, update, delete). |
| `CommonValidator` | Base validator with `validateStatusCode()`, `validateMessage()`, `validateResponseTime()`, `validateContentType()`. |
| `ProductValidator` | Extends `CommonValidator`. Adds JSON schema validation and product name assertion. |
| `UserValidator` | Extends `CommonValidator`. Validates user account API responses and schema. |
| `ApiEndpoints` | Central constants class — all endpoint URLs as `public static final String`. |
| `UserDataBuilder` | Builds a `User` POJO using JavaFaker for dynamic, realistic test data. |
| `APITestRunner` | Sequential Cucumber runner for API tests. |
| `ParallelTestRunner` | Parallel Cucumber runner for API tests. |
| `FailedTestRunner` | Re-runs only failed API scenarios from the previous run. |

### `cross-layer-integration` — Key Classes

| Class | Description |
|-------|-------------|
| `HybridSteps` | Step definitions that combine UI and API actions within a single scenario — validates end-to-end flows across both layers. |
| `Hooks` | `@Before` / `@After` hooks scoped to the hybrid module, handling setup and teardown for cross-layer tests. |
| `CrossLayerTestRunner` | Cucumber runner for hybrid feature execution. |
| `FailedTestRunner` | Re-runs only failed hybrid scenarios from the previous run. |

---

## 11. Design Patterns

| Pattern | Applied In | Benefit |
|---------|-----------|---------|
| **Page Object Model (POM)** | `ui-automation/pages/*` | Locator changes require edits in only one place — no test logic impact. |
| **Factory Pattern** | `DriverFactory`, `OptionsFactory` | Adding a new browser requires changes only in factory classes. |
| **Singleton Pattern** | `DriverFactory` (ThreadLocal), `ConfigReader` | Prevents redundant object creation; ensures consistent shared state. |
| **Builder Pattern** | `RequestBuilder` | Centralizes all API request construction; clients and step defs are unaffected by changes. |
| **Layered Architecture** | API module (Client → Builder → Validator) | Each layer has a single responsibility; independently modifiable and testable. |
| **BDD (Cucumber)** | All feature files | Scenarios readable by non-technical stakeholders without knowing Java. |
| **Cross-Layer Integration** | `cross-layer-integration` module | Combines UI and API in a single test flow, validating end-to-end consistency across layers. |

---

## Troubleshooting

**Tests fail with `SessionNotCreatedException`**  
Chrome version mismatch. WebDriverManager should handle this automatically — ensure you have an active internet connection on first run.

**`RuntimeException: Property not found: <key>`**  
A key is missing from `config.properties`. Check the key name against the configuration table in Section 6.

**Allure report is empty after a run**  
Verify `allure.properties` in the module's resources folder points to `target/allure-results`. Run `allure serve target/allure-results` from the module directory, not the root.

**Parallel tests fail with `NullPointerException` on WebDriver**  
Ensure all Page Object constructors accept the `WebDriver` from `DriverFactory.getDriver()` and do not store a static driver reference.

---

*Precision Unified Automation Framework — Veeva Systems Fresh Graduate Assignment, April 2026*
