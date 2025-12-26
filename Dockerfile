# --- ETAPA 1: BUILD (Compilação) ---
# Usamos uma imagem Linux oficial do Maven para compilar
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia os arquivos do seu projeto Windows para o Linux do Docker
COPY . .

# Compila o projeto e gera o .jar (pulando testes para economizar memória no Render)
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUNTIME (Execução) ---
# Usamos uma imagem Linux super leve (Alpine) só para rodar o app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Pega apenas o arquivo .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]