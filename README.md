### Build jar
./gradlew clean assemble

### Build docker image
docker build --build-arg JAR_FILE=build/libs/order-solution-0.0.1.jar -t ostapenko/order-solution .

### To run application 
docker compose up