# Coding Solutions Guidelines

Applies to this repository. Write production-ready, secure, and maintainable code. Target Java 24 runtime while favoring features that are stable by the latest LTS (Java 21). Do not use preview/incubator features unless explicitly enabled in the build and justified in the PR.

## 0. How to Use
- Treat these as acceptance criteria for code reviews and PRs.
- Prefer clarity over cleverness; optimize only after measuring.
- When in doubt, document rationale in code comments or the PR description.

## 1. Fundamental Principles
- SOLID, DRY, KISS; small cohesive methods and meaningful names.
- Prefer composition over inheritance; apply design patterns judiciously.
- Follow Java conventions (naming, packages) and project formatting rules.

## 2. Architecture and Design
- Layered architecture (API, service, domain, persistence); clear separation of concerns.
- Dependency Injection via constructors by default.
- Stable interfaces and abstractions; design for testability and maintainability.
- Immutable data where possible; minimize shared mutable state.
- 12-factor app practices for cloud-ready services.

## 3. Security
- Validate and sanitize all inputs; enforce size, format, and range constraints.
- Parameterized queries/prepared statements only; no string concatenation in SQL.
- Protect against common threats: SQLi, XSS, CSRF, SSRF, deserialization, path traversal.
- Enforce authentication/authorization on sensitive operations; least-privilege access.
- Never hardcode secrets; use environment variables or secret managers.
- Encrypt sensitive data at rest and in transit; HTTPS/TLS everywhere.
- Rate limiting/throttling on public endpoints.
- Log security-relevant events without leaking secrets or PII.

## 4. Error Handling and Logging
- Throw specific exceptions; do not swallow errors.
- User-facing errors are safe and actionable; detailed diagnostics go to logs.
- Centralize handling:
  - Spring MVC: @ControllerAdvice + @ExceptionHandler with consistent response types (e.g., problem details/ResponseEntity).
  - Jakarta EE: Exception mappers/handlers where applicable.
- Fail fast on unrecoverable states; degrade gracefully for non-critical features.
- Use try-with-resources for deterministic cleanup.
- Avoid NPEs via validation and judicious use of Optional.
- Structured, leveled logging with correlation/trace IDs.

## 5. Java Language and Features
- Runtime: Java 24; prefer APIs and patterns stable by Java 21 LTS.
- Use:
  - Records for immutable data carriers.
  - Sealed classes/interfaces for constrained hierarchies.
  - Pattern matching for instanceof and switch expressions.
  - Text blocks for multiline strings.
  - Streams, lambdas, and functional style where it improves clarity.
  - Virtual threads for scalable I/O-bound workloads when appropriate.
- Avoid deprecated/removed APIs. Use preview/incubator features only if explicitly enabled and justified.

## 6. Code Quality
- Javadoc for public APIs; concise comments for non-obvious logic.
- Proper access modifiers; minimize visibility.
- Use final for constants and immutable fields.
- Builders for complex object construction; avoid telescoping constructors.
- Enforce formatting and static analysis (e.g., SpotBugs, Checkstyle, Error Prone) as configured.
- Code must be unit-testable with meaningful coverage.

## 7. Framework Guidance
- Spring MVC:
  - Constructor injection; avoid field injection.
  - Use @Validated and Bean Validation (jakarta.validation) on DTOs and method parameters.
  - Consistent API responses (ResponseEntity or RFC 7807 problem details).
  - Centralize exceptions with @ControllerAdvice.
- Jakarta EE:
  - Use jakarta.* imports exclusively (no javax.*).
  - Use CDI for DI and Jakarta Transactions for boundaries.
  - Use Bean Validation, JSON-B/JSON-P as needed.
- Lombok:
  - Use thoughtfully (@Builder, @Value, @Getter/@Setter, @Slf4j).
  - Do not auto-generate equals/hashCode/toString where domain rules require custom logic.

## 8. Persistence and Data
- Repository pattern; keep business logic out of repositories.
- Transactions at service boundaries with clear semantics.
- Database migrations with versioning (Flyway/Liquibase).
- Separate DTOs from Entities; do not expose entities over the wire.
- Implement secure, efficient pagination, filtering, and sorting.
- Avoid N+1 queries; prefer joins/fetch strategies or batch operations.

## 9. Performance and Scalability
- Choose appropriate data structures and algorithms; measure before optimizing.
- Use connection pooling and reuse expensive resources.
- Cache where appropriate with explicit invalidation strategies.
- Use async/non-blocking or virtual threads when beneficial.
- Minimize network round-trips and excessive remote calls.

## 10. Observability
- Structured logs with levels (TRACE/DEBUG/INFO/WARN/ERROR).
- Correlation IDs for distributed tracing; integrate OpenTelemetry where applicable.
- Metrics, health checks, readiness/liveness endpoints.

## 11. Testing Strategy
- Unit tests: fast, isolated, deterministic.
- Integration tests for persistence, messaging, and web layers.
- Use Testcontainers or embedded servers where helpful.
- Mock external systems; avoid real network calls in unit tests.
- Property-based tests for critical logic when useful.

## 12. Configuration and Secrets
- Externalize configuration via env/profiles; provide secure defaults.
- Manage secrets with vaults/secret managers; never commit secrets.
- Fail secure when configuration is missing.

## 13. Dependency Management
- Pin versions; prefer LTS-supported libraries.
- Avoid unnecessary dependencies; audit regularly (e.g., OWASP Dependency-Check).
- Control transitives; use BOMs where appropriate.

## 14. API Design
- Consistent REST conventions; meaningful resource names; idempotency where needed.
- Validation errors return structured problem details.
- Version APIs; avoid breaking changes without deprecation and migration paths.
- Document endpoints with OpenAPI/Swagger.

## 15. Definition of Done (Review Checklist)
- SOLID/DRY/clean code; clear naming and structure.
- Proper validation, authorization, and centralized error handling.
- Uses stable Java features; no deprecated/removed APIs.
- No secrets or sensitive data leakage.
- Tests added/updated and passing; meaningful coverage.
- Logging, metrics, tracing, and configuration align with standards.
- Spring MVC, Jakarta (jakarta.* imports), and Lombok usage follow best practices.
- PR description includes rationale for non-obvious choices and any feature flags (e.g., preview use).

---
Note: Favor clarity, safety, and maintainability. Document rationale for trade-offs. Security and error handling are first-class concerns.
