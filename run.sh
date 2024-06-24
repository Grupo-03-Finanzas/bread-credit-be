sudo docker rm $(sudo docker ps -aq -f status=exited)
sudo docker image prune -f
sudo docker volume rm $(sudo docker volume ls -q)
git pull https://github.com/Grupo-03-Finanzas/bread-credit-be.git prod-azure
sudo docker compose up --build