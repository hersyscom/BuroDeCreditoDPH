DEVTEST_HOME=/home/miguel/DevTest
git pull
cd ../../../
mvn clean package
cp ./target/BuroDeCreditoDPH-1.0-SNAPSHOT.jar $DEVTEST_HOME/hotDeploy
