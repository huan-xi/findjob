#备份数据
mysqldump -ufindjob -p125512 -S /tmp/mysql.sock findjob | gzip > /tmp/huanxi/backup/findjob_$(date +%F).sql.gz
echo $(date +%F) 数据库备份完成 | mail -s "数据库备份提示" 13054106714@163.com
