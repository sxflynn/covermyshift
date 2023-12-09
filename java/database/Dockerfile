# Start from the official PostgreSQL image
FROM postgres:12-alpine

# Set the working directory to /docker-entrypoint-initdb.d in the image
WORKDIR /docker-entrypoint-initdb.d

# Copy the SQL files from your host into the image
COPY . /docker-entrypoint-initdb.d

# Rename the SQL files
RUN mv dropdb.sql 01dropdb.sql && \
    mv schema.sql 02schema.sql && \
    mv data.sql 03data.sql && \
    mv user.sql 04user.sql

# Set environment variables for the PostgreSQL instance
ENV POSTGRES_DB=final_capstone \
    POSTGRES_USER=POSTGRES_USER \
    POSTGRES_PASSWORD=postgres1

# The default command of the postgres:12-alpine image will start the PostgreSQL server