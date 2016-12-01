@echo off
%projectName是要创建的项目名字，每一次给projectName起个名字即可%
set projectName=TestName
set packageName=com.daxin
call mvn archetype:generate -DarchetypeGroupId=net.alchim31.maven  -DarchetypeArtifactId=scala-archetype-simple -DremoteRepositories=http://scala-tools.org/repo-releases -DgroupId=%packageName% -DartifactId=%projectName% -Dversion=1.0-SNAPSHOT
echo 创建完毕！
pause 