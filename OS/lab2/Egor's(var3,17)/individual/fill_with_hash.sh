
for i in {1..50}; 
do
  echo `openssl rand -hex 20` >> "$1/file$i.txt" 
done
