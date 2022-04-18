@ECHO OFF
TITLE Deploy ESRS Docker to AWS ECR
ECHO =========================================
ECHO    Deploy ESRS Docker to AWS ECR
ECHO =========================================

ECHO Logging in to AWS...
aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/c5h4p0b9

ECHO Building Docker image...
docker build -t esrs .

ECHO Tagging new image as latest...
docker tag esrs:latest public.ecr.aws/c5h4p0b9/esrs:latest

ECHO Pushing image to AWS ECR...
docker push public.ecr.aws/c5h4p0b9/esrs:latest

ECHO Deploying image to AWS Cluster
aws ecs update-service --cluster CryptoCluster2 --service esrsservice --force-new-deployment

ECHO DONE

