package main

import (
	"log/slog"
	"os/exec"
	"path/filepath"
)

func main() {
	uploadFile("../data/yellow_tripdata_2025-01.parquet", "/data/taxi/yellow_tripdata_2025-01.parquet")
	uploadFile("../data/taxi_zone_lookup.csv", "/data/taxi/taxi_zone_lookup.csv")
}

func uploadFile(localFile string, hdfsPath string) {
	containerTempPath := "/tmp/" + filepath.Base(localFile)

	cpCmd := exec.Command("docker", "cp", localFile, "namenode:"+containerTempPath)
	if output, err := cpCmd.CombinedOutput(); err != nil {
		slog.Error("Failed to copy file to container",
			"file", localFile, "error", err, "output", string(output))
		return
	}

	hdfsDir := filepath.Dir(hdfsPath)
	putCmd := exec.Command("docker", "exec", "namenode", "bash", "-c",
		"hdfs dfs -mkdir -p "+hdfsDir+" && hdfs dfs -put -f "+containerTempPath+" "+hdfsPath)

	output, err := putCmd.CombinedOutput()
	if err != nil {
		slog.Error("Upload failed",
			"file", localFile, "error", err, "output", string(output))
		return
	}

	slog.Info("File uploaded successfully",
		"file", localFile,
		"hdfsPath", hdfsPath)
}
