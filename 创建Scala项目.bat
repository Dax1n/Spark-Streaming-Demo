@echo off
%projectName��Ҫ��������Ŀ���֣�ÿһ�θ�projectName������ּ���%
set projectName=TestName
set packageName=com.daxin
call mvn archetype:generate -DarchetypeGroupId=net.alchim31.maven  -DarchetypeArtifactId=scala-archetype-simple -DremoteRepositories=http://scala-tools.org/repo-releases -DgroupId=%packageName% -DartifactId=%projectName% -Dversion=1.0-SNAPSHOT
echo ������ϣ�
pause 