FROM golang:1.24.2 AS builder

ENV GOPROXY=https://proxy.golang.com.cn,direct
WORKDIR /src
COPY . .
RUN go build -o downloader main.go

FROM ubuntu:22.04
WORKDIR /app

COPY --from=builder /src/downloader /app/downloader
RUN apt-get update && \
    apt-get install -y curl docker.io && \
    rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["/app/downloader"]