package main

import (
	"fmt"
	"log/slog"
	"os/exec"
)

func main() {
	var link string

	link = "https://d37ci6vzurychx.cloudfront.net/trip-data/yellow_tripdata_2025-01.parquet"

	order := fmt.Sprintf("curl -sSL '%s' | hdfs dfs -put - /data/taxi/yellow_tripdata_2025-01.parquet", link)

	cmd := exec.Command("docker", "exec", "-i", "namenode", "bash", "-c", order)

	output, err := cmd.CombinedOutput()

	if err != nil {
		slog.Error("Command failed", "error", err, "output", string(output))
		return
	}

	slog.Info("Done", "output", string(output))

}
