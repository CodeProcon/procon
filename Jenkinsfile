//github的凭证
def git_auth = "f9a705b6-155b-45b6-b1a4-6fcbda3b42b3"
//构建版本的名称
def tag = "latest"
//Harbor私服地址
def harbor_url = "129.204.12.131:8989"
//Harbor的项目名称
def harbor_project_name = "blog"
//Harbor的凭证
def harbor_auth = "bdbf5fe8-de86-4386-a424-8b2ddecc41da"
node {
    //把选择的项目信息转为数组
    def selectedProjects = "${project_name}".split(',')
    //把选择的服务区信息转为数组
    def selectedServers = "${publish_server}".split(',')
    stage('拉取代码') {
        checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']],doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],userRemoteConfigs: [[credentialsId: '${git_auth}', url:'git@github.com:WongProcon/procon.git']]])
    }

    stage('编译，构建镜像，部署服务') {
        //编译并安装公共工程
        sh "mvn -f procon clean install"
        for(int i=0;i<selectedProjects.size();i++){
            //取出每个项目的名称和端口
            def currentProject = selectedProjects[i];
            //项目名称
            def currentProjectName = currentProject.split('@')[0]
            //项目启动端口
            def currentProjectPort = currentProject.split('@')[1]
            //定义镜像名称
            def imageName = "${currentProjectName}:${tag}"
            //编译，构建本地镜像
            sh "mvn -f ${currentProjectName} clean package dockerfile:build"
            //给镜像打标签
            sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
            //登录Harbor，并上传镜像
            withCredentials([usernamePassword(credentialsId:"${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')])
                {
                    //登录
                    sh "docker login -u ${username} -p ${password} ${harbor_url}"
                    //上传镜像
                    sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
                }
            //删除本地镜像
            sh "docker rmi -f ${imageName}"
            sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"
        //=====以下为远程调用进行项目部署========
        for(int j=0;j<selectedServers.size();j++){
            //每个服务名称
            def currentServer = selectedServers[j]
            //添加微服务运行时的参数：spring.profiles.active
            def activeProfile = "--spring.profiles.active=pd"
            sshPublisher(publishers: [sshPublisherDesc(configName:"${currentServer}", transfers: [sshTransfer(cleanRemote: false, excludes: '',
execCommand: "/home/docker/shell/deploy.sh $harbor_url $harbor_project_name $currentProjectName $tag $currentProjectPort $activeProfile", execTimeout: 120000, flatten: false, makeEmptyDirs: false,
noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '',
remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')],
usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])

            echo "${currentProjectName}完成编译，构建镜像"
        }
    }
}
