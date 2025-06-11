# Use OpenJDK 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Install bash, curl, and other dependencies to install Maven
RUN apk add --no-cache bash curl tar

# Set Maven version you want
ENV MAVEN_VERSION=3.9.3
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=${MAVEN_HOME}/bin:${PATH}

# Download and install Maven
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /usr/share/ \
    && ln -s /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests

# Stage 2: run app with just the JRE
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=0 /app/target/release-tracker-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]