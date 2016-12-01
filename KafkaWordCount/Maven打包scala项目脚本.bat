@echo off
title Maven自动打包工具【青春常驻Dax1n】
call mvn -U -DskipTests clean package
echo 打包完毕
pause