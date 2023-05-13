FROM adopt:17
EXPOSE 8080
ADD target/vendor-hub.jar vendor-hub.jar
ENTRYPOINT ["java","-jar","/vendor-hub.jar"]