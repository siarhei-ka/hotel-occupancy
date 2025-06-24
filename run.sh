#!/bin/bash
./mvnw clean package -DskipTests
java -jar target/hotel-occupancy-1.0.0.jar
