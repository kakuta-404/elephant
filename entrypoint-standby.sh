# entrypoint-standby.sh
#!/usr/bin/env bash
set -e

hdfs namenode -bootstrapStandby -force -nonInteractive

exec hdfs namenode