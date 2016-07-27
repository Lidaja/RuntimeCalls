touch /RuntimeCalls/command.txt
chmod 777 /RuntimeCalls/command.txt
touch /RuntimeCalls/command2.txt
chmod 777 /RuntimeCalls/command2.txt
command="curl --unix-socket /var/run/system-docker.sock -H \"Content-Type: application/json\" -X POST -d '{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"python\", \"/tmp/Api-Invokers/createCluster.py\", \"10.10.30.235\", \"10.10.30.234\" ] }' http:/containers/console/exec | jq '.Id' | tr -d '\"'"
command2="ls -al"
exec_id=$(eval $command)
exec_id2=$(eval $command2)
echo "Command is: $command" >> /RuntimeCalls/command.txt
echo "Exec_id is: $exec_id" >> /RuntimeCalls/command.txt
echo "Command is: $command2" >> /RuntimeCalls/command2.txt
echo "Exec_id is: $exec_id2" >> /RuntimeCalls/command2.txt
#command="curl --unix-socket /var/run/system-docker.sock -H \"Content-Type: application/json\" -X POST -d '{\"Detach\": false, \"Tty\": true }' http:/exec/$exec_id/start"
#result=$(eval $command)
#echo $result
