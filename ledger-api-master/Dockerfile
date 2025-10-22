# Multi-stage build for LedgerX Spring Boot app

FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy Maven descriptor(s) first to leverage caching
COPY pom.xml ./
COPY code-foundation/pom.xml ./code-foundation/pom.xml
COPY ledgerx/pom.xml ./ledgerx/pom.xml

# Pre-fetch dependencies (best-effort)
RUN mvn -q -DskipTests dependency:go-offline || true

# Copy source
COPY code-foundation ./code-foundation
COPY ledgerx ./ledgerx

# Build only the ledgerx module and its dependencies
RUN mvn -q -DskipTests -pl ledgerx -am package

FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app

# Copy executable jar
COPY --from=builder /workspace/ledgerx/target/*.jar /app/app.jar

ENV JAVA_OPTS=""
EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]


