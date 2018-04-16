#!/bin/bash
cd /root
tar -zxvf $1.tar.gz
##sleep 20s
cd $1
cp $2 /root/
cp $3 /root/
cp $4 /root/
cd /root
##sleep 10s
tar -zxvf $3
##sleep 20s
tar -zxvf $4
##sleep 20s
sh $2
#sleep 20s
sed -i "s/xdata123/$5/g" /root/aus-4.1/bin/aus-manager.sh
sed -i "s/10\.0\.31\.216/$6/g" /root/aus-4.1/conf/aus_meta.yml
sed -i "s/10\.0\.31\.216/$6/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/0\.0\.0\.0/$6/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/localhost/$6/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/read userinput/userinput=1/g" /root/aus-4.1/bin/aus-manager.sh
sh /root/aus-4.1/bin/aus-manager.sh
sed -i "s/userinput=1/read userinput/g" /root/aus-4.1/bin/aus-manager.sh
sed -i "s/userinput=1/read userinput/g" /opt/XDataAus/bin/aus-manager.sh
