#echo 0 > /tmp/huanxi/isAlarm #没有报警
count=2
check(){
	curl -s $1 > /dev/null
	if [ $? -ne 0 ];then
        #检测失败
		#记录日志
		echo `date`${1}"失败" >> /tmp/huanxi/log
      	return 1  
    fi
}
for((i=0;i< $count;i++))
do
	check http://localhost:808${i}/public/isAlive
	if [ $? -ne 0 ];then
		#检测失败
		error_time=0
		error_time=`expr $error_time + 1`
		for((j=0;j<5;j++))
		do
			check http://localhost:808${i}/public/isAlive
			if [ $? -ne 0 ];then
       		 #检测失败
        	error_time=`expr $error_time + 1`
			fi
		done
		if [ $error_time -gt 3 ]; then
			isAlarm=`cat /tmp/huanxi/isAlarm`
			if [ $isAlarm -eq 0 ]; then
				#报警
				time=`date`
           		echo "${time}服务器${i}出现错误" | mail -s "findjob异常" 13054106714@163.com
				echo 1 > /tmp/huanxi/isAlarm
			fi
		fi	
	fi
done
#echo $res
