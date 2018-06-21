FROM maven:3.5-jdk-8-alpine
VOLUME /tmp
ADD "target/incidashboard_e3b-0.0.1-SNAPSHOT.jar" "/maven/usr/share/incidashboard_e3b/incidashboard_e3b.jar"
EXPOSE 8092
ENTRYPOINT ["/usr/bin/java" \
			,"-Djava.security.egd=file:/dev/./urandom" \
			,"-jar" \
			,"/maven/usr/share/incidashboard_e3b/incidashboard_e3b.jar" \
]