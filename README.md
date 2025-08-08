for using this projects 
make sure follow  this steps by this order :
```
zookeeper
namenode
datanode
loader -- > downloader for files 
spark-master
spark-worker
Pass to your gitbash.exec and your run.sh
& "C:\YOUR GIT BASH PATH\Git\bin\bash.exe" "C:\YOUR FILE LOCATION\elephant\run-reports.sh
```
```
for easier use 
docker-compose up zookeeper
docker-compose up namenode
docker-compose up datanode
docker-compose up loader 
docker-compose up spark-master-1 spark-master-2
docker-compose up spark-worker-1 spark-worker-2 spark-worker-3
& "C:\YOUR GIT BASH PATH\Git\bin\bash.exe" "C:\YOUR FILE LOCATION\elephant\run-reports.sh
```
