FROM golang:1.24.2-alpine AS builder

ENV GOPROXY=https://proxy.golang.com.cn,direct
WORKDIR /src
COPY . .
RUN go build -o downloader main.go

FROM alpine:3.19
WORKDIR /app

COPY --from=builder /src/downloader /app/downloader
RUN apk add --no-cache curl docker-cli

ENTRYPOINT ["/app/downloader"]