# entrypoint-active.sh
#!/usr/bin/env bash
set -e

if [ ! -d /hadoop/dfs/name/current ]; then
  hdfs namenode -format "$CLUSTER_NAME" -force
fi

exec hdfs namenode