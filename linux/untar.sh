#!/bin/bash
cd /root
tar -zxvf XData-AUS4.1.0.24181.58.tar.gz
##sleep 20s
cd XData-AUS4.1.0.24181.58
cp aus-autoinstall.24181.sh /root/
cp XData-AUS-4.1.0.24181.tar.gz /root/
cp XData-AUS-dept-4.1.tar.gz /root/
cd /root
##sleep 10s
tar -zxvf XData-AUS-4.1.0.24181.tar.gz
##sleep 20s
tar -zxvf XData-AUS-dept-4.1.tar.gz
##sleep 20s
sh aus-autoinstall.24181.sh
#sleep 20s
sed -i "s/xdata123/$1/g" /root/aus-4.1/bin/aus-manager.sh
sed -i "s/10\.0\.31\.216/$2/g" /root/aus-4.1/conf/aus_meta.yml
sed -i "s/10\.0\.31\.216/$2/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/0\.0\.0\.0/$2/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/localhost/$2/g" /root/aus-4.1/conf/aus_service.yml
sed -i "s/read userinput/userinput=1/g" /root/aus-4.1/bin/aus-manager.sh
sh /root/aus-4.1/bin/aus-manager.sh
sed -i "s/userinput=1/read userinput/g" /root/aus-4.1/bin/aus-manager.sh
sed -i "s/userinput=1/read userinput/g" /opt/XDataAus/bin/aus-manager.sh
